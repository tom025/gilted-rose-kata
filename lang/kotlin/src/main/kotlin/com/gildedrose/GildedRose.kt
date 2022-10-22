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
                    in 1..Int.MAX_VALUE -> item.incrementQuality(by = 1)
                    else -> item.incrementQuality(by = 2)
                }
            }
            "Backstage passes to a TAFKAL80ETC concert" -> {
                when (item.sellIn) {
                    in (11..Int.MAX_VALUE) -> item.incrementQuality()
                    in (6..10) -> item.incrementQuality(by = 2)
                    in (1..5) -> item.incrementQuality(by = 3)
                    else -> item.quality = 0
                }
            }
            else -> {
                when (item.sellIn) {
                    in (1..Int.MAX_VALUE) -> item.decrementQuality()
                    else -> item.decrementQuality(by = 2)
                }
            }
        }
        if (item.name != "Sulfuras, Hand of Ragnaros") item.sellIn = item.sellIn - 1
    }

    private fun Item.decrementQuality(by: Int = 1) {
        quality = max(quality - by, 0)
    }

    private fun Item.incrementQuality(by: Int = 1) {
        quality = min(quality + by, 50)
    }
}