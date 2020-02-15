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


'''java
    if(previousState==ALIVE && (numAliveNeighbors<2 || numAliveNeighbors>3)){
        currentState = DEAD;
        return;
     }
'''

* What makes it encapsulated or not (i.e., can the implementation be easily changed without affecting the rest of the program)? Give specific examples.
    * Using class GameOfLifeCell
    

* What have you learned about design (either good or bad) by reading your team mates' code?
    * Using class GameOfLifeCell
    


## Your Design



## Flexibilty



## Alternate Designs


## Conclusions

