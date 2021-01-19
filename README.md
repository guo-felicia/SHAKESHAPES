# SHAKESHAPES
A java animator application

##Regular Version:
An interface for a view, which is directly implemented by the text, visual and SVG views. Interaction, or ''back-and-forth between the user and the program" makes the role of a controller more well-defined and non-trivial than it may have seemed in the previous assignment. Like views and models, controllers are best described as an interface whose purpose is to mediate the interactions between the view and the model. Multiple implementations of controllers are possible --- potentially a specialized one for every model/view pairing. 


##Enhanced Version:

###Level 1: New shape and UI improvements
- A new shape: plus sign is supported to draw out or move or scale.
- A new UI feature is introduced. One should be able to toggle the drawing of all shapes in ''fill" vs ''outline" mode. In outline mode, each shape should be drawn only using its outlines (the plus symbol should have its proper outline, no ''square in the middle). The color of the outline should match the color of the shape, but you can use any suitable value for the thickness of the outline.

###Level 2: Discrete-time playing of animation
- A discrete way of playing your animation in the interactive view. The view will display only the frames at the start and the end of any motion and the frame could show the correct interpolated states for those shapes at a suitable tempo.

###Level 3: Slo-mo animation

- The ability of an input file to take in the slow-motion information. Specifically you will have to specify several (time-interval: tempo) instances for an animation.
- The ability of your program to play the animation in slow motion according to the inputs, in your interactive view. You do not have to support this feature for the other views, including the visual view.





##Design choices:

###Split the main and controller
- In our main, it basically does two jobs: 
 -- <1> Read the input file then build the model and view. 
 -- <2> Create a corresponding controller class according to different '-view' type (and pass in the modified model and view into that specific Controller), finally invoking the controller for rendering the animation. 

###Create an Interface of controller 
- Design different controller class corresponds to different view types (e.g. textual, svg, graphic and interactive animation etc.)
- Notes: InteractiveController is not an independent class but extends the visualViewController since they based on same functionality. The InteractiveController eliminates any mention of Swing-specific events, and generalized it further so that the controller did not need to depend on the Swing library at all. It create, accept and add each actionListener calling individual method in the view class.

###Each type of view is an individual view interface.
- The view interface has one-to-one relationship of the controller class.
- Notes: the interactiveView class (ControllableVisualView) extends the animationVisualView and use the same JPanel class as animationVisualView.
 -- it override the setUp() method and actionListener method() in animationVisualView to fulfill the requirements of new interactions from user.

###Tips for interaction view
- when the Panel is as large as our screen, the Controller button panel cannot be fully expanded which makes the button not clickable. In order to play with the button, please make sure you drag this animation window to a large screens and fully expand the control button panel. Thanks.



##Class Design 

####Animator Package
- class `Excellence` serves as `main()`
    - read a given file using class `AnimationBuilder`
    - output a chosen view class `AnimationReader`

####Model Package
- interface `IAnimation`
- class `SimpleAnimation` implements `IAnimation`
    - fields
        - `Map<String, IShape> shapes;`
        - `Map<String, List<Motion>> motions;`
        - `boolean started;`
        - `int canvasTopLeftX;`
        - `int canvasTopLeftY;`
        - `int canvasWidth;`
        - `int canvasHeight;`

####Controller Package
- interface `Controller`
- class `TextualController` implements `Controller`
- class `SVGController` implements `Controller`
- class `VisualController` implements `Controller`
  - class `InteractiveController` extends `VisualController`

####View Package
- interface `ITextualView`
- class `AnimationTextualView` implements `ITextualView`
- interface `ISVGView`
- class `AnimationSVGView` implements `ISVGView `
- interface `IVisualView`
- class `AnimationVisualView` implements `IVisualView`
  - class `ControllableVisualView` extends `AnimationVisualView`
- common field : model, speed
- common method: render(Appendable out) throws IOException;


####Shape Package
_**Purpose**: create different tags for a shape_
- interface `IShape`
    - methods to create different tags
- abstract class `AShape` implements `IShape`
    - class `Rectangle` extends `AShape`
    - class `Ellipse` extends `AShape`
    - more concrete shape classes could be added

####Drawable-Shape Package
_**Purpose**: draw a shape as 2D graphics_
- interface `IDrawableShape`
    - method `draw()
- abstract class `ADrawableShape` implements `IDrawableShape`
    - class `DrawableRectangle` extends `ADrawableShape`
    - class `DrawableEllipse` extends `ADrawableShape`
    - more concrete shape classes could be added

####Motion Package
_**Purpose**: record the end state of a motion without mentioning any specific shape_
- interface `IMotion`
    - `Motion makeCopy();`
    - `Motion retrieve(Motion lastInterval, int tick)`
    - `Motion changePosition(int newTick, int newX, int newY)`
    - `Motion changeSize(int newTick, int newW, int newH)`
    - `Motion changeColor(int newTick, int newR, int newG, int newB)`
    - `Motion freeze(int newTick)`
- class `Motion` implements `IMotion`



##Create Animation
####Programmatically-Created
Mergesort Visualization
- visualize the mergesort algorithm
- support comparison (when two bars blink) and order (when bars change position)
- to sort a new array of integers, let the creator take in that array and run the main method as an entry to the program
####Manually-Created
Traffic Visualization
- visualize the traffic on a road and cars' responses to the traffic light
