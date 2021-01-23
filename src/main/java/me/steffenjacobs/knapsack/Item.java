package me.steffenjacobs.knapsack;



public class Item {
    private final int weight;
    private final int price;

    public Item(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }
}
