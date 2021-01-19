import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.IAnimation;
import model.SimpleAnimation;
import motion.Motion;
import org.junit.Test;
import shape.Ellipse;
import shape.IShape;
import shape.Rectangle;

/**
 * Tests the builder class in the SimpleAnimation class which builds the model correctly according
 * to different files read from reader.
 */
public class SimpleAnimationBuilderTest {

  private Map<String, IShape> shapes = new LinkedHashMap<>();
  Map<String, List<Motion>> motions = new LinkedHashMap<>();
  private SimpleAnimation.Builder builder = new SimpleAnimation.Builder();
  private IAnimation simpleAnimation;

  @Test
  public void testSetCanvas() {
    builder.setBounds(0, 10, 330, 200);
    simpleAnimation = builder.build();
    assertEquals(0, simpleAnimation.getCanvasTopLeftX());
    assertEquals(10, simpleAnimation.getCanvasTopLeftY());
    assertEquals(330, simpleAnimation.getCanvasWidth());
    assertEquals(200, simpleAnimation.getCanvasHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetCanvasInvalidWidth() {
    builder.setBounds(0, 0, -1, 200);
    simpleAnimation = builder.build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetCanvasInvalidHeight() {
    builder.setBounds(0, 0, 10, 0);
    simpleAnimation = builder.build();
  }

  @Test
  public void testDeclareRectShapes() {
    simpleAnimation = builder.build();
    builder.declareShape("disk1", "rectangle");
    IShape s = new Rectangle("disk1", "rectangle");
    shapes.put("disk1", s);
    assertEquals(shapes.get("disk1").getType(), simpleAnimation.getShapes().get("disk1").getType());
  }

  @Test
  public void testDeclareEllipseShapes() {
    builder.declareShape("ellipse1", "ellipse");
    simpleAnimation = builder.build();
    IShape s = new Ellipse("ellipse1", "ellipse");
    shapes.put("ellipse1", s);
    assertEquals(shapes.get("ellipse1").getType(),
        simpleAnimation.getShapes().get("ellipse1").getType());
  }

  @Test
  public void testAddMotions() {
    builder.declareShape("disk1", "rectangle");
    builder.addMotion("disk1", 1, 190, 180, 20, 30, 0, 49, 90,
        1, 190, 180, 20, 30, 0, 49, 90);
    simpleAnimation = builder.build();
    Motion newMotion = new Motion(1, 190, 180, 20, 30, 0, 49, 90);
    assertEquals(1, simpleAnimation.getMotionsFor("disk1").size());
    assertEquals(newMotion.getTick(), simpleAnimation.getMotionsFor("disk1").get(0).getTick());
  }

}
