package me.steffenjacobs.knapsack.strategy;

import me.steffenjacobs.knapsack.Item;
import me.steffenjacobs.knapsack.KnapsackUtil;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;

public class RandomStrategy implements KnapsackStrategy {

    Random random = new Random();

    @Override
    public List<Item> solve(List<Item> items, int maxWeight) {

        int score = 0;
        List<Item> selectedItems = new ArrayList<>();
        while (score == 0) {
            int chromosomeLength = items.size();
            BitSet bits = new BitSet(chromosomeLength);
            for (int i = 0; i < chromosomeLength; i++) {
                bits.set(i, random.nextBoolean());
            }
            selectedItems = KnapsackUtil.createPermutationFromBitvector(items, bits);
            score = KnapsackUtil.score(selectedItems, maxWeight);
        }
        return selectedItems;
    }

    @Override
    public String getName() {
        return "RN";
    }
}
