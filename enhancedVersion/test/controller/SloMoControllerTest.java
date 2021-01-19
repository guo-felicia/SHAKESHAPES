package controller;

import static org.junit.Assert.assertEquals;

import actionlisteners.ButtonListener;
import animator.utils.AnimationReaderSloMo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import model.IAnimation;
import model.SimpleAnimation;
import org.junit.Test;
import view.SloMoView;

/**
 * Represents tests for the slo-mo controller.
 */
public class SloMoControllerTest {

  Map<String, Runnable> buttonClickedMap = new HashMap<String, Runnable>();

  @Test
  public void testGo() throws IOException {

    BufferedReader reader = new BufferedReader(new FileReader("toh-3.txt"));
    SimpleAnimation.Builder builder = new SimpleAnimation.Builder();
    IAnimation model = AnimationReaderSloMo.parseFile(reader, builder);
    SloMoView view = new SloMoView(model, 20);

    Appendable out = new StringBuilder();
    view.render(out);
    configureButtonListener(view);

    Controller controller = new SloMoController(model, 20);
    controller.animate("");
    assertEquals(8, buttonClickedMap.size());
  }

  private void configureButtonListener(SloMoView view) {
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
    view.addActionListener(buttonListener);
  }
}