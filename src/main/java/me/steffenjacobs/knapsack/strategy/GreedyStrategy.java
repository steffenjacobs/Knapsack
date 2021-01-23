package me.steffenjacobs.knapsack.strategy;

import me.steffenjacobs.knapsack.Item;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GreedyStrategy implements KnapsackStrategy {
    @Override
    public List<Item> solve(List<Item> items, int maxWeight) {
        List<Item> sorted = new ArrayList<>(items);
        sorted.sort(Comparator.comparingInt(Item::getPrice));

        List<Item> selected = new ArrayList<>();
        int weight = 0;
        for (Item i : sorted) {
            if (weight + i.getWeight() <= maxWeight) {
                selected.add(i);
                weight += i.getWeight();
            }
        }
        return selected;
    }

    @Override
    public String getName() {
        return "GR";
    }
}
