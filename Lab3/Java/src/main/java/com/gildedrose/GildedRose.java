package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (item.getName().equals("Aged Brie")
                || item.getName().equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (item.getQuality() < 50) {
                        item.increaseQualituByOne();

                        if (item.getName().equals("Backstage passes to a TAFKAL80ETC concert")) {
                            if (item.getSellIn() < 11) {
                                item.increaseQualituByOne();
                            }

                            if (item.getSellIn() < 6) {
                                item.increaseQualituByOne();
                            }
                        }
                    }
                } else {
                if (!item.getName().equals("Sulfuras, Hand of Ragnaros")) {
                    item.decreaseQualituByOne();
                }
            }

            if (!item.getName().equals("Sulfuras, Hand of Ragnaros")) {
                item.setSellIn(item.getSellIn() - 1);
            }

            if (item.getSellIn() < 0) {
                if (item.getName().equals("Aged Brie")) {
                    item.increaseQualituByOne();
                } else {
                    if (!item.getName().equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (!item.getName().equals("Sulfuras, Hand of Ragnaros")) {
                            item.decreaseQualituByOne();
                        }
                    } else {
                        item.setQuality(0);
                    }
                }
            }
        }
    }
}
