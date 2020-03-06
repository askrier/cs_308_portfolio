# Weekly Reflection
### NAME
Andrew Krier
### DATE
03/06/2020
#### Interesting Notes

##### Thing #1
Vineet and I went to office hours to try and finish TO implementation once and for all. The TA looked at what we were 
doing and gave us largely the same assessment as we had done ourselves, that our framework was particularly poorly 
suited for TO in particular. He gave us some guidance on what decisions could be made to progress from there and we 
pair programmed that and Tell for the rest of the night.

##### Thing #2
In the code for tell, since I didn't know how long a block command's arguments are generally, I had it return the value 
at an index until it ran into a nullPointerException. This was not the most particularly beautiful resolution to this 
problem, and that showed when Vineet and I refactored that part of the code. We instead had Tell and every other command 
with blocks for arguments extend the BlockCommand class so that it could have access to protected methods. This 
solution was one of the stronger results of our late-night pair programming session.

#### Summaries

##### Thing #1
One of the bigger decisions for the week was deciding to have all commands that have a block command as an argument 
extend the block command object to get access to protected variables and methods. This was a suggestion given to us by
an office hour TA, and we weighed the consequences of making this change and decided to move forward with it. This 
change has allowed us to give specific methods partial but necessary access to parts of the block command. The 
alternative to this would have been making a bunch of methods in BlockCommand generally public, which is definitely 
something we are trying to avoid at all costs for this project.

##### Thing #2
One design decision that was made early on was making sure that the parser only processed the current string bit, and
passed the resulting command type to the manager for further processing and execution. It definitely led to some tougher
design problems, but overall made our code more independent instead of having a parser thrown together as a mix of tons 
of classes and if statements. We did a similar thing for the manager and making sure it never got a Turtle (eventually
TurtleController) class, instead passing it each time through the commands themselves. This allowed the manager to focus
on its own node structure and not concern itself with what has which Turtle. Both these decisions, while in the moment
were tough to deal with, have made our code that much more robust.

#### Reflection

##### What was difficult about your work this week and why?
So much time and effort has been poured into implementing the TO command that at some points it has seemed reasonable to 
let it go and focus on other functionality. Many hours have been spent looking over every aspect of our setup, and it 
only gets harder and harder to make progress the closer one gets to the finished product.

##### What were the most important things you learned?
I learned just how important establishing an inheritance plan is for managing a large project with many different 
instances inheriting the same thing. All of the commands to this point implement an Interface class that was designed 
primarily for the API. In hindsight this would've been much better served as an abstract class so that we could have more
assumed default methods with the private variables already largely in place. If this were the situation we had, some 
commands (such as the arithmetic operators) would only be a couple dozen lines for the whole class instead of a 
guaranteed 60 lines per class.

##### How will this learning change your work next week?
Next week (the week after spring break) will be the start of the last and longest project. I have been painfully
learning all semester how vitally important planning is to these projects in anticipating everything that it should need
to do and then some, and next week would be the best chance I have to show that I've learned from my past mistakes. Once
we have the green light I want to sit the whole team down and just brainstorm, chat, discuss, and improve a comprehensive 
design before anyone even looks at IntelliJ. 