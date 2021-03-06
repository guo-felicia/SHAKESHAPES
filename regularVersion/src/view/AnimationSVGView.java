package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.IAnimation;
import motion.Motion;
import shape.IShape;

/**
 * Represents the class for viewing the svg animation of the model.
 */
public class AnimationSVGView implements ISVGView {

  private final IAnimation model;
  private final int speed;

  /**
   * Represents the initial constructor of AnimationVisualView.
   *
   * @param model the simple animation model
   * @param speed the speed of the animation
   */
  public AnimationSVGView(IAnimation model, int speed) {
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
    out.append(this.toString());
  }

  @Override
  public String toString() {
    StringBuilder ans = new StringBuilder();
    String openTag = String.format(
        "<svg viewBox=\"%d %d %d %d\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">",
        model.getCanvasTopLeftX(), model.getCanvasTopLeftY(), model.getCanvasWidth(),
        model.getCanvasHeight());
    ans.append(openTag);

    // for each shape, draw its motions
    for (String name : model.getMotions().keySet()) {
      ans.append(createMotionsFor(name));
    }
    ans.append("\n</svg>");
    return ans.toString();
  }

  /*
  Create the motions of a given shape on SVG canvas.
   */
  private String createMotionsFor(String name) {
    StringBuilder ans = new StringBuilder();
    List<Motion> lom = model.getMotionsFor(name);
    IShape shape = model.getShapeFor(name);
    String tag = shape.getTagShapeType();
    String attributeX = shape.getTagX();
    String attributeY = shape.getTagY();
    String attributeW = shape.getTagW();
    String attributeH = shape.getTagH();
    Motion m0 = lom.get(0);
    String openTag = String.format(
        "\n<%s id=\"%s\" %s=\"%d\" %s=\"%d\" %s=\"%d\" %s=\"%d\" fill=\"rgb(%d,%d,%d)\">",
        tag, name, attributeX, m0.getX(), attributeY, m0.getY(), attributeW, m0.getW(), attributeH,
        m0.getH(), m0.getR(), m0.getG(), m0.getB());
    ans = ans.append(openTag);

    // for each motion, create an animate log
    List<String> logs;
    for (int i = 1; i < lom.size(); i++) {
      logs = createASingleMotionAt(name, lom.get(i - 1), lom.get(i));
      for (int j = 0; j < logs.size(); j++) {
        ans = ans.append("\n" + logs.get(j));
      }
    }
    ans.append(String.format("\n</%s>", tag));
    return ans.toString();
  }

  /*
  Create a single motion of a given shape at a given point on SVG canvas.
   */
  private List<String> createASingleMotionAt(String name, Motion m1, Motion m2) {
    IShape shape = model.getShapeFor(name);
    double tick1 = m1.getTick();
    double t1 = Math.round(tick1 / speed * 1000);
    double tick2 = m2.getTick();
    double t2 = Math.round(tick2 / speed * 1000);
    String title = String
        .format("<animate attributeType=\"xml\" begin=\"%.1fms\" dur=\"%.1fms\"", t1, t2 - t1);
    String attributeX = shape.getTagX();
    String attributeY = shape.getTagY();
    String attributeW = shape.getTagW();
    String attributeH = shape.getTagH();

    List<String> temps = new ArrayList<>();
    if (m2.getX() - m1.getX() != 0) {
      String x = String
          .format("%s attributeName=\"%s\" from=\"%s\" to=\"%s\" fill=\"freeze\" />", title,
              attributeX, m1.getX(), m2.getX());
      temps.add(x);
    }
    if (m2.getY() - m1.getY() != 0) {
      String y = String
          .format("%s attributeName=\"%s\" from=\"%s\" to=\"%s\" fill=\"freeze\" />", title,
              attributeY, m1.getY(), m2.getY());
      temps.add(y);
    }
    if (m2.getW() - m1.getW() != 0) {
      String w = String
          .format("%s attributeName=\"%s\" from=\"%s\" to=\"%s\" fill=\"freeze\" />", title,
              attributeW, m1.getW(), m2.getW());
      temps.add(w);
    }
    if (m2.getH() - m1.getH() != 0) {
      String h = String
          .format("%s attributeName=\"%s\" from=\"%s\" to=\"%s\" fill=\"freeze\" />", title,
              attributeH, m1.getH(), m2.getH());
      temps.add(h);
    }
    if ((m2.getR() - m1.getR()) != 0 || (m2.getG() - m1.getG()) != 0
        || (m2.getB() - m1.getB()) != 0) {
      String c = String.format(
          "%s attributeName=\"fill\" from=\"rgb(%d,%d,%d)\" to=\"rgb(%d,%d,%d)\""
              + " fill=\"freeze\" />",
          title, m1.getR(), m1.getG(), m1.getB(), m2.getR(), m2.getG(), m2.getB());
      temps.add(c);
    }
    if (m1.getX() == m2.getX() && m1.getY() == m2.getY() && m1.getW() == m2.getW()
        && m1.getH() == m2.getH() && m1.getR() == m2.getR() && m1.getG() == m2.getG()
        && m1.getB() == m2.getB()) {
      String c = String.format("%s fill=\"freeze\" />", title);
      temps.add(c);
    }
    return temps;
  }
}