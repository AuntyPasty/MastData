package daveho.co.auntypasty.mastdata.repository;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import daveho.co.auntypasty.mastdata.models.MastDataItem;

import static daveho.co.auntypasty.mastdata.modules.ApplicationModule.applicationContext;

public class MastDataRepository {

    private static final String TAG = MastDataRepository.class.getSimpleName();

    Context mContext;

    ArrayList<MastDataItem> mMastDataList = new ArrayList<>();

    public MastDataRepository() {
        this(applicationContext());
    }

    public MastDataRepository(Context context) {
        this.mContext = context;

        MastCsvParser mastCsvParser = new MastCsvParser(mContext);
        List<String[]> list = mastCsvParser.parseCsvFile();

        mMastDataList.clear();

        if (list != null && !list.isEmpty()) {
            mMastDataList.addAll(getListFromDataFile(list));
        }

    }

    public ArrayList<MastDataItem> getMastDataList() {
        return mMastDataList;
    }

    // Really only public for unit test
    public ArrayList<MastDataItem> getListFromDataFile(List<String[]> mastStringArray) {

        ArrayList<MastDataItem> result = new ArrayList<>();

        for (String[] line: mastStringArray) {

            try {
                MastDataItem item = new MastDataItem();
                item.setPropertyName(line[0]);
                item.setAddress1(line[1]);
                item.setAddress2(line[2]);
                item.setAddress3(line[3]);
                item.setAddress4(line[4]);
                item.setUnitName(line[5]);
                item.setTenantName(line[6]);
                item.setLeaseStart(line[7]);
                item.setLeaseEnd(line[8]);
                item.setLeaseYears(line[9]);
                item.setCurrentRent(line[10]);

                result.add(item);
            }
            catch (Exception e) {
                Log.e(TAG, "Exception generating MastList from data");
            }
        }
        // Remove the first line since it i the headers
        if (!result.isEmpty()) {
            result.remove(0);
        }

        return result;
    }
}
