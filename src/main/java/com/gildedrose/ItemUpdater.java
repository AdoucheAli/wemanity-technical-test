package com.gildedrose;

import java.util.function.Consumer;
import java.util.stream.Stream;

public enum ItemUpdater implements Consumer<Item> {

	SULFURAS("Sulfuras, Hand of Ragnaros", item -> {}), 
	BACKSTAGE("Backstage passes to a TAFKAL80ETC concert", ItemUpdater::updateBackstage), 
	AGED_BRIE("Aged Brie", ItemUpdater::updateAgedBrie), 
	CONJURED("Conjured Mana Cake", ItemUpdater::updateConjured), 
	DEFAULT(null, ItemUpdater::updateDefault);

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
		decreaseSellIn(item);
		decreaseQuality(item, 1);
	}

	private static void updateConjured(Item item) {
		decreaseSellIn(item);
		decreaseQuality(item, 2);
	}

	private static void updateAgedBrie(Item item) {
		decreaseSellIn(item);
		increaseQuality(item, 1);
	}

	private static void updateBackstage(Item item) {

		decreaseSellIn(item);

		if (item.getSellIn() < 0) {
			item.setQuality(0);
		} else {
			int times = getIncreseQUalityTimes(item);
			increaseQuality(item, times);
		}
	}

	private static int getIncreseQUalityTimes(Item item) {
		if (item.getSellIn() < 5) {
			return 3;
		}

		if (item.getSellIn() < 10) {
			return 2;
		}

		return 1;
	}

	private static void decreaseSellIn(Item item) {
		item.setSellIn(item.getSellIn() - 1);
	}

	private static void increaseQuality(Item item, int times) {

		for (int i = 0; i < times; i++) {
			increaseItemQuality(item);

			if (item.getSellIn() < 0) {
				increaseItemQuality(item);
			}
		}
	}

	private static void decreaseQuality(Item item, int times) {

		for (int i = 0; i < times; i++) {
			decreaseItemQuality(item);

			if (item.getSellIn() < 0) {
				decreaseItemQuality(item);
			}
		}
	}

	private static void increaseItemQuality(Item item) {
		if (item.getQuality() < GildedRose.QUALITY_MAX) {
			item.setQuality(item.getQuality() + 1);
		}
	}

	private static void decreaseItemQuality(Item item) {
		if (item.getQuality() > GildedRose.QUALITY_MIN) {
			item.setQuality(item.getQuality() - 1);
		}
	}
}