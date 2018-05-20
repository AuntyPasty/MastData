package daveho.co.auntypasty.mastdata.repository;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

public class MastCsvParser {

    private static final String TAG = MastCsvParser.class.getSimpleName();

    Context mContext;

    public MastCsvParser(Context context) {
        this.mContext = context;
    }

    /**
     *
     */
    public List<String[]> parseCsvFile() {

        AssetManager assetManager = mContext.getResources().getAssets();

        try {
            InputStream is = mContext.getAssets().open("MobilePhoneMasts.csv");
            InputStreamReader reader = new InputStreamReader(is, Charset.forName("UTF-8"));
            List<String[]> csv = new CSVReader(reader).readAll();

            return csv;
        }
        catch (Exception e) {
            Log.d(TAG, "Exception trying to parse CSV file" + e.getLocalizedMessage());
        }

        return null;
    }
}
