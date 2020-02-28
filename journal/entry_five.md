# Weekly Reflection
### NAME
Andrew Krier
### DATE
02/28/2020
#### Interesting Notes

##### Thing #1
Getting to know reflection will really enhance the capabilities of my code. Enabling it to run new objects on the fly 
with just strings, or and undefined object will allow for much smoother transitions between different classes. The only
issue with this is reflection's propensity for introducing tons of errors, and that for bigger problems it starts to bog
down the processes of everything.

https://www.oracle.com/technical-resources/articles/java/javareflection.html

This was a very helpful resource in actually implementing the class reflection in my code.
##### Thing #2
From the start of the planning process, once we had decided to organize a command-based framework, both Vineet and I 
knew that block commands would be a huge step in being able to have full functionality of our code. This is something 
Vineet and I focused on as soon as we had the tree structure and just a few commands figured out since so much was 
contingent on this one feature. We sat down and got to work, pair programming late in the link until it was able to work.
This was a great experience for collaborative coding as we both came up with multiple potential solutions until we arrived
at something that actually worked.

#### Summaries

##### Thing #1
Early on, our tree was structured in a way that if a command was satisfied, it would run immediately and disappear from 
the tree. This worked very well initially, but we quickly found a flaw in that it could not run the same command more
than once without making a new instance, and it couldn't make any decisions on what to run and when. This made commands 
such as REPEAT, IF, IFELSE, and FOR virtually impossible. We discussed potential remedies and landed on changing how the 
tree was processed from one motion of both constructing and executing to two separate motions of constructing the full 
tree, then conditionally running the tree. This format worked very well in the long run, even supporting FOR among other
challenging commands later in the process.

##### Thing #2
Reflection for me had a very high barrier to entry especially since I was trying to implement it before it was taught in
class. I leaned heavily on the tutorial linked above, as well as my team's simulation code where someone had also 
implemented reflection. I learned that its functionality doesn't vary all that much between abstract classes and 
interfaces (which was good since commands were formatted by an interface and not an abstract class). The parser benefited
heavily from being able to use reflection, and it is definitely something I will look to get more comfortable with in 
the future.

#### Reflection

##### What was difficult about your work this week and why?
Working to get many different things to all work together in the same way
The most difficult part of this week was getting so many different objects to all properly implement the ICommand 
interface. There were so many different commands to keep track of, especially since arguments, variables, and block 
commands also functioned as commands in the tree. Keeping all of the methods, arguments, and functionality consistent
across commands and between developers was also very difficult.

##### What were the most important things you learned?
Creating a solid framework for something you are going to implement multiple times in a row is very important
There were some instances in command development that highlighted how shortsighted our interface design was. The issue
came when trying to change this interface and needing to fix a ton of different commands that were already built. I think
that solutions to this issue could either be more methodical in planning, or use an abstract class instead so that some 
functionality is assumed but can still be overridden.

##### How will this learning change your work next week?
Focus more time on the interfaces and potentially abstract classes before diving into specifics

In order to make the code more solid I might attempt to switch the commands to implementing an abstract class instead of 
an interface, and will put more planning into the API's before coding many many instances of those objects.