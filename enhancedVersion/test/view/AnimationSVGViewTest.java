package view;

import static org.junit.Assert.assertTrue;

import animator.utils.AnimationReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import model.IAnimation;
import model.SimpleAnimation;
import org.junit.Test;


/**
 * Tests of the view that output is svg format.
 */
public class AnimationSVGViewTest {

  BufferedReader reader;
  SimpleAnimation.Builder builder;
  IAnimation model;
  ISVGView view;

  private void emptySpeed20() {
    model = new SimpleAnimation();
    view = new AnimationSVGView(model, 20);
  }

  private void toh3Speed20() {
    try {
      reader = new BufferedReader(new FileReader("toh-3.txt"));
      System.out.println(reader);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    builder = new SimpleAnimation.Builder();
    model = AnimationReader.parseFile(reader, builder);
    view = new AnimationSVGView(model, 20);
  }

  private void toh5Speed20() {
    try {
      reader = new BufferedReader(new FileReader("toh-5.txt"));
      System.out.println(reader);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    builder = new SimpleAnimation.Builder();
    model = AnimationReader.parseFile(reader, builder);
    view = new AnimationSVGView(model, 20);
  }

  private void toh3Speed10() {
    try {
      reader = new BufferedReader(new FileReader("toh-3.txt"));
      System.out.println(reader);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    builder = new SimpleAnimation.Builder();
    model = AnimationReader.parseFile(reader, builder);
    view = new AnimationSVGView(model, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    view = new AnimationSVGView(null, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroSpeed() {
    IAnimation model = new SimpleAnimation();
    view = new AnimationSVGView(model, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeSpeed() {
    IAnimation model = new SimpleAnimation();
    view = new AnimationSVGView(model, -20);
  }

  @Test
  public void testCanvasEmpty() {
    emptySpeed20();
    String tag = "<svg viewBox=\"0 0 360 360\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">";
    assertTrue(view.toString().contains(tag));
  }

  @Test
  public void testCanvasToh3() {
    toh3Speed20();
    String tag = "<svg viewBox=\"145 50 410 220\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">";
    assertTrue(view.toString().contains(tag));
  }

  @Test
  public void testCanvasToh5() {
    toh5Speed20();
    String tag = "<svg viewBox=\"145 50 410 208\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">";
    assertTrue(view.toString().contains(tag));
  }

  @Test
  public void testSpeed20() {
    toh3Speed20();
    String disk2 = "<rect id=\"disk2\" x=\"167\" y=\"210\" width=\"65\" height=\"30\" "
        + "fill=\"rgb(6,247,41)\">";
    assertTrue(view.toString().contains(disk2));

  }

  @Test
  public void testSpeed10() {
    toh3Speed10();
    String disk1 = "<rect id=\"disk1\" x=\"190\" y=\"180\" width=\"20\" height=\"30\" "
        + "fill=\"rgb(0,49,90)\">";
    assertTrue(view.toString().contains(disk1));
  }

}