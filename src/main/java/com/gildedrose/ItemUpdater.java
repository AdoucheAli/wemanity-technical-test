package com.gildedrose;

import java.util.function.Consumer;
import java.util.stream.Stream;

public enum ItemUpdater implements Consumer<Item>{
	
	SULFURAS("Sulfuras, Hand of Ragnaros", item -> {}),
	BACKSTAGE("Backstage passes to a TAFKAL80ETC concert", ItemUpdater::updateBackstage),
	AGED_BRIE("Aged Brie", ItemUpdater::updateAgedBrie),
	CONJURED("Conjured Mana Cake", ItemUpdater::updateConjured),
	DEFAULT(null,ItemUpdater::updateDefault);
	
	private final String label;
	private final Consumer<Item> consumer;
	
	private ItemUpdater(final String label, final Consumer<Item> consumer) {
		this.label = label;
		this.consumer = consumer;
	}

	@Override
	public void accept(Item item) {
		consumer.accept(item);
		
	}
	
	public static ItemUpdater getItemUpdaterByLabel(String label) {
		return Stream
				.of(ItemUpdater.values())
				.filter(itemUpdater -> itemUpdater.label != null)
				.filter(itemUpdater -> itemUpdater.label.equals(label))
				.findFirst()
				.orElse(DEFAULT);
		
	}
	
	private static void updateDefault(Item item) {
		item.setSellIn(item.getSellIn() - 1);
		decreaseQuality(item);
	}
	
	private static void updateConjured(Item item) {
		item.setSellIn(item.getSellIn() - 1);
		decreaseQuality(item);
		decreaseQuality(item);
	}
	
	private static void updateAgedBrie(Item item) {
		item.setSellIn(item.getSellIn() - 1);
		increaseItemQuality(item);
		
		if (item.getSellIn() < 0) {
			increaseItemQuality(item);
		}
	}
	
	private static void updateBackstage(Item item) {
		
		item.setSellIn(item.getSellIn() - 1);
		
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
	}
	
	private static void decreaseQuality(Item item) {
		decreaseItemQuality(item);
		
		if (item.getSellIn() < 0) {
			decreaseItemQuality(item);
		}
	}

	private static void increaseItemQuality(Item item) {
		if(item.getQuality() < 50) {
			item.setQuality(item.getQuality() + 1);
		}
	}
	
	private static void decreaseItemQuality(Item item) {
		if(item.getQuality() > 0) {
			item.setQuality(item.getQuality() - 1);
		}
	}
}


