package controller;

import static org.junit.Assert.assertEquals;

import animator.utils.AnimationReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import model.IAnimation;
import model.SimpleAnimation;
import org.junit.Test;
import view.AnimationSVGView;
import view.ISVGView;

/**
 * Tests for the SVG controller.
 */
public class SVGControllerTest {

  String content =
      "<svg viewBox=\"145 50 410 220\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n"
          + "<rect id=\"disk1\" x=\"190\" y=\"180\" width=\"20\" height=\"30\" fill=\"rgb(0,49,90"
          + ")\">\n"
          + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"1200.0ms\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"1250.0ms\" dur=\"500.0ms\" attributeName=\"y\""
          + " from=\"180\" to=\"50\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"1750.0ms\" dur=\"50.0ms\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"1800.0ms\" dur=\"500.0ms\" attributeName=\"x\" "
          + "from=\"190\" to=\"490\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"2300.0ms\" dur=\"50.0ms\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"2350.0ms\" dur=\"500.0ms\" attributeName=\"y\" "
          + "from=\"50\" to=\"240\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"2850.0ms\" dur=\"1600.0ms\" fill=\"freeze\" /"
          + ">\n"
          + "<animate attributeType=\"xml\" begin=\"4450.0ms\" dur=\"500.0ms\" attributeName=\"y\""
          + " from=\"240\" to=\"50\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"4950.0ms\" dur=\"50.0ms\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\"500.0ms\" attributeName=\"x\""
          + " from=\"490\" to=\"340\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"5500.0ms\" dur=\"50.0ms\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"5550.0ms\" dur=\"500.0ms\" attributeName=\"y\""
          + " from=\"50\" to=\"210\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"6050.0ms\" dur=\"1600.0ms\" fill=\"freeze\""
          + " />\n"
          + "<animate attributeType=\"xml\" begin=\"7650.0ms\" dur=\"500.0ms\" attributeName=\"y\""
          + " from=\"210\" to=\"50\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"8150.0ms\" dur=\"50.0ms\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"8200.0ms\" dur=\"500.0ms\" attributeName=\"x\""
          + " from=\"340\" to=\"190\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"8700.0ms\" dur=\"50.0ms\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"8750.0ms\" dur=\"500.0ms\" attributeName=\"y\""
          + " from=\"50\" to=\"240\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"9250.0ms\" dur=\"1600.0ms\" fill=\"freeze\""
          + " />\n"
          + "<animate attributeType=\"xml\" begin=\"10850.0ms\" dur=\"500.0ms\" attributeName=\"y\""
          + " from=\"240\" to=\"50\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"11350.0ms\" dur=\"50.0ms\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"11400.0ms\" dur=\"500.0ms\" attributeName=\"x\""
          + " from=\"190\" to=\"490\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"11900.0ms\" dur=\"50.0ms\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"11950.0ms\" dur=\"500.0ms\" attributeName=\"y\""
          + " from=\"50\" to=\"180\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"12450.0ms\" dur=\"400.0ms\" attributeName=\""
          + "fill\" from=\"rgb(0,49,90)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"12850.0ms\" dur=\"2250.0ms\" fill=\"freeze\""
          + " />\n"
          + "</rect>\n"
          + "<rect id=\"disk2\" x=\"167\" y=\"210\" width=\"65\" height=\"30\" fill=\"rgb(6,247,41"
          + ")\">\n"
          + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"2800.0ms\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"2850.0ms\" dur=\"500.0ms\" attributeName=\"y\""
          + " from=\"210\" to=\"50\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"3350.0ms\" dur=\"50.0ms\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"3400.0ms\" dur=\"500.0ms\" attributeName=\"x\""
          + " from=\"167\" to=\"317\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"3900.0ms\" dur=\"50.0ms\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"3950.0ms\" dur=\"500.0ms\" attributeName=\"y\""
          + " from=\"50\" to=\"240\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"4450.0ms\" dur=\"4800.0ms\" fill=\"freeze\""
          + " />\n"
          + "<animate attributeType=\"xml\" begin=\"9250.0ms\" dur=\"500.0ms\" attributeName=\"y\""
          + " from=\"240\" to=\"50\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"9750.0ms\" dur=\"50.0ms\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"9800.0ms\" dur=\"500.0ms\" attributeName=\"x\""
          + " from=\"317\" to=\"467\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"10300.0ms\" dur=\"50.0ms\" fill=\"freeze\""
          + " />\n"
          + "<animate attributeType=\"xml\" begin=\"10350.0ms\" dur=\"500.0ms\" attributeName=\"y\""
          + " from=\"50\" to=\"210\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"10850.0ms\" dur=\"400.0ms\" attributeName"
          + "=\"fill\" from=\"rgb(6,247,41)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"11250.0ms\" dur=\"3850.0ms\" fill=\"freeze\""
          + " />\n"
          + "</rect>\n"
          + "<rect id=\"disk3\" x=\"145\" y=\"240\" width=\"110\" height=\"30\" fill=\"rgb(11,45,"
          + "175)\">\n"
          + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"6000.0ms\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"6050.0ms\" dur=\"500.0ms\" attributeName=\"y\""
          + " from=\"240\" to=\"50\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"6550.0ms\" dur=\"50.0ms\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"6600.0ms\" dur=\"500.0ms\" attributeName=\"x\""
          + " from=\"145\" to=\"445\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"7100.0ms\" dur=\"50.0ms\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"7150.0ms\" dur=\"500.0ms\" attributeName=\"y\""
          + " from=\"50\" to=\"240\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"7650.0ms\" dur=\"400.0ms\" attributeName=\""
          + "fill\" from=\"rgb(11,45,175)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"8050.0ms\" dur=\"7050.0ms\" fill=\"freeze\""
          + " />\n"
          + "</rect>\n"
          + "</svg>";

  @Test
  public void testGo() throws IOException {

    String outputFileName = "";
    Appendable out;
    BufferedReader reader = new BufferedReader(new FileReader("toh-3.txt"));
    SimpleAnimation.Builder builder = new SimpleAnimation.Builder();
    IAnimation model = AnimationReader.parseFile(reader, builder);
    ISVGView view = new AnimationSVGView(model, 20);

    if (outputFileName.equals("")) {
      out = new StringBuilder();
      view.render(out);
      System.out.println(out);
    } else {
      out = new PrintStream(outputFileName);
      view.render(out);
    }
    Controller controller = new SVGController(model, 20);
    controller.animate("svg");
    assertEquals(content, out.toString());
  }

}