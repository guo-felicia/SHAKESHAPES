package shape;

import java.util.ArrayList;
import java.util.List;
import motion.Motion;

/**
 * Represents the 2D graphic of a plus sign.
 */
public class Plus extends AShape {

  /**
   * Constructs a Shape in a manner selected by concrete subcl asses of this class.
   *
   * @param name the name of the shape
   * @param type the type of the shape
   */
  public Plus(String name, String type) {
    super(name, type);
  }

  @Override
  public String getOpenTag(int x, int y, int w, int h, int r, int g, int b) {
    int[] xValues = {
        (int) (x + 0.25 * w), (int) (x + 0.75 * w), (int) (x + 0.75 * w), x + w, x + w,
        (int) (x + 0.75 * w), (int) (x + 0.75 * w), (int) (x + 0.25 * w), (int) (x + 0.25 * w), x,
        x,
        (int) (x + 0.25 * w)};
    int[] yValues = {y, y, (int) (y + 0.25 * h), (int) (y + 0.25 * h), (int) (y + 0.75 * h),
        (int) (y + 0.75 * h), y + h, y + h, (int) (y + 0.75 * h), (int) (y + 0.75 * h),
        (int) (y + 0.25 * h),
        (int) (y + 0.25 * h)};
    StringBuilder points = new StringBuilder();
    for (int i = 0; i < xValues.length; i++) {
      points.append(xValues[i]).append(",").append(yValues[i]).append(" ");
    }
    return String
        .format("\n<polygon id=\"%s\" points=\"%s\" fill=\"rgb(%d,%d,%d)\">", getName(), points, r,
            g, b);
  }

  @Override
  public List<String> getAnimateTag(double begin, double duration, Motion m1, Motion m2) {
    int x1 = m1.getX();
    int y1 = m1.getY();
    int w1 = m1.getW();
    int h1 = m1.getH();
    int x2 = m2.getX();
    int y2 = m2.getY();
    int w2 = m2.getW();
    int h2 = m2.getH();
    int[] xValues1 = {
        (int) (x1 + 0.25 * w1), (int) (x1 + 0.75 * w1), (int) (x1 + 0.75 * w1), x1 + w1, x1 + w1,
        (int) (x1 + 0.75 * w1), (int) (x1 + 0.75 * w1), (int) (x1 + 0.25 * w1),
        (int) (x1 + 0.25 * w1), x1, x1, (int) (x1 + 0.25 * w1)};
    int[] yValues1 = {y1, y1, (int) (y1 + 0.25 * h1), (int) (y1 + 0.25 * h1),
        (int) (y1 + 0.75 * h1), (int) (y1 + 0.75 * h1), y1 + h1, y1 + h1, (int) (y1 + 0.75 * h1),
        (int) (y1 + 0.75 * h1), (int) (y1 + 0.25 * h1), (int) (y1 + 0.25 * h1)};
    int[] xValues2 = {
        (int) (x2 + 0.25 * w2), (int) (x2 + 0.75 * w2), (int) (x2 + 0.75 * w2), x2 + w2, x2 + w2,
        (int) (x2 + 0.75 * w2), (int) (x2 + 0.75 * w2), (int) (x2 + 0.25 * w2),
        (int) (x2 + 0.25 * w2), x2, x2, (int) (x2 + 0.25 * w2)};
    int[] yValues2 = {y2, y2, (int) (y2 + 0.25 * h2), (int) (y2 + 0.25 * h2),
        (int) (y2 + 0.75 * h2), (int) (y2 + 0.75 * h2), y2 + h2, y2 + h2, (int) (y2 + 0.75 * h2),
        (int) (y2 + 0.75 * h2), (int) (y2 + 0.25 * h2), (int) (y2 + 0.25 * h2)};
    StringBuilder from = new StringBuilder();
    for (int i = 0; i < xValues1.length; i++) {
      from.append(xValues1[i]).append(",").append(yValues1[i]).append(" ");
    }
    StringBuilder to = new StringBuilder();
    for (int i = 0; i < xValues2.length; i++) {
      to.append(xValues2[i]).append(",").append(yValues2[i]).append(" ");
    }
    List<String> temps = new ArrayList<>();
    String tag = String
        .format(
            "<animate attributeType=\"xml\" begin=\"%.1fms\" dur=\"%.1fms\" "
                + "attributeName=\"points\" from=\"%s\" to=\"%s\" fill=\"freeze\" />",
            begin, duration, from, to);
    temps.add(tag);
    return temps;
  }

  @Override
  public String getTagShapeType() {
    return "polygon";
  }

  @Override
  public String getTagX() {
    return null;
  }

  @Override
  public String getTagY() {
    return null;
  }

  @Override
  public String getTagW() {
    return null;
  }

  @Override
  public String getTagH() {
    return null;
  }
}
