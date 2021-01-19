package motion;

import java.util.Objects;

/**
 * Represents a motion of a shape. It includes changing position, size, or/and color in a specific
 * time interval.
 */
public class Motion implements IMotion {

  private int tick;
  private int x;
  private int y;
  private int w;
  private int h;
  private int r;
  private int g;
  private int b;

  /**
   * Construct a motion of a shape.
   *
   * @param tick the end tick of the motion interval.
   * @param x    ending x position
   * @param y    ending y position
   * @param w    ending shape width
   * @param h    ending shape height
   * @param r    ending r attribute of a rgb color
   * @param g    ending g attribute of a rgb color
   * @param b    ending b attribute of a rgb color
   */
  public Motion(int tick, int x, int y, int w, int h, int r, int g, int b) {
    if (tick < 0) {
      throw new IllegalArgumentException("the tick you entered should be non-negative.");
    }
    // invalidPosition(x, y);
    invalidSize(w, h);
    invalidColor(r, g, b);
    this.tick = tick;
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.r = r;
    this.g = g;
    this.b = b;
  }

  @Override
  public Motion makeCopy() {
    return new Motion(this.getTick(), this.getX(), this.getY(), this.getW(), this.getH(),
        this.getR(), this.getG(), this.getB());
  }

  @Override
  public Motion retrieve(Motion lastInterval, int tick) {
    // exception
    if (lastInterval == null) {
      throw new IllegalArgumentException("this motion must have a previous interval.");
    }
    if (tick < lastInterval.getTick() || tick > this.getTick()) {
      throw new IllegalArgumentException("the tick you entered is invalid.");
    }
    // calculate percentage
    int lastTick = lastInterval.getTick();
    int thisTick = this.getTick();
    double percentage = calculatePercentage(lastTick, tick, thisTick);
    // retrieve a moment
    int x = calculateRetrievedAttribute(lastInterval.getX(), this.getX(), percentage);
    int y = calculateRetrievedAttribute(lastInterval.getY(), this.getY(), percentage);
    int w = calculateRetrievedAttribute(lastInterval.getW(), this.getW(), percentage);
    int h = calculateRetrievedAttribute(lastInterval.getH(), this.getH(), percentage);
    int r = calculateRetrievedAttribute(lastInterval.getR(), this.getR(), percentage);
    int g = calculateRetrievedAttribute(lastInterval.getG(), this.getG(), percentage);
    int b = calculateRetrievedAttribute(lastInterval.getB(), this.getB(), percentage);
    return new Motion(tick, x, y, w, h, r, g, b);
  }

  @Override
  public Motion changePosition(int newTick, int newX, int newY) {
    invalidTick(newTick);
    invalidPosition(newX, newY);
    this.setTick(newTick);
    this.setX(newX);
    this.setY(newY);
    return this.makeCopy();
  }

  @Override
  public Motion changeSize(int newTick, int newW, int newH) {
    invalidTick(newTick);
    invalidSize(newW, newH);
    this.setTick(newTick);
    this.setW(newW);
    this.setH(newH);
    return this.makeCopy();
  }

  @Override
  public Motion changeColor(int newTick, int newR, int newG, int newB) {
    invalidTick(newTick);
    invalidColor(newR, newG, newB);
    this.setTick(newTick);
    this.setR(newR);
    this.setG(newG);
    this.setB(newB);
    return this.makeCopy();
  }

  @Override
  public Motion freeze(int newTick) {
    invalidTick(newTick);
    this.setTick(newTick);
    return this.makeCopy();
  }

  @Override
  public String toString() {
    return tick + " " + x + " " + y + " " + w + " " + h + " " + r + " " + g + " " + b;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Motion motion = (Motion) o;
    return tick == motion.tick && x == motion.x && y == motion.y && w == motion.w && h == motion.h
        && r == motion.r && g == motion.g && b == motion.b;
  }

  @Override
  public int hashCode() {
    return Objects.hash(tick, x, y, w, h, r, g, b);
  }

  private void invalidTick(int tick) {
    if (tick < this.getTick()) {
      throw new IllegalArgumentException("the tick you entered is invalid.");
    }
  }

  private void invalidPosition(int x, int y) {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("You entered an invalid position.");
    }
  }

  private void invalidSize(int w, int h) {
    if (w <= 0 || h <= 0) {
      throw new IllegalArgumentException("You entered an invalid shape size.");
    }
  }

  private void invalidColor(int r, int g, int b) {
    if (r < 0 || g < 0 || b < 0 || r > 255 || g > 255 || b > 255) {
      throw new IllegalArgumentException("You entered an invalid shape color.");
    }
  }

  private double calculatePercentage(int lastTick, int tick, int thisTick) {
    if (thisTick - lastTick == 0) {
      return 0;
    }
    return Math.round(1000 * (tick - lastTick) / (thisTick - lastTick)) * 0.001;
  }

  private int calculateRetrievedAttribute(int lastValue, int thisValue, double percentage) {
    if (lastValue != thisValue) {
      return (int) Math.round(lastValue + (thisValue - lastValue) * percentage);
    } else {
      return lastValue;
    }
  }

  public int getTick() {
    return tick;
  }

  private void setTick(int tick) {
    this.tick = tick;
  }

  public int getX() {
    return x;
  }

  private void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  private void setY(int y) {
    this.y = y;
  }

  public int getW() {
    return w;
  }

  private void setW(int w) {
    this.w = w;
  }

  public int getH() {
    return h;
  }

  private void setH(int h) {
    this.h = h;
  }

  public int getR() {
    return r;
  }

  private void setR(int r) {
    this.r = r;
  }

  public int getG() {
    return g;
  }

  private void setG(int g) {
    this.g = g;
  }

  public int getB() {
    return b;
  }

  private void setB(int b) {
    this.b = b;
  }
}
