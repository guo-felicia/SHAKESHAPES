package view;

import drawableshape.IDrawableShape;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import model.IAnimation;


/**
 * Represents the class for interacting with the slo-motion animation and viewing the graphic
 * animation of the model.
 */
public class SloMoView extends AnimationVisualView {

  private final IAnimation model;
  private int speed;
  private boolean isStart;
  private boolean isLooping;
  private boolean isSloMo;

  private int tick;
  private int changeSpeed = this.speed;
  private List<IDrawableShape> loS;
  private String msg = "You have to start the animation first.";
  private Timer animationTimer;
  private AnimateJPanel animatePanel;
  private JButton startButton;
  private JButton playButton;
  private JButton restartButton;
  private JButton loopButton;
  private JButton slo10Button;
  private JButton slo25Button;
  private JButton slo50Button;
  private JButton slo75Button;
  private JLabel speedText;

  private int index = 0;
  private int delay;

  /**
   * Represents the initial constructor of ControllableVisualView.
   *
   * @param model the simple animation model
   * @param speed the speed of the animation
   */
  public SloMoView(IAnimation model, int speed) {
    super(model, speed);
    this.initialSpeed(speed);
    this.speed = speed;
    this.model = model;
    this.isStart = false;
    this.isLooping = false;
  }

  /**
   * Represents secondary constructor of controllable animation view class that support for setting
   * the state of animation such as isStarted and isLooping.
   *
   * @param model     the simple animation model
   * @param speed     the speed of the animation
   * @param isStart   whether the animation is started
   * @param isLooping whether the current animation allows loop
   */
  public SloMoView(IAnimation model, int speed, boolean isStart, boolean isLooping) {
    super(model, speed);
    this.initialSpeed(speed);
    this.speed = speed;
    this.model = model;
    this.isStart = isStart;
    this.isLooping = isLooping;
  }

  @Override
  public void render(Appendable out) {
    this.setup();
  }

  //Sets up the Jframe and start the Timer
  private void setup() {
    //TIMER
    tick = 1;
    delay = 1000 / speed;
    animationTimer = new Timer(delay, this);
    animationTimer.setInitialDelay(delay);

    //FRAME SIZE
    int widthPanel = this.model.getCanvasWidth() + this.model.getCanvasTopLeftX() + 50;
    int heightPanel = this.model.getCanvasHeight() + this.model.getCanvasTopLeftY() + 50;

    //BorderLayout
    this.setLayout(new BorderLayout());
    this.setPreferredSize(new Dimension(widthPanel, heightPanel));

    //PAGE_START - title
    JLabel title = new JLabel(
        "                              "
            + "Welcome! Happy Animation :)");
    title.setFont(new Font("Verdana", Font.PLAIN, 16));
    this.add(title, BorderLayout.NORTH);

    //CENTER
    //animation panel
    animatePanel = new AnimateJPanel();
    animatePanel.setPreferredSize(new Dimension(widthPanel, heightPanel));
    this.drawInitialScene();
    this.add(new JScrollPane(animatePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);
    this.pack();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);

    //BOTTOM:
    JPanel controlPanel = new JPanel();
    controlPanel.setLayout(new BorderLayout());

    //1.button panel
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());

    //startButton
    startButton = new JButton("Start");
    startButton.setActionCommand("Start Button");

    //pauseButton && resumeButton
    playButton = new JButton("Pause");
    this.pauseOrResume();

    //restartButton
    restartButton = new JButton("Restart");
    restartButton.setActionCommand("Restart Button");

    //loopingButton
    loopButton = new JButton("Enable looping");
    this.loop();

    buttonPanel.add(startButton);
    buttonPanel.add(playButton);
    buttonPanel.add(restartButton);
    buttonPanel.add(loopButton);

    controlPanel.add(buttonPanel, BorderLayout.NORTH);

    //2.speedPanel
    JPanel speedPanel = new JPanel();
    speedPanel.setLayout(new FlowLayout());
    this.add(speedPanel);

    //text
    String s = "Original Speed:" + changeSpeed;
    speedText = new JLabel("<html><font size='4' color=blue>" + s + "</font>");

    //buttons:
    //0.10x Button
    slo10Button = new JButton("0.10x");
    slo10Button.setActionCommand("Speed10 Button");

    //0.25x Button
    slo25Button = new JButton("0.25x");
    slo25Button.setActionCommand("Speed25 Button");

    //0.50x Button
    slo50Button = new JButton("0.50x");
    slo50Button.setActionCommand("Speed50 Button");

    //0.75x Button
    slo75Button = new JButton("0.75x");
    slo75Button.setActionCommand("Speed75 Button");

    speedPanel.add(speedText);
    speedPanel.add(slo10Button);
    speedPanel.add(slo25Button);
    speedPanel.add(slo50Button);
    speedPanel.add(slo75Button);

    controlPanel.add(speedPanel, BorderLayout.SOUTH);

    this.add(controlPanel, BorderLayout.SOUTH);
  }

  /**
   * Start the animation by start the Timer.
   */
  public void startAnimate() {
    if (!getIsStart()) {
      animationTimer.start();
      isStart = true;
    } else {
      msg = "The animation is already started. Press <Restart> back to beginning.";
      this.popWindow(msg);
    }
  }

  /**
   * Get the copy of the isStart reference to avoid any cheating.
   */
  public boolean getIsStart() {
    boolean start = isStart;
    return start;
  }

  /**
   * Control the animation by pause the animation or resume it.
   */
  public void pauseOrResume() {
    playButton.setActionCommand("Pause Button");
    playButton.addActionListener((ActionEvent e) -> {
      if (getIsStart()) {
        if (e.getActionCommand().equals("Pause Button")) {
          animationTimer.setDelay(999999999);
          playButton.setText("Resume");
          playButton.setActionCommand("Resume Button");
        } else {
          animationTimer.restart();
          int d = animationTimer.getInitialDelay();
          animationTimer.setDelay(d);
          playButton.setText("Pause");
          playButton.setActionCommand("Pause Button");
        }
      } else {
        this.popWindow(msg);
      }
    });
  }


  /**
   * Allows to loop the animation after hit the enable loop button and disable the looping after
   * press the disable loop button.
   */
  public void loop() {
    loopButton.setActionCommand("Loop Button");
    loopButton.addActionListener((ActionEvent e) -> {
      if (getIsStart()) {
        if (e.getActionCommand().equals("Loop Button")) {
          loopButton.setText("Disable Looping");
          loopButton.setActionCommand("Disable Looping");
          isLooping = true;
        } else {
          loopButton.setText("Enable Looping");
          loopButton.setActionCommand("Loop Button");
          isLooping = false;
        }
      } else {
        this.popWindow(msg);
      }
    });
  }

  /**
   * Get the copy of the isLooping reference to avoid any cheating.
   */
  public boolean getIsLoop() {
    boolean loop = isLooping;
    return loop;
  }

  /**
   * Restart the animation after the animation is start.
   */
  public void restart() {
    if (isStart) {
      tick = 1;
    } else {
      this.popWindow(msg);
    }
  }

  /**
   * Speeds up the animation (add 1 to speed for each time).
   */
  public void speedSloMo10() {
    if (isSloMo) {
      changeSpeed = (int) ((int) speed * 0.1);
      int d = 1000 / changeSpeed;
      animationTimer.setDelay(d);
      speedText.setText("slomo:" + changeSpeed + "(0.10x)");
    }
  }

  /**
   * Speeds up the animation (add 1 to speed for each time).
   */
  public void speedSloMo25() {
    if (isSloMo) {
      changeSpeed = (int) ((int) speed * 0.25);
      int d = 1000 / changeSpeed;
      animationTimer.setDelay(d);
      speedText.setText("slomo:" + changeSpeed + "(0.25x)");
    }
  }

  /**
   * Slow down the animation (subtract 1 for each time).
   */
  public void speedSloMo50() {
    if (isSloMo) {
      changeSpeed = (int) ((int) speed * 0.5);
      int d = 1000 / changeSpeed;
      animationTimer.setDelay(d);
      speedText.setText("slomo:" + changeSpeed + "(0.50x)");
    }
  }

  /**
   * Slow down the animation (subtract 1 for each time).
   */
  public void speedSloMo75() {
    if (isSloMo) {
      changeSpeed = (int) ((int) speed * 0.75);
      int d = 1000 / changeSpeed;
      animationTimer.setDelay(d);
      speedText.setText("slomo:" + changeSpeed + "(0.75x)");
    }
  }

  /**
   * Add the action event to each button by given action listener from controller.
   *
   * @param actionListener the corresponding action needed to take by each button
   */
  public void addActionListener(ActionListener actionListener) {
    startButton.addActionListener(actionListener);
    restartButton.addActionListener(actionListener);
    slo10Button.addActionListener(actionListener);
    slo25Button.addActionListener(actionListener);
    slo50Button.addActionListener(actionListener);
    slo75Button.addActionListener(actionListener);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (!isSloMo) {
      speedText.setText("Original Speed:" + speed);
      animationTimer.setDelay(delay);
    }
    animate();
  }

  private void animate() {
    Map<String, List<Integer>> loK = this.model.getSloMo();
    List<Integer> s = loK.get("start");
    List<Integer> e = loK.get("end");
    int startTick = s.get(index);
    int endTick = e.get(index);
    isSloMo = (startTick <= tick) && (tick <= endTick);
    if (tick == endTick && index + 1 < s.size()) {
      index += 1;
    }

    tick += 1;
    try {
      this.renderShapes();
    } catch (Exception exception) {
      this.checkLoop(exception);
    }

    this.setShapes(loS);
    repaint();
  }

  //Enable and disable loops by setting tick value.
  private void checkLoop(Exception e) {
    if (e.getMessage().equals("Over bound.")) {
      if (getIsLoop()) {
        tick = 1;
      } else {
        msg = "The animation is end. Close the window to terminate the program.";
        System.exit(0);
      }
    }
  }

  //Sets up the initial speed of animation.
  private void initialSpeed(int s) {
    changeSpeed = s;
  }

  //Gets all shapes at the current tick.
  private void renderShapes() {
    IDrawableShape shape;
    loS = new ArrayList<>();
    for (String name : model.getShapes().keySet()) {
      shape = model.retrieveFor(name, tick);
      loS.add(shape);
    }
  }

  //Draws the first scene before the animation start.
  private void drawInitialScene() {
    tick = 1;
    loS = new ArrayList<>();
    try {
      this.renderShapes();
    } catch (Exception ignored) {
    }
    this.setShapes(loS);
  }


  //Gets the list of shapes and pass it to the animation panel.
  private void setShapes(List<IDrawableShape> loS) {
    animatePanel.setShapes(loS);
  }

  //Pops up the windows to notice the user with some invalid approach they did.
  private void popWindow(String msg) {
    JOptionPane
        .showMessageDialog(null, msg, "Invalid Approach",
            JOptionPane.ERROR_MESSAGE);
  }
}


