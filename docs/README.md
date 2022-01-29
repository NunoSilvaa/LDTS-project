# LDTS_T06_G01 - Flappy Bird

## Game Description

In this amazing game you will guide flappy, our little bird, through a world of obstacles. Just like in life, the game gets harder and harder and when you think you've mastered the skill of flying through pipes more and different pipes and potions will appear.
Press Arrow up to slap your wings, dodge the enemies and their different attacks and pay attentions to your life and health.
Some postion will make faster, some will make you slower so be very careful with the potions you drink.
If you want to stop the game at any given point just press esc !

This project was developed by *Daniel Rodrigues* (*up202006562*@fe.up.pt), *Nuno Silva* (*up202005501*@fe.up.pt) and *Pedro Moreira* (*up201905429*@fe.up.pt) for LDTS 2021⁄22.

### IMPLEMENTED FEATURES

- Pipe (rectangles that kill flappy if he touches them)
- Flappy Bird (flies when arrow up is pressed
- flappyBird.game Views & States 
- Random Pipe Creation
- Collision Detection
- Power-ups
- Power-Downs
- Enemies with difference
- Score
- Text based entities

<img src="https://user-images.githubusercontent.com/80840262/151600968-11a86534-44b0-434a-b710-d02e8452828d.png" width="250">    <img src="https://user-images.githubusercontent.com/80840262/151601121-9fa4d87a-5eef-4f4e-be3b-6d39bbc750b4.png" width ="250">   <img src="https://user-images.githubusercontent.com/80840262/151601250-7668daa2-a246-4e39-8381-0fc3b2187818.png" width="250">    <img src="https://user-images.githubusercontent.com/80840262/151601342-e56438a4-2275-4d23-8dd7-adf014d02a6f.png" width="250">    <img src="https://user-images.githubusercontent.com/80840262/151601586-7422f202-edf8-47f0-bf54-72af81c59452.png" width="250">




### PLANNED FEATURES

- Pipe (rectangles that kill flappy if he touches them)
- Flappy Bird (flies when arrow up is pressed
- flappyBird.game Views & States 
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

## Design



### Game - State Pattern
#### Problem in Context:  
Our game required at least three different states for the Game. A Starting state where the user can quit or play, a Run state where the game per say lives and an end Screen with the Score. It was also added a pause screen in case our user wants to stop the game to go grab something to eat.

#### The Pattern:
We have applied the **_state pattern_** which is a behavioral design pattern that lets an object alter its behavior when its internal state changes. It appears as if the object changed its class.

#### Implementation:
Our object changes states according to user inputs. By choosing the play option the game will get into run state, if you're already in run state and you press ESC you'll find yourself in pause screen. Some states are called when our flappy bird changes it state, for example if the bird dies you'll be greeted with the game over screen.

These classes can be found in the following files:
- [Game](../src/main/java/flappyBird/game/Game.java)
- [GameStates](../src/main/java/flappyBird/controls/gameStates/)

#### Consequences:
Some consequences of using the stated pattern:
- Simplify the code of the context by eliminating bulky state machine conditionals.
- Single Responsibility Principle. Organize the code related to particular states into separate classes.


The use of these patterns in the current design allow the following benefits:
- The several states that represent the different menus become explicit in the code, instead of relying on a series of flags.
- A well organized code acknowledging the Single Responsibility Principle.
- Easy to add new features throughout the development stage.





### Arena - State Pattern
#### Problem in Context:  
When the bird "eats" a powerup it can make the bird faster or slower. Since we're playing from the reference point of the bird the bird has no speed along th x axis, everything else moves and the bird remains static. Therefore if we want to make the bird faster what we actually need to do is make everuthing else in arena faster. To handle that we have different arena states.

#### The Pattern:
We have applied the **_state pattern_** which is a behavioral design pattern that lets an object alter its behavior when its internal state changes. It appears as if the object changed its class.

#### Implementation:
Our object changes states according to wich power our bird "eats"

<p align="center" justify="center">
  <img src="https://user-images.githubusercontent.com/80840262/151604485-c858993f-0fcc-4c94-b832-c7ef13ec9a2f.png" width="250">
</p>
<p align="center">
  <b><i>Fig 1. Arena States</i></b>
</p>

These classes can be found in the following files:
- [ArenaStates](../src/main/java/flappyBird/game/states/)
- [Arena](../src/main/java/flappyBird/game/Arena.java)

#### Consequences:
Some consequences of using the stated pattern:
- Simplify the code of the context by eliminating bulky state machine conditionals.
- Single Responsibility Principle. Organize the code related to particular states into separate classes.






### Entities - Factory Method & Abstract Factory
#### **Problem in Context:** 
Most of the entities you see on the screen share a lot of atributes, such as position, how their're drawn and represented. 

#### The Pattern:
We have applied the **Factory Method** and  **Abstract Factory** both Creational Patterns.
On one side the Factory Method is a creational design pattern that provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created and on the other the Abstract Factory is a creational design pattern that lets you produce families of related objects without specifying their concrete classes.

#### Implementation:
Trivial Implementation of both methods without meaningful variations

<p align="center" justify="center">
  <img src="https://user-images.githubusercontent.com/80840262/151605642-10c5f2d3-f0c6-4ecf-b50c-0abb0b5a1e85.png" width="250">
</p>
<p align="center">
  <b><i>Fig 2. Enemies</i></b>
</p>


<p align="center" justify="center">
  <img src="https://user-images.githubusercontent.com/80840262/151606754-3eda5287-7341-4327-8bdb-e1ccc61c29f9.png" width="250">
</p>
<p align="center">
  <b><i>Fig 3. Powerups</i></b>
</p>

These classes can be found in the following files:
- [Entities](../src/main/java/flappyBird/entities/)

#### Consequences:
The Command Pattern allows the following consequences:
-  You can be sure that the products you’re getting from a factory are compatible with each other.
-  Open/Closed Principle. You can introduce new variants of products without breaking existing client code.





### Bird - Singleton
#### Problem in Context:
At any given time there can only be one object bird in arena, and all interactions need to be made with that bird.

#### The Pattern: 
We have applied the **Singleton** pattern, a creational design pattern that lets you ensure that a class has only one instance, while providing a global access point to this instance.

#### Implementation:

- [Arena](../src/main/java/flappyBird/game/Arena.java )


#### Consequences: 
The use of the Facade Pattern in the current design allows the following benefits:
- You can be sure that a class has only a single instance.
- You gain a global access point to that instance.
- The singleton object is initialized only when it’s requested for the first time.


## Known Code Smells And Refactoring Suggestions
#### **Large Class**
Some classes (e.g. Game, Arena) contain many fields and others contain many methods. In both cases, we find it justifiable as the classes require these fields, in one hand the Game class is the main class of the program and it needs to store a considerable amount of data, on the other hand various methods are needed for the interface and it wouldn't make sense to split it into two separate ones (extract method).

#### **Feature envy and message chains**
The class Move for example decides to make use only of features not implemented in itself

## Testing

### Screenshot of coverage report
<p align="center" justify="center">
  <img src="https://user-images.githubusercontent.com/80840262/151610326-461fe3f5-c96c-4449-afdf-53470adf7242.png" width="250">
</p>
<p align="center">
  <b><i>Fig 4. Pitest Mutation Test </i></b>
</p>

<p align="center" justify="center">
  <img src="https://user-images.githubusercontent.com/80840262/151610519-2c52f4f9-3103-48e6-85d5-6d8a38e947f7.png" width="250">
</p>
<p align="center">
  <b><i>Fig 5. Test Coverage </i></b>
</p>

### Link to mutation testing report
[Mutation tests](../docs/Pitest/pitest/202201281956/index.html)

### SELF-EVALUATION

Despite a late and bumpy start the project is improving day by day. Tested throughly ( with the exception of getters and setters ) our project is now a playable game with a very decent amount of fearures

- Daniel Rodrigues: 33,3%
- Nuno Silva : 33,3%
- Pedro Moreira : 33,3%

