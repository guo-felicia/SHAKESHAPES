package controller;

import java.io.IOException;

/**
 * The interface of the controller of the animation, connect the animation model with view to
 * support any kinds of animations.
 */
public interface Controller {

  /**
   * Connecting model and view to create the corresponding animation given by user.
   *
   * @param outputFileName the file name that will created by the program
   * @throws IOException throws exception if the view cannot render the model.
   */
  void go(String outputFileName) throws IOException;

}
