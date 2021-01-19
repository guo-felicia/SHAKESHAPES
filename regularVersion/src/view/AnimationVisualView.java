package view;

import drawableshape.IDrawableShape;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import model.IAnimation;
import motion.Motion;

/**
 * Represents the class for viewing the graphic animation of the model.
 */
public class AnimationVisualView extends JFrame implements IVisualView, ActionListener {

  private final IAnimation model;
  private final int speed;
  private int tick;
  private List<IDrawableShape> loS;
  private AnimateJPanel animatePanel;

  /**
   * Represents the initial constructor of AnimationVisualView.
   *
   * @param model the simple animation model
   * @param speed the speed of the animation
   */
  public AnimationVisualView(IAnimation model, int speed) {
    super();
    if (model == null) {
      throw new IllegalArgumentException("the model cannot be null.");
    }
    if (speed <= 0) {
      throw new IllegalArgumentException("the speed should be a positive integer.");
    }
    this.model = model;
    this.speed = speed;
  }


  @Override
  public void render(Appendable out) {
    this.setup();
    return;
  }

  //Sets up the Jframe and start the Timer
  private void setup() {
    //LOCAL VARIABLES
    tick = 1;
    int delay = 1000 / speed;
    int w = this.model.getCanvasWidth();
    int h = this.model.getCanvasHeight();
    int widthPanel = this.model.getCanvasWidth() + this.model.getCanvasTopLeftX() + 50;
    int heightPanel = this.model.getCanvasHeight() + this.model.getCanvasTopLeftY() + 50;

    //animation panel
    animatePanel = new AnimateJPanel();
    animatePanel.setPreferredSize(new Dimension(widthPanel, heightPanel));

    Timer animationTimer = new Timer(delay, this);
    animationTimer.start();
    this.setPreferredSize(new Dimension(w, h));
    this.add(new JScrollPane(animatePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
    this.pack();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    //checks whether the animation is go out of the screen
    resizeScreen(w, h);
  }


  //resize the screen if the current frame cannot show the entire panel
  private void resizeScreen(int w, int h) {
    int finalW = 0;
    int finalH = 0;
    for (String name : model.getShapes().keySet()) {
      List<Motion> loM = model.getMotionsFor(name);
      for (int i = 0; i < loM.size(); i++) {
        Motion m = loM.get(i);
        if (m.getX() > w) {
          w = m.getX() + m.getW();
        }
        if (m.getY() > h) {
          h = m.getH() + m.getH();
        }
        if (w > finalW) {
          finalW = w + model.getCanvasTopLeftX();
        }
        if (h > finalH) {
          finalH = h + model.getCanvasTopLeftY();
        }
        this.setPreferredSize(new Dimension(finalW, finalH));
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    animate();
  }

  private void animate() {
    tick += 1;
    IDrawableShape shape = null;
    loS = new ArrayList<>();
    for (String name : model.getShapes().keySet()) {
      try {
        shape = model.retrieveFor(name, tick);
      } catch (Exception e) {

      }
      loS.add(shape);
    }
    this.setShapes(loS);
    repaint();
  }

  private void setShapes(List<IDrawableShape> loS) {
    animatePanel.setShapes(loS);
  }
}

