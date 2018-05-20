package daveho.co.auntypasty.mastdata.repository;

import android.content.Context;
import android.util.Log;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Class to use the opencsv library to resad the csv file
 * Does not format the data.
 */
public class MastCsvParser {

    private static final String TAG = MastCsvParser.class.getSimpleName();

    private Context mContext;

    MastCsvParser(Context context) {
        this.mContext = context;
    }

    /**
     *  Parse the csv file. The location is in the assets folder
     */
    public List<String[]> parseCsvFile() {

        try {
            InputStream is = mContext.getAssets().open("MobilePhoneMasts.csv");
            InputStreamReader reader = new InputStreamReader(is, Charset.forName("UTF-8"));
            return new CSVReader(reader).readAll();
        }
        catch (Exception e) {
            Log.d(TAG, "Exception trying to parse CSV file" + e.getLocalizedMessage());
        }

        return null;
    }
}
