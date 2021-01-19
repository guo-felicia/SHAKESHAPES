package controller;

import actionlisteners.ButtonListener;
import java.util.HashMap;
import java.util.Map;
import model.IAnimation;
import view.SloMoView;

/**
 * Demonstrate the Interactive Controller, allowing user to interact with animation through the
 * control of button, with an enhanced feature of slo-mo.
 */
public class SloMoController extends InteractiveController {

  private SloMoView view;

  /**
   * Primary constructor of InteractiveController to initialize the controller by taking the model
   * which is read and constructed from the main and the initial speed for view to create the
   * animation.
   *
   * @param model the animation model
   * @param speed the speed of the animation.
   */
  public SloMoController(IAnimation model, int speed) {
    super(model, speed);
    this.view = new SloMoView(model, speed);
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
    buttonClickedMap.put("Speed10 Button", () -> {
      view.speedSloMo10();
    });
    buttonClickedMap.put("Speed25 Button", () -> {
      view.speedSloMo25();
    });
    buttonClickedMap.put("Speed50 Button", () -> {
      view.speedSloMo50();
    });

    buttonClickedMap.put("Speed75 Button", () -> {
      view.speedSloMo75();
    });

    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.view.addActionListener(buttonListener);
  }
}
