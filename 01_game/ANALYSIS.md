# CompSci 308: Game Project Design Review

### Name: 
Andrew Krier

## Status

I spent the most time on designing the interface of an individual modular level, this went decently well as I planned 
things out well and figured out what things needed access to what information. I spent the most brain energy towards the
end on figuring out how to switch between different levels and the menu and end screens. Lots of the debugging I did
was with regard to what class was handling the different operations which I think would be a lot smoother if I had a more 
comprehensive plan.

I tried to avoid duplication in my code as much as possible, and as far as I can tell succeeded to some degree. There 
were a couple const variables that were declared for multiple classes that potentially could've been worked around but 
in the time I spent working on it I did not see a reasonable and straightforward solution. Another instance of duplicate 
code is in the Level class where each of the objects except for the blocks were called individually both in initializing
them as well as the LevelStep and keyInput methods. This redundancy could have been addressed by making a collection of 
shapes but I did not implement that in the given time.

The most glaring bug still remaining in the program is one I frankly don't know how to solve at the moment. It's the 
issue of taking in multiple key inputs at the same time. This prevents both players from making simultaneous inputs, and 
really effects the play of the game if one doesn't choose to rapidly press each button for their paddle's movement.

In the Ball class there is a method called "handleKeys" that manages the players' inputs from the keyboard for the ball 
object. If the game has not yet started, the ball moves left and right in tandem with the paddle towards the bottom of 
the screen. It also waits for the spacebar to be pressed to start the ball moving as well as turn a boolean called 
hasStart to true so that it will no longer respond to the bottom paddle's inputs. "handleKeys" also controls all of the 
cheat keys that it is always testing for since it either will affect the upcoming/current match, or will reset back to 
the middle of the screen regardless. I think this method is easy to read in its decision making, but not in what the 
responses to those decisions are. The if logic of "not has start" seems pretty intuitive, and past that the key inputs 
are the only things that make any sort of logic decisions. The motivation for the ball to move left and right if the game 
hasn't started is not clear, and nor for triggering the cheat keys. These functions would definitely need comments to be 
understandable in their current state. One of the things I did very poorly for this project is I did not make frequent 
nor very descriptive GIT commits. This means that the updates to this particular method are tough to find. The origins 
handleKeys is in the "two players" commit where player interaction was first introduced. The first cheat key 'V' was added
in the Level as a class commit to help with debugging and clearing the levels faster, then the functionality to move before
the game started and the other two cheat keys were added in the "all but files" commit. Evidently there was no rhyme or 
reason beyond this is what needed to get done for the commit messages and when they were executed. This pertains for the 
next method as well.

The "endOfGame" method in the Level class was far and away the easiest level to implement. Once I got the call down it 
consisted of filling a blank screen with one of two messages, either player one won, or player two won. This method is 
easy to read because the first thing you see is the decision that it makes, then the rest of it is placing that message
in the proper location on the screen. Each of the numbers used (other than a divide in half) are justified final ints 
and the whole thing is only eight lines long.

In my first few commits each method had a laid out and direct purpose for advancing the program and what exactly it did. 
Through hasty code additions to implement new options this became significantly more convoluted. In the future I will
be sure to start earlier and allocate my time to making sure my code is legible along the way instead of looking back in
utter confusion of what exactly happened to everything.

## Design

A new level can be added by making two new text files, adding two more lines to read them in in the Level class, then 
another line to add them to the array in the int[][][][] fullLvl. Finally all the instances of 4 in InitialScreen should
be updated to 5 to accommodate for the new level (or could all be switched to an int that is 1+num of levels, making it 
more responsive if this happens multiple times).

My code is not very shy at all, the main driver of the program InitialScreen calls so many specific things from its 
dependent classes since most of the things are either the classes themselves or a specific type of argument (ex. Group 
returned from the levelBegin method). This is not something I focused on in my design and often used it as a crutch to 
deliver the necessary data that I needed to the methods that wanted it.

* One of the features I implemented was giving each player two paddles to manage simultaneously. I implemented this 
immediately after I got one paddle to work effectively since it would just add another paddle to the group (necessary 
regardless since this is a two player game) and the only other functionality needed was deciding if it was a floater or
not and assigning the proper keystrokes to it. The roamer was a certain type of bouncer class, and needed to edit the 
collision calculations for the ball since I didn't want the ball rebounding back on a player when hitting their own 
paddle. The assumptions made is that the behavior and speed would stay the same as the original anchored to the baseline
as this is part of the stated program design. This feature was changed little if at all throughout the process and is 
one of the more consistent pieces of code I wrote for this project.
* A second feature I implemented was damaging all of the opponent's blocks when getting the ball past both of their 
paddles. This was easiest to implement once I had put all of the blocks into an ArrayList that I could iterate through 
and call the damage() method on each one. The assumptions made were that no blocks would be spared from this damage, and
that it would only ever be a damage of one health. This design did not change much until I needed to figure out how to 
trigger an end of level event, then I needed to update and remove blocks from the ArrayList, and this impacted the 
performance of this particular feature. However, once the damage() method was operational again it was back up and 
running. The original implementation of this feature initially made it so that removed blocks would still be present on 
the screen, but this was fixed by specifically selecting which blocks would be removed from the list.

The main driver of the program InitialScreen manages everything. First it calls a Level which instantiates all the paddles, blocks, 
and ball, then Level sends back to InitialScreen which advances a step for all the objects and monitors key inputs, 
letting each of the objects to decide for themselves how to handle that. Changing the block layouts, colors of all 
objects, and changing the pace of the game should all be moderately straightforward.

## Alternate Designs

Lots of my design decisions were centered on how much work I wanted to delegate to classes, and when I did, what 
specifically I would let them do. This made some functions tricky with finding what objects had the proper permissions 
to access certain things but it usually worked out better when I delegated more to other classes.

This is something I wanted to focus on in my game design, I made sure that InitialScreen wasn't an extremely long mess 
of code. In doing so I basically shifted the blame to the Level class for being super weedy and dense. If I were to do 
this over again I would try and focus on the Level implement to make sure that it calls necessary helper classes to make 
it more readable and less bug-prone.

I considered for a while whether Bouncer and Block should be in the same class and looking back I am very glad I took
the time to make them separate. Each class ended up being so unbelievably different on how they implemented different 
processes and interacted with other objects that the big class would've been unreadable by the end. This doesn't even
mention how convoluted those constructor calls would've been to make each object different, this decision was far 
reaching and as far as I can tell it has been the right one so far.

When I was planning out my game class by class I thought it would be much easier if each level were a different class. 
I reasoned that InitialScreen could just call three different constructors and be done. This was the prevailing thought 
until I got to the end of making the first level all in InitialScreen. I realized that there was no good reason to have 
so much duplicated code for what could really be something as simple as an iterated number. This proved to do well in 
the end since I was able to implement any variation of level I wanted and still maintained some form of sanity in my 
code.

One of the biggest regrets I have is not figuring out how to navigate between levels and screens be the first thing I 
did. This was such a hassle to have to deal with at the end and a lot of my code changed because of it. I am to this 
point happy with how I allocated jobs to different classes, though obviously it stands to improve considerably. New 
features are not seamless to add, but it's always clear where they should be implemented and what needs to change because
of it. 

## Conclusion

The best thing I learned in this project is building something organized from start to finish. Before this I never truly
understood any of the CS 201 frameworks given to me, I just knew how to plug away at methods until things started working
properly. This definitely gives me more confidence to try and implement more projects by myself to see just how far I 
can push my abilities. The worst thing that happened in the project was a very big obstacle in the beginning. I had 
Block extend Rectangle since it had a lot of functionality I wanted as well, little did I know that IntelliJ picked the 
wrong package to extend from so I was so confused why the methods that I wanted weren't showing up. By the time I 
figured out the issue so much time had been lost in trying to accommodate this incorrect class extension. 

To be a better designer I should put in more time and thinking upfront. I really want to get as deep as I can into the 
depths of a program's requirements before even getting close to a .java file. I am going to keep my classes minimalistic
and my methods as basic as possible. I'm going to stop ignoring the importance of naming things until the very last minute
when I'm trying to figure out what things are only to find a variable called "number" stashed somewhere in my code.

