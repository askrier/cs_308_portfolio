# CompSci 308: Parser Project Design Review

### Name: Andrew Krier
03/24/2020

> This is the link to the [assignment](http://www.cs.duke.edu/courses/compsci308/current/assign/03_parser/):


## Overall Design

* Describe the overall design of the complete program:
    * What is the high level design of each part (front and back end) and how do they work together (i.e., what behavior, data, or resources each part depends on from the others).
        * Front End
            * The Slogo frame is the main window holding everything together, within it it has a workspace, based on the
            Workspace abstract class, and formalized in the Custom Workspace using the workspace factory. Each workspace
            has a collection of GUI elements that provide their own functionality. There is also Buttons and Menus 
            available to facilitate a more interactive GUI.
        * Back End
            * The parser takes in text and after removing all the comments, reads through every text string separated by
            whitespace. It then processes each piece and sends the appropriate type of command to the manager. The manager 
            builds a tree out of the commands it gets until a tree is satisfied. Once it is, the tree is executed according
            to the logic within the initial commands.
        * Interaction
            * Both sides interact by the front end giving the parser a long string of text, and the back end calling commands
            on the TurtleController object. The parser is given a TurtleController to begin with, and gives it to each 
            command it makes. The commands, when executed, call on the public methods given in the TurtleController to 
            achieve whatever it needs to.
    * What is needed to add one of the following (include all the parts of the code or resources that need to be changed):
        * New command to the language
            * In order to add a new command to a language it should first be added to the language resource file. With 
            the exact spelling of how it was mapped to the English translation, a class should be made with that name. It
            should implement the ICommand interface and have similar instance variables to the other commands (list of 
            arguments, number of max arguments, etc.).
        * New component to the front end
            * If that particular GUI element doesn't exist yet, it should be made in the element package, extending the 
            GuiElement abstract class. It should also be added to the list of GUI elements in the CustomWorkspace.
        * Feature from the specification that was not implemented
            * ASKWITH would need to have the turtle controller have a getter for each attribute of a turtle that returns
            a map of all the values with their corresponding ID's as keys. The logic following would then be executed on
            each of the values to see which turtles end up not being equal to zero. The command would then follow similar 
            functionality as the ASK command.
    * Are the dependencies between the parts clear and easy to find (e.g., public methods and parameters) or do they exist through "back channels" (e.g., static calls, order of method call, sub-class type requirements)?
        * Most dependencies are found in public methods that are to the tops of classes on the back end. Something that 
        I was wondering through this development. Are static, mutable private variables bad code? I used them as the basis
        to keeping track of variable values but don't know if it's bad code or just a weird workaround.
* Reflect on the project's APIs especially one that you did not implement:
    * What makes it easy to use and hard to misuse or not (i.e., do classes and methods do what you expect and have logic that is clear and easy to follow)? Give specific examples.
        * The massive quantity of command classes and the different names make it a little hard to follow everything. 
        The manager class gets caught up very easily in the sea of classes that could certainly be cleaned up. But for
        the most part, methods and classes, do exactly what they say they do (though I wish "execute2" had been changed 
        as a method name at some point or another).
    * What makes it encapsulated or not (i.e., can the implementation be easily changed without affecting the rest of the program)? Give specific examples.
        * There are very few instances of specific data types being requested by methods or classes, and most of them have
        try/catches implemented to safeguard against the case that they are not provided.
    * What have you learned about design (either good or bad) by reading your team mates' code?
        * The front end did a very good job in naming methods, which made reading it the first time around very
        easy for someone who wasn't involved in its development.

## Your Design

* Describe how your code is designed at a high level (focus on how the classes relate to each other through behavior (methods) rather than their state (instance variables)).
    * The back end has three central parts, the parser, the manager, and the commands. Careful consideration was made so 
    that the functionality of each of these three parts are very defined and specific. The parser is what gets text from
    the frontend, removes all commented text, then makes different commands and arguments depending on what it sees. The
    only decision the parser would ever make besides the comments is whether it is constructing a block command. The 
    commands made by the parser are each given a TurtleController object to mutate and communicate with the front end.
    Each constructed command is then sent to the manager that builds a tree of commands and arguments, and when the tree
    is complete, runs the tree according to whatever logic is within the execute aspect of each command. The execute
    of each command uses the public methods of the TurtleController class to make changes on the front end's side of 
    things.
* Discuss any remaining Design Checklist issues within your code (justify why they do not need to be fixed or describe how they could be fixed if you had more time).
    * One of the Design Checklist issues is inter-file duplication between some of the more elementary commands. 
    Something that I had been trying to fix for a while is to transition the interface for a command to an abstract
    class. This would allow me to generalize a default execution for most of the commands, remove a lot of
    duplicated code, and make implementing new commands significantly easier.
    * Exception handling is something that is imperative when dealing with a very particular parser, and this is 
    something that involved a lot of unresolved try/catches. There is already an ExceptionFeedback class included in the
    frontend that was never comprehensively implemented into the backend because not every caught exception implied an
    error. Given more time I would make sure to go through each of those instances to make sure they are handled properly
    and that the user received the proper feedback for improper commands.
* Describe two features that you implemented in detail - one that you feel is good and one that you feel could be improved:
    * Justify why the code is designed the way it is or what issues you wrestled with that made the design challenging.
    * Are there any assumptions or dependencies from this code that impact the overall design of the program? If not, how did you hide or remove them?
        * The Tell command was the first command where simply switching a turtle to a turtlecontroller wasn't the 
        straightforward solution. It was able to use BlockCommand functionality to access the values held in its 
        arguments to adjust the list to give to the turtlecontroller. This was a great instance of back end using the 
        given front end API. This code is dependent on two BlockCommand public methods as well as the tellTurtles 
        method from the turtle controller. This made a type of List necessary to be what is returned into the method
        to change which turtles were active. I hid the dependency as much as possible by making the argument a generic
        List instead of an ArrayList or anything else.
        * The comment removing feature of the Parser was introduced late in the process of getting Basic delivered. It 
        functioned by taking each line and removing anything after a '#' character. It would then concatenate all the 
        remaining line fragments into one long string that would be processed exactly the same as text was before by 
        splitting at whitespaces. This whole process could very easily be handled by a helper method or class given more
        time to complete it. There are not any introduced assumptions or dependencies in this code since it is given a 
        string, and then it returns a shorter string. 

## Flexibilty

* Describe what you think makes this project's design flexible or not (i.e., able to support adding similar features easily).
    * Flexibility in the back end comes from being able to easily add new commands. To add a command, if it is included 
    in the language documentation, is simply having it extend the ICommand interface, making a default constructor, and
    filling in each of the methods to match the desired performance. This process would be made simpler with default 
    executions with an abstract class but it is still rather straightforward.
* Describe two features from the assignment specification that you feel were made easier to implement by the design and why.
    * Since the manager builds the tree and executes the commands in different steps, logic functions were very 
    straightforward to implement. This includes the IF and IFELSE commands since they only needed to execute the commands
    in the block commands given what the result of the value is.
    * Implementing TELL and ASK were very straightforward given the contract that was made between frontend and backend.
    At the beginning of design, we decided that the interface between the two groups would be a turtle class that 
    backend could run commands on to make changes on the frontend. The only difference between running one turtle and 
    multiple was changing from a turtle class to a turtle controller for each command, and giving TELL and ASK the
    public method from turtle controller to change the active turtles. This was very easy to implement for the backend
    and was done very quickly after frontend had completed their part.
* Describe two features from the assignment specification that you did not implement in detail (these can overlap the previous ones but must be discussed from this different perspective):
    * What is interesting about this code (why did you choose it)?
    * What classes or resources are required to implement this feature
    * Describe the design of this feature in detail (what parts are closed? what implementation details are encapsulated? what assumptions are made? do they limit its flexibility?)
    * How extensible is the design of this feature (is it clear how to extend the code as designed? what kind of change might be hard given this design?)
        * The ASKWITH command was unfortunately not implemented for the complete in this project. What's interesting
        about this code is that it needs to change dependent on each turtle's independent characteristics. This would
        be accomplished if for each testable attribute there were a map returned with each turtle's ID being the key
        to its corresponding value. The following specified operations would be performed with these values, then the 
        turtles that have a value greater than zero will be made the active turtles. This only needs a lot more access
        in the turtle controller class, and adjusting the ASK command to accommodate the extra step. The assumptions are
        that the characteristics being tested are not executable commands, this would limit its flexibility since it
        would run these commands on every turtle regardless of whether or not it meets requirements. Something 
        challenging might be mutating the values once obtained, but if that is resolved, everything else should be
        straightforward.
        * Grouping is interesting because it stretches the functionality of the program in a way that particularly tests
        our team's assumptions. Something we ran into in trying to implement the TO command is that we had always hard
        coded our number of arguments, and we worked extensively in trying to get around that. I think to resolve this 
        there could be a boolean in a command that is default set to false, if true it would change the execute
        functionality and the test for enough arguments in the first command. The parser would need to be able to handle
        the added '(' and ')' characters for functionality much like it did for the brackets ('[' and ']'), and each 
        command would subsequently need the boolean added as a private variable and get a second constructor, as well as
        a second "infinite execute" method. This would certainly run into some kinks with commands that require 
        block commands (e.g. repeat, if, for, etc.). Unfortunately this would involve manually editing each command, 
        which is obviously not ideal. 

## Alternate Designs

* Describe which classes were affected by adding the multiple turtles features. Why? Is there a way to reduce that number?
    * Every single command class was impacted by the multiple turtles feature. While this sounds fatalistic, the only 
    changes that ever needed to be made were changing the privately held turtle to a TurtleController instead, as well
    as the constructor where a Turtle is given needed to change to a TurtleController. This whole mess could be easily
    avoided if an abstract class were implemented instead so that the default constructor could very easily be changed,
    leaving most of the commands exactly how they were before. The parser was also impacted, and that was in the public
    method it has to receive a turtle from the frontend (changed from a turtle to a turtle controller). The 
    makeCommand method was also updated to give each new command a turtle controller instead of a turtle.
* Describe how well (or poorly) the original design handled the project's extensions to the original specifications and how these changes were discussed and decisions ultimately made.
    * The original design short of the ASKWITH command handled the project's extensions very well, we were able to 
    maintain the separation between front and back ends throughout. The only struggle came when the backend needed more 
    information about each individual turtle than was readily available. If something was needed that wasn't yet provided,
    communication between front and back ends for that was very straightforward, and we could all rely on each other.
    This decision was all based on our extensive planning session at the beginning of the project, which proved prudent
    in some areas, but slightly shortsighted in others.
* Describe two design decisions discussed by the team about any part of the program in detail:
    * What alternate designs were proposed?
    * What are the trade-offs of all the design choices (describe the pros and cons of the different designs)?
    * Which would you prefer and why (it does not have to be the one that is currently implemented)?
        * In the initial mass production of all the math commands for basic implementation, it was weighed whether we
        should switch from commands implementing an interface or an abstract class. The decision was made to stay with
        the interface due to a time constraint for delivering for basic. This decision in retrospect could have saved a 
        lot of time if it went the other way. While functionally there would be no difference, time-wise a lot of man 
        hours were sunk in implementing the same thing in each and every command when a change could have been made 
        generally.
        * When trying to decide the interactions between front and back end, I argued for a very limited turtle that 
        only had a few basic commands, an alternative was proposed and argued for where the turtle would have slightly
        increased functionality to make commands easier to implement on the back end. I was eventually swayed from my
        position and thank goodness as well, the proposal we went with made integration so much smoother than it would
        have been. More time and effort had to be put into the turtle's functionality, and the turtle ended up being one 
        of the most complicated classes, but it totally paid off in the long run.

## Conclusions

* Describe the best feature of the project's current design and what did you learn from reading or implementing it?
    * The best feature of the project's current design that I implemented is the reflection aspect of the parser building
    commands to add to the manager. I learned a lot about how reflection can be used to develop on the fly, and what
    needs to be in place in order to support this admittedly delicate process.
* Describe the worst feature that remains in the project's current design and what did you learn from reading or implementing it?
    * The worst feature that still remains in the project is how block commands are implemented. The whole framework
    is so messy, and really needs to be entirely reimagined to be a robust piece of code.
* To be a better designer in the next project, what should you
    * start doing differently
        * Make more things into their own classes! Functionality should be restricted for each class to handle.
    * keep doing the same
        * Put lots of time in in the beginning to plan out what will happen. If our team hadn't met in the beginning
        like we did, so many hours would have been wasted along the way.
    * stop doing
        * Not doing JavaDoc comments along the way. Since there has been a 2+ week break since I last looked at any of
        the code I wrote, it has been a struggle to relearn exactly what everything does.
