package daveho.co.auntypasty.mastdata.views;

import java.util.ArrayList;

import daveho.co.auntypasty.mastdata.models.MastDataItem;

public interface RentalsView {

    void showItemsInGivenDateRange(ArrayList<MastDataItem> list);
}
