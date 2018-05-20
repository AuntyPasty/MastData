package daveho.co.auntypasty.mastdata.presenters;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;

import daveho.co.auntypasty.mastdata.models.MastDataItem;
import daveho.co.auntypasty.mastdata.repository.MastDataRepository;
import daveho.co.auntypasty.mastdata.views.MastListFragment;
import daveho.co.auntypasty.mastdata.views.MastListView;

/**
 * Presenter for managing the lists of mast data items
 */
public class MastDataPresenter {

    private static final String TAG = MastDataPresenter.class.getSimpleName();

    private Context mContext;
    private MastListView mMastListView;
    private MastDataRepository mMastDataRepository;

    private ArrayList<MastDataItem> mShortMastList = new ArrayList<>();

    public MastDataPresenter(Context context, MastListFragment mastListView, MastDataRepository mastDataRepository) {
        this.mContext = context;
        this.mMastListView = mastListView;
        this.mMastDataRepository = mastDataRepository;
    }

    /**
     * Retrieves list from the repository
     * Shortens the list and passes the list to the view
     * Gets the rent data and passes it to the view
     */
    public void getMastListFromStorageToShow() {

        ArrayList<MastDataItem> mastList = mMastDataRepository.getMastDataList();

        mMastListView.showTop5MastList(getTopFiveFromList(mastList));

        // This needs to be called after the top 5 list has been created.
        mMastListView.showTotalRent(getTotalRentFromList(mShortMastList));
    }

    /**
     * With a given raw list, it sorts it in ascending rent order then takes the first 5 as a short list
     * @param rawList the unsorted data
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

    /**
     * Sorts the short top 5 list by Rent, either ascending or descending
     * @param ascending boolean whether to sort ascending or not.
     */
    public void getSortedShortList(boolean ascending) {

        if (!mShortMastList.isEmpty()) {
            Collections.sort(mShortMastList, new LeaseAmountOrderingComparator());

            if (!ascending) {
                Collections.reverse(mShortMastList);
            }
            mMastListView.showTop5MastList(mShortMastList);
        }
    }

    /**
     * Iterates through the given list and sums the rent values
     * @param shortList the list to iterate
     * @return total rent value
     */
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
