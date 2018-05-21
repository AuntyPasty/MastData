package daveho.co.auntypasty.mastdata.views;

/**
 * Interface to notify whether new mast data has been submitted correctly.
 */
public interface MastDataSubmissionView {

    void notifySubmissionResult(boolean success);
}
