package hw5;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import drawableshape.DrawableRectangle;
import drawableshape.IDrawableShape;
import model.IAnimation;
import model.SimpleAnimation;
import model.SimpleAnimation.Builder;
import motion.Motion;
import org.junit.Test;
import shape.Ellipse;
import shape.IShape;
import shape.Rectangle;

/**
 * Tests a simple animator to make sure it functions properly.
 */
public class SimpleAnimationTest {

  IAnimation a;
  Builder builder = new SimpleAnimation.Builder();

  private void simpleAnimation() {
    builder.declareShape("R", "rectangle");
    builder.addMotion("R", 0, 0, 0, 1, 1, 0, 0, 255, 0, 0, 0, 1, 1, 0, 0, 255);
    builder.addMotion("R", 0, 0, 0, 1, 1, 0, 0, 255, 4, 4, 4, 1, 1, 0, 0, 255);
    a = builder.build();
  }

  @Test(expected = IllegalStateException.class)
  public void play() {
    simpleAnimation();
    a.play();
    builder.addMotion("R", 4, 4, 4, 1, 1, 0, 0, 255, 5, 5, 5, 1, 1, 0, 0, 255);
  }

  @Test
  public void stop() {
    simpleAnimation();
    a.stop();
    builder.addMotion("R", 4, 4, 4, 1, 1, 0, 0, 255, 5, 5, 5, 1, 1, 0, 0, 255);
    assertEquals(3, a.getMotionsFor("R").size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void retrieveForInvalidName() {
    simpleAnimation();
    a.retrieveFor("C", 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void retrieveForInvalidTick() {
    simpleAnimation();
    a.retrieveFor("R", 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void retrieveForInvalidTick2() {
    simpleAnimation();
    a.retrieveFor("R", -1);
  }

  @Test
  public void retrieveForStartTick() {
    simpleAnimation();
    IDrawableShape rect = new DrawableRectangle("R", "rectangle", 0, 0, 1, 1, 0, 0, 255);
    assertEquals(rect, a.retrieveFor("R", 0));
  }

  @Test
  public void retrieveForMiddleTick() {
    simpleAnimation();
    IDrawableShape rect = new DrawableRectangle("R", "rectangle", 2, 2, 1, 1, 0, 0, 255);
    assertEquals(rect, a.retrieveFor("R", 2));
  }

  @Test
  public void retrieveForEndTick() {
    simpleAnimation();
    IDrawableShape rect = new DrawableRectangle("R", "rectangle", 4, 4, 1, 1, 0, 0, 255);
    assertEquals(rect, a.retrieveFor("R", 4));
  }

  @Test
  public void initialCanvas() {
    simpleAnimation();
    builder.setBounds(10, 11, 100, 101);
    assertEquals(10, a.getCanvasTopLeftX());
    assertEquals(11, a.getCanvasTopLeftY());
    assertEquals(100, a.getCanvasWidth());
    assertEquals(101, a.getCanvasHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void initialCanvasInvalidX() {
    simpleAnimation();
    builder.setBounds(-1, 11, 100, 101);
  }

  @Test(expected = IllegalArgumentException.class)
  public void initialCanvasInvalidY() {
    simpleAnimation();
    builder.setBounds(10, -1, 100, 101);
  }

  @Test(expected = IllegalArgumentException.class)
  public void initialCanvasInvalidW() {
    simpleAnimation();
    builder.setBounds(10, 11, -1, 101);
  }

  @Test(expected = IllegalArgumentException.class)
  public void initialCanvasInvalidH() {
    simpleAnimation();
    builder.setBounds(10, 11, 100, -1);
  }

  @Test(expected = IllegalStateException.class)
  public void initialCanvasInvalidGameState() {
    simpleAnimation();
    a.play();
    builder.setBounds(10, 11, 100, 101);
  }

  @Test
  public void addShapeRect() {
    simpleAnimation();
    builder.declareShape("R2", "rectangle");
    IShape rect = new Rectangle("R2", "rectangle");
    assertEquals(rect, a.getShapeFor("R2"));
    assertTrue(a.getMotions().containsKey("R2"));
  }

  @Test
  public void addShapeEllipse() {
    simpleAnimation();
    builder.declareShape("C", "ellipse");
    IShape ellipse = new Ellipse("C", "ellipse");
    assertEquals(ellipse, a.getShapeFor("C"));
    assertTrue(a.getMotions().containsKey("C"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void addShapeInvalidShape() {
    simpleAnimation();
    builder.declareShape("T", "triangle");
  }

  @Test(expected = IllegalArgumentException.class)
  public void addShapeDuplicateShape() {
    simpleAnimation();
    builder.declareShape("R", "rectangle");
  }

  @Test
  public void removeShape() {
    simpleAnimation();
    builder.declareShape("R2", "rectangle");
    IShape rect = new Rectangle("R2", "rectangle");
    assertEquals(rect, a.getShapeFor("R2"));
    assertTrue(a.getMotions().containsKey("R2"));
    a.removeShape("R2");
    assertFalse(a.getShapes().containsKey("R2"));
    assertFalse(a.getMotions().containsKey("R2"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeShapeInvalidName() {
    simpleAnimation();
    a.removeShape("R2");
  }

  @Test
  public void addMotion() {
    simpleAnimation();
    builder.addMotion("R", 4, 4, 4, 1, 1, 0, 0, 255, 5, 5, 5, 1, 1, 0, 0, 255);
    Motion m = new Motion(5, 5, 5, 1, 1, 0, 0, 255);
    assertTrue(a.getMotionsFor("R").contains(m));
  }

  @Test
  public void addMotion2() {
    simpleAnimation();
    builder.declareShape("C", "ellipse");
    builder.addMotion("C", 4, 4, 4, 1, 1, 0, 0, 255, 5, 5, 5, 1, 1, 0, 0, 255);
    Motion m = new Motion(5, 5, 5, 1, 1, 0, 0, 255);
    assertTrue(a.getMotionsFor("C").contains(m));
  }

  @Test(expected = IllegalArgumentException.class)
  public void addMotionInvalidName() {
    simpleAnimation();
    builder.addMotion("R1", 4, 4, 4, 1, 1, 0, 0, 255, 5, 5, 5, 1, 1, 0, 0, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addMotionInvalidTick1() {
    simpleAnimation();
    builder.addMotion("R", 4, 4, 4, 1, 1, 0, 0, 255, -1, 5, 5, 1, 1, 0, 0, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addMotionInvalidX() {
    simpleAnimation();
    builder.addMotion("R", 4, 4, 4, 1, 1, 0, 0, 255, 5, -1, 5, 1, 1, 0, 0, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addMotionInvalidY() {
    simpleAnimation();
    builder.addMotion("R", 4, 4, 4, 1, 1, 0, 0, 255, 5, 5, 600, 1, 1, 0, 0, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addMotionInvalidW() {
    simpleAnimation();
    builder.addMotion("R", 4, 4, 4, 1, 1, 0, 0, 255, 5, 5, 5, 0, 1, 0, 0, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addMotionInvalidH() {
    simpleAnimation();
    builder.addMotion("R", 4, 4, 4, 1, 1, 0, 0, 255, 5, 5, 5, 1, 600, 0, 0, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addMotionInvalidR() {
    simpleAnimation();
    builder.addMotion("R", 4, 4, 4, 1, 1, 0, 0, 255, 5, 5, 5, 1, 1, -1, 0, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addMotionInvalidG() {
    simpleAnimation();
    builder.addMotion("R", 4, 4, 4, 1, 1, 0, 0, 255, 5, 5, 5, 1, 1, 0, 300, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addMotionInvalidB() {
    simpleAnimation();
    builder.addMotion("R", 4, 4, 4, 1, 1, 0, 0, 255, 5, 5, 5, 1, 1, 0, 0, 300);
  }

  @Test
  public void addMotionPosition() {
    simpleAnimation();
    Motion m = new Motion(5, 5, 5, 1, 1, 0, 0, 255);
    a.addMotionPosition("R", 5, 5, 5);
    assertTrue(a.getMotionsFor("R").contains(m));
  }

  @Test
  public void addMotionPositionOverlap() {
    simpleAnimation();
    a.addMotion("R", 5, 4, 4, 6, 6, 0, 0, 255);
    Motion m = new Motion(5, 5, 5, 6, 6, 0, 0, 255);
    a.addMotionPosition("R", 5, 5, 5);
    assertTrue(a.getMotionsFor("R").contains(m));
  }

  @Test(expected = IllegalArgumentException.class)
  public void addMotionPositionInvalidOverlap() {
    simpleAnimation();
    a.addMotionPosition("R", 4, 5, 5);
  }

  @Test(expected = IllegalStateException.class)
  public void addMotionPositionInvalidGameState() {
    simpleAnimation();
    a.play();
    a.addMotionPosition("R", 5, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addMotionPositionInvalidName() {
    simpleAnimation();
    a.addMotionPosition("P", 5, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addMotionPositionInvalidInterval() {
    simpleAnimation();
    a.addMotionPosition("R", -1, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addMotionPositionInvalidOverlap2() {
    simpleAnimation();
    a.addMotionPosition("R", 5, 600, 5);
  }

  @Test
  public void addMotionSize() {
    simpleAnimation();
    Motion m = new Motion(5, 4, 4, 6, 6, 0, 0, 255);
    a.addMotionSize("R", 5, 6, 6);
    assertTrue(a.getMotionsFor("R").contains(m));
  }

  @Test(expected = IllegalStateException.class)
  public void addMotionSizeInvalidGameState() {
    simpleAnimation();
    a.play();
    a.addMotionSize("R", 5, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addMotionSizeInvalidName() {
    simpleAnimation();
    a.addMotionSize("P", 5, 5, 5);
  }

  @Test
  public void addMotionSizeOverlap() {
    simpleAnimation();
    Motion m = new Motion(4, 4, 4, 6, 6, 0, 0, 255);
    a.addMotionSize("R", 4, 6, 6);
    assertTrue(a.getMotionsFor("R").contains(m));
  }

  @Test(expected = IllegalArgumentException.class)
  public void addMotionSizeInvalidOverlap() {
    simpleAnimation();
    a.addMotion("R", 5, 4, 4, 6, 6, 0, 0, 255);
    a.addMotionSize("R", 5, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addMotionSizeInvalidInterval() {
    simpleAnimation();
    a.addMotionSize("R", -1, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addMotionSizeInvalidOverlap2() {
    simpleAnimation();
    a.addMotionSize("R", 5, 600, 5);
  }

  @Test
  public void addMotionColor() {
    simpleAnimation();
    Motion m = new Motion(5, 4, 4, 1, 1, 0, 255, 0);
    a.addMotionColor("R", 5, 0, 255, 0);
    assertTrue(a.getMotionsFor("R").contains(m));
  }

  @Test(expected = IllegalStateException.class)
  public void addMotionColorInvalidGameState() {
    simpleAnimation();
    a.play();
    a.addMotionColor("R", 5, 5, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addMotionColorInvalidName() {
    simpleAnimation();
    a.addMotionColor("P", 5, 5, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addMotionColorInvalidOverlap() {
    simpleAnimation();
    a.addMotion("R", 5, 4, 4, 1, 1, 255, 0, 0);
    a.addMotionColor("R", 5, 0, 255, 0);
  }

  @Test
  public void addMotionColorOverlap() {
    simpleAnimation();
    Motion m = new Motion(4, 4, 4, 1, 1, 0, 255, 0);
    a.addMotionColor("R", 4, 0, 255, 0);
    assertTrue(a.getMotionsFor("R").contains(m));
  }

  @Test(expected = IllegalArgumentException.class)
  public void addMotionColorInvalidInterval() {
    simpleAnimation();
    a.addMotionColor("R", -1, 5, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addMotionColorInvalidOverlap2() {
    simpleAnimation();
    a.addMotionColor("R", 5, 600, 5, 5);
  }

  @Test
  public void addMotionFreeze() {
    simpleAnimation();
    Motion m = new Motion(5, 4, 4, 1, 1, 0, 0, 255);
    a.addMotionFreeze("R", 5);
    assertTrue(a.getMotionsFor("R").contains(m));
  }

  @Test(expected = IllegalStateException.class)
  public void addMotionFreezeInvalidGameState() {
    simpleAnimation();
    a.play();
    a.addMotionFreeze("R", 5);
  }

  @Test
  public void addMotionFreezeOverlap() {
    simpleAnimation();
    a.addMotion("R", 5, 4, 4, 1, 1, 255, 0, 0);
    a.addMotionFreeze("R", 5);
    Motion m = new Motion(5, 4, 4, 1, 1, 255, 0, 0);
    assertTrue(a.getMotionsFor("R").contains(m));
  }

  @Test(expected = IllegalArgumentException.class)
  public void addMotionFreezeInvalidInterval() {
    simpleAnimation();
    a.addMotionFreeze("R", -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addMotionFreezeInvalidName() {
    simpleAnimation();
    a.addMotionFreeze("P", 5);
  }
}