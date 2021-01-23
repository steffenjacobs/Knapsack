package me.steffenjacobs.knapsack.strategy;

import org.junit.jupiter.api.Test;

import java.util.BitSet;

import static org.assertj.core.api.Assertions.assertThat;

class TestGenericGeneticSolvingStrategy {

    @Test
    void testCrossover() {
        //Arrange
        BitSet b1 = new BitSet(8);
        b1.set(0, 8, true);

        BitSet b2 = new BitSet(8);

        //Act
        BitSet result = new GenericGeneticSolvingStrategy().crossover((BitSet)b1.clone(), (BitSet)b2.clone(), 8);

        //Assert
        assertThat(result).isEqualTo(BitSet.valueOf(new byte[]{(byte) 0b1111}));
    }

    @Test
    void testMutate(){
        BitSet b1 = new BitSet(8);
        BitSet mutated = new GenericGeneticSolvingStrategy().mutate((BitSet)b1.clone(), 8, 1, 1);
        assertThat(mutated).isNotEqualTo(b1);
    }
}
