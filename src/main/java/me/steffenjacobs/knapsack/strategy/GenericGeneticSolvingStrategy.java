package me.steffenjacobs.knapsack.strategy;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Random;
import java.util.function.ToIntFunction;

public class GenericGeneticSolvingStrategy {

    private final Random random = new Random();

    public BitSet solve(int chromosomeLength, ToIntFunction<BitSet> scoringFunction, int populationSize,
                        int maxIterations, int maxMutations) {

        //Generate initial population
        BitSet[] population = generatePopulation(populationSize, chromosomeLength);

        for (int iteration = 0; iteration < maxIterations; iteration++) {

            //Scoring
            int[] score = scorePopulation(scoringFunction, populationSize, population);

            //Random selection of new parents weighted by score
            BitSet[] selectedParents = selectNewParents(chromosomeLength, populationSize, population, score);

            //Generate new children + mutate
            for (int i = 0; i < selectedParents.length; i += 2) {
                BitSet child = crossover(selectedParents[i], selectedParents[i + 1], chromosomeLength);
                population[i / 2] = mutate(child, chromosomeLength, maxMutations, maxMutations * 1D / (iteration + 1));
            }
        }

        //Select best population
        return selectBestPopulation(scoringFunction, populationSize, population);
    }

    private BitSet[] selectNewParents(int chromosomeLength, int populationSize, BitSet[] population, int[] score) {
        BitSet[] selected = new BitSet[populationSize * 2];
        int sumScores = Arrays.stream(score).sum();
        for (int i = 0; i < selected.length; i++) {
            int selectedScore = sumScores > 0 ? random.nextInt(sumScores) : 0;
            for (int j = 0; j < populationSize; j++) {
                if (selectedScore - score[j] <= 0) {
                    selected[i] = selectOrReroleChromosome(chromosomeLength, population[j], score[j]);
                    break;
                }
                selectedScore -= score[j];
            }
        }
        return selected;
    }

    private BitSet selectOrReroleChromosome(int chromosomeLength, BitSet population, int score) {
        if (score == 0) {
            return generateRandomChromosome(chromosomeLength);
        } else {
            return population;
        }
    }

    private BitSet selectBestPopulation(ToIntFunction<BitSet> scoringFunction, int populationSize,
                                        BitSet[] population) {
        int[] finalScores = scorePopulation(scoringFunction, populationSize, population);

        int max = 0;
        BitSet top = population[0];
        for (int i = 0; i < finalScores.length; i++) {
            if (max < finalScores[i]) {
                max = finalScores[i];
                top = population[i];
            }
        }
        return top;
    }

    private int[] scorePopulation(ToIntFunction<BitSet> scoringFunction, int populationSize, BitSet[] population) {
        int[] score = new int[populationSize];
        for (int i = 0; i < populationSize; i++) {
            score[i] = scoringFunction.applyAsInt(population[i]);
        }
        return score;
    }

    BitSet crossover(BitSet a, BitSet b, int chromosomeLength) {
        BitSet a1 = (BitSet) a.clone();
        BitSet b1 = (BitSet) b.clone();
        BitSet left = new BitSet(chromosomeLength);
        left.set(0, chromosomeLength / 2, true);
        BitSet right = new BitSet(chromosomeLength);
        right.set(chromosomeLength / 2, chromosomeLength, true);
        a1.and(left);
        b1.and(right);
        a1.or(b1);
        return a1;
    }

    BitSet mutate(BitSet a, int chromosomeLength, int maxNumMutations, double mutationProbability) {
        BitSet a1 = (BitSet) a.clone();
        for (int i = 0; i < maxNumMutations; i++) {
            if (Math.random() < mutationProbability) {
                int bitToFlip = random.nextInt(chromosomeLength);
                a1.flip(bitToFlip);
            }
        }
        return a1;
    }

    private BitSet[] generatePopulation(int populationSize, int chromosomeLength) {
        BitSet[] population = new BitSet[populationSize];
        for (int i = 0; i < populationSize; i++) {
            population[i] = generateRandomChromosome(chromosomeLength);
        }
        return population;
    }

    private BitSet generateRandomChromosome(int chromosomeLength) {
        BitSet bits = new BitSet(chromosomeLength);
        for (int i = 0; i < chromosomeLength; i++) {
            bits.set(i, random.nextBoolean());
        }
        return bits;
    }
}
