package com.gildedrose

import kotlin.math.max
import kotlin.math.min

class GildedRose(private val items: List<Item>) {
    fun updateItems() {
        items.forEach { item ->
            updateItem(item).also { (quality, sellIn) ->
                item.quality = quality
                item.sellIn = sellIn
            }
        }
    }
}

private fun updateItem(item: Item): Pair<Int, Int> = when (item.name) {
    "Sulfuras, Hand of Ragnaros" -> LegendaryItem
    "Aged Brie" -> AgedBrie
    "Backstage passes to a TAFKAL80ETC concert" -> BackstagePasses
    "Conjured Mana Cake" -> Conjured
    else -> NormalItem
}.let {
    Pair(
        it.updateQuality(item.sellIn, item.quality),
        it.updateSellIn(item.sellIn)
    )
}

object Conjured : ItemUpdater {
    override fun updateQuality(sellIn: Int, quality: Int): Int = (0..1).fold(quality) { acc, _ ->
        NormalItem.updateQuality(sellIn, quality = acc)
    }
}

private fun incrementQuality(quality: Int, by: Int = 1): Int = min(quality + by, 50)
private fun decrementQuality(quality: Int, by: Int = 1): Int = max(quality - by, 0)

object LegendaryItem : ItemUpdater {
    override fun updateQuality(sellIn: Int, quality: Int): Int = quality

    override fun updateSellIn(sellIn: Int) = sellIn
}

object AgedBrie : ItemUpdater {
    override fun updateQuality(sellIn: Int, quality: Int): Int = when (sellIn) {
        in 1..Int.MAX_VALUE -> incrementQuality(quality, by = 1)
        else -> incrementQuality(quality, by = 2)
    }
}

object BackstagePasses : ItemUpdater {
    override fun updateQuality(sellIn: Int, quality: Int): Int = when (sellIn) {
        in 11..Int.MAX_VALUE -> incrementQuality(quality)
        in 6..10 -> incrementQuality(quality, by = 2)
        in 1..5 -> incrementQuality(quality, by = 3)
        else -> 0
    }
}

object NormalItem : ItemUpdater {
    override fun updateQuality(sellIn: Int, quality: Int) = when (sellIn) {
        in 1..Int.MAX_VALUE -> decrementQuality(quality)
        else -> decrementQuality(quality, by = 2)
    }
}

interface ItemUpdater {
    fun updateQuality(sellIn: Int, quality: Int): Int
    fun updateSellIn(sellIn: Int): Int = sellIn - 1
}
