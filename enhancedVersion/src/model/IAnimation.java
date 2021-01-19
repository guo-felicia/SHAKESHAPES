package model;

import drawableshape.IDrawableShape;
import java.util.List;
import java.util.Map;
import motion.Motion;
import shape.IShape;

/**
 * Represents a model of a 2D shape animator which maintains animation timelines and shape motions.
 */
public interface IAnimation {

  /**
   * Starts the animation and initialize the animation.
   */
  void play();

  /**
   * Stops the animation.
   */
  void stop();

  /**
   * Retrieve the correct shape with the given name at the given tick.
   *
   * @param name the name of the shape
   * @param tick the current tick
   * @return the correct drawable shape with the name at the given tick
   */
  IDrawableShape retrieveFor(String name, int tick);

  /**
   * Sets up the canvas of animation.
   *
   * @param x      the TopLeftX of the canvas
   * @param y      the TopLeftY of the canvas
   * @param width  the width of the canvas
   * @param height the height of the canvas
   */
  void initialCanvas(int x, int y, int width, int height);

  /**
   * Initialize the shapes and add them into the animation motion and shape list.
   *
   * @param name the name of the shape
   * @param type the type of the shape
   */
  void addShape(String name, String type);

  /**
   * Remove the given shape from the animation.
   *
   * @param name the name of the shape
   */
  void removeShape(String name);

  /**
   * Adds the motion log of the given shape at the tick.
   *
   * @param name the name of the shape
   * @param tick the current tick of the animation
   * @param x    the X Posn of the shape
   * @param y    the Y Posn of the shape
   * @param w    the width position of the Shape
   * @param h    the height position of the Shape
   * @param r    attribute r of rgb color of the Shape
   * @param g    attribute g of rgb color of the Shape
   * @param b    attribute b of rgb color of the Shape
   */
  void addMotion(String name, int tick, int x, int y, int w, int h, int r, int g, int b);

  /**
   * Adds the motion with updated position when the position of the shape changes in animation.
   *
   * @param name    the name of the shape
   * @param newTick the new tick of the animation
   * @param newX    the updated X position of the shape
   * @param newY    the updated Y position of the shape
   */
  void addMotionPosition(String name, int newTick, int newX, int newY);

  /**
   * Adds the motion with updated width and height when the size of the shape changes in animation.
   *
   * @param name    the name of the shape
   * @param newTick the new tick of the animation
   * @param newW    the updated width  of the Shape
   * @param newH    the updated height of the Shape
   */
  void addMotionSize(String name, int newTick, int newW, int newH);

  /**
   * Adds the motion with updated rgb Color when the color of the shape changes in animation.
   *
   * @param name    the name of the shape
   * @param newTick the new tick of the animation
   * @param newR    the updated r attributes of the Shape color
   * @param newG    the updated g attributes of the Shape color
   * @param newB    the updated b attributes of the Shape color
   */
  void addMotionColor(String name, int newTick, int newR, int newG, int newB);

  /**
   * Adds the motion when the shape is freeze in animation.
   *
   * @param name    the name of the shape
   * @param newTick the new tick of the animation
   */
  void addMotionFreeze(String name, int newTick);

  /**
   * Get the x-coordinate of the top left corner on canvas.
   *
   * @return the x-coordinate of the top left corner on canvas
   */
  int getCanvasTopLeftX();

  /**
   * Get the y-coordinate of the top left corner on canvas.
   *
   * @return the y-coordinate of the top left corner on canvas
   */
  int getCanvasTopLeftY();

  /**
   * Get the width of canvas.
   *
   * @return the width of canvas
   */
  int getCanvasWidth();

  /**
   * Get the height of canvas.
   *
   * @return the height of canvas
   */
  int getCanvasHeight();

  /**
   * Get all shapes in this animation.
   *
   * @return all shapes in this animation.
   */
  Map<String, IShape> getShapes();

  /**
   * Get detailed information of a given shape.
   *
   * @param name the given shape
   * @return the corresponding shape
   */
  IShape getShapeFor(String name);

  /**
   * Get all motions of this animation.
   *
   * @return all motions
   */
  Map<String, List<Motion>> getMotions();

  /**
   * Get all motions of a given shape.
   *
   * @param name the given shape
   * @return all motions of the given shape
   */
  List<Motion> getMotionsFor(String name);

  /**
   * get the list of keys of the key frames.
   *
   * @return the list of keys of the key frames
   */
  List<Integer> getKeys();

  /**
   * add the keys to the list of keys of the key frames.
   *
   * @param t1 the start tick of the motion
   * @param t2 the end tick of the motion
   */
  void addKeys(int t1, int t2);

  /**
   * add the keys to the list of keys of the key frames.
   *
   * @param start the start tick of the motion
   * @param end   the end tick of the motion
   */
  void addSloMo(int start, int end);

  /**
   * get the record of the slomo intervals.
   *
   * @return the list of keys of the key slomos
   */
  Map<String, List<Integer>> getSloMo();
}
