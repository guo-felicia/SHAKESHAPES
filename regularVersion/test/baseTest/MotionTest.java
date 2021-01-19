package hw5;

import static org.junit.Assert.assertEquals;

import motion.Motion;
import org.junit.Test;

/**
 * Tests motion class.
 */
public class MotionTest {

  Motion lastMotion = new Motion(1, 0, 0, 1, 1, 255, 0, 0);
  Motion thisMotion = new Motion(25, 190, 180, 20, 30, 0, 50, 90);

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidTick() {
    new Motion(-1, 0, 0, 1, 1, 0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidX() {
    new Motion(1, -1, 0, 1, 1, 0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidY() {
    new Motion(1, 0, -1, 1, 1, 0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidW() {
    new Motion(1, 0, 0, 0, 1, 0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidH() {
    new Motion(1, 0, 0, 1, 0, 0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidR() {
    new Motion(1, 0, 0, 1, 1, -1, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidG() {
    new Motion(1, 0, 0, 1, 1, 0, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidB() {
    new Motion(1, 0, 0, 1, 1, 0, 0, 256);
  }

  @Test
  public void testMakeCopy() {
    Motion lastMotionCopy = new Motion(1, 0, 0, 1, 1, 255, 0, 0);
    assertEquals(lastMotionCopy, lastMotion.makeCopy());
  }

  @Test
  public void testRetrieve() {
    assertEquals(lastMotion, thisMotion.retrieve(lastMotion, 1));
    assertEquals(thisMotion, thisMotion.retrieve(lastMotion, 25));
    assertEquals(new Motion(12, 87, 82, 10, 14, 138, 23, 41), thisMotion.retrieve(lastMotion, 12));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRetrieveNullMotion() {
    thisMotion.retrieve(null, 12);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRetrieveInvalidTick() {
    thisMotion.retrieve(lastMotion, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRetrieveInvalidTick2() {
    thisMotion.retrieve(lastMotion, 100);
  }


  @Test
  public void testChangePosition() {
    assertEquals(new Motion(25, 20, 30, 1, 1, 255, 0, 0), lastMotion.changePosition(25, 20, 30));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangePositionInvalidTick() {
    lastMotion.changePosition(0, 20, 30);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangePositionInvalidX() {
    lastMotion.changePosition(25, -1, 30);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangePositionInvalidY() {
    lastMotion.changePosition(25, 20, -1);
  }

  @Test
  public void testChangeSize() {
    assertEquals(new Motion(25, 0, 0, 20, 30, 255, 0, 0), lastMotion.changeSize(25, 20, 30));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeSizeInvalidTick() {
    lastMotion.changeSize(0, 20, 30);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeSizeInvalidW() {
    lastMotion.changeSize(25, -1, 30);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeSizeInvalidH() {
    lastMotion.changeSize(25, 20, -1);
  }

  @Test
  public void testChangeColor() {
    assertEquals(new Motion(25, 0, 0, 1, 1, 0, 255, 255), lastMotion.changeColor(25, 0, 255, 255));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorInvalidTick() {
    lastMotion.changeColor(0, 0, 255, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorInvalidR() {
    lastMotion.changeColor(25, -1, 255, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorInvalidG() {
    lastMotion.changeColor(25, 0, 256, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorInvalidB() {
    lastMotion.changeColor(25, 0, 255, 256);
  }

  @Test
  public void testFreeze() {
    assertEquals(new Motion(25, 0, 0, 1, 1, 255, 0, 0), lastMotion.freeze(25));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFreezeInvalidTick() {
    lastMotion.freeze(0);
  }

}
