package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;
import static com.gildedrose.GildedRose.*;

public class GildedRoseTest {

	private final static String OTHER_ITEM = "Elixir of the Mongoose";

	private final static int SULFURAS_QUALITY = 80;
	
	private GildedRose app = new GildedRose();
	private Item[] items;
	
	@After
    public void after() throws Exception {
		items = null;
    }

	@Test
	public void should_neverAltersQualityAndSellIn_when_sulfuras() {
		int firstSellIn = 0;
		int secondSellIn = -1;
		
		items = new Item[] { new Item(SULFURAS, firstSellIn, SULFURAS_QUALITY), new Item(SULFURAS, secondSellIn, SULFURAS_QUALITY) };

		app.setItems(items);
		app.updateQuality();
		
		assertEquals(SULFURAS, app.getItem(0).name);
		
		assertEquals(SULFURAS_QUALITY, app.getItem(0).quality);
		assertEquals(SULFURAS_QUALITY, app.getItem(1).quality);

		assertEquals(firstSellIn, app.getItem(0).sellIn);
		assertEquals(secondSellIn, app.getItem(1).sellIn);
	}

	@Test
	public void should_increaseInQuality_when_agedBrieAndQualityLessThan50() {
		int initialAgedBrieQuality = 0;
		int expectedAgedBrieQuality = initialAgedBrieQuality + 1;
		
		int initialAgedBrieSellIn = 2;
		int expectedAgedBrieSellIn = initialAgedBrieSellIn - 1;
		
		items = new Item[] { new Item(AGED_BRIE, initialAgedBrieSellIn, initialAgedBrieQuality) };

		app.setItems(items);
		app.updateQuality();
		
		assertEquals(AGED_BRIE, app.getItem(0).name);
		assertEquals(expectedAgedBrieQuality, app.getItem(0).quality);
		assertEquals(expectedAgedBrieSellIn, app.getItem(0).sellIn);
	}

	@Test
	public void should_keepSameQuality_when_agedBrieAndQualityEquals50() {
		int initialAgedBrieQuality = 50;
		int expectedAgedBrieQuality = initialAgedBrieQuality;
		
		items = new Item[] { new Item(AGED_BRIE, 2, initialAgedBrieQuality) };

		app.setItems(items);
		app.updateQuality();
		
		assertEquals(AGED_BRIE, app.getItem(0).name);
		assertEquals(expectedAgedBrieQuality, app.getItem(0).quality);
	}

	@Test
	public void should_increaseInQualityBy2_when_agedBrieAndSellInPassed() {
		int initialAgedBrieQuality = 2;
		int expectedAgedBrieQuality = initialAgedBrieQuality + 2;
		
		items = new Item[] { new Item(AGED_BRIE, -1, initialAgedBrieQuality) };

		app.setItems(items);
		app.updateQuality();
		
		assertEquals(AGED_BRIE, app.getItem(0).name);
		assertEquals(expectedAgedBrieQuality, app.getItem(0).quality);
	}

	@Test
	public void should_increaseInQuality_when_backstageAndQualityLessThan50() {
		int initialBackstageQuality = 0;
		int expectedBackstageQuality = initialBackstageQuality + 1;
		
		int initialBackstageSellIn = 20;
		int expectedBackstageSellIn = initialBackstageSellIn - 1;
		
		items = new Item[] { new Item(BACKSTAGE, initialBackstageSellIn, initialBackstageQuality) };

		app.setItems(items);
		app.updateQuality();
		
		assertEquals(BACKSTAGE, app.getItem(0).name);
		assertEquals(expectedBackstageQuality, app.getItem(0).quality);
		assertEquals(expectedBackstageSellIn, app.getItem(0).sellIn);
	}

	@Test
	public void should_keepSameQuality_when_backstageAndQualityEquals50() {
		int initialBackstageQuality = 50;
		int expectedBackstageQuality = initialBackstageQuality;
		
		items = new Item[] { new Item(BACKSTAGE, 20, initialBackstageQuality) };

		app.setItems(items);
		app.updateQuality();
		
		assertEquals(BACKSTAGE, app.getItem(0).name);
		assertEquals(expectedBackstageQuality, app.getItem(0).quality);
	}

	@Test
	public void should_increaseInQualityBy2_when_backstageAndSellInBetween10And6() {
		int initialBackstageQuality = 16;
		int expectedBackstageQuality = initialBackstageQuality + 2;
		
		items = new Item[] { new Item(BACKSTAGE, 10, initialBackstageQuality),
				new Item(BACKSTAGE, 6, initialBackstageQuality) };

		app.setItems(items);
		app.updateQuality();
		
		assertEquals(BACKSTAGE, app.getItem(0).name);
		assertEquals(expectedBackstageQuality, app.getItem(0).quality);
		assertEquals(expectedBackstageQuality, app.getItem(1).quality);
	}

	@Test
	public void should_increaseInQualityBy3_when_backstageAndSellInBetween5And1() {
		int initialBackstageQuality = 15;
		int expectedBackstageQuality = initialBackstageQuality + 3;
		
		items = new Item[] { new Item(BACKSTAGE, 5, initialBackstageQuality),
				new Item(BACKSTAGE, 1, initialBackstageQuality) };

		app.setItems(items);
		app.updateQuality();
		
		assertEquals(BACKSTAGE, app.getItem(0).name);
		assertEquals(expectedBackstageQuality, app.getItem(0).quality);
		assertEquals(expectedBackstageQuality, app.getItem(1).quality);
	}

	@Test
	public void should_dropQualityTo0_when_backstageAndAfterTheCancert() {
		int initialBackstageQuality = 50;
		int expectedBackstageQuality = 0;
		
		items = new Item[] { new Item(BACKSTAGE, -1, initialBackstageQuality) };

		app.setItems(items);
		app.updateQuality();
		
		assertEquals(BACKSTAGE, app.getItem(0).name);
		assertEquals(expectedBackstageQuality, app.getItem(0).quality);
	}

	@Test
	public void should_decreaseInQuality_when_otherItem() {
		int initialOtherItemQuality = 10;
		int expectedOtherItemQuality = initialOtherItemQuality - 1;
		
		int initialOtherItemSellIn = 10;
		int expectedOtherItemSellIn = initialOtherItemSellIn - 1;
		
		items = new Item[] { new Item(OTHER_ITEM, initialOtherItemSellIn, initialOtherItemQuality) };

		app.setItems(items);
		app.updateQuality();
		
		assertEquals(OTHER_ITEM, app.getItem(0).name);
		assertEquals(expectedOtherItemQuality, app.getItem(0).quality);
		assertEquals(expectedOtherItemSellIn, app.getItem(0).sellIn);
	}

	@Test
	public void should_decreaseInQuality_when_otherItemAndSellInPassed() {
		int initialOtherItemQuality = 10;
		int expectedOtherItemQuality = initialOtherItemQuality - 2;
		
		items = new Item[] { new Item(OTHER_ITEM, -1, initialOtherItemQuality) };

		app.setItems(items);
		app.updateQuality();
		
		assertEquals(OTHER_ITEM, app.getItem(0).name);
		assertEquals(expectedOtherItemQuality, app.getItem(0).quality);
	}
}
