package drawableshape;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents the DrawableEllipse shape class which extends the abstract ADrawableShape class and
 * implement the IDrawableShape.
 */
public class DrawableEllipse extends ADrawableShape implements IDrawableShape {

  /**
   * Constructs a drawable ellipse in a manner selected by concrete sub classes of this class.
   *
   * @param name the name of the shape
   * @param type the type of the shape
   */
  public DrawableEllipse(String name, String type, int x, int y, int w, int h, int r, int g,
      int b) {
    super(name, type, x, y, w, h, r, g, b);
  }

  @Override
  public void draw(Graphics g2D) {
    Color color = new Color(r, g, b);
    g2D.setColor(color);
    g2D.fillOval(x, y, w, h);
  }
}
