package com.gildedrose

import kotlin.math.max
import kotlin.math.min

class GildedRose(private val items: List<Item>) {
    fun updateItems() {
        items.forEach { item ->
            updateItem(item).also { (_, quality, sellIn) ->
                item.quality = quality
                item.sellIn = sellIn
            }
        }
    }
}

private fun updateItem(item: Item): Item = when (item.name) {
    "Sulfuras, Hand of Ragnaros" -> LegendaryItem
    "Aged Brie" -> AgedBrie
    "Backstage passes to a TAFKAL80ETC concert" -> BackstagePasses
    else -> NormalItem
}.let {
    item.copy(
        quality = it.updateQuality(item),
        sellIn = it.updateSellIn(item)
    )
}

private fun Item.copy(
    name: String = this.name,
    quality: Int = this.quality,
    sellIn: Int = this.sellIn
): Item = Item(
    name, sellIn, quality
)

private operator fun Item.component1() = name
private operator fun Item.component2() = quality
private operator fun Item.component3() = sellIn


private fun incrementQuality(item: Item, by: Int = 1): Int = min(item.quality + by, 50)
private fun decrementQuality(item: Item, by: Int = 1): Int = max(item.quality - by, 0)

object LegendaryItem : ItemUpdater {
    override fun updateQuality(item: Item) = item.quality

    override fun updateSellIn(item: Item) = item.sellIn
}

object AgedBrie : ItemUpdater {
    override fun updateQuality(item: Item): Int = when (item.sellIn) {
        in 1..Int.MAX_VALUE -> incrementQuality(item, by = 1)
        else -> incrementQuality(item, by = 2)
    }
}

object BackstagePasses : ItemUpdater {
    override fun updateQuality(item: Item): Int = when (item.sellIn) {
        in (11..Int.MAX_VALUE) -> incrementQuality(item)
        in (6..10) -> incrementQuality(item, by = 2)
        in (1..5) -> incrementQuality(item, by = 3)
        else -> 0
    }
}

object NormalItem : ItemUpdater {
    override fun updateQuality(item: Item): Int = when (item.sellIn) {
        in (1..Int.MAX_VALUE) -> decrementQuality(item)
        else -> decrementQuality(item, by = 2)
    }
}

interface ItemUpdater {
    fun updateQuality(item: Item): Int
    fun updateSellIn(item: Item): Int = item.sellIn - 1
}
