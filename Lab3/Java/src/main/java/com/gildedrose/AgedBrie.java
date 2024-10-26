package com.gildedrose;

public class AgedBrie extends Item {
    public AgedBrie(int sellIn, int quality) {
        super("Aged Brie", sellIn, quality);
    }

    @Override
    public void updateQuality() {
        increaseQualituByOne();
        setSellIn(getSellIn() - 1);
        if (getSellIn() < 0) {
            increaseQualituByOne();
        }
    }
}
