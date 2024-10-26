package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {

            switch (item.getName()) {
                case "Aged Brie":
                    item.increaseQualituByOne();
                    break;
                case "Backstage passes to a TAFKAL80ETC concert":
                    item.increaseQualituByOne();
                    if (item.getSellIn() < 6) {
                        item.increaseQualituByOne();
                    }
                    if (item.getSellIn() < 11) {
                        item.increaseQualituByOne();
                    }
                    break;
                case "Sulfuras, Hand of Ragnaros":
                    break;
                default:
                    item.decreaseQualituByOne();
                    break;
            }

            if (!item.getName().equals("Sulfuras, Hand of Ragnaros")) {
                item.setSellIn(item.getSellIn() - 1);
            }

            switch (item.getName()) {
                case "Aged Brie":
                    if (item.getSellIn() < 0) {
                        item.increaseQualituByOne();
                    }
                    break;
                case "Backstage passes to a TAFKAL80ETC concert":
                    if (item.getSellIn() < 0) {
                        item.setQuality(0);
                    }
                    break;
                case "Sulfuras, Hand of Ragnaros":
                    break;
                default:
                    if (item.getSellIn() < 0) {
                        item.decreaseQualituByOne();
                    }
                    break;
            }
        }
    }
}
