package model;

import animator.utils.AnimationBuilder;
import drawableshape.DrawableEllipse;
import drawableshape.DrawableRectangle;
import drawableshape.IDrawableShape;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import motion.Motion;
import shape.Ellipse;
import shape.IShape;
import shape.Rectangle;

/**
 * Represents an animator that animates shapes moving, changing sizes and colors on the screen. It
 * defines a canvas, creates shapes and their corresponding motions. It serves as a script of an
 * animation.
 */
public class SimpleAnimation implements IAnimation {

  private Map<String, IShape> shapes;
  private Map<String, List<Motion>> motions;
  private boolean started;
  private int canvasTopLeftX;
  private int canvasTopLeftY;
  private int canvasWidth;
  private int canvasHeight;

  /**
   * A default constructor constructing a simple animation only for test purpose.
   */
  public SimpleAnimation() {
    this.shapes = new LinkedHashMap<>();
    this.motions = new LinkedHashMap<>();
    this.started = false;
    this.canvasTopLeftX = 0;
    this.canvasTopLeftY = 0;
    this.canvasWidth = 360;
    this.canvasHeight = 360;
  }

  private SimpleAnimation(Map<String, IShape> shapes, Map<String, List<Motion>> motions,
      boolean started, int canvasTopLeftX, int canvasTopLeftY, int canvasWidth,
      int canvasHeight) {
    this.shapes = shapes;
    this.motions = motions;
    this.started = started;
    this.canvasTopLeftX = canvasTopLeftX;
    this.canvasTopLeftY = canvasTopLeftY;
    this.canvasWidth = canvasWidth;
    this.canvasHeight = canvasHeight;
  }

  /**
   * A Animation Builder which help build a simple animation model.
   */
  public static final class Builder implements AnimationBuilder<IAnimation> {

    IAnimation simpleAnimation;

    public Builder() {
      simpleAnimation = new SimpleAnimation();
    }

    @Override
    public IAnimation build() {
      return simpleAnimation;
    }

    @Override
    public AnimationBuilder<IAnimation> setBounds(int x, int y, int width, int height) {
      simpleAnimation.initialCanvas(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<IAnimation> declareShape(String name, String type) {
      simpleAnimation.addShape(name, type);
      return this;
    }

    @Override
    public AnimationBuilder<IAnimation> addMotion(String name, int t1, int x1, int y1, int w1,
        int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
        int b2) {
      simpleAnimation.addMotion(name, t2, x2, y2, w2, h2, r2, g2, b2);
      return this;
    }
  }

  @Override
  public void play() {
    this.started = true;
  }

  @Override
  public void stop() {
    this.started = false;
  }

  @Override
  public IDrawableShape retrieveFor(String name, int tick) {
    invalidName(name);
    invalidTick(tick);
    List<Motion> lom = getMotionsFor(name);
    // locate two intervals
    int index = locateRetrievedIntervalIndex(name, tick);
    Motion lastInterval;
    if (index == 0) {
      lastInterval = lom.get(index);
    } else {
      lastInterval = lom.get(index - 1);
    }
    Motion thisInterval = lom.get(index);
    // retrieve a moment
    Motion moment = thisInterval.retrieve(lastInterval, tick);
    IShape shape = getShapeFor(name);
    // create a drawable shape
    return createDrawableShape(shape.getName(), shape.getType(),
        moment.getX(), moment.getY(), moment.getW(), moment.getH(), moment.getR(), moment.getG(),
        moment.getB());
  }

  @Override
  public void initialCanvas(int x, int y, int width, int height) {
    invalidGameState();
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("You are trying to initial an invalid canvas size.");
    }
    this.canvasTopLeftX = x;
    this.canvasTopLeftY = y;
    this.canvasWidth = width;
    this.canvasHeight = height;
  }

  @Override
  public void addShape(String name, String shapeType) {

    invalidGameState();
    if (shapes.containsKey(name)) {
      throw new IllegalArgumentException("The shape name you entered is taken.");
    }
    String type = shapeType.toLowerCase();
    IShape newShape;
    switch (type) {
      case "rectangle":
        newShape = new Rectangle(name, type);
        shapes.put(name, newShape);
        motions.put(name, new ArrayList<Motion>());
        break;
      case "ellipse":
        newShape = new Ellipse(name, type);
        shapes.put(name, newShape);
        motions.put(name, new ArrayList<Motion>());
        break;
      default:
        throw new IllegalArgumentException(
            "This animation do not support the type of shape you entered.");
    }
  }

  @Override
  public void removeShape(String name) {
    invalidName(name);
    shapes.remove(name);
    motions.remove(name);
  }

  @Override
  public void addMotion(String name, int tick, int x, int y, int w, int h, int r, int g,
      int b) {
    invalidGameState();
    invalidName(name);
    invalidTick(tick);
    // invalidPosition(x, y);
    invalidSize(w, h);
    invalidColor(r, g, b);

    Motion newMotion = new Motion(tick, x, y, w, h, r, g, b);
    if (motions.get(name) != null) {
      motions.get(name).add(newMotion);
    } else {
      motions.put(name, List.of(newMotion));
    }
  }

  @Override
  public void addMotionPosition(String name, int newTick, int newX, int newY) {

    invalidGameState();
    invalidName(name);
    List<Motion> history = motions.get(name);
    Motion lastMotion = getLastMotion(name);
    invalidInterval(getLastTick(name), newTick);
    invalidPosition(newX, newY);
    Motion newMotion = lastMotion.changePosition(newTick, newX, newY);

    if (operatingTheSameInterval(getLastTick(name), newTick)) {
      alreadyBeenChangingTheSameAttribute(name, "position");
      history.set(history.size() - 1, newMotion);
    } else {
      history.add(newMotion);
    }
  }

  @Override
  public void addMotionSize(String name, int newTick, int newW, int newH) {
    invalidGameState();
    invalidName(name);
    List<Motion> history = motions.get(name);
    Motion lastMotion = history.get(history.size() - 1).makeCopy();
    invalidInterval(lastMotion.getTick(), newTick);
    invalidSize(newW, newH);
    Motion newMotion = lastMotion.changeSize(newTick, newW, newH);

    if (operatingTheSameInterval(getLastTick(name), newTick)) {
      alreadyBeenChangingTheSameAttribute(name, "size");
      history.set(history.size() - 1, newMotion);
    } else {
      history.add(newMotion);
    }
  }

  @Override
  public void addMotionColor(String name, int newTick, int newR, int newG, int newB) {
    invalidGameState();
    invalidName(name);
    List<Motion> history = motions.get(name);
    Motion lastMotion = history.get(history.size() - 1).makeCopy();
    invalidInterval(lastMotion.getTick(), newTick);
    invalidColor(newR, newG, newB);
    Motion newMotion = lastMotion.changeColor(newTick, newR, newG, newB);

    if (operatingTheSameInterval(getLastTick(name), newTick)) {
      alreadyBeenChangingTheSameAttribute(name, "color");
      history.set(history.size() - 1, newMotion);
    } else {
      history.add(newMotion);
    }
  }

  @Override
  public void addMotionFreeze(String name, int newTick) {

    invalidGameState();
    invalidName(name);
    List<Motion> history = motions.get(name);
    Motion lastMotion = getLastMotion(name);

    invalidInterval(getLastTick(name), newTick);
    Motion newMotion = lastMotion.freeze(newTick);

    history.add(newMotion);

  }

  @Override
  public int getCanvasTopLeftX() {
    return this.canvasTopLeftX;
  }

  @Override
  public int getCanvasTopLeftY() {
    return this.canvasTopLeftY;
  }

  @Override
  public int getCanvasWidth() {
    return this.canvasWidth;
  }

  @Override
  public int getCanvasHeight() {
    return this.canvasHeight;
  }

  @Override
  public Map<String, IShape> getShapes() {
    Map<String, IShape> copy = new LinkedHashMap<>();
    for (String name : this.shapes.keySet()) {
      copy.put(name, getShapeFor(name));
    }
    return copy;
  }

  @Override
  public IShape getShapeFor(String name) {
    return this.shapes.get(name);
  }

  @Override
  public Map<String, List<Motion>> getMotions() {
    Map<String, List<Motion>> copy = new LinkedHashMap<>();
    for (String name : this.motions.keySet()) {
      copy.put(name, getMotionsFor(name));
    }
    return copy;
  }

  @Override
  public List<Motion> getMotionsFor(String name) {
    List<Motion> copy = new ArrayList<>();
    for (Motion m : this.motions.get(name)) {
      copy.add(m.makeCopy());
    }
    return copy;
  }

  private IDrawableShape createDrawableShape(String name, String type, int x, int y, int w, int h,
      int r, int g,
      int b) {
    switch (type) {
      case "rectangle":
        return new DrawableRectangle(name, type, x, y, w, h, r, g, b);
      case "ellipse":
        return new DrawableEllipse(name, type, x, y, w, h, r, g, b);
      default:
        throw new IllegalArgumentException(
            "The type of shape you entered is not drawable in this animation.");
    }
  }

  private int locateRetrievedIntervalIndex(String name, int tick) {
    List<Motion> lom = getMotionsFor(name);
    if (tick == lom.get(0).getTick()) {
      return 0;
    }
    if (tick < lom.get(0).getTick() || tick > getLastTick(name)) {
      if (tick > getLastTick(name)) {
        throw new IllegalArgumentException("Over bound.");
      } else {
        throw new IllegalArgumentException("The tick you entered does not exist.");
      }
    }
    for (int i = 1; i < lom.size(); i++) {
      int lastTick = lom.get(i - 1).getTick();
      int thisTick = lom.get(i).getTick();
      if (lastTick < tick && tick <= thisTick) {
        return i;
      }
    }
    throw new IllegalArgumentException("The tick you entered does not exist.");
  }

  private void invalidGameState() {
    if (started) {
      throw new IllegalStateException(
          "You are not allowed to edit the animation once it has been started.");
    }
  }

  private void invalidName(String name) {
    if (!shapes.containsKey(name)) {
      throw new IllegalArgumentException("The name you entered is invalid.");
    }
  }

  private void invalidTick(int tick) {
    if (tick < 0) {
      throw new IllegalArgumentException("The tick you entered is invalid.");
    }
  }

  private void invalidInterval(int oldTick, int newTick) {
    if (newTick <= 0 || newTick < oldTick) {
      throw new IllegalArgumentException("You are trying to operate on an invalid interval.");
    }
  }

  private void invalidPosition(int x, int y) {
    if (x < getCanvasTopLeftX() || y < getCanvasTopLeftY()
        || x > getCanvasTopLeftX() + getCanvasWidth()
        || y > getCanvasTopLeftY() + getCanvasHeight()) {
      throw new IllegalArgumentException("You entered an invalid position.");
    }
  }

  private void invalidSize(int w, int h) {
    if (w <= 0 || w > getCanvasWidth() || h <= 0 || h > getCanvasHeight()) {
      throw new IllegalArgumentException("You entered an invalid shape size.");
    }
  }

  private void invalidColor(int r, int g, int b) {
    if (r < 0 || g < 0 || b < 0 || r > 255 || g > 255 || b > 255) {
      throw new IllegalArgumentException("You entered an invalid shape color.");
    }
  }

  private boolean operatingTheSameInterval(int oldTick, int newTick) {
    return oldTick == newTick;
  }

  private void alreadyBeenChangingTheSameAttribute(String name, String attr) {
    List<Motion> history = motions.get(name);
    if (history.size() < 2) {
      return;
    }
    Motion last = history.get(history.size() - 1).makeCopy();
    Motion secondLast = history.get(history.size() - 2).makeCopy();
    boolean ans = false;
    switch (attr) {
      case "position":
        ans = last.getX() != secondLast.getX() || last.getY() != secondLast.getY();
        break;
      case "size":
        ans = last.getW() != secondLast.getW() || last.getH() != secondLast.getH();
        break;
      case "color":
        ans = last.getR() != secondLast.getR() || last.getG() != secondLast.getG()
            || last.getB() != secondLast.getB();
        break;
      default:
        throw new IllegalArgumentException("the attribute you entered does not exist.");
    }
    if (ans) {
      throw new IllegalArgumentException(
          "You are simultaneously changing this shape's position.");
    }
  }

  private Motion getLastMotion(String name) {
    List<Motion> history = motions.get(name);
    return history.get(history.size() - 1).makeCopy();
  }

  private int getLastTick(String name) {
    List<Motion> history = motions.get(name);
    Motion lastMotion = history.get(history.size() - 1).makeCopy();
    return lastMotion.getTick();
  }

}
