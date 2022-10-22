package com.gildedrose

class GildedRose(private val items: List<Item>) {
    fun updateQuality() {
        items.forEach(::updateItem)
    }

    private fun updateItem(item: Item) {
        when (item.name) {
            "Sulfuras, Hand of Ragnaros" -> { /* Do nothing */
            }
            "Aged Brie" -> {
                incrementQuality(item)
                if (item.sellIn <= 0) {
                    incrementQuality(item)
                }
            }
            "Backstage passes to a TAFKAL80ETC concert" -> {
                incrementQuality(item)
                if (item.sellIn <= 10) {
                    incrementQuality(item)
                }
                if (item.sellIn <= 5) {
                    incrementQuality(item)
                }
                if (item.sellIn <= 0) {
                    item.quality = 0
                }
            }
            else -> {
                decrementQuality(item)
                if (item.sellIn <= 0) {
                    decrementQuality(item)
                }
            }
        }
        if (item.name != "Sulfuras, Hand of Ragnaros") item.sellIn = item.sellIn - 1
    }

    private fun decrementQuality(item: Item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1
        }
    }

    private fun incrementQuality(item: Item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1
        }
    }
}