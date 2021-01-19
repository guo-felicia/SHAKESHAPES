package controller;

import java.io.IOException;
import java.io.PrintStream;
import model.IAnimation;
import view.AnimationTextualView;
import view.ITextualView;

/**
 * Demonstrate the textual Controller, allowing user to interact with animation through the
 * control of button.
 */
public class TextualController implements Controller {
  private IAnimation model;
  private int speed;
  private ITextualView view;

  /**
   * Primary constructor for TextualController to initialize the controller by taking the model
   * which is read and constructed from the main and the initial speed for view to create the
   * animation.
   *
   * @param model the animation model
   * @param speed the speed of the animation.
   */
  public TextualController(IAnimation model, int speed) {
    this.model = model;
    this.speed = speed;
    this.view = new AnimationTextualView(model,speed);
  }

  @Override
  public void go(String outputFileName) throws IOException {
    Appendable out;
    if (outputFileName.equals("")) {
      out = new StringBuilder();
      view.render(out);
      System.out.println(out);
    } else {
      out = new PrintStream(outputFileName);
      view.render(out);
    }
  }
}
