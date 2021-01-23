package me.steffenjacobs.knapsack.strategy;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;

public class GenericGeneticSolvingStrategy {

    private final Random random = new Random();

    public int solve(int chromosomeLength, Function<Integer, Integer> scoringFunction, int populationSize,
                     int maxIterations, int maxMutations) {

        //Generate initial population
        int[] population = generatePopulation(populationSize, chromosomeLength);

        for (int iteration = 0; iteration < maxIterations; iteration++) {

            //Scoring
            int[] score = scorePopulation(scoringFunction, populationSize, population);

            //Random selection weighted by score
            int[] selected = new int[populationSize * 2];
            int sumScores = Arrays.stream(score).sum();
            for (int i = 0; i < selected.length; i++) {
                int selectedScore = sumScores > 0 ? random.nextInt(sumScores) : 0;
                for (int j = 0; j < populationSize; j++) {
                    if (selectedScore - score[j] <= 0) {
                        selected[i] = population[j];
                        break;
                    }
                    selectedScore -= score[j];
                }
            }

            //Generate new parents + Mutate
            for (int i = 0; i < selected.length; i += 2) {
                int newParent = crossover(selected[i], selected[i + 1], chromosomeLength);
                population[i / 2] = mutate(newParent, chromosomeLength, maxMutations,
                        maxMutations * 1D / (iteration + 1));
            }
        }

        //Select best population
        return selectBestPopulation(scoringFunction, populationSize, population);
    }

    private int selectBestPopulation(Function<Integer, Integer> scoringFunction, int populationSize, int[] population) {
        int[] finalScores = scorePopulation(scoringFunction, populationSize, population);

        int max = 0;
        int top = 0;
        for (int i = 0; i < finalScores.length; i++) {
            if (max < finalScores[i]) {
                max = finalScores[i];
                top = population[i];
            }
        }
        return top;
    }

    private int[] scorePopulation(Function<Integer, Integer> scoringFunction, int populationSize, int[] population) {
        int[] score = new int[populationSize];
        for (int i = 0; i < populationSize; i++) {
            score[i] = scoringFunction.apply(population[i]);
        }
        return score;
    }

    private int crossover(int a, int b, int chromosomeLength) {
        a <<= chromosomeLength;
        a >>= chromosomeLength;

        b >>= chromosomeLength;
        b <<= chromosomeLength;
        return a | b;
    }

    private int mutate(int a, int chromosomeLength, int maxNumMutations, double mutationProbability) {
        for (int i = 0; i < maxNumMutations; i++) {
            if (Math.random() < mutationProbability) {
                int bitToFlip = random.nextInt(chromosomeLength);
                a ^= (int) Math.pow(2, bitToFlip);
            }
        }
        return a;
    }

    private int[] generatePopulation(int populationSize, int chromosomeLength) {
        int[] population = new int[populationSize];
        for (int i = 0; i < populationSize; i++) {
            population[i] = generateRandomChromosome(chromosomeLength);
        }
        return population;
    }

    private int generateRandomChromosome(int chromosomeLength) {
        return random.nextInt((int) Math.pow(2, chromosomeLength));
    }
}
