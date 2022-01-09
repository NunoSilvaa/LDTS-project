# ldts-project-assignment-g1308
# Flappy Bird 


In this amazing game you will guide flappy, our little bird, through a world of obstacles. Just like in life, the game gets harder and harder and when you think you've mastered the skill of flying through pipes more and different pipes and potions will appear

This project was developed by *Daniel Rodrigues* (*up202006562*@fe.up.pt), *Nuno Silva* (*up202005501*@fe.up.pt) and *Pedro Moreira* (*up201905429*@fe.up.pt) for LDTS 2021⁄22.

### IMPLEMENTED FEATURES

- Pipe (rectangles that kill flappy if he touches them)
- Flappy Bird (flies when arrow up is pressed
- Game Views & States 
- Random Pipe Creation
- Collision Detection

<img src="https://user-images.githubusercontent.com/80840262/148662678-ba7558f1-cc9d-4430-9c2a-a5742004b644.png" width="250">    <img src="https://user-images.githubusercontent.com/80840262/148662686-ce990760-0412-47f1-ad01-561468908d9d.png" width ="250"><img src="https://user-images.githubusercontent.com/80840262/148662689-c108c05f-0dc5-45e4-ab32-e658f53d592a.png" width="250">




### PLANNED FEATURES

- Pipe (rectangles that kill flappy if he touches them)
- Flappy Bird (flies when arrow up is pressed
- Game Views & States 
- Random Pipe Creation
- Collision Detection
- Hovering Pipes
- Moving Pipes
- Coins
- Score & LeaderBoard
- Power ups
- "Power downs" - for example if you catch a donut you'll get bigger making it more difficult to go through the pipes
- Improved Graphics and actual "images" in place of characters



<img src="https://user-images.githubusercontent.com/80840262/148662762-b21bd9dc-12f6-4574-8e1f-60c106ac4c9b.png" width = "700">
<img src="https://user-images.githubusercontent.com/80840262/148662768-9043a3e7-4eb9-446a-82e7-66382cd3f963.png" width = "450">

### DESIGN

#### The different "menus"/game states SHOULD BEHAVE DIFFERENTLY DEPENDING ON THEIR STATE

**Problem in Context**

The game must have different states such as playing or pause, implementing this in one class would lead to much confusion and an inneficient code

**The Pattern**

We have applied the State pattern. This pattern allows you to represent different states with different subclasses. We can switch to a different state of the application by switching to another implementation (i.e., another subclass). This pattern allowed to address the identified problems because […].


**Consequences**

The use of the State Pattern in the current design allows the following benefits:

- We don’t need to have a long set of conditional if or switch statements associated with the various states; instead, polimorphism is used to activate the right behavior.
- There are now more classes and instances to manage, but still in a reasonable number.

### SELF-EVALUATION

Despite a late and bumpy start the project is improving day by day. It is a playable game (not very pleasant) and we hope it will become to be the best version of flappy bird we've ever played

- Daniel Rodrigues: 33%
- Nuno Silva : 33%
- Pedro Moreira : 33%
