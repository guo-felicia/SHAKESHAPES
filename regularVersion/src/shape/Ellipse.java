package shape;

/**
 * Represents the Ellipse shape class which extends the abstract AShape class.
 */
public class Ellipse extends AShape {

  /**
   * Constructs an ellipse in a manner selected by concrete sub classes of this class.
   *
   * @param name the name of the shape
   * @param type the type of the shape
   */
  public Ellipse(String name, String type) {
    super(name, type);
  }

  @Override
  public String getTagShapeType() {
    return "ellipse";
  }

  @Override
  public String getTagX() {
    return "cx";
  }

  @Override
  public String getTagY() {
    return "cy";
  }

  @Override
  public String getTagW() {
    return "rx";
  }

  @Override
  public String getTagH() {
    return "ry";
  }
}
