package drawableshape;

import java.util.Objects;

/**
 * Abstract base class for implementations of IShape for graphic drawing.
 */
public abstract class ADrawableShape implements IDrawableShape {

  protected String name;
  protected String type;
  protected int x;
  protected int y;
  protected int w;
  protected int h;
  protected int r;
  protected int g;
  protected int b;

  /**
   * Constructs a Drawable Shape in a manner selected by concrete subclasses of this class.
   *
   * @param name the name of the Shape
   * @param type the type of the Shape
   * @param x    the x position of the Shape
   * @param y    the y position of the Shape
   * @param w    the width position of the Shape
   * @param h    the height position of the Shape
   * @param r    attribute r of rgb color of the Shape
   * @param g    attribute g of rgb color of the Shape
   * @param b    attribute b of rgb color of the Shape
   */
  public ADrawableShape(String name, String type, int x, int y, int w, int h, int r, int g, int b) {
    this.name = name;
    this.type = type;
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.r = r;
    this.g = g;
    this.b = b;
  }

  public String getName() {
    return name;
  }

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
    ADrawableShape that = (ADrawableShape) o;
    return x == that.x && y == that.y && w == that.w && h == that.h && r == that.r && g == that.g
        && b == that.b && name.equals(that.name) && type.equals(that.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type, x, y, w, h, r, g, b);
  }

  @Override
  public String toString() {
    return "ADrawableShape{" + "name=" + getName() + ", type=" + getType() + ", x=" + x + ", y=" + y
        + ", w=" + w + ", h=" + h + ", r=" + r + ", g=" + g + ", b=" + b + '}';
  }
}
