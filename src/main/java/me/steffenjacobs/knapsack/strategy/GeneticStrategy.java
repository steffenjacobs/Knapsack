package me.steffenjacobs.knapsack.strategy;

import me.steffenjacobs.knapsack.Item;
import me.steffenjacobs.knapsack.KnapsackUtil;

import java.util.List;

public class GeneticStrategy implements KnapsackStrategy {


    @Override
    public List<Item> solve(List<Item> items, int maxWeight) {
        int topPerm = new GenericGeneticSolvingStrategy().solve(items.size(),
                perm -> KnapsackUtil.score(KnapsackUtil.createPermutationFromBitvector(items, perm), maxWeight), 40,
                200, 1);
        return KnapsackUtil.createPermutationFromBitvector(items, topPerm);
    }

    @Override
    public String getName() {
        return "GE";
    }
}
