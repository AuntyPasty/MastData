package daveho.co.auntypasty.mastdata.presenters;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;

import daveho.co.auntypasty.mastdata.models.MastDataItem;
import daveho.co.auntypasty.mastdata.repository.MastDataRepository;
import daveho.co.auntypasty.mastdata.views.MastListFragment;
import daveho.co.auntypasty.mastdata.views.MastListView;

public class MastDataPresenter {

    private static final String TAG = MastDataPresenter.class.getSimpleName();

    Context mContext;
    MastListView mMastListView;
    MastDataRepository mMastDataRepository;

    ArrayList<MastDataItem> mShortMastList = new ArrayList<>();

    
    public MastDataPresenter(Context context, MastListFragment mastListView, MastDataRepository mastDataRepository) {
        this.mContext = context;
        this.mMastListView = mastListView;
        this.mMastDataRepository = mastDataRepository;
    }
    
    public void getMastListFromStorageToShow() {

        ArrayList<MastDataItem> mastList = mMastDataRepository.getMastDataList();

        mMastListView.showTop5MastList(getTopFiveFromList(mastList));

        // This needs tobe called after the top 5 list has been created.
        mMastListView.showTotalRent(getTotalRentFromList(mShortMastList));
    }

    /**
     * With a given raw list, it sorts it in ascending rent order then takes the first 5 as a short list
     * @param rawList
     * @return top five short list
     */
    public ArrayList<MastDataItem> getTopFiveFromList(ArrayList<MastDataItem> rawList) {
        ArrayList<MastDataItem> shortList = new ArrayList<>();

        //Sort ascending
        Collections.sort(shortList, new LeaseAmountOrderingComparator());

        // We only want to show the first 5 items. We assume the list has been sorted
        for (int i = 0; i < 5; i++) {
            shortList.add(rawList.get(i));
        }

        mShortMastList = shortList;

        return mShortMastList;
    }

    public void getSortedShortList(boolean ascending) {

        if (!mShortMastList.isEmpty()) {
            if (ascending) {
                Collections.sort(mShortMastList, new LeaseAmountOrderingComparator());
            } else {
                Collections.sort(mShortMastList, new LeaseAmountOrderingComparator());
                Collections.reverse(mShortMastList);
            }
            mMastListView.showTop5MastList(mShortMastList);
        }


    }

    public float getTotalRentFromList(ArrayList<MastDataItem> shortList) {

        if (!shortList.isEmpty()) {
            float totalRent = 0f;

            for (MastDataItem item: shortList) {
                float rent = Float.valueOf(item.getCurrentRent());
                totalRent = totalRent + rent;
            }

            return totalRent;
        }

        return 0;
    }


}
