package com.gildedrose

import kotlin.math.max
import kotlin.math.min

class GildedRose(private val items: List<Item>) {
    fun updateQuality() {
        items.forEach(::updateItem)
    }

    private fun updateItem(item: Item) {
        when (item.name) {
            "Sulfuras, Hand of Ragnaros" -> LegendaryItem()
            "Aged Brie" -> AgedBrie()
            "Backstage passes to a TAFKAL80ETC concert" -> BackstagePasses()
            else -> NormalItem()
        }.apply {
            item.quality = updateQuality(item)
            item.sellIn = updateSellIn(item)
        }
    }
}


private fun incrementQuality(item: Item, by: Int = 1): Int = min(item.quality + by, 50)
private fun decrementQuality(item: Item, by: Int = 1): Int = max(item.quality - by, 0)

class LegendaryItem : Updatable {
    override fun updateQuality(item: Item) = item.quality

    override fun updateSellIn(item: Item) = item.sellIn
}

class AgedBrie : Updatable {
    override fun updateQuality(item: Item): Int = when (item.sellIn) {
        in 1..Int.MAX_VALUE -> incrementQuality(item, by = 1)
        else -> incrementQuality(item, by = 2)
    }
}

class BackstagePasses : Updatable {
    override fun updateQuality(item: Item): Int = when (item.sellIn) {
        in (11..Int.MAX_VALUE) -> incrementQuality(item)
        in (6..10) -> incrementQuality(item, by = 2)
        in (1..5) -> incrementQuality(item, by = 3)
        else -> 0
    }
}

class NormalItem : Updatable {
    override fun updateQuality(item: Item): Int {
        return when (item.sellIn) {
            in (1..Int.MAX_VALUE) -> decrementQuality(item)
            else -> decrementQuality(item, by = 2)
        }
    }
}

interface Updatable {
    fun updateQuality(item: Item): Int
    fun updateSellIn(item: Item): Int = item.sellIn - 1
}
