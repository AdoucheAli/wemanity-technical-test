package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;
import static com.gildedrose.GildedRose.*;

public class GildedRoseTest {

	private final static String OTHER_ITEM = "Elixir of the Mongoose";

	private final static int SULFURAS_QUALITY = 80;

	@Test
	public void should_neverAltersQuality_when_sulfuras() {
		Item[] items = new Item[] { new Item(SULFURAS, 0, SULFURAS_QUALITY), new Item(SULFURAS, -1, SULFURAS_QUALITY) };

		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(SULFURAS, app.items[0].name);
		assertEquals(SULFURAS_QUALITY, app.items[0].quality);
		assertEquals(SULFURAS_QUALITY, app.items[1].quality);
	}

	@Test
	public void should_increaseInQuality_when_agedBrieAndQualityLessThan50() {
		int initialAgedBrieQuality = 0;
		int expectedAgedBrieQuality = initialAgedBrieQuality + 1;
		Item[] items = new Item[] { new Item(AGED_BRIE, 2, initialAgedBrieQuality) };

		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(AGED_BRIE, app.items[0].name);
		assertEquals(expectedAgedBrieQuality, app.items[0].quality);
	}

	@Test
	public void should_keepSameQuality_when_agedBrieAndQualityEquals50() {
		int initialAgedBrieQuality = 50;
		int expectedAgedBrieQuality = initialAgedBrieQuality;
		Item[] items = new Item[] { new Item(AGED_BRIE, 2, initialAgedBrieQuality) };

		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(AGED_BRIE, app.items[0].name);
		assertEquals(expectedAgedBrieQuality, app.items[0].quality);
	}

	@Test
	public void should_increaseInQualityBy2_when_agedBrieAndSellInPassed() {
		int initialAgedBrieQuality = 2;
		int expectedAgedBrieQuality = initialAgedBrieQuality + 2;
		Item[] items = new Item[] { new Item(AGED_BRIE, -1, initialAgedBrieQuality) };

		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(AGED_BRIE, app.items[0].name);
		assertEquals(expectedAgedBrieQuality, app.items[0].quality);
	}

	@Test
	public void should_increaseInQuality_when_backstageAndQualityLessThan50() {
		int initialBackstageQuality = 0;
		int expectedBackstageQuality = initialBackstageQuality + 1;
		Item[] items = new Item[] { new Item(BACKSTAGE, 20, initialBackstageQuality) };

		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(BACKSTAGE, app.items[0].name);
		assertEquals(expectedBackstageQuality, app.items[0].quality);
	}

	@Test
	public void should_keepSameQuality_when_backstageAndQualityEquals50() {
		int initialBackstageQuality = 50;
		int expectedBackstageQuality = initialBackstageQuality;
		Item[] items = new Item[] { new Item(BACKSTAGE, 20, initialBackstageQuality) };

		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(BACKSTAGE, app.items[0].name);
		assertEquals(expectedBackstageQuality, app.items[0].quality);
	}

	@Test
	public void should_increaseInQualityBy2_when_backstageAndSellInBetween10And6() {
		int initialBackstageQuality = 16;
		int expectedBackstageQuality = initialBackstageQuality + 2;
		Item[] items = new Item[] { new Item(BACKSTAGE, 10, initialBackstageQuality),
				new Item(BACKSTAGE, 6, initialBackstageQuality) };

		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(BACKSTAGE, app.items[0].name);
		assertEquals(expectedBackstageQuality, app.items[0].quality);
		assertEquals(expectedBackstageQuality, app.items[1].quality);
	}

	@Test
	public void should_increaseInQualityBy3_when_backstageAndSellInBetween5And1() {
		int initialBackstageQuality = 15;
		int expectedBackstageQuality = initialBackstageQuality + 3;
		Item[] items = new Item[] { new Item(BACKSTAGE, 5, initialBackstageQuality),
				new Item(BACKSTAGE, 1, initialBackstageQuality) };

		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(BACKSTAGE, app.items[0].name);
		assertEquals(expectedBackstageQuality, app.items[0].quality);
		assertEquals(expectedBackstageQuality, app.items[1].quality);
	}

	@Test
	public void should_dropQualityTo0_when_backstageAndAfterTheCancert() {
		int initialBackstageQuality = 50;
		int expectedBackstageQuality = 0;
		Item[] items = new Item[] { new Item(BACKSTAGE, -1, initialBackstageQuality) };

		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(BACKSTAGE, app.items[0].name);
		assertEquals(expectedBackstageQuality, app.items[0].quality);
	}

	@Test
	public void should_decreaseInQuality_when_otherItem() {
		int initialBackstageQuality = 10;
		int expectedBackstageQuality = initialBackstageQuality - 1;
		Item[] items = new Item[] { new Item(OTHER_ITEM, 10, initialBackstageQuality) };

		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(OTHER_ITEM, app.items[0].name);
		assertEquals(expectedBackstageQuality, app.items[0].quality);
	}

	@Test
	public void should_decreaseInQuality_when_otherItemAndSellInPassed() {
		int initialBackstageQuality = 10;
		int expectedBackstageQuality = initialBackstageQuality - 2;
		Item[] items = new Item[] { new Item(OTHER_ITEM, -1, initialBackstageQuality) };

		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(OTHER_ITEM, app.items[0].name);
		assertEquals(expectedBackstageQuality, app.items[0].quality);
	}
}
