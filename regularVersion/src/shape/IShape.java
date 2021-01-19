package shape;

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
