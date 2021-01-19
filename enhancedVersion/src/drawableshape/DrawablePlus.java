package drawableshape;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents the DrawableRectangle shape class which extends the abstract ADrawableShape class and
 * implement the IDrawableShape.
 */
public class DrawablePlus extends ADrawableShape implements IDrawableShape {

  private final int[] xValues = {
      (int) (x + 0.25 * w), (int) (x + 0.75 * w), (int) (x + 0.75 * w), x + w, x + w,
      (int) (x + 0.75 * w), (int) (x + 0.75 * w), (int) (x + 0.25 * w), (int) (x + 0.25 * w), x, x,
      (int) (x + 0.25 * w)};
  private final int[] yValues = {y, y, (int) (y + 0.25 * h), (int) (y + 0.25 * h),
      (int) (y + 0.75 * h),
      (int) (y + 0.75 * h), y + h, y + h, (int) (y + 0.75 * h), (int) (y + 0.75 * h),
      (int) (y + 0.25 * h),
      (int) (y + 0.25 * h)};

  /**
   * Constructs a drawable plus sign in a manner selected by concrete sub classes of this class.
   *
   * @param name the name of the shape
   * @param type the type of the shape
   */
  public DrawablePlus(String name, String type, int x, int y, int w, int h, int r, int g,
      int b) {
    super(name, type, x, y, w, h, r, g, b);
  }

  @Override
  public void draw(Graphics g2D) {
    Color color = new Color(r, g, b);
    g2D.setColor(color);
    // fill polygon with two arrays
    g2D.fillPolygon(xValues, yValues, 12);
  }

  @Override
  public void drawOutline(Graphics g2D) {
    Color color = new Color(r, g, b);
    g2D.setColor(color);
    g2D.drawPolygon(xValues, yValues, 12);
  }
}
