package com.jth.betonandroid.entities;

import com.jth.betonandroid.enums.Side;
import com.jth.betonandroid.enums.OrderStatus;
import com.jth.betonandroid.enums.PersistenceType;
import com.jth.betonandroid.enums.OrderType;

import java.util.Date;
import java.util.List;

public class ClearedOrderSummaryReport {
    public List<ClearedOrderSummary> getClearedOrders() {
            return clearedOrders;
    }

    public void setClearedOrders(List<ClearedOrderSummary> clearedOrders) {
        this.clearedOrders = clearedOrders;
    }

    public boolean isMoreAvailable() {
        return moreAvailable;
    }

    public void setMoreAvailable(boolean moreAvailable) {
        this.moreAvailable = moreAvailable;
    }

    private boolean moreAvailable; 
    private List<ClearedOrderSummary> clearedOrders;
}

