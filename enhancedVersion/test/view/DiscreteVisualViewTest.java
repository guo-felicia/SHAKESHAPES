package view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.swing.Timer;
import model.SimpleAnimation;
import org.junit.Before;
import org.junit.Test;


/**
 * Represents tests for the discrete visual view.
 */
public class DiscreteVisualViewTest extends DiscreteView {

  private int delay = 1000 / 20;
  private boolean isStart;
  private boolean isLooping;
  private boolean isDiscrete;
  private boolean isNormalPlay;
  private String buttonActionCommand;

  private int tick;
  private int changeSpeed = 20;
  private String msg = "You have to start the animation first.";
  private Timer animationTimer;
  private String speedText;
  private int tempo = 5;
  private int i = 0;


  /**
   * Constructs a discrete visual view test instance.
   */
  public DiscreteVisualViewTest() {
    super(new SimpleAnimation(), 1, false, false);
  }

  DiscreteVisualViewTest v;

  @Before
  public void setUpTest() {
    v = new DiscreteVisualViewTest();
  }


  //Test the start button function well
  @Test
  public void startAnimateTest() {
    v.startAnimate();
    assertTrue(v.isStart);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startAnimateTestInvalid() {
    v.isStart = true;
    v.startAnimate();
  }

  //Test the pause button function well
  @Test
  public void pauseAnimate() {
    v.isStart = true;
    v.pauseOrResume("Pause Button");
    assertEquals("Resume Button", v.buttonActionCommand);
  }

  @Test
  public void resumeAnimate() {
    v.isStart = true;
    v.pauseOrResume("Resume Button");
    assertEquals("Pause Button", v.buttonActionCommand);
  }


  @Test(expected = IllegalArgumentException.class)
  public void pauseResumeAnimateInvalid() {
    isStart = false;
    v.pauseOrResume("Pause Button");
  }

  //Test the restart button function well
  @Test
  public void restartAnimate() {
    v.isStart = true;
    v.tick = 20;
    int preTick = v.tick;
    v.restart();
    assertEquals(20, preTick);
    assertEquals(1, v.tick);
  }


  @Test(expected = IllegalArgumentException.class)
  public void restartAnimateInvalid() {
    v.isStart = false;
    v.restart();
  }

  //Test the looping button function well
  @Test
  public void disableLoopAnimate() {
    v.isStart = true;
    v.isLooping = true;
    v.loop("Disable Looping");
    assertFalse(v.isLooping);
    assertEquals("Loop Button", v.buttonActionCommand);
  }

  @Test
  public void LoopAnimate() {
    v.isStart = true;
    v.loop("Loop Button");
    assertTrue(v.isLooping);
    assertEquals("Disable Looping", v.buttonActionCommand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void loopAnimateInvalid() {
    v.isStart = false;
    v.loop("Loop Button");
  }

  //Test the increase the speed button function well
  @Test
  public void speedUpAnimate() {
    v.isStart = true;
    for (int i = 0; i < 10; i++) {
      v.speedUp();
    }
    assertEquals(30, v.changeSpeed);
    assertEquals("speed:20", v.speedText);
  }


  @Test(expected = IllegalArgumentException.class)
  public void speedUpAnimateInvalid() {
    isStart = false;
    v.speedUp();
  }


  //Test to decrease the speed button function well
  @Test
  public void speedDownAnimate() {
    v.isStart = true;
    for (int i = 0; i < 6; i++) {
      v.speedDown();
    }
    assertEquals(14, v.changeSpeed);
    assertEquals("speed:4", v.speedText);
  }

  @Test(expected = IllegalArgumentException.class)
  public void speedDownAnimateInvalid() {
    v.changeSpeed = 1;
    v.speedDown();
  }

  //Test to switch the discrete and continuous mood
  @Test
  public void discreteCurrent() {
    v.isStart = true;
    v.discreteOrContinues("Discrete");
    assertTrue(v.isDiscrete);
    assertEquals("Continuous", v.buttonActionCommand);
  }

  @Test
  public void continuousCurrent() {
    v.isStart = true;
    v.discreteOrContinues("Continuous");
    assertFalse(v.isDiscrete);
    assertEquals("Discrete", v.buttonActionCommand);
  }

  @Test
  public void actionPerformDiscreteTempo3() {
    v.isStart = true;
    v.isDiscrete = true;
    v.tempo = 3;
    v.actionPerformed();
    assertFalse(v.isNormalPlay);
    assertEquals(2, v.tempo);
  }

  @Test
  public void actionPerformDiscreteTempo0() {
    v.isStart = true;
    v.isDiscrete = true;
    v.tempo = 0;
    v.i = 0;
    v.actionPerformed();
    assertFalse(v.isNormalPlay);
    //check whether the tempo is reset
    assertEquals(5, v.tempo);
    //check whether the index is incremented
    assertEquals(1, v.i);
  }

  @Test
  public void actionPerformContinuous() {
    v.isStart = true;
    v.isDiscrete = false;
    v.tempo = 0;
    v.actionPerformed();
    assertTrue(v.isNormalPlay);
  }


  /**
   * Start the animation by start the Timer.
   */
  @Override
  public void startAnimate() {
    if (!isStart) {
      isStart = true;
    } else {
      msg = "The animation is already started. Press <Restart> back to beginning.";
      throw new IllegalArgumentException(msg);
    }
  }

  /**
   * Control the animation by pause the animation or resume it.
   */
  public void pauseOrResume(String command) {
    animationTimer = new Timer(delay, this);
    animationTimer.setInitialDelay(delay);
    if (isStart) {
      if (command.equals("Pause Button")) {
        animationTimer.setDelay(999999999);
        delay = 999999999;
        //playButton.setText("Resume");
        //playButton.setActionCommand("Resume Button");
        buttonActionCommand = "Resume Button";
      } else {
        animationTimer.restart();
        int d = animationTimer.getInitialDelay();
        animationTimer.setDelay(d);
        //playButton.setText("Pause");
        //playButton.setActionCommand("Pause Button");
        buttonActionCommand = "Pause Button";
      }
    } else {
      throw new IllegalArgumentException(msg);
    }
  }


  /**
   * Allows to loop the animation after hit the enable loop button and disable the looping after
   * press the disable loop button.
   */
  public void loop(String command) {
    if (isStart) {
      if (command.equals("Loop Button")) {
        //loopButton.setText("Disable Looping");
        //loopButton.setActionCommand("Disable Looping");
        buttonActionCommand = "Disable Looping";
        isLooping = true;
      } else {
        //loopButton.setText("Enable Looping");
        //loopButton.setActionCommand("Loop Button");
        buttonActionCommand = "Loop Button";
        isLooping = false;
      }
    } else {
      throw new IllegalArgumentException(msg);
    }
  }

  /**
   * Control the animation by pause the animation or resume it.
   */
  public void discreteOrContinues(String command) {
    if (command.equals("Discrete")) {
      isDiscrete = true;
      buttonActionCommand = "Continuous";
    } else {
      //do something
      isDiscrete = false;
      buttonActionCommand = "Discrete";
    }
  }

  /**
   * Restart the animation after the animation is start.
   */
  @Override
  public void restart() {
    if (isStart) {
      tick = 1;
    } else {
      throw new IllegalArgumentException(msg);
    }
  }

  /**
   * Speeds up the animation (add 1 to speed for each time).
   */
  @Override
  public void speedUp() {
    animationTimer = new Timer(delay, this);
    changeSpeed += 1;
    int d = 1000 / changeSpeed;
    animationTimer.setDelay(d);
    speedText = "speed:" + changeSpeed;
  }

  /**
   * Slow down the animation (subtract 1 for each time).
   */
  @Override
  public void speedDown() {
    animationTimer = new Timer(delay, this);
    if (changeSpeed <= 1) {
      changeSpeed = 1;
      msg = "The speed can't be smaller than 1!!";
      throw new IllegalArgumentException(msg);
    } else {
      changeSpeed -= 1;
    }
    int d = 1000 / changeSpeed;
    animationTimer.setDelay(d);
    speedText = "speed:" + changeSpeed;
  }


  private void actionPerformed() {
    if (isDiscrete) {
      isNormalPlay = false;
      if (tempo == 0) {
        i += 1;
        tempo = 5;
      } else {
        tempo -= 1;
      }
    } else {
      isNormalPlay = true;
    }
  }

}

