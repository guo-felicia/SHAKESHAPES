package controller;

import model.IAnimation;
import view.AnimationVisualView;
import view.IVisualView;

/**
 * Demonstrate the Visual Controller, allowing user to interact with animation through the control
 * of button.
 */
public class VisualController implements Controller {

  private IAnimation model;
  private int speed;
  private IVisualView view;

  /**
   * Primary constructor for VisualController to initialize the controller by taking the model which
   * is read and constructed from the main and the initial speed for view to create the animation.
   *
   * @param model the animation model
   * @param speed the speed of the animation.
   */
  public VisualController(IAnimation model, int speed) {
    this.model = model;
    this.speed = speed;
    this.view = new AnimationVisualView(model, speed);
  }

  @Override
  public void go(String outputFileName) {
    Appendable out = new StringBuilder();
    view.render(out);
  }
}
