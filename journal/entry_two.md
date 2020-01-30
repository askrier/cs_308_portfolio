# Weekly Reflection
### Andrew Krier
### January  30th, 2020

#### Interesting Notes

##### Thing #1:
The team dynamic seems to be working well so far. I was unfortunately unable to make 
the first out of class meeting that involved assigning responsibilities for the project.
I still made sure I contributed as much as possible before the plan deadline. The group
chat we made to communicate has seen seemingly equal amounts of participation, which if
that can continue I think the team will be in good shape for the future.

##### Thing #2:
When organizing how each of the components of the code would work together, we as a team
found drawing a diagram of dependencies and flow to be quite valuable to the development
process. This actually aided me in my ECE/CS 250 class where I was stuck on a problem
in C, and resolved it by drawing out a diagram. This is certainly something I will 
continue to address problems with in the future.

#### Summaries

##### Thing #1:
There was a design decision early on to make the cells inherit from rectangles for the 
implementation. This however did not last very long as we quickly realized that 
rectangles are not the only shape that can tessellate. This means that I will be 
working to format the XML file to also be able to support triangles, hexagons and
the like. It also means that whoever implements the shapes should have any reference to
it inherit from a shape class to make room for the polygon extension.

##### Thing #2
I read the GIT ready document assigned to us, and it really did not clarify anything for 
me when I needed help figuring out how branches worked. This led me so seek documentation
online, and to ask a teammember who has worked extensively with GIT for assistance. 
This ended up being far more useful and straightforward than the tutorial for me personally.

#### Reflection

##### What was difficult about your work this week and why?
The most difficult part of work this week was finding time to put the analysis together
and to meet with all the group members. (I'm not sure if the analysis counted as the 
last journal or not, but I'm going to discuss it anyways) When looking at the documents
describing the requirements for analysis I did a really poor job of evaluating how long
it would be and how much time it would take. Needless to say starting Friday afternoon
was not the best strategy for a stress-free completion, but I now know for the future.
Our simulation group has four people in it, and managing all the different calendars to
get all of us in the same room has been difficult, so we have been settling with meetings
with only two or three of us then communicating in the group chat. This is working
to this point, and I hope the high communication continues throughout the project.

##### What were the most important things you learned?
The most important thing I learned continues to be to start work sooner than I think
is necessary. This time around I am actually trying to act on it by having my teammates
hold me accountable to some extent. They know what I need to deliver to the team by when,
and having those parts have upcoming deadlines keeps me honest. This isn't constant 
monitoring, but it is certainly working better than before.

##### How will this learning change your work next week?
I want to have more confidence in my actions and my code. Now that I'm working and
interacting with other people that depend on my code, it is very intimidating to start
work on something when I don't fully understand how branches work. This has lead me to
explore the documentation on the internet, and to ask for help when I need it. Both
of these have helped me improve as a coder and I look to continue it into the next week.