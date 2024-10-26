package com.gildedrose;

public class BackstagePass extends Item {
    public BackstagePass(int sellIn, int quality) {
        super("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
    }

    @Override
    public void updateQuality() {
        increaseQualituByOne();
        if (getSellIn() < 6) {
            increaseQualituByOne();
        }
        if (getSellIn() < 11) {
            increaseQualituByOne();
        }
        setSellIn(getSellIn() - 1);
        if (getSellIn() < 0) {
            setQuality(0);
        }
    }
}
