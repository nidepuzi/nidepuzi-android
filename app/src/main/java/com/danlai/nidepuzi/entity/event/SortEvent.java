package com.danlai.nidepuzi.entity.event;

/**
 * Created by wisdom on 17/3/18.
 */

public class SortEvent {
    private boolean sortByPrice;

    public SortEvent(boolean sortByPrice) {
        this.sortByPrice = sortByPrice;
    }


    public boolean isSortByPrice() {
        return sortByPrice;
    }

    public void setSortByPrice(boolean sortByPrice) {
        this.sortByPrice = sortByPrice;
    }
}
