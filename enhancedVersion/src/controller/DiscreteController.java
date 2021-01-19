package controller;

import actionlisteners.ButtonListener;
import view.DiscreteView;
import java.util.HashMap;
import java.util.Map;
import model.IAnimation;

/**
 * Demonstrate the Interactive Controller, allowing user to interact with animation through the
 * control of button, with an enhanced feature that could choose between continuous and discrete
 * animation mode.
 */
public class DiscreteController extends InteractiveController {

  private DiscreteView view;

  /**
   * Primary constructor of InteractiveController to initialize the controller by taking the model
   * which is read and constructed from the main and the initial speed for view to create the
   * animation.
   *
   * @param model the animation model
   * @param speed the speed of the animation.
   */
  public DiscreteController(IAnimation model, int speed) {
    super(model, speed);
    this.view = new DiscreteView(model, speed);
  }


  @Override
  public void animate(String outputFileName) {
    Appendable out = new StringBuilder();
    view.render(out);
    configureButtonListener();
  }

  private void configureButtonListener() {
    Map<String, Runnable> buttonClickedMap = new HashMap<String, Runnable>();
    ButtonListener buttonListener = new ButtonListener();

    buttonClickedMap.put("Start Button", () -> {
      view.startAnimate();
    });
    buttonClickedMap.put("Play Button", () -> {
      view.pauseOrResume();
    });
    buttonClickedMap.put("Restart Button", () -> {
      view.restart();
    });
    buttonClickedMap.put("Loop Button", () -> {
      view.loop();
    });
    buttonClickedMap.put("SpeedUp Button", () -> {
      view.speedUp();
    });
    buttonClickedMap.put("SpeedDown Button", () -> {
      view.speedDown();
    });

    buttonClickedMap.put("Discrete Play Button", () -> {
      view.discreteOrContinues();
    });

    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.view.addActionListener(buttonListener);
  }
}
