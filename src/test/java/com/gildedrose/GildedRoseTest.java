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

	private static final String DEXTERITY_VEST = "+5 Dexterity Vest";

	private final static String ELIXIR = "Elixir of the Mongoose";

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
		
		assertItem(app.getItem(0), SULFURAS, SULFURAS_QUALITY, firstSellIn);
		assertItem(app.getItem(1), SULFURAS, SULFURAS_QUALITY, secondSellIn);
	}

	@Test
	public void should_increaseInQuality_when_agedBrieAndQualityLessThan50() {
		int initialAgedBrieQuality = 0;
		int expectedAgedBrieQuality = initialAgedBrieQuality + 1;
		
		int initialAgedBrieSellIn = 2;
		int expectedAgedBrieSellIn = initialAgedBrieSellIn - 1;
		
		app.addItem(new Item(AGED_BRIE, initialAgedBrieSellIn, initialAgedBrieQuality));
		
		app.updateQuality();
		
		assertItem(app.getItem(0), AGED_BRIE, expectedAgedBrieQuality, expectedAgedBrieSellIn);
	}

	@Test
	public void should_keepSameQuality_when_agedBrieAndQualityEquals50() {
		int initialAgedBrieQuality = 50;
		int expectedAgedBrieQuality = initialAgedBrieQuality;
		
		app.addItem(new Item(AGED_BRIE, 2, initialAgedBrieQuality));
		
		app.updateQuality();
		
		assertItem(app.getItem(0), AGED_BRIE, expectedAgedBrieQuality);
	}

	@Test
	public void should_increaseInQualityBy2_when_agedBrieAndSellInPassed() {
		int initialAgedBrieQuality = 2;
		int expectedAgedBrieQuality = initialAgedBrieQuality + 2;
		
		app.addItem(new Item(AGED_BRIE, -1, initialAgedBrieQuality));
		
		app.updateQuality();
		
		assertItem(app.getItem(0), AGED_BRIE, expectedAgedBrieQuality);
	}

	@Test
	public void should_increaseInQuality_when_backstageAndQualityLessThan50() {
		int initialBackstageQuality = 0;
		int expectedBackstageQuality = initialBackstageQuality + 1;
		
		int initialBackstageSellIn = 20;
		int expectedBackstageSellIn = initialBackstageSellIn - 1;
		
		app.addItem(new Item(BACKSTAGE, initialBackstageSellIn, initialBackstageQuality));
		
		app.updateQuality();
		
		assertItem(app.getItem(0), BACKSTAGE, expectedBackstageQuality, expectedBackstageSellIn);		
	}

	@Test
	public void should_keepSameQuality_when_backstageAndQualityEquals50() {
		int initialBackstageQuality = 50;
		int expectedBackstageQuality = initialBackstageQuality;
		
		app.addItem(new Item(BACKSTAGE, 20, initialBackstageQuality));
		
		app.updateQuality();
		
		assertItem(app.getItem(0), BACKSTAGE, expectedBackstageQuality);
	}

	@Test
	public void should_increaseInQualityBy2_when_backstageAndSellInBetween10And6() {
		int initialBackstageQuality = 16;
		int expectedBackstageQuality = initialBackstageQuality + 2;
		
		app.addItem(new Item(BACKSTAGE, 10, initialBackstageQuality));
		app.addItem(new Item(BACKSTAGE, 6, initialBackstageQuality));
		
		app.updateQuality();
		
		assertItem(app.getItem(0), BACKSTAGE, expectedBackstageQuality);
		assertItem(app.getItem(1), BACKSTAGE, expectedBackstageQuality);
	}

	@Test
	public void should_increaseInQualityBy3_when_backstageAndSellInBetween5And1() {
		int initialBackstageQuality = 15;
		int expectedBackstageQuality = initialBackstageQuality + 3;
		
		app.addItem(new Item(BACKSTAGE, 5, initialBackstageQuality));
		app.addItem(new Item(BACKSTAGE, 1, initialBackstageQuality));
		
		app.updateQuality();
		
		assertItem(app.getItem(0), BACKSTAGE, expectedBackstageQuality);
		assertItem(app.getItem(1), BACKSTAGE, expectedBackstageQuality);
	}

	@Test
	public void should_dropQualityTo0_when_backstageAndAfterTheConcert() {
		int initialBackstageQuality = 50;
		int expectedBackstageQuality = 0;
		
		app.addItem(new Item(BACKSTAGE, -1, initialBackstageQuality));
		
		app.updateQuality();
		
		assertItem(app.getItem(0), BACKSTAGE, expectedBackstageQuality);
	}

	@Test
	public void should_decreaseInQuality_when_otherItem() {
		int initialOtherItemQuality = 10;
		int expectedOtherItemQuality = initialOtherItemQuality - 1;
		
		int initialOtherItemSellIn = 10;
		int expectedOtherItemSellIn = initialOtherItemSellIn - 1;
		
		app.addItem(new Item(ELIXIR, initialOtherItemSellIn, initialOtherItemQuality));

		app.updateQuality();
		
		assertItem(app.getItem(0), ELIXIR, expectedOtherItemQuality, expectedOtherItemSellIn);
	}

	@Test
	public void should_decreaseInQuality_when_otherItemAndSellInPassed() {
		int initialOtherItemQuality = 10;
		int expectedOtherItemQuality = initialOtherItemQuality - 2;
		
		app.addItem(new Item(ELIXIR, -1, initialOtherItemQuality));

		app.updateQuality();
		
		assertItem(app.getItem(0), ELIXIR, expectedOtherItemQuality);
	}
	
	@Test
	public void should_decreaseInQuality_when_conjured() {
		int initialQuality = 10;

		app.addItem(new Item(ELIXIR, 10, initialQuality));
		app.addItem(new Item(CONJURED, 10, initialQuality));
		
		app.updateQuality();
		
		assertEquals(ELIXIR, app.getItem(0).getName());
		assertEquals(CONJURED, app.getItem(1).getName());

		int expectedOtherItemQuality = initialQuality - 1;
		int expectedConjuredQuality = initialQuality - 2;
		
		assertItem(app.getItem(0), ELIXIR, expectedOtherItemQuality);
		assertItem(app.getItem(1), CONJURED, expectedConjuredQuality);
	}
	
	@Test
	public void should_decreaseInQuality_when_conjuredAndSellInPassed() {
		int initialQuality = 10;

		app.addItem(new Item(ELIXIR, -1, initialQuality));
		app.addItem(new Item(CONJURED, -1, initialQuality));
		
		app.updateQuality();
		
		assertEquals(ELIXIR, app.getItem(0).getName());
		assertEquals(CONJURED, app.getItem(1).getName());

		int expectedOtherItemQuality = initialQuality - 2;
		int expectedConjuredQuality = initialQuality - 4;
		
		assertItem(app.getItem(0), ELIXIR, expectedOtherItemQuality);
		assertItem(app.getItem(1), CONJURED, expectedConjuredQuality);
	}
	
	@Test
	public void should_compare_with_legacy() {
		ItemLegacy[] itemsLegacy = new ItemLegacy[] {
                new ItemLegacy(DEXTERITY_VEST, 10, 20),
                new ItemLegacy(AGED_BRIE, 2, 0),
                new ItemLegacy(ELIXIR, 5, 7),
                new ItemLegacy(SULFURAS, 0, 80),
                new ItemLegacy(SULFURAS, -1, 80),
                new ItemLegacy(BACKSTAGE, 15, 20),
                new ItemLegacy(BACKSTAGE, 10, 49),
                new ItemLegacy(BACKSTAGE, -1, 49),
                new ItemLegacy(BACKSTAGE, 5, 49)};

		List<Item> items = Arrays.asList(
				new Item(DEXTERITY_VEST, 10, 20),
                new Item(AGED_BRIE, 2, 0),  
                new Item(ELIXIR, 5, 7), 
                new Item(SULFURAS, 0, 80),  
                new Item(SULFURAS, -1, 80), 
                new Item(BACKSTAGE, 15, 20),
                new Item(BACKSTAGE, 10, 49),
                new Item(BACKSTAGE, -1, 49),
                new Item(BACKSTAGE, 5, 49));
		
		GildedRoseLegacy appLegacy = new GildedRoseLegacy(itemsLegacy);
	
		for (Item item : items) {
			app.addItem(item);
		}
		
		int days = 100;
		for (int i = 0; i < days; i++) {
			appLegacy.updateQuality();
			app.updateQuality();
		}
		
		String expectedName = null;
		int expectedQuality;
		int expectedSellIn;
		
		for (int i = 0; i < items.size(); i++) {
			expectedName = appLegacy.items[i].name;
			expectedQuality = appLegacy.items[i].quality;
			expectedSellIn = appLegacy.items[i].sellIn;
			
			assertItem(app.getItem(i), expectedName,expectedQuality, expectedSellIn);
		}
	}
	
	private void assertItem(Item item, String name, int quality, int sellIn) {
		assertThat(item.getName()).isEqualTo(name);
		assertThat(item.getQuality()).isEqualTo(quality);
		assertThat(item.getSellIn()).isEqualTo(sellIn);
	}
	
	private void assertItem(Item item, String name, int quality) {
		assertThat(item.getName()).isEqualTo(name);
		assertThat(item.getQuality()).isEqualTo(quality);
		
	}
}
