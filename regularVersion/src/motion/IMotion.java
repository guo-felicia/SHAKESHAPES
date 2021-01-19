package motion;

/**
 * Represents the Motion class which records the animation states for each tick.
 */

public interface IMotion {


  /**
   * Make a copy of the motion to avoid user change the immutable reference.
   *
   * @return the copied reference of the motion
   */
  Motion makeCopy();

  /**
   * Retrieve the motion by given motion interval and at the given tick.
   *
   * @param lastInterval the previous motion of before the given tick
   * @param tick         the tick of the animation and motion
   * @return the motion at given tick
   */
  Motion retrieve(Motion lastInterval, int tick);


  /**
   * add the motion with updated POSN when the position of the shape changes in animation.
   *
   * @param newTick the new tick of the animation
   * @param newX    the updated X Posn of the shape
   * @param newY    the updated Y Posn of the shape
   */
  Motion changePosition(int newTick, int newX, int newY);

  /**
   * Adds the motion with updated width and height when the size of the shape changes in animation.
   *
   * @param newTick the new tick of the animation
   * @param newW    the updated width  of the Shape
   * @param newH    the updated height of the Shape
   */
  Motion changeSize(int newTick, int newW, int newH);

  /**
   * Adds the motion with updated rgb Color when the color of the shape changes in animation.
   *
   * @param newTick the new tick of the animation
   * @param newR    the updated r attributes of the Shape color
   * @param newG    the updated g attributes of the Shape color
   * @param newB    the updated b attributes of the Shape color
   */
  Motion changeColor(int newTick, int newR, int newG, int newB);

  /**
   * Adds the motion when the shape is freeze in animation.
   *
   * @param newTick the new tick of the animation
   */
  Motion freeze(int newTick);

}