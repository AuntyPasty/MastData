package daveho.co.auntypasty.mastdata.presenters;

import java.util.Comparator;

import daveho.co.auntypasty.mastdata.models.MastDataItem;

public class LeaseAmountOrderingComparator implements Comparator<MastDataItem> {

    @Override
    public int compare(MastDataItem o1, MastDataItem o2) {
        Float lease1 = Float.valueOf(o1.getCurrentRent());
        Float lease2 = Float.valueOf(o2.getCurrentRent());

        return lease1.compareTo(lease2);
    }
}
