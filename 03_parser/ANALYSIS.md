# CompSci 308: Parser Project Design Review

### Name: Andrew Krier
03/24/2020

> This is the link to the [assignment](http://www.cs.duke.edu/courses/compsci308/current/assign/03_parser/):


## Overall Design

* Describe the overall design of the complete program:
    * What is the high level design of each part (front and back end) and how do they work together (i.e., what behavior, data, or resources each part depends on from the others).
    * What is needed to add one of the following (include all the parts of the code or resources that need to be changed):
        * New command to the language
        * New component to the front end
        * Feature from the specification that was not implemented
    * Are the dependencies between the parts clear and easy to find (e.g., public methods and parameters) or do they exist through "back channels" (e.g., static calls, order of method call, sub-class type requirements)?
* Reflect on the project's APIs especially one that you did not implement:
    * What makes it easy to use and hard to misuse or not (i.e., do classes and methods do what you expect and have logic that is clear and easy to follow)? Give specific examples.
    * What makes it encapsulated or not (i.e., can the implementation be easily changed without affecting the rest of the program)? Give specific examples.
    * What have you learned about design (either good or bad) by reading your team mates' code?

## Your Design

* Describe how your code is designed at a high level (focus on how the classes relate to each other through behavior (methods) rather than their state (instance variables)).
* Discuss any remaining Design Checklist issues within your code (justify why they do not need to be fixed or describe how they could be fixed if you had more time).
* Describe two features that you implemented in detail - one that you feel is good and one that you feel could be improved:
    * Justify why the code is designed the way it is or what issues you wrestled with that made the design challenging.
    * Are there any assumptions or dependencies from this code that impact the overall design of the program? If not, how did you hide or remove them?

## Flexibilty

* Describe what you think makes this project's design flexible or not (i.e., able to support adding similar features easily).
* Describe two features from the assignment specification that you feel were made easier to implement by the design and why.
* Describe two features from the assignment specification that you did not implement in detail (these can overlap the previous ones but must be discussed from this different perspective):
    * What is interesting about this code (why did you choose it)?
    * What classes or resources are required to implement this feature
    * Describe the design of this feature in detail (what parts are closed? what implementation details are encapsulated? what assumptions are made? do they limit its flexibility?)
    * How extensible is the design of this feature (is it clear how to extend the code as designed? what kind of change might be hard given this design?)

## Alternate Designs

* Describe which classes were affected by adding the multiple turtles features. Why? Is there a way to reduce that number?
* Describe how well (or poorly) the original design handled the project's extensions to the original specifications and how these changes were discussed and decisions ultimately made.
* Describe two design decisions discussed by the team about any part of the program in detail:
    * What alternate designs were proposed?
    * What are the trade-offs of all the design choices (describe the pros and cons of the different designs)?
    * Which would you prefer and why (it does not have to be the one that is currently implemented)?

## Conclusions

* Describe the best feature of the project's current design and what did you learn from reading or implementing it?
* Describe the worst feature that remains in the project's current design and what did you learn from reading or implementing it?
* To be a better designer in the next project, what should you
    * start doing differently
    * keep doing the same
    * stop doing
