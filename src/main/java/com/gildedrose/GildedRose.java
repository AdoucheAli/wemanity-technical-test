package com.gildedrose;

import java.util.ArrayList;
import java.util.List;

class GildedRose {

	public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
	public static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
	public static final String AGED_BRIE = "Aged Brie";
	public static final String CONJURED = "Conjured Mana Cake";
	
	private List<Item> items;

	public GildedRose() {
		items = new ArrayList<>();
	}

	public Item getItem(int index) {
		return items.get(index);
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public void updateQuality() {
		items.forEach(this::updateQualityItem);
	}

	private void updateQualityItem(Item item) {
		ItemUpdater
			.getItemUpdaterByLabel(item.getName())
			.accept(item);
	}
}