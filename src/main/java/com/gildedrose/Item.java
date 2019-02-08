package com.gildedrose;

import java.io.Serializable;

public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;

    private int sellIn;

    private int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }
    
   public String getName() {
		return name;
	}

	public int getSellIn() {
		return sellIn;
	}

	public int getQuality() {
		return quality;
	}

	public void setSellIn(int sellIn) {
		this.sellIn = sellIn;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	@Override
	public String toString() {
        return String.join(", ",this.name, String.valueOf(this.sellIn), String.valueOf(this.quality));
    }
}
