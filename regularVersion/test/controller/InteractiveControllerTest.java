package controller;

import static org.junit.Assert.*;

import actionlisteners.ButtonListener;
import animator.utils.AnimationReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import model.IAnimation;
import model.SimpleAnimation;
import org.junit.Test;
import view.ControllableVisualView;

public class InteractiveControllerTest {

  Map<String, Runnable> buttonClickedMap = new HashMap<String, Runnable>();

  @Test
  public void testGo() throws IOException {

    BufferedReader reader = new BufferedReader(new FileReader("toh-3.txt"));
    SimpleAnimation.Builder builder = new SimpleAnimation.Builder();
    IAnimation model = AnimationReader.parseFile(reader, builder);
    ControllableVisualView view = new ControllableVisualView(model, 20);

    Appendable out = new StringBuilder();
    view.render(out);
    configureButtonListener(view);

    Controller controller = new InteractiveController(model, 20);
    controller.go("");
    assertEquals(6, buttonClickedMap.size());
  }


  //use Feature to add the button to the action event
  private void configureButtonListener(ControllableVisualView view) {
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

    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    view.addActionListener(buttonListener);
  }

}