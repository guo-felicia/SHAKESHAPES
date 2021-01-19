package controller;

import static org.junit.Assert.*;

import animator.utils.AnimationReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import model.IAnimation;
import model.SimpleAnimation;
import org.junit.Test;
import view.AnimationSVGView;
import view.AnimationTextualView;
import view.ISVGView;
import view.ITextualView;
import view.IVisualView;

public class TextualControllerTest {

  String content = "canvas 145 50 410 220\n"
      + "shape disk1 rectangle\n"
      + "motion disk1 0.05 190 180 20 30 0 49 90 1.25 190 180 20 30 0 49 90\n"
      + "motion disk1 1.25 190 180 20 30 0 49 90 1.75 190 50 20 30 0 49 90\n"
      + "motion disk1 1.75 190 50 20 30 0 49 90 1.80 190 50 20 30 0 49 90\n"
      + "motion disk1 1.80 190 50 20 30 0 49 90 2.30 490 50 20 30 0 49 90\n"
      + "motion disk1 2.30 490 50 20 30 0 49 90 2.35 490 50 20 30 0 49 90\n"
      + "motion disk1 2.35 490 50 20 30 0 49 90 2.85 490 240 20 30 0 49 90\n"
      + "motion disk1 2.85 490 240 20 30 0 49 90 4.45 490 240 20 30 0 49 90\n"
      + "motion disk1 4.45 490 240 20 30 0 49 90 4.95 490 50 20 30 0 49 90\n"
      + "motion disk1 4.95 490 50 20 30 0 49 90 5.00 490 50 20 30 0 49 90\n"
      + "motion disk1 5.00 490 50 20 30 0 49 90 5.50 340 50 20 30 0 49 90\n"
      + "motion disk1 5.50 340 50 20 30 0 49 90 5.55 340 50 20 30 0 49 90\n"
      + "motion disk1 5.55 340 50 20 30 0 49 90 6.05 340 210 20 30 0 49 90\n"
      + "motion disk1 6.05 340 210 20 30 0 49 90 7.65 340 210 20 30 0 49 90\n"
      + "motion disk1 7.65 340 210 20 30 0 49 90 8.15 340 50 20 30 0 49 90\n"
      + "motion disk1 8.15 340 50 20 30 0 49 90 8.20 340 50 20 30 0 49 90\n"
      + "motion disk1 8.20 340 50 20 30 0 49 90 8.70 190 50 20 30 0 49 90\n"
      + "motion disk1 8.70 190 50 20 30 0 49 90 8.75 190 50 20 30 0 49 90\n"
      + "motion disk1 8.75 190 50 20 30 0 49 90 9.25 190 240 20 30 0 49 90\n"
      + "motion disk1 9.25 190 240 20 30 0 49 90 10.85 190 240 20 30 0 49 90\n"
      + "motion disk1 10.85 190 240 20 30 0 49 90 11.35 190 50 20 30 0 49 90\n"
      + "motion disk1 11.35 190 50 20 30 0 49 90 11.40 190 50 20 30 0 49 90\n"
      + "motion disk1 11.40 190 50 20 30 0 49 90 11.90 490 50 20 30 0 49 90\n"
      + "motion disk1 11.90 490 50 20 30 0 49 90 11.95 490 50 20 30 0 49 90\n"
      + "motion disk1 11.95 490 50 20 30 0 49 90 12.45 490 180 20 30 0 49 90\n"
      + "motion disk1 12.45 490 180 20 30 0 49 90 12.85 490 180 20 30 0 255 0\n"
      + "motion disk1 12.85 490 180 20 30 0 255 0 15.10 490 180 20 30 0 255 0\n"
      + "shape disk2 rectangle\n"
      + "motion disk2 0.05 167 210 65 30 6 247 41 2.85 167 210 65 30 6 247 41\n"
      + "motion disk2 2.85 167 210 65 30 6 247 41 3.35 167 50 65 30 6 247 41\n"
      + "motion disk2 3.35 167 50 65 30 6 247 41 3.40 167 50 65 30 6 247 41\n"
      + "motion disk2 3.40 167 50 65 30 6 247 41 3.90 317 50 65 30 6 247 41\n"
      + "motion disk2 3.90 317 50 65 30 6 247 41 3.95 317 50 65 30 6 247 41\n"
      + "motion disk2 3.95 317 50 65 30 6 247 41 4.45 317 240 65 30 6 247 41\n"
      + "motion disk2 4.45 317 240 65 30 6 247 41 9.25 317 240 65 30 6 247 41\n"
      + "motion disk2 9.25 317 240 65 30 6 247 41 9.75 317 50 65 30 6 247 41\n"
      + "motion disk2 9.75 317 50 65 30 6 247 41 9.80 317 50 65 30 6 247 41\n"
      + "motion disk2 9.80 317 50 65 30 6 247 41 10.30 467 50 65 30 6 247 41\n"
      + "motion disk2 10.30 467 50 65 30 6 247 41 10.35 467 50 65 30 6 247 41\n"
      + "motion disk2 10.35 467 50 65 30 6 247 41 10.85 467 210 65 30 6 247 41\n"
      + "motion disk2 10.85 467 210 65 30 6 247 41 11.25 467 210 65 30 0 255 0\n"
      + "motion disk2 11.25 467 210 65 30 0 255 0 15.10 467 210 65 30 0 255 0\n"
      + "shape disk3 rectangle\n"
      + "motion disk3 0.05 145 240 110 30 11 45 175 6.05 145 240 110 30 11 45 175\n"
      + "motion disk3 6.05 145 240 110 30 11 45 175 6.55 145 50 110 30 11 45 175\n"
      + "motion disk3 6.55 145 50 110 30 11 45 175 6.60 145 50 110 30 11 45 175\n"
      + "motion disk3 6.60 145 50 110 30 11 45 175 7.10 445 50 110 30 11 45 175\n"
      + "motion disk3 7.10 445 50 110 30 11 45 175 7.15 445 50 110 30 11 45 175\n"
      + "motion disk3 7.15 445 50 110 30 11 45 175 7.65 445 240 110 30 11 45 175\n"
      + "motion disk3 7.65 445 240 110 30 11 45 175 8.05 445 240 110 30 0 255 0\n"
      + "motion disk3 8.05 445 240 110 30 0 255 0 15.10 445 240 110 30 0 255 0\n";

  @Test
  public void testGo() throws IOException {

    String outputFileName = "";
    Appendable out;
    BufferedReader reader = new BufferedReader(new FileReader("toh-3.txt"));
    SimpleAnimation.Builder builder = new SimpleAnimation.Builder();
    IAnimation model = AnimationReader.parseFile(reader, builder);
    ITextualView view = new AnimationTextualView(model, 20);

    if (outputFileName.equals("")) {
      out = new StringBuilder();
      view.render(out);
      System.out.println(out);
    } else {
      out = new PrintStream(outputFileName);
      view.render(out);
    }
    Controller controller = new TextualController(model, 20);
    controller.go("txt");
    assertEquals(content, out.toString());
  }


}