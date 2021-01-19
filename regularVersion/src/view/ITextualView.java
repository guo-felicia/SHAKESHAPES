package view;

import java.io.IOException;

/**
 * Demonstrate the Interface of Textual view, allowing user to rendering view of the simple
 * animation in txt type.
 */
public interface ITextualView {

  /**
   * Renders a model in some manner (e.g. as text).
   *
   * @param out the appendable of the output given by user
   * @throws IOException          if the rendering fails for some reason
   * @throws InterruptedException if the rendering fails for some reason
   */
  void render(Appendable out) throws IOException;

}
