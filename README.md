Course [exercise](https://paper.dropbox.com/doc/Introduction-to-Programming-Exercises-8-Batman-Edition--A93PQ7HuTtbFOVTWp0qQocdzAg-kcP51Ol1o8Qs1l7tDjPZc) for [TAMK Introduction to programming 2020](https://paper.dropbox.com/doc/Introduction-to-Programming-2020--A95PoUsJq1yfRP7CqqRDc1BKAg-en4Efk8wXCvQhQwXbORuV)

Exercise on packages, documentation, code reusability and maintainability, and an intro to Github.

# Lotto!
A lottery simulator.

A program that can be used to simulate basic lottery number drawings and then test how long it will take you to win the jackpot. Currently the program reruns the simulation if it took more time than a human lifespan.

![Exmaple run](/img/example.png "Example of running the simulation")

You can either feed your numbers directly the command line arguments or enter them in the program.

Lottery parameters are currently defined as constants inside the main class. At the moment you can change the size of the number pool and the amount of numbers to draw. The ifespan limit is also defined there.

Included are some utility classes for handling arrays, user input and random numbers.

## Future updates
* Internationalization/localisation support
* Configuration menu and saving settings to a file
* Improved support for different lottery systems, bonus balls etc.
* UI tweaking
* Statistics collection and analysis
