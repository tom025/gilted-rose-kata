package com.gildedrose

class GildedRose(private val items: List<Item>) {
    fun updateQuality() {
        items.forEach(::updateItem)
    }

    private fun updateItem(item: Item) {
        when (item.name) {
            "Sulfuras, Hand of Ragnaros" -> { /* Do nothing */
            }

            else -> {
                if (item.name == "Aged Brie" || item.name == "Backstage passes to a TAFKAL80ETC concert") {
                    incrementQuality(item)
                    if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                        if (item.sellIn < 11) {
                            incrementQuality(item)
                        }

                        if (item.sellIn < 6) {
                            incrementQuality(item)
                        }
                    }
                } else {
                    decrementQuality(item)
                }

                item.sellIn = item.sellIn - 1

                if (item.sellIn < 0) {
                    if (item.name == "Aged Brie") {
                        incrementQuality(item)
                    } else {
                        if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                            item.quality = 0
                        } else {
                            decrementQuality(item)
                        }
                    }
                }
            }
        }
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