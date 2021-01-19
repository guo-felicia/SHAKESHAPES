package view;

import java.io.IOException;

/**
 * Demonstrate the Interface of Visual view, allowing user to rendering view of the simple animation in
 * visual type.
 */
public interface IVisualView {

  /**
   * Renders a model in some manner (e.g. graphics, etc.).
   *
   * @param out the appendable of the output given by user
   */
  void render(Appendable out);

}
