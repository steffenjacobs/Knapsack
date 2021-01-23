package me.steffenjacobs.knapsack.strategy;

import me.steffenjacobs.knapsack.Item;

import java.util.List;

public interface KnapsackStrategy {
    List<Item> solve(List<Item> items, int maxWeight);

    String getName();
}
