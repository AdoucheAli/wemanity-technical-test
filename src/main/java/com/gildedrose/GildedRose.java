package com.gildedrose;

import java.util.ArrayList;
import java.util.List;

class GildedRose {

	public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
	public static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
	public static final String AGED_BRIE = "Aged Brie";
	public static final String CONJURED = "Conjured Mana Cake";

	private static final int QUALITY_MIN = 0;
	private static final int QUALITY_MAX = 50;

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
		for (Item item : items) {
			updateQualityItem(item);
		}
	}

	private void updateQualityItem(Item item) {
		if(SULFURAS.equals(item.getName())) {
			return;
		}
		
		item.setSellIn(item.getSellIn() - 1);
		
		if (BACKSTAGE.equals(item.getName())) {
			
			if(item.getSellIn() < 0) {
				item.setQuality(0);
				return;
			}
			
			increaseItemQuality(item);
			
			if (item.getSellIn() < 10) {
				increaseItemQuality(item);
			}
			
			if (item.getSellIn() < 5) {
				increaseItemQuality(item);
			}
			
		} else if (AGED_BRIE.equals(item.getName())) {
			increaseItemQuality(item);
			
			if (item.getSellIn() < 0) {
				increaseItemQuality(item);
			}
		}else {
			decreaseQuality(item);
			
			if (CONJURED.equals(item.getName())) {
				decreaseQuality(item);
			}
		}
		
	}

	private void decreaseQuality(Item item) {
		decreaseItemQuality(item);
		
		if (item.getSellIn() < 0) {
			decreaseItemQuality(item);
		}
	}

	private void increaseItemQuality(Item item) {
		if(item.getQuality() < QUALITY_MAX) {
			item.setQuality(item.getQuality() + 1);
		}
	}
	
	private void decreaseItemQuality(Item item) {
		if(item.getQuality() > QUALITY_MIN) {
			item.setQuality(item.getQuality() - 1);
		}
	}
}