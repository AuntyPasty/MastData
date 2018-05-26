package daveho.co.auntypasty.mastdata.presenters;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import daveho.co.auntypasty.mastdata.models.MastDataItem;
import daveho.co.auntypasty.mastdata.repository.MastDataRepository;
import daveho.co.auntypasty.mastdata.views.MastDataSubmissionView;

import static daveho.co.auntypasty.mastdata.modules.MastDataRepositoryModule.mastDataRepository;

/**
 * Responsible for validating a mast submission, adding to the respository and notifying the view of the results.
 */
public class MastSubmissionPresenter {

    private static final String TAG = MastSubmissionPresenter.class.getSimpleName();

    private MastDataSubmissionView mMastDataSubmissionView;
    private MastDataRepository mMastDataRepository;

    public MastSubmissionPresenter(MastDataSubmissionView mastDataSubmissionView) {
        this(mastDataSubmissionView, mastDataRepository());
    }
    public MastSubmissionPresenter(MastDataSubmissionView mastDataSubmissionView, MastDataRepository mMastDataRepository) {
        this.mMastDataSubmissionView = mastDataSubmissionView;
        this.mMastDataRepository = mMastDataRepository;
    }

    /**
     * Takes new mast data and if it's ok, adds it to the repository
     * and notifys the result to the view
     * @param item mast data
     */
    public void addNewMastData(MastDataItem item) {

        boolean isValid = mastDataIsValid(item);
        if (isValid) {
            mMastDataRepository.addNewMast(item);
        }

        mMastDataSubmissionView.notifySubmissionResult(isValid);
    }

    /**
     * Checks that the required fields are present and in the correct format
     * @param item new mast item
     * @return the result
     */
    public boolean mastDataIsValid(MastDataItem item) {

        //Check required fields are present
        if (item.getPropertyName().isEmpty() ||
                item.getCurrentRent().isEmpty() ||
                item.getTenantName().isEmpty() ||
                item.getLeaseStart().isEmpty() ||
                item.getLeaseEnd().isEmpty()) {

            return false;
        }

        //All required fields are present. Now check for correct format
        //Check date formats by attempting to parse.
        SimpleDateFormat requiredDateFormat = new SimpleDateFormat("dd MMM yyyy");

        try {
            Date dateResult = requiredDateFormat.parse(item.getLeaseStart());
            dateResult = requiredDateFormat.parse(item.getLeaseEnd());
        }
        catch (Exception e) {
            Log.d(TAG, "Error with the submitted dates");
            return false;
        }

        // Check the rent is a number
        try {
            float rentResult = Float.valueOf(item.getCurrentRent());
        }
        catch (Exception e1) {
            Log.d(TAG, "Rent data is invalid");
            return false;
        }

        return true;
    }


}
