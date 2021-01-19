package drawableshape;

import java.awt.Graphics;

/**
 * Represents shapes that could be drawn as 2D graphics.
 */
public interface IDrawableShape {

  /**
   * Draw the graphic shape.
   *
   * @param g the graphics panel that shapes draw on it.
   */
  void draw(Graphics g);

  /**
   * Draw outline of the graphic shape.
   *
   * @param g the graphics panel that shapes draw on it.
   */
  void drawOutline(Graphics g);

}
