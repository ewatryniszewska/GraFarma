# Farm simulator - Gra Farma
> "Farm simulator" is a simple text game that simulates some farm-life nuances.
> In this game, you are going to manage a farm with animals and plants. Your goal is
> to become an ideal farmer as soon as possible. To make it, you must have
> at least 20 ha of a land, at least 5 types of animals, at least 5 types of plants,
> and at least 1M PLN. The game implements single-computer multiplayer mode!

## Table of Contents

* [Farm simulator - Gra Farma](#farm-simulator---gra-farma)
  - [Table of Contents](#table-of-contents)
  - [General Information](#general-information)
  - [Technologies Used](#technologies-used)
  - [Features](#features)
  - [Gameplay](#gameplay)
    + [Buy a farm](#buy-a-farm)
    + [Buing and selling land, buing buildings](#buing-and-selling-land-and-buildings)
    + [Buing and selling goods](#buing-and-selling-goods)
  - [Setup](#setup)
  - [Development](#development)
  - [Project Status](#project-status)
  - [Room for Improvement](#room-for-improvement)
  - [Contact](#contact)


## General Information
This program was made for the "Java Programming" subject during my 4th semester.
The idea was to create a simple, text game that simulates some basics of country
life. This is supposed to be easy to play, but not too easy to win, so the player
would not be bored quickly. Hopefully, I managed this task and the game will bring
you a lot of joy!

**Have fun!**


## Technologies Used
- JAVA 15
- JDK 15 (Oracle OpenJDK 15.0.2) for development


## Features
List the ready features here:
* Planting
* Animal breeding
* Animal reproduction
* Animal exchange
* Plants and seedlings exchange
* Buildings and farms announcements and buying process


## Gameplay
In the beginning, you are going to be asked about the number of players. Then
type your names. Before and after every action the menu is displayed.

At the very top, there's a name of the player that currently is making a move.
Also at the top, there's a week counter, cash, and the number of owned farms.

At each round (week) you can do any of the actions:
* Buy a farm,
* Buy a land,
* Sell a land,
* Buy buildings,
* Buy plants or animals,
* Plant plants,
* Crop plants,
* Sell plants or animals,
* Get info about player's farms,

You can make any number of actions during your round. To select the action type
its number and press enter.
To end the round type 0.

```
-------------------------------------------------------
Teraz gra: Ewa
-------------------------------------------------------
Calkowita liczba tygodni: 22                12000000 zl
Tydzien 22 roku 2020                Posiadane farmy: 10
-------------------------------------------------------
Menu:
1.   Zakup farmy.
2.   Zakup ziemi uprawnej.
3.   Sprzedaz ziemi uprawnej.
4.   Zakup budynkow.
5.   Zakup zwierzat lub roslin.
6.   Posadzenie roslin.
7.   Zbiory roslin.
8.   Sprzedaz zwierzat lub roslin.
9.   Informacje o farmach.
10.  Zasady gry.
123. Zakoncz gre.
0.   Zakonczenie tygodnia.
```

At the end of each week following actions will be made:
* animals
  - aging
  - eating
  - getting/losing weight
  - producing products (eggs, milk, etc.)
  - breeding/reproduction
* plants
  - grow
  - plant protection costs
* if there's not enough food for animals - animals lose weight or die
* if there's not enough money for plant protection - crops might be damaged.

For more info select 10 in the main menu. You can find all types of available
animals and plants and their characteristics there.


### Buy a farm
In the main menu select 1. A few random sale announcements will be shown.
You can see the price, details of buildings that are placed on the farm,
and more. Choose a farm or type 0 to cancel.

```
Lista farm do kupienia: 
-- FARMA 1 ---------				 cena: 31143 zl
Całkowita wartość farmy: 31143 zl 
Budynek hodowlany;	1143 zl;	9 max szt. zwierzat
Powierzchnia ziemi uprawnej: 3 ha

...

Podaj numer farmy lub 0 aby anulowac
Podaj liczbe (min 0, max 3)
```

### Buing and selling land and buildings
To buy land or a building choose a farm. Then select the type of building or
type the number of ha you want to buy.

You can sell the land, but you cannot sell the building, so buy wisely!

```
1.	Budynek hodowlany;  5000 zl;	5 max szt. zwierzat
2.	Budynek hodowlany;  6900 zl;	7 max szt. zwierzat
3.	Budynek hodowlany;  8700 zl;	9 max szt. zwierzat
4.	Budynek hodowlany;  9500 zl;	10 max szt. zwierzat
5.	Budynek hodowlany;  14000 zl;	15 max szt. zwierzat
6.	Magazyn;            15000 zl;	5000 max kg paszy
7.	Magazyn;            17000 zl;	6000 max kg paszy
8.	Magazyn;            19000 zl;	7000 max kg paszy
9.	Magazyn;            21000 zl;	8000 max kg paszy
10.	Magazyn;            25000 zl;	10000 max kg paszy
Podaj numer lub 0 aby anulowac
Podaj liczbe (min 0, max 10)
```

### Buing and selling goods
Buying all types of goods looks similar. In the beginning, select a farm and
the building to deliver goods. Select the amount.

To sell goods select a farm and a building, and then the goods and the amount.

```
Wybierz zwierzeta na sprzedaz:
1: Swinia:  Dojrzalych:                   0
            Niedojrzalych:                5
            Za mlode na sprzedaz:         0
            Minimalny / Maksymalny wiek:  7/7
Podaj numer lub 0 aby anulowac
Podaj liczbe (min 0, max 1)
```


## Setup
This project has no binary release avaliable yet.
Please check the "Development" section to run the application.


## Development
I recommend using IntelliJ Idea IDE for development purposes.
Clone the repository. Open the folder in the IDE. Select `src/com.company/Main.java`
file and run it.


## Project Status
Complete with no binary release


## Room for Improvement

Room for improvement:
* binary release
* game levels - easy, medium, hard
* online multiplayer mode


## Contact
Created by @ewatryniszewska - feel free to contact me!
