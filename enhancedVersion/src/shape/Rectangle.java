package shape;

import java.util.ArrayList;
import java.util.List;
import motion.Motion;

/**
 * Represents the Rectangle shape class which extends the abstract AShape class.
 */
public class Rectangle extends AShape {

  /**
   * Constructs an rectangle in a manner selected by concrete sub classes of this class.
   *
   * @param name the name of the shape
   * @param type the type of the shape
   */
  public Rectangle(String name, String type) {
    super(name, type);
  }

  @Override
  public String getOpenTag(int x, int y, int w, int h, int r, int g, int b) {
    return String.format(
        "\n<rect id=\"%s\" x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" fill=\"rgb(%d,%d,%d)\">",
        getName(), x, y, w, h, r, g, b);
  }

  @Override
  public List<String> getAnimateTag(double begin, double duration, Motion m1, Motion m2) {
    List<String> temps = new ArrayList<>();
    String title = String
        .format("<animate attributeType=\"xml\" begin=\"%.1fms\" dur=\"%.1fms\"", begin, duration);
    if (m2.getX() - m1.getX() != 0) {
      String x = String
          .format("%s attributeName=\"x\" from=\"%s\" to=\"%s\" fill=\"freeze\" />", title,
              m1.getX(), m2.getX());
      temps.add(x);
    }
    if (m2.getY() - m1.getY() != 0) {
      String y = String
          .format("%s attributeName=\"y\" from=\"%s\" to=\"%s\" fill=\"freeze\" />", title,
              m1.getY(), m2.getY());
      temps.add(y);
    }
    if (m2.getW() - m1.getW() != 0) {
      String w = String
          .format("%s attributeName=\"width\" from=\"%s\" to=\"%s\" fill=\"freeze\" />", title,
              m1.getW(), m2.getW());
      temps.add(w);
    }
    if (m2.getH() - m1.getH() != 0) {
      String h = String
          .format("%s attributeName=\"height\" from=\"%s\" to=\"%s\" fill=\"freeze\" />", title,
              m1.getH(), m2.getH());
      temps.add(h);
    }
    return temps;
  }

  @Override
  public String getTagShapeType() {
    return "rect";
  }

  @Override
  public String getTagX() {
    return "x";
  }

  @Override
  public String getTagY() {
    return "y";
  }

  @Override
  public String getTagW() {
    return "width";
  }

  @Override
  public String getTagH() {
    return "height";
  }
}
