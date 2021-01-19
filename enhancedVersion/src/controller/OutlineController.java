package controller;

import actionlisteners.ButtonListener;
import java.util.HashMap;
import java.util.Map;
import model.IAnimation;
import view.OutlineView;

/**
 * Demonstrate an outline controller, allowing user to interact with animation through the control
 * of button, with an enhanced feature that could choose between fill and outline mode.
 */
public class OutlineController extends InteractiveController {

  private OutlineView view;

  /**
   * Primary constructor of OutlineController to initialize the controller by taking the model which
   * is read and constructed from the main and the initial speed for view to create the animation.
   *
   * @param model the animation model
   * @param speed the speed of the animation.
   */
  public OutlineController(IAnimation model, int speed) {
    super(model, speed);
    this.view = new OutlineView(model, speed);
  }

  /**
   * Connecting model and view to create the corresponding animation given by user.
   *
   * @param outputFileName the file name that will created by the program
   */
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

    buttonClickedMap.put("Outline", () -> {
      view.fillOrOutline();
    });

    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.view.addActionListener(buttonListener);
  }
}
