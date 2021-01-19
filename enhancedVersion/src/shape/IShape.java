package shape;

import java.util.List;
import motion.Motion;

/**
 * Represents a 2D shape in size, position, and color.
 */
public interface IShape {

  /**
   * Gets the name of the Shape.
   *
   * @return the name of the Shape
   */
  String getName();

  /**
   * Gets the type of the Shape.
   *
   * @return the type of the Shape
   */
  String getType();

  /**
   * Gets the opening SVG tag of a shape.
   *
   * @param x x value
   * @param y y value
   * @param w width value
   * @param h height value
   * @param r r color value
   * @param g g color value
   * @param b b color value
   * @return the opening tag of the shape
   */
  String getOpenTag(int x, int y, int w, int h, int r, int g, int b);

  /**
   * Gets the SVG animate tag of a shape.
   *
   * @param begin the beginning time
   * @param duration duration of the animation
   * @param prev previous motion
   * @param curr current motion
   * @return a list of animations of the shape
   */
  List<String> getAnimateTag(double begin, double duration, Motion prev, Motion curr);

  /**
   * Gets the tagged shape type for the given Shape.
   *
   * @return the tag type of the Shape
   */
  String getTagShapeType();

  /**
   * Gets the tag X Posn of the Shape.
   *
   * @return the x position of the Shape
   */
  String getTagX();

  /**
   * Gets the Y Posn of the Shape.
   *
   * @return the y position of the Shape
   */
  String getTagY();

  /**
   * Gets the width of the Shape.
   *
   * @return the width of the Shape
   */
  String getTagW();

  /**
   * Gets the height of the Shape.
   *
   * @return the height of the Shape
   */
  String getTagH();

}
