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
 * Tests the textual representation of a shape animation.
 */
public class AnimationTextualViewTest {

  BufferedReader reader;
  SimpleAnimation.Builder builder;
  IAnimation model;
  ITextualView view;

  private void emptySpeed20() {
    model = new SimpleAnimation();
    view = new AnimationTextualView(model, 20);
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
    view = new AnimationTextualView(model, 20);
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
    view = new AnimationTextualView(model, 20);
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
    view = new AnimationTextualView(model, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    ITextualView view = new AnimationTextualView(null, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroSpeed() {
    IAnimation model = new SimpleAnimation();
    ITextualView view = new AnimationTextualView(model, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeSpeed() {
    IAnimation model = new SimpleAnimation();
    ITextualView view = new AnimationTextualView(model, -20);
  }

  @Test
  public void testCanvasEmpty() {
    emptySpeed20();
    String canvasString = "canvas 0 0 360 360";
    assertTrue(view.toString().contains(canvasString));
  }

  @Test
  public void testCanvasToh3() {
    toh3Speed20();
    String canvasString = "canvas 145 50 410 220";
    assertTrue(view.toString().contains(canvasString));
  }

  @Test
  public void testCanvasToh5() {
    toh5Speed20();
    String canvasString = "canvas 145 50 410 208";
    assertTrue(view.toString().contains(canvasString));
  }

  @Test
  public void testSpeed20() {
    toh3Speed20();
    String firstMotion = "shape disk1 rectangle\nmotion disk1 0.05 190 180 20 30 0 49 90 1.25";
    assertTrue(view.toString().contains(firstMotion));
  }

  @Test
  public void testSpeed10() {
    toh3Speed10();
    String firstMotion = "shape disk1 rectangle\nmotion disk1 0.10 190 180 20 30 0 49 90 2.50";
    assertTrue(view.toString().contains(firstMotion));
  }

}