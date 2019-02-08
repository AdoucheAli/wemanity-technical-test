package com.gildedrose;

class GildedRose {

	public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
	public static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
	public static final String AGED_BRIE = "Aged Brie";

	private static final int QUALITY_MIN = 0;
	private static final int QUALITY_MAX = 50;

	private Item[] items;

	public GildedRose() {}
	
	public GildedRose(Item[] items) {
		this.items = items;
	}
	
	public Item getItem(int position) {
		return items[position];
	}

	public void setItems(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		for (int i = 0; i < items.length; i++) {

			if(items[i].name.equals(SULFURAS)) {
				continue;
			}
			
			if (items[i].name.equals(BACKSTAGE)) {
				if(items[i].quality < QUALITY_MAX) {
					if (items[i].sellIn > 10) {
						items[i].quality = items[i].quality + 1;
					}else if (items[i].sellIn <= 10 && items[i].sellIn > 5) {
						items[i].quality = items[i].quality + 2;
					}else if (items[i].sellIn <= 5) {
						items[i].quality = items[i].quality + 3;
					}
				}	
			} else if (items[i].name.equals(AGED_BRIE)) {
				if(items[i].quality < QUALITY_MAX) {
					items[i].quality = items[i].quality + 1;
				}
			}else {
				if (items[i].quality > QUALITY_MIN) {
					items[i].quality = items[i].quality - 1;
				}
			}
			
			items[i].sellIn = items[i].sellIn - 1;
			
			if (items[i].sellIn < 0) {
	
				if (items[i].name.equals(BACKSTAGE)) {
					
					items[i].quality = 0;
					
				} else if (items[i].name.equals(AGED_BRIE)) {
					
					if (items[i].quality < QUALITY_MAX) {
						items[i].quality = items[i].quality + 1;
					}
				} else {
					if (items[i].quality > QUALITY_MIN) {
						items[i].quality = items[i].quality - 1;
					}
				}
			}
		}
	}
}