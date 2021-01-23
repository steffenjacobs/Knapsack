package me.steffenjacobs.knapsack;

import me.steffenjacobs.knapsack.strategy.BruteForceStrategy;
import me.steffenjacobs.knapsack.strategy.GeneticStrategy;
import me.steffenjacobs.knapsack.strategy.GreedyStrategy;
import me.steffenjacobs.knapsack.strategy.KnapsackStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Knapsack {

    private final Random random = new Random();

    public static void main(String... args) {
        new Knapsack().runStrategies();
    }

    private void runStrategies() {
        int itemCount = 15;
        int maxWeight = 25;


        List<Item> items = new ArrayList<>();
        for (int i = 0; i < itemCount; i++) {
            items.add(new Item(random.nextInt(10), random.nextInt(10)));
        }
        System.out.printf("Generated (%s): ", items.size());
        for (Item i : items) {
            System.out.print(i.getPrice() + "/" + i.getWeight() + " ");
        }
        System.out.println();


        executeStrategy(new BruteForceStrategy(), items, maxWeight);
        executeStrategy(new GreedyStrategy(), items, maxWeight);
        executeStrategy(new GeneticStrategy(), items, maxWeight);
    }

    private List<Item> executeStrategy(KnapsackStrategy strategy, List<Item> items, int maxWeight) {
        long start = System.currentTimeMillis();
        List<Item> list = strategy.solve(items, maxWeight);
        printResult(System.currentTimeMillis() - start, maxWeight, list, strategy.getName());
        return list;
    }

    private void printResult(long millis, int maxWeight, List<Item> topList, String strategy) {
        System.out.printf("%s -> Score: %s, Time: %s,  Weight: %s, Items (%s)%n", strategy,
                KnapsackUtil.score(topList, maxWeight), millis, topList.stream().mapToDouble(Item::getWeight).sum(),
                topList.size());
        for (Item i : topList) {
            System.out.print(i.getPrice() + "/" + i.getWeight() + " ");
        }
        System.out.println("\n");
    }
}
