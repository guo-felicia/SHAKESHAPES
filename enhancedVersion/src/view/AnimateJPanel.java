package view;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import drawableshape.IDrawableShape;

/**
 * This panel represents the region where the animation of each shapes is drawn.
 */
public class AnimateJPanel extends JPanel {

  private List<IDrawableShape> loS;
  private boolean isOutline;

  /**
   * Primary constructor to create a JPanel for the animation.
   */
  public AnimateJPanel() {
    super();
    loS = new ArrayList<>();
    isOutline = false;
  }

  @Override
  protected void paintComponent(Graphics g2D) {
    for (IDrawableShape shape : loS) {
      if (isOutline) {
        shape.drawOutline(g2D);
      } else {
        shape.draw(g2D);
      }
    }
  }

  //Gets the list of Shapes at the current tick to draw.
  public void setShapes(List<IDrawableShape> list) {
    loS = list;
  }

  //Gets the list of Shapes at the current tick to draw.
  public void setShapes(List<IDrawableShape> list, boolean outline) {
    loS = list;
    isOutline = outline;
  }

}
