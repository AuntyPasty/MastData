package daveho.co.auntypasty.mastdata.presenters;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import daveho.co.auntypasty.mastdata.models.MastDataItem;
import daveho.co.auntypasty.mastdata.repository.MastDataRepository;
import daveho.co.auntypasty.mastdata.views.RentalsView;

import static daveho.co.auntypasty.mastdata.modules.MastDataRepositoryModule.mastDataRepository;

/**
 * Presenter for managing the data within a date range
 * Also changes date format of entries.
 */
public class RentalsPresenter {

    private static final String TAG = RentalsPresenter.class.getSimpleName();

    private RentalsView mRentalsView;
    private MastDataRepository mMastDataRepository;

    public RentalsPresenter(RentalsView rentalsView) {
        this(rentalsView, mastDataRepository());
    }
    public RentalsPresenter(RentalsView rentalsView, MastDataRepository mastDataRepository) {
        this.mRentalsView = rentalsView;
        this.mMastDataRepository = mastDataRepository;
    }

    /**
     * This method passes a list to the view containing a list
     * with lease date between 1 June 1999 and 31 Aug 2007.
     */
    public void showListWithinDateRange() {

        ArrayList<MastDataItem> filteredList = getRentalsFromListInDateRange();
        mRentalsView.showItemsInGivenDateRange(filteredList);
    }

    /**
     * Obtains the mast list from the repository
     * iterates over list and filters out ones within specified date range.
     * @return filtered list of MastDataItem
     */
    public ArrayList<MastDataItem> getRentalsFromListInDateRange() {

        ArrayList<MastDataItem> mastList = mMastDataRepository.getMastDataList();
        ArrayList<MastDataItem> filteredList = new ArrayList<>();

        // The format of the raw data
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");

        try {
            // These are specified date boundaries
            Date startDateRange = dateFormat.parse("01 Jun 1999");
            Date endDateRange = dateFormat.parse("31 Aug 2007");

            //We need to convert the date string to a date and then do a comparision
            for (MastDataItem item : mastList) {

                //String should be in the format dd MMM yyyy
                String leaseStartString = item.getLeaseStart();

                Date date = dateFormat.parse(leaseStartString);

                //Check to see if the date is within the required range. Add to filtered list if it's ok.
                if (date.after(startDateRange) && date.before(endDateRange)) {
                    // We need to convert the dates in the item to DD/MM/YYYY
                    filteredList.add(convertDatesWithinItem(item));
                }
            }

        }
        catch (Exception e) {
            Log.e(TAG, "problem parsing date String. " + e.getLocalizedMessage());
        }

        //Pass back the list. There is no requirement to order by date.
        return filteredList;
    }

    /**
     * Changes the internal lease start and end date formats to required format
     * @param item The mast Data item
     * @return the updated item
     */
    public MastDataItem convertDatesWithinItem(MastDataItem item) {

        SimpleDateFormat oldDateFormat = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date date = oldDateFormat.parse(item.getLeaseStart());
            item.setLeaseStart(newDateFormat.format(date));

            date = oldDateFormat.parse(item.getLeaseEnd());
            item.setLeaseEnd(newDateFormat.format(date));
        }
        catch (Exception e) {
            Log.e(TAG, "Problem converting date formats. " + e.getLocalizedMessage());
            return item;
        }

        return item;
    }
}
