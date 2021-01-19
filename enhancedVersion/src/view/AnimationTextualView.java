package view;

import java.io.IOException;
import model.IAnimation;
import motion.Motion;

/**
 * Represents a textual description of a shape animation.
 */
public class AnimationTextualView implements ITextualView {

  private final IAnimation model;
  private final int speed;

  /**
   * Constructs a textual representation of a given shape animation.
   *
   * @param model a shape animation
   * @param speed the speed of the givin animation
   */
  public AnimationTextualView(IAnimation model, int speed) {
    if (model == null) {
      throw new IllegalArgumentException("the model cannot be null.");
    }
    if (speed <= 0) {
      throw new IllegalArgumentException("the speed should be a positive integer.");
    }
    this.model = model;
    this.speed = speed;
  }

  @Override
  public void render(Appendable out) throws IOException {
    //dealing with appendable
    out.append(this.toString());
  }

  @Override
  public String toString() {
    StringBuilder ans = new StringBuilder(
        "canvas " + model.getCanvasTopLeftX() + " " + model.getCanvasTopLeftY() + " " + model
            .getCanvasWidth() + " " + model.getCanvasHeight() + "\n");
    for (String name : model.getMotions().keySet()) {
      if (model.getMotionsFor(name).size() < 2) {
        continue;
      }
      String temp =
          "shape " + name + " " + model.getShapeFor(name).getType() + "\n" + motionToString(name);
      ans.append(temp);
    }
    return ans.toString();
  }

  private String motionToString(String name) {
    StringBuilder ans = new StringBuilder();
    for (int i = 1; i < model.getMotionsFor(name).size(); i++) {
      Motion lastInterval = model.getMotionsFor(name).get(i - 1);
      Motion thisInterval = model.getMotionsFor(name).get(i);
      String temp =
          "motion " + name + " " + motionToStringHelper(lastInterval) + " " + motionToStringHelper(
              thisInterval) + "\n";
      ans.append(temp);
    }
    return ans.toString();
  }

  private String motionToStringHelper(Motion m) {
    double f = Math.round(100 * m.getTick() / speed) * 0.01;
    String inSeconds = String.format("%.2f", f);
    return inSeconds + " " + m.getX() + " " + m.getY() + " " + m.getW() + " " + m.getH() + " " + m
        .getR() + " " + m.getG() + " " + m.getB();
  }

}