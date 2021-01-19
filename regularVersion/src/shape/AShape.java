package shape;

import java.util.Objects;

/**
 * Abstract base class for implementations of IShape for Simple Animation.
 */
public abstract class AShape implements IShape {

  private final String name;
  private final String type;

  /**
   * Constructs a Shape in a manner selected by concrete subcl asses of this class.
   *
   * @param name the name of the shape
   * @param type the type of the shape
   */
  public AShape(String name, String type) {
    if (name == null) {
      throw new IllegalArgumentException("shape name cannot be null.");
    }
    if (type == null) {
      throw new IllegalArgumentException("shape type cannot be null.");
    }

    this.name = name;
    this.type = type;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AShape aShape = (AShape) o;
    return name.equals(aShape.name) && type.equals(aShape.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type);
  }

  @Override
  public String toString() {
    return "shape " + name + " " + type;
  }
}
