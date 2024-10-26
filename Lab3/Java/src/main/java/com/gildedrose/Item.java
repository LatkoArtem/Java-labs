package com.gildedrose;

public class Item {

    private final String name;

    private int sellIn;

    private int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public String getName() {return name;}

    public int getSellIn() {return sellIn;}

    public void setSellIn(int sellIn) {this.sellIn = sellIn;}

    public int getQuality() {return quality;}

    public void setQuality(int quality) {this.quality = quality;}

    public void increaseQualituByOne() {
        if (quality < 50) {
            quality = quality + 1;
        }
    }

    public void decreaseQualituByOne() {
        if (quality > 0) {
            quality = quality - 1;
        }
    }

    public void updateQuality() {
        decreaseQualituByOne();
        setSellIn(getSellIn() - 1);
        if (getSellIn() < 0) {
            decreaseQualituByOne();
        }
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

}
