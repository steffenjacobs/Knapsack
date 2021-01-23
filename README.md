# Solving the Knapsack problem in Java

This projects showcases a possible attempt to solve the knapsack problem in Java.

There are three strategies included:

1. A random strategy generating solutions until one with a score >0 appears (`RandomStrategy.java`).
2. A brute force strategy trying out all possible solutions and selecting the best one (`BruteForceStrategy.java`).
3. A greedy strategy selecting all the highest value items until the weight limit is reached (`GreedyStrategy.java`).
4. A genetic strategy using an evolutionary approach to select the best combination of items by mutating chromosomes (`GeneticStrategy.java` and `GenericGeneticSolvingStrategy.java`).

## Limitations

- Brute Force strategy slows down fast (>28).
- Random strategy slows down after ~50 permutations (same reason why the genetic strategy requires many iterations).
- Genetic strategy requires lots of iterations, if the total number of items is large while the number of desired items is small.
 
E.g.: Assume 100 items with an average weight of 5 should be packed into a backpack with a maximum weight of 25.

Therefore, probably about 5 items will be selected on average for a valid solution. Since there are 100 items to choose from, and the genome generation generates a random bit with a 50% chance for true/false for each possible item, there are on average 50 items selected in the first chromosome when rolling these chromosomes randomly.

Therefore, a few more iterations are required to roll a genome that has a score of >0 to start the optimization from.  

This issue could be resolved by only providing valid genomes with a score >0 for the first generation.

## Usage
Call `Knapsack.main()`. Simply adjust the parameters in `Knapsack.java`.


![Knapsack image](Knapsack.jpg)
