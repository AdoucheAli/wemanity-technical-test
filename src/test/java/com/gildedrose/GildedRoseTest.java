package com.gildedrose;

import static com.gildedrose.GildedRose.AGED_BRIE;
import static com.gildedrose.GildedRose.BACKSTAGE;
import static com.gildedrose.GildedRose.CONJURED;
import static com.gildedrose.GildedRose.SULFURAS;
import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GildedRoseTest {

	private final static String OTHER_ITEM = "Elixir of the Mongoose";

	private final static int SULFURAS_QUALITY = 80;
	
	private GildedRose app;
	
	@Before
    public void before() throws Exception {
		app = new GildedRose();
    }
	
	@After
    public void after() throws Exception {
		app = null;
    }

	@Test
	public void should_neverAltersQualityAndSellIn_when_sulfuras() {
		int firstSellIn = 0;
		int secondSellIn = -1;

		app.addItem(new Item(SULFURAS, firstSellIn, SULFURAS_QUALITY));
		app.addItem(new Item(SULFURAS, secondSellIn, SULFURAS_QUALITY));
		
		app.updateQuality();
		
		assertThat(app.getItem(0).getName()).isEqualTo(SULFURAS);
		
		assertThat(app.getItem(0).getQuality()).isEqualTo(SULFURAS_QUALITY);
		assertThat(app.getItem(1).getQuality()).isEqualTo(SULFURAS_QUALITY);

		assertThat(app.getItem(0).getSellIn()).isEqualTo(firstSellIn);
		assertThat(app.getItem(1).getSellIn()).isEqualTo(secondSellIn);
	}

	@Test
	public void should_increaseInQuality_when_agedBrieAndQualityLessThan50() {
		int initialAgedBrieQuality = 0;
		int expectedAgedBrieQuality = initialAgedBrieQuality + 1;
		
		int initialAgedBrieSellIn = 2;
		int expectedAgedBrieSellIn = initialAgedBrieSellIn - 1;
		
		app.addItem(new Item(AGED_BRIE, initialAgedBrieSellIn, initialAgedBrieQuality));
		
		app.updateQuality();
		
		assertEquals(AGED_BRIE, app.getItem(0).getName());
		assertEquals(expectedAgedBrieQuality, app.getItem(0).getQuality());
		assertEquals(expectedAgedBrieSellIn, app.getItem(0).getSellIn());
	}

	@Test
	public void should_keepSameQuality_when_agedBrieAndQualityEquals50() {
		int initialAgedBrieQuality = 50;
		int expectedAgedBrieQuality = initialAgedBrieQuality;
		
		app.addItem(new Item(AGED_BRIE, 2, initialAgedBrieQuality));
		
		app.updateQuality();
		
		assertEquals(AGED_BRIE, app.getItem(0).getName());
		assertEquals(expectedAgedBrieQuality, app.getItem(0).getQuality());
	}

	@Test
	public void should_increaseInQualityBy2_when_agedBrieAndSellInPassed() {
		int initialAgedBrieQuality = 2;
		int expectedAgedBrieQuality = initialAgedBrieQuality + 2;
		
		app.addItem(new Item(AGED_BRIE, -1, initialAgedBrieQuality));
		
		app.updateQuality();
		
		assertEquals(AGED_BRIE, app.getItem(0).getName());
		assertEquals(expectedAgedBrieQuality, app.getItem(0).getQuality());
	}

	@Test
	public void should_increaseInQuality_when_backstageAndQualityLessThan50() {
		int initialBackstageQuality = 0;
		int expectedBackstageQuality = initialBackstageQuality + 1;
		
		int initialBackstageSellIn = 20;
		int expectedBackstageSellIn = initialBackstageSellIn - 1;
		
		app.addItem(new Item(BACKSTAGE, initialBackstageSellIn, initialBackstageQuality));
		
		app.updateQuality();
		
		assertEquals(BACKSTAGE, app.getItem(0).getName());
		assertEquals(expectedBackstageQuality, app.getItem(0).getQuality());
		assertEquals(expectedBackstageSellIn, app.getItem(0).getSellIn());
	}

	@Test
	public void should_keepSameQuality_when_backstageAndQualityEquals50() {
		int initialBackstageQuality = 50;
		int expectedBackstageQuality = initialBackstageQuality;
		
		app.addItem(new Item(BACKSTAGE, 20, initialBackstageQuality));
		
		app.updateQuality();
		
		assertEquals(BACKSTAGE, app.getItem(0).getName());
		assertEquals(expectedBackstageQuality, app.getItem(0).getQuality());
	}

	@Test
	public void should_increaseInQualityBy2_when_backstageAndSellInBetween10And6() {
		int initialBackstageQuality = 16;
		int expectedBackstageQuality = initialBackstageQuality + 2;
		
		app.addItem(new Item(BACKSTAGE, 10, initialBackstageQuality));
		app.addItem(new Item(BACKSTAGE, 6, initialBackstageQuality));
		
		app.updateQuality();
		
		assertEquals(BACKSTAGE, app.getItem(0).getName());
		assertEquals(expectedBackstageQuality, app.getItem(0).getQuality());
		assertEquals(expectedBackstageQuality, app.getItem(1).getQuality());
	}

	@Test
	public void should_increaseInQualityBy3_when_backstageAndSellInBetween5And1() {
		int initialBackstageQuality = 15;
		int expectedBackstageQuality = initialBackstageQuality + 3;
		
		app.addItem(new Item(BACKSTAGE, 5, initialBackstageQuality));
		app.addItem(new Item(BACKSTAGE, 1, initialBackstageQuality));
		
		app.updateQuality();
		
		assertEquals(BACKSTAGE, app.getItem(0).getName());
		assertEquals(expectedBackstageQuality, app.getItem(0).getQuality());
		assertEquals(expectedBackstageQuality, app.getItem(1).getQuality());
	}

	@Test
	public void should_dropQualityTo0_when_backstageAndAfterTheConcert() {
		int initialBackstageQuality = 50;
		int expectedBackstageQuality = 0;
		
		app.addItem(new Item(BACKSTAGE, -1, initialBackstageQuality));
		
		app.updateQuality();
		
		assertEquals(BACKSTAGE, app.getItem(0).getName());
		assertEquals(expectedBackstageQuality, app.getItem(0).getQuality());
	}

	@Test
	public void should_decreaseInQuality_when_otherItem() {
		int initialOtherItemQuality = 10;
		int expectedOtherItemQuality = initialOtherItemQuality - 1;
		
		int initialOtherItemSellIn = 10;
		int expectedOtherItemSellIn = initialOtherItemSellIn - 1;
		
		app.addItem(new Item(OTHER_ITEM, initialOtherItemSellIn, initialOtherItemQuality));

		app.updateQuality();
		
		assertEquals(OTHER_ITEM, app.getItem(0).getName());
		assertEquals(expectedOtherItemQuality, app.getItem(0).getQuality());
		assertEquals(expectedOtherItemSellIn, app.getItem(0).getSellIn());
	}

	@Test
	public void should_decreaseInQuality_when_otherItemAndSellInPassed() {
		int initialOtherItemQuality = 10;
		int expectedOtherItemQuality = initialOtherItemQuality - 2;
		
		app.addItem(new Item(OTHER_ITEM, -1, initialOtherItemQuality));

		app.updateQuality();
		
		assertEquals(OTHER_ITEM, app.getItem(0).getName());
		assertEquals(expectedOtherItemQuality, app.getItem(0).getQuality());
	}
	
	@Test
	public void should_decreaseInQuality_when_conjured() {
		int initialQuality = 10;

		app.addItem(new Item(OTHER_ITEM, 10, initialQuality));
		app.addItem(new Item(CONJURED, 10, initialQuality));
		
		app.updateQuality();
		
		assertEquals(OTHER_ITEM, app.getItem(0).getName());
		assertEquals(CONJURED, app.getItem(1).getName());

		int expectedOtherItemQuality = initialQuality - 1;
		int expectedConjuredQuality = initialQuality - 2;
		
		assertEquals(expectedOtherItemQuality, app.getItem(0).getQuality());
		assertEquals(expectedConjuredQuality, app.getItem(1).getQuality());
	}
	
	@Test
	public void should_decreaseInQuality_when_conjuredAndSellInPassed() {
		int initialQuality = 10;

		app.addItem(new Item(OTHER_ITEM, -1, initialQuality));
		app.addItem(new Item(CONJURED, -1, initialQuality));
		
		app.updateQuality();
		
		assertEquals(OTHER_ITEM, app.getItem(0).getName());
		assertEquals(CONJURED, app.getItem(1).getName());

		int expectedOtherItemQuality = initialQuality - 2;
		int expectedConjuredQuality = initialQuality - 4;
		
		assertEquals(expectedOtherItemQuality, app.getItem(0).getQuality());
		assertEquals(expectedConjuredQuality, app.getItem(1).getQuality());
	}
	
	@Test
	public void should_compare_with_legacy() {
		ItemLegacy[] itemsLegacy = new ItemLegacy[] {
                new ItemLegacy("+5 Dexterity Vest", 10, 20),
                new ItemLegacy("Aged Brie", 2, 0),
                new ItemLegacy("Elixir of the Mongoose", 5, 7),
                new ItemLegacy("Sulfuras, Hand of Ragnaros", 0, 80),
                new ItemLegacy("Sulfuras, Hand of Ragnaros", -1, 80),
                new ItemLegacy("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new ItemLegacy("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new ItemLegacy("Backstage passes to a TAFKAL80ETC concert", -1, 49),
                new ItemLegacy("Backstage passes to a TAFKAL80ETC concert", 5, 49)};

		List<Item> items = Arrays.asList(
				new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", -1, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49));
		
		GildedRoseLegacy appLegacy = new GildedRoseLegacy(itemsLegacy);
	
		for (Item item : items) {
			app.addItem(item);
		}
		
		int days = 100;
		for (int i = 0; i < days; i++) {
			appLegacy.updateQuality();
			app.updateQuality();
		}
		
		for (int i = 0; i < items.size(); i++) {
			assertEquals(appLegacy.items[i].name, app.getItem(i).getName());
			assertEquals(appLegacy.items[i].quality, app.getItem(i).getQuality());
			assertEquals(appLegacy.items[i].sellIn, app.getItem(i).getSellIn());
		}
	}
}
