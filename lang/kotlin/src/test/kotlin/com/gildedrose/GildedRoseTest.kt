package com.gildedrose

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class GildedRoseTest {

    @Test
    fun updateQuality_item_decrease_in_quality_to_zero() {
        val normalItem = Item("some normal item", 10, 1)
        val app = GildedRose(listOf(normalItem))

        app.updateItems()
        assertEquals(0, normalItem.quality)

        app.updateItems()
        assertEquals(0, normalItem.quality)
    }

    @Test
    fun updateQuality_item_quality_decreases_by_one_before_sell_by_date() {
        val normalItem = Item("some normal item", 10, 10)
        val app = GildedRose(listOf(normalItem))

        app.updateItems()
        assertEquals(9, normalItem.quality)
        assertEquals(9, normalItem.sellIn)
    }

    @Test
    fun updateQuality_item_quality_decreases_by_two_after_sell_by_date() {
        val normalItem = Item("some normal item", 1, 10)
        val app = GildedRose(listOf(normalItem))

        app.updateItems()
        assertEquals(9, normalItem.quality)
        assertEquals(0, normalItem.sellIn)

        app.updateItems()
        assertEquals(7, normalItem.quality)
        assertEquals(-1, normalItem.sellIn)
    }

    @Test
    fun updateQuality_aged_brie_increase_in_quality_upto_50() {
        val agedBrie = Item("Aged Brie", 5, 49)
        val app = GildedRose(listOf(agedBrie))

        app.updateItems()
        assertEquals(50, agedBrie.quality)
        assertEquals(4, agedBrie.sellIn)

        app.updateItems()
        assertEquals(50, agedBrie.quality)
        assertEquals(3, agedBrie.sellIn)
    }

    @Test
    fun updateQuality_aged_brie_qaulity_keeps_increasing_past_its_sell_by_date() {
        val agedBrie = Item("Aged Brie", 1, 20)
        val app = GildedRose(listOf(agedBrie))

        app.updateItems()
        assertEquals(21, agedBrie.quality)
        assertEquals(0, agedBrie.sellIn)

        app.updateItems()
        assertEquals(23, agedBrie.quality)
        assertEquals(-1, agedBrie.sellIn)
    }

    @Test
    fun updateQuality_legendary_items_do_not_change_quality() {
        val legendaryItem = Item(
            "Sulfuras, Hand of Ragnaros",
            1,
            20
        )
        val app = GildedRose(listOf(legendaryItem))

        app.updateItems()
        assertEquals(20, legendaryItem.quality)
        assertEquals(1, legendaryItem.sellIn)
    }

    @Test
    fun updateQuality_backstage_passes_increase_quality_by_1_10_days_before_sellIn() {
        val legendaryItem = Item(
            "Backstage passes to a TAFKAL80ETC concert",
            11,
            20
        )
        val app = GildedRose(listOf(legendaryItem))

        app.updateItems()
        assertEquals(21, legendaryItem.quality)
        assertEquals(10, legendaryItem.sellIn)
    }

    @Test
    fun updateQuality_backstage_passes_increase_quality_by_2_between_10_and_6_days_before() {
        val legendaryItem = Item(
            "Backstage passes to a TAFKAL80ETC concert",
            7,
            20
        )
        val app = GildedRose(listOf(legendaryItem))

        app.updateItems()
        assertEquals(22, legendaryItem.quality)
        assertEquals(6, legendaryItem.sellIn)
    }

    @Test
    fun updateQuality_backstage_passes_increase_quality_by_3_between_over_6_days_before() {
        val legendaryItem = Item(
            "Backstage passes to a TAFKAL80ETC concert",
            5,
            20
        )
        val app = GildedRose(listOf(legendaryItem))

        app.updateItems()
        assertEquals(23, legendaryItem.quality)
        assertEquals(4, legendaryItem.sellIn)
    }

    @Test
    fun updateQuality_backstage_passes_are_worthless_after_the_concert() {
        val legendaryItem = Item(
            "Backstage passes to a TAFKAL80ETC concert",
            0,
            20
        )
        val app = GildedRose(listOf(legendaryItem))

        app.updateItems()
        assertEquals(0, legendaryItem.quality)
        assertEquals(-1, legendaryItem.sellIn)
    }
}