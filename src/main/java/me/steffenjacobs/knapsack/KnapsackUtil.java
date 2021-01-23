package me.steffenjacobs.knapsack;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class KnapsackUtil {
    private KnapsackUtil() {
        throw new UnsupportedOperationException("Singleton");
    }

    public static List<Item> createPermutationFromBitvector(List<Item> items, long bitFilter) {
        List<Item> selection = new ArrayList<>();
        for (int j = 0; j < items.size(); ++j) {
            long b = ((bitFilter >> j) & 1);
            if (b != 0) {
                selection.add(items.get(j));
            }
        }
        return selection;
    }

    public static List<Item> createPermutationFromBitvector(List<Item> items, BitSet bitFilter) {
        List<Item> selection = new ArrayList<>();
        for (int j = 0; j < items.size(); ++j) {
            if (bitFilter.get(j)) {
                selection.add(items.get(j));
            }
        }
        return selection;
    }

    public static int score(List<Item> items, int maxWeight) {
        int weight = 0;
        int score = 0;
        for (Item i : items) {
            weight += i.getWeight();
            score += i.getPrice();
        }
        return weight > maxWeight ? 0 : score;
    }
}
