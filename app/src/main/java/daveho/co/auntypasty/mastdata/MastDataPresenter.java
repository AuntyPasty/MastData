package daveho.co.auntypasty.mastdata;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import daveho.co.auntypasty.mastdata.models.MastDataItem;
import daveho.co.auntypasty.mastdata.views.MastListView;
import daveho.co.auntypasty.mastdata.views.RentalsView;
import daveho.co.auntypasty.mastdata.views.TenantsView;

public class MastDataPresenter {

    private static final String TAG = MastDataPresenter.class.getSimpleName();

    ArrayList mMastDataItemList = new ArrayList();
    Context mContext;
    MastListView mMastListView;
    RentalsView mRentalsView;
    TenantsView mTenantsView;
    
    public MastDataPresenter(Context context, MastListView mastListView, RentalsView rentalsView, TenantsView tenantsView) {
        this.mContext = context;
        this.mMastListView = mastListView;
        this.mRentalsView = rentalsView;
        this.mTenantsView = tenantsView;
    }
    
    public void getListFromDataFile(List<String[]> mastStringArray) {

        for (String[] line: mastStringArray) {
            //TODO Check that the first line is relevant in the array
            // We have to assume that the string array has everything in the right order.

            try {
                MastDataItem item = new MastDataItem();
                item.setPropertyName(line[0]);
                item.setAddress1(line[1]);
                item.setAddress2(line[2]);
                item.setAddress3(line[3]);
                item.setAddress4(line[4]);
                item.setUnitName(line[5]);
                item.setTenantName(line[6]);
                item.setLeaseStart(Integer.parseInt(line[7]));
                item.setLeaseEnd(Integer.parseInt(line[8]));
                item.setLeaseYears(Integer.parseInt(line[9]));
                item.setCurrentRent(Integer.parseInt(line[10]));

                mMastDataItemList.add(item);
            }
            catch (Exception e) {
                Log.e(TAG, "Exception generating MastList from data");
            }
        }
        // Remove the first line since it i the headers
        if (!mMastDataItemList.isEmpty()) {
            mMastDataItemList.remove(0);
        }

        mMastListView.showTop5MastList(mMastDataItemList);
    }
    
}
