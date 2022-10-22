package com.gildedrose

import kotlin.math.max
import kotlin.math.min

class GildedRose(private val items: List<Item>) {
    fun updateQuality() {
        items.forEach(::updateItem)
    }

    private fun updateItem(item: Item) {
        when (item.name) {
            "Sulfuras, Hand of Ragnaros" -> { /* Do nothing */
            }
            "Aged Brie" -> {
                when (item.sellIn) {
                    in 1..Int.MAX_VALUE -> incrementQuality(item, by = 1)
                    else -> incrementQuality(item, by = 2)
                }
            }
            "Backstage passes to a TAFKAL80ETC concert" -> {
                when (item.sellIn) {
                    in (11..Int.MAX_VALUE) -> incrementQuality(item)
                    in (6..10) -> incrementQuality(item, by = 2)
                    in (1..5) -> incrementQuality(item, by = 3)
                    else -> item.quality = 0
                }
            }
            else -> {
                when (item.sellIn) {
                    in (1..Int.MAX_VALUE) -> decrementQuality(item)
                    else -> decrementQuality(item, by = 2)
                }
            }
        }
        if (item.name != "Sulfuras, Hand of Ragnaros") item.sellIn = item.sellIn - 1
    }

    private fun decrementQuality(item: Item, by: Int = 1) {
        item.quality = max(item.quality - by, 0)
    }

    private fun incrementQuality(item: Item, by: Int = 1) {
        item.quality = min(item.quality + by, 50)
    }
}