package view;

import java.io.IOException;

/**
 * Demonstrate the Interface of SVG view, allowing user to rendering view of the simple animation in
 * svg type.
 */
public interface ISVGView {


  /**
   * Renders a model in some manner (svg).
   *
   * @param out the appendable of the output given by user
   * @throws IOException          if the rendering fails for some reason
   * @throws InterruptedException if the rendering fails for some reason
   */
  void render(Appendable out) throws IOException;

}
