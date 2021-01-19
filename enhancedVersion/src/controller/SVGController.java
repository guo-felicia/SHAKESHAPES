package controller;

import java.io.IOException;
import java.io.PrintStream;
import model.IAnimation;
import view.AnimationEnhancedSVGView;
import view.ISVGView;

/**
 * Demonstrate the SVG Controller, allowing user to interact with animation through the control of
 * button.
 */
public class SVGController implements Controller {

  private ISVGView view;

  /**
   * Primary constructor of SVGController to initialize the controller by taking the model which is
   * read and constructed from the main and the initial speed for view to create the animation.
   *
   * @param model the animation model
   * @param speed the speed of the animation.
   */
  public SVGController(IAnimation model, int speed) {
    this.view = new AnimationEnhancedSVGView(model, speed);
  }

  @Override
  public void animate(String outputFileName) throws IOException {
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
