package shape;

import java.util.ArrayList;
import java.util.List;
import motion.Motion;

/**
 * Represents the Ellipse shape class which extends the abstract AShape class.
 */
public class Ellipse extends AShape {

  /**
   * Constructs an ellipse in a manner selected by concrete sub classes of this class.
   *
   * @param name the name of the shape
   * @param type the type of the shape
   */
  public Ellipse(String name, String type) {
    super(name, type);
  }

  @Override
  public String getOpenTag(int x, int y, int w, int h, int r, int g, int b) {
    return String.format(
        "\n<ellipse id=\"%s\" cx=\"%d\" cy=\"%d\" rx=\"%d\" ry=\"%d\" fill=\"rgb(%d,%d,%d)\">",
        getName(), x, y, w, h, r, g, b);
  }

  @Override
  public List<String> getAnimateTag(double begin, double duration, Motion m1, Motion m2) {
    List<String> temps = new ArrayList<>();
    String title = String
        .format("<animate attributeType=\"xml\" begin=\"%.1fms\" dur=\"%.1fms\"", begin, duration);
    if (m2.getX() - m1.getX() != 0) {
      String x = String
          .format("%s attributeName=\"cx\" from=\"%s\" to=\"%s\" fill=\"freeze\" />", title,
              m1.getX(), m2.getX());
      temps.add(x);
    }
    if (m2.getY() - m1.getY() != 0) {
      String y = String
          .format("%s attributeName=\"cy\" from=\"%s\" to=\"%s\" fill=\"freeze\" />", title,
              m1.getY(), m2.getY());
      temps.add(y);
    }
    if (m2.getW() - m1.getW() != 0) {
      String w = String
          .format("%s attributeName=\"rx\" from=\"%s\" to=\"%s\" fill=\"freeze\" />", title,
              m1.getW(), m2.getW());
      temps.add(w);
    }
    if (m2.getH() - m1.getH() != 0) {
      String h = String
          .format("%s attributeName=\"ry\" from=\"%s\" to=\"%s\" fill=\"freeze\" />", title,
              m1.getH(), m2.getH());
      temps.add(h);
    }
    return temps;
  }

  @Override
  public String getTagShapeType() {
    return "ellipse";
  }

  @Override
  public String getTagX() {
    return "cx";
  }

  @Override
  public String getTagY() {
    return "cy";
  }

  @Override
  public String getTagW() {
    return "rx";
  }

  @Override
  public String getTagH() {
    return "ry";
  }
}
