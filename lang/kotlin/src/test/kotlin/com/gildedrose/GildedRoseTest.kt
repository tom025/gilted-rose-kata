package com.gildedrose

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class GildedRoseTest {

    @Test
    fun updateQuality() {
        val items = listOf(Item("foo", 0, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("fixme", items[0].name)
    }
}