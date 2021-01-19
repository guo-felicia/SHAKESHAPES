package shape;

/**
 * Represents the Rectangle shape class which extends the abstract AShape class.
 */
public class Rectangle extends AShape {

  /**
   * Constructs an rectangle in a manner selected by concrete sub classes of this class.
   *
   * @param name the name of the shape
   * @param type the type of the shape
   */
  public Rectangle(String name, String type) {
    super(name, type);
  }

  @Override
  public String getTagShapeType() {
    return "rect";
  }

  @Override
  public String getTagX() {
    return "x";
  }

  @Override
  public String getTagY() {
    return "y";
  }

  @Override
  public String getTagW() {
    return "width";
  }

  @Override
  public String getTagH() {
    return "height";
  }
}
