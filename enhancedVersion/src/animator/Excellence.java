package animator;


import animator.utils.AnimationReader;
import controller.Controller;
import controller.InteractiveController;
import controller.SVGController;
import controller.TextualController;
import controller.VisualController;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import model.IAnimation;
import model.SimpleAnimation;


/**
 * Demonstrate the Simple Animation, taking user inputs to initialize the model and connect with
 * view to present any corresponding animation.
 */
public final class Excellence {

  /**
   * The main methods for the Simple animation.
   *
   * @param args the input from user.
   */
  public static void main(String[] args) throws FileNotFoundException {
    String filename = "";
    String viewType = "";
    String outputFileName = "";
    String speedAsString = "1";

    // parse command-line arguments
    for (int i = 0; i < args.length; i++) {
      if (i + 1 == args.length) {
        break;
      }
      String type = args[i];
      String input = args[i + 1];
      switch (type) {
        case "-in":
          filename = input;
          break;
        case "-view":
          viewType = input;
          break;
        case "-out":
          if (input.equals("out")) {
            input = "txt";
          }
          outputFileName = input;
          break;
        case "-speed":
          speedAsString = input;
          break;
        default:
          continue;
      }
      i++;
    }

    if (filename.length() < 1 || viewType.length() < 1) {
      throw new IllegalArgumentException("-in and -view is required.");
    }

    //READ FIlE
    BufferedReader reader = new BufferedReader(new FileReader(filename));
    SimpleAnimation.Builder builder = new SimpleAnimation.Builder();
    IAnimation model = AnimationReader.parseFile(reader, builder);

    try {
      //OUTPUT ANIMATION
      int speed = Integer.parseInt(speedAsString);
      Controller controller = null;
      switch (viewType) {
        case "text":
          controller = new TextualController(model, speed);
          controller.animate(outputFileName);
          break;
        case "svg":
          controller = new SVGController(model, speed);
          controller.animate(outputFileName);
          break;
        case "visual":
          controller = new VisualController(model, speed);
          controller.animate(outputFileName);
          break;
        case "interactive":
          controller = new InteractiveController(model, speed);
          controller.animate(outputFileName);
          break;
        default:
          break;
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("Cannot operation the animation.");
    }

  }
}