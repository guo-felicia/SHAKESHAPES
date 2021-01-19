package view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.swing.Timer;
import model.SimpleAnimation;
import org.junit.Before;
import org.junit.Test;

/**
 * Represents tests for slo-mo visual view.
 */
public class SloMoVisualViewTest extends SloMoView {

  private boolean isStart;
  private boolean isLooping;
  private boolean isSloMo = true;
  private String buttonActionCommand;

  private int tick = 1;
  private int speed = 20;
  private int delay = 1000 / 20;
  private int changeSpeed = 10;
  private String msg = "You have to start the animation first.";
  private String speedText;


  /**
   * Constructs a slo-mo visual view test instance.
   */
  public SloMoVisualViewTest() {
    super(new SimpleAnimation(), 1, false, false);
  }

  SloMoVisualViewTest v;

  @Before
  public void setUpTest() {
    v = new SloMoVisualViewTest();
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
  public void speed010() {
    v.speedSloMo10();
    assertEquals(2, v.changeSpeed);
    assertEquals("slomo:2(0.10x)", v.speedText);
  }

  //Test to decrease the speed button function well by 0.25x
  @Test
  public void speed025() {
    v.speedSloMo25();
    assertEquals(5, v.changeSpeed);
    assertEquals("slomo:5(0.25x)", v.speedText);
  }

  //Test to decrease the speed button function well by 0.25x
  @Test
  public void speed050() {
    v.speedSloMo50();
    assertEquals(10, v.changeSpeed);
    assertEquals("slomo:10(0.50x)", v.speedText);
  }

  //Test to decrease the speed button function well by 0.25x
  @Test
  public void speed075() {
    v.speedSloMo75();
    assertEquals(15, v.changeSpeed);
    assertEquals("slomo:15(0.75x)", v.speedText);
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
    Timer animationTimer = new Timer(delay, this);
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
  public void speedSloMo10() {
    if (isSloMo) {
      changeSpeed = (int) ((int) speed * 0.1);
      speedText = "slomo:" + changeSpeed + "(0.10x)";
    }
  }

  /**
   * Speeds up the animation (add 1 to speed for each time).
   */
  @Override
  public void speedSloMo25() {
    if (isSloMo) {
      changeSpeed = (int) ((int) speed * 0.25);
      speedText = "slomo:" + changeSpeed + "(0.25x)";
    }
  }

  /**
   * Slow down the animation (subtract 1 for each time).
   */
  @Override
  public void speedSloMo50() {
    if (isSloMo) {
      changeSpeed = (int) ((int) speed * 0.5);
      speedText = "slomo:" + changeSpeed + "(0.50x)";
    }
  }

  /**
   * Slow down the animation (subtract 1 for each time).
   */
  @Override
  public void speedSloMo75() {
    if (isSloMo) {
      changeSpeed = (int) ((int) speed * 0.75);
      speedText = "slomo:" + changeSpeed + "(0.75x)";
    }
  }

}
