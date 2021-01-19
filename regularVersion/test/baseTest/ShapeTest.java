package hw5;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import shape.Ellipse;
import shape.IShape;
import shape.Rectangle;

/**
 * Tests shape classes.
 */
public class ShapeTest {

  IShape rect = new Rectangle("R", "rectangle");
  IShape ellipse = new Ellipse("C", "ellipse");

  @Test(expected = IllegalArgumentException.class)
  public void testShapeNameNotNull() {
    IShape rect = new Rectangle(null, "rectangle");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShapeTypeNotNull() {
    IShape rect = new Rectangle("R", null);
  }

  @Test
  public void testGetName() {
    assertEquals("R", rect.getName());
    assertEquals("C", ellipse.getName());
  }

  @Test
  public void testGetType() {
    assertEquals("rectangle", rect.getType());
    assertEquals("ellipse", ellipse.getType());
  }

  @Test
  public void testGetTagShapeType() {
    assertEquals("rect", rect.getTagShapeType());
    assertEquals("ellipse", ellipse.getTagShapeType());
  }

  @Test
  public void testGetTagX() {
    assertEquals("x", rect.getTagX());
    assertEquals("cx", ellipse.getTagX());
  }

  @Test
  public void testGetTagY() {
    assertEquals("y", rect.getTagY());
    assertEquals("cy", ellipse.getTagY());
  }

  @Test
  public void testGetTagW() {
    assertEquals("width", rect.getTagW());
    assertEquals("rx", ellipse.getTagW());
  }

  @Test
  public void testGetTagH() {
    assertEquals("height", rect.getTagH());
    assertEquals("ry", ellipse.getTagH());
  }

}
