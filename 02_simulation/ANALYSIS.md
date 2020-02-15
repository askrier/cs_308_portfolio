# CompSci 308: Simulation Project Design Review

### Name: Andrew Krier
02/14/2020

> This is the link to the [assignment](http://www.cs.duke.edu/courses/compsci308/current/assign/02_simulation/):


## Overall Design

* What is the high level design of each part (simulation, configuration, and visualization) and how do they work together (i.e., what behavior, data, or resources each part depends on from the others).
    * Simulation: The simulation is a grid of cells that are all taking in the states of the cells around it to make a decision about what state to be next. Each cell is of the same type of the simulation, and this is how the decision making process changes. Simulation is the machine behind the scenes of the visualization, and takes input from configuration when needed. Simulation can't start without configuration providing it a file, and is near meaningless without the visualization aspect to send all of its values to.
    * Configuration: Configuration is responsible for taking data in from a file, consolidating it into one convenient location, and getting the proper data attributes to the right places. This involves communicating with both the simulation to provide an initial condition, and the visualization to hand over important display attributes (such as shape). Configuration also ensures that the file given to it is a meaningful file and not just random characters with a .xml tag.
    * Visualization: Visualization is the group of two very important classes. VisualizationModel is the go-between for View and the actual simulation, always getting updated data, information on shapes and colors, and telling the grid to begin the simulation. View takes all that information and puts it on the computer screen, this involves managing all the button inputs, the graph, and the colors displaying all at once. Visualization wouldn't have anything to show without simulation, and gets certain aesthetic settings from the initial configuration.
    
* What is needed to add a new kind of simulation to the project (describe all parts of the code or resources that need to be changed to recognize new XML tags and new automata rules written in Java)?
    *  As long as there are no attributes that cannot be described with the current available input fields, all that would need to be changed is the SimData class to adjust for how many types of cells it has, and the cell class itself would be made to be able to implement the decision making. The rest of the infrastructure should be able to handle any type of simulation and rules handed to it as long as it is within certain criterea.

* Are the dependencies between the parts clear and easy to find (e.g., public methods and parameters) or do they exist through "back channels" (e.g., static calls, order of method call, requirements for specific subclass types)?
    * Class dependencies are pretty clear and easy to find, the assumed state of a method is private, but the public ones are almost all getters, and setters when necessary.


* What makes it readable or not (i.e., do classes and methods do what you expect and have logic that is clear and easy to follow)? Give specific examples.
    * Using class GameOfLifeCell
    * The strengths of this class's readability is how the variables and magic numbers are named, there is no ambiguity with 
    ```java
    if(previousState==ALIVE && (numAliveNeighbors<2 || numAliveNeighbors>3)){
        currentState = DEAD;
        return;
     }
    ```
    the only confusing detail is found in teh constructor where neighborArray is completely ambiguous, even when called in the one method.

* What makes it encapsulated or not (i.e., can the implementation be easily changed without affecting the rest of the program)? Give specific examples.
    * Using class GameOfLifeCell
    * This cell will only ever be called in the instance of a GameOfLife simulation, and when that does happen, it returns a class that extends the general cell, making it simpler to plug into a grid class that only generically handles cells. Also the one method in the entire class returns a void, which can assume anything about what is calling it.

* What have you learned about design (either good or bad) by reading your team mates' code?
    * Using class GameOfLifeCell
    * In reading this code I got a direct example of how a class can be effective and still only be a constructor and a handful of methods. To this point most of my classes have at least double digit classes, and this is something I am looking to change about my code. This is a great example of a class that accomplishes that.


## Your Design

* Describe how your code is designed at a high level (focus on how the classes relate to each other through behavior (methods) rather than their state (instance variables)).
    * Nearly all of the code I was responsible for is contained in the XML package and the data files for running any tests. The file name would be passed into the XMLParser, which would in turn load the parsed data into a SimData object. This would all be handled through SimData's two constructors, allowing it to have all the data at the ready when called upon by other classes. Most of the methods in SimData are either public getters or functions to set up the nested ArrayList of values. The XMLDocumentBuilder could take a given SimData class, along with the current grid, and build an XML file of the current state, it accomplished this through a few DocumentBuilder resources and specific file design.
    * Errors are being checked for throughout this whole process. The XML Parser has its own suite of errors to make sure that the file given has all the right attributes, and the SimData class has some errors to make sure that the cell values all make sense for the given design.


* Discuss any remaining Design Checklist issues within your code (justify why they do not need to be fixed or describe how they could be fixed if you had more time).
    * The only errors thrown are in the SimData and XMLDocumentBuilder classes
    * SimData errors are all either not doing anything with caught exceptions, initializing variables incorrectly or in the wrong place, and in the instance of the constructor using too many input fields.
        * Caught exceptions is something I wish I had more time to implement since that way I could be more confident that the code could handle just about any XML file, and it would help me understand classes throwing errors more. 
        * Variable initializations could literally follow what is written on the Design Checklist and be resolved very quickly. Though giving some of the variables the "static" tag proved problematic
        * The constructor could be resolved by making the constructor with the super call more private setter methods to get the other constructor under the threshold of 7 parameters allowed.
    * XMLDocumentBuilder errors that don't already show up in SimData are all just magic numbers in the same location. These values were hardcoded in order to make the addition of each attribute into the file iterative, and any more attributes being added would already force code to be changed elsewhere in the programs.

* Describe two features that you implemented in detail — one that you feel is good and one that you feel could be improved
    * Short of the magic number initializations, I am really happy with the XMLDocument builder. It is able to so concisely build an entirely functional XML file, and iteratively add all the child attributes to the root. When I originally got it to work, the class was very resistant to new attributes, but now as long as the support is there from the other classes, adding an attribute only changes the class in 3 places. This class only depends externally on SimData and Grid's formats, and even then, it only uses the primitive functionality of those classes.
    * I could definitely improve my error handling. The errors that SimData encounters either have niche workarounds (such as modulo for unexpected integers) or throw an error without doing anything else. This code manages all of its dependencies by throwing an error for something completely unexpected so that the damage doesn't go any further down the line.

## Flexibilty

* Describe what you think makes this project's overall design flexible or not (i.e., what new features do you feel it is able to support adding easily).
    * I think this project's design flexibility hinged on the capacity to handle many many different types of cells. Not being dependent on cell type or attribute allowed the grid to hold these generic classes and still pass the correct information to the visualization, and properly interpret the information from the configuration. This design can flex to many different cell rule parameters given the neighborhood.
    
    
* Describe two features from the assignment specification that you feel were made easier to implement by the design and why.
    * Random testcases were very straightforward to implement since the cell type limits were readily available and the simulation didn't have the initial conditions pre-installed.
    * XMLDocumentBuilding was very straightforward to implement since each simulation had a set number of attributes, allowing them all to be sequentially formatted and added.

* Describe two features from the assignment specification that you did not implement in detail (these can overlap the previous ones but must be discussed from this different perspective):
    * Allowing users to control a parameter of the simulation would be an interesting one to implement. I chose this one because we were already able to implement sliders into the visualization. The example of this that comes to mind is FireSpread probability. To implement this, Visualization would need another slider class, that in turn would access a setter on simulations privately held double. Changing this value would have immediate and tangible effects on the simulation displayed. The assumptions made is that the simulation has an attribute like this to modify. This does limit its flexibility when it comes to simulations like RPS that don't take any dice-rolling into account.
    * Having the XML detail what size the cells should be would also work decently well. It would only be one or two additional parameters, and scrolling is already implemented, the values given would just be passed in for width and height in the createList method of the Grid class. There would need to be error checking involved to test for appropriate values, and if the parameters are even there in the first place. The only change that might be a challenge would be if there were two parameters (width/height) since the current model assumes that for 4 sided shapes they are all squares.

## Alternate Designs



## Conclusions



