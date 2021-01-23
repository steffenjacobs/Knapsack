package me.steffenjacobs.knapsack.strategy;

import me.steffenjacobs.knapsack.Item;
import me.steffenjacobs.knapsack.KnapsackUtil;

import java.util.ArrayList;
import java.util.List;

public class BruteForceStrategy implements  KnapsackStrategy{

    @Override
    public List<Item> solve(List<Item> items, int maxWeight) {

        List<Item> topList = new ArrayList<>();
        int score = 0;
        int numPerms = (int) Math.pow(2, items.size());
        for (int bits = 0; bits < numPerms; bits++) {
            List<Item> selection = KnapsackUtil.createPermutationFromBitvector(items, bits);
            int selScore = KnapsackUtil.score(selection, maxWeight);
            if (selScore > score) {
                score = selScore;
                topList = selection;
            }
        }
        return topList;
    }

    @Override
    public String getName() {
        return "BF";
    }
}
