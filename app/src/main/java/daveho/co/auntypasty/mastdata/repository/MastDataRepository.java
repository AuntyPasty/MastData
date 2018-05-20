package daveho.co.auntypasty.mastdata.repository;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import daveho.co.auntypasty.mastdata.models.MastDataItem;

import static daveho.co.auntypasty.mastdata.modules.ApplicationModule.applicationContext;

/**
 * This class acts as th repository. It uses a CSV parser to extract the data from the CSV file
 * converts it to an object and maintains an internal list.
 * For this sample there is no need for persistent storage solutions.
 */
public class MastDataRepository {

    private static final String TAG = MastDataRepository.class.getSimpleName();

    private Context mContext;

    private ArrayList<MastDataItem> mMastDataList = new ArrayList<>();

    /**
     * Normal constructor
     */
    public MastDataRepository() {
        this(applicationContext());
    }

    /**
     * Constructor allowing the context to be injected.
     * @param context The context
     */
    public MastDataRepository(Context context) {
        this.mContext = context;

        MastCsvParser mastCsvParser = new MastCsvParser(mContext);
        List<String[]> list = mastCsvParser.parseCsvFile();

        mMastDataList.clear();

        if (list != null && !list.isEmpty()) {
            mMastDataList.addAll(getListFromDataFile(list));
        }
    }

    /**
     * Getter for latest mast data
     * @return list of mast data
     */
    public ArrayList<MastDataItem> getMastDataList() {
        return mMastDataList;
    }

    // Really only public for unit test
    /**
     * Used to convert the data from the read CSV fie into a list of objects
     * @param mastStringArray CSV data
     * @return list of objects
     */
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

    /**
     * Adds a new entry into the repository
     * @param item new Mast data item
     */
    public void addNewMast(MastDataItem item) {
        Log.d(TAG, "New mast Data added to repository");
        mMastDataList.add(item);
    }
}
