package animator;


import animator.utils.AnimationReader;
import animator.utils.AnimationReaderSloMo;
import controller.Controller;
import controller.DiscreteController;
import controller.OutlineController;
import controller.SVGController;
import controller.SloMoController;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import model.IAnimation;
import model.SimpleAnimation;

/**
 * Demonstrate the Simple Animation, taking user inputs to initialize the model and connect with
 * view to present any corresponding animation.
 */
public final class ExcellenceHw9 {

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

    try {
      //OUTPUT ANIMATION
      int speed = Integer.parseInt(speedAsString);
      Controller controller = null;
      switch (viewType) {
        case "interactive":
          IAnimation model = AnimationReader.parseFile(reader, builder);
          controller = new OutlineController(model, speed);
          controller.animate(outputFileName);
          break;
        case "discrete":
          model = AnimationReader.parseFile(reader, builder);
          controller = new DiscreteController(model, speed);
          controller.animate(outputFileName);
          break;
        case "slomo":
          IAnimation modelSloMo = AnimationReaderSloMo.parseFile(reader, builder);
          controller = new SloMoController(modelSloMo, speed);
          controller.animate(outputFileName);
          break;
        case "svg":
          model = AnimationReader.parseFile(reader, builder);
          controller = new SVGController(model, speed);
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