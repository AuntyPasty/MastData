package daveho.co.auntypasty.mastdata;

import android.content.Context;

import java.util.ArrayList;

import daveho.co.auntypasty.mastdata.models.MastDataItem;
import daveho.co.auntypasty.mastdata.repository.MastDataRepository;
import daveho.co.auntypasty.mastdata.views.MastListFragment;
import daveho.co.auntypasty.mastdata.views.MastListView;

public class MastDataPresenter {

    private static final String TAG = MastDataPresenter.class.getSimpleName();

    Context mContext;
    MastListView mMastListView;
    MastDataRepository mMastDataRepository;

    
    public MastDataPresenter(Context context, MastListFragment mastListView, MastDataRepository mastDataRepository) {
        this.mContext = context;
        this.mMastListView = mastListView;
        this.mMastDataRepository = mastDataRepository;
    }
    
    public void getMastListFromStorageToShow() {

        ArrayList<MastDataItem> mastList = mMastDataRepository.getMastDataList();
        mMastListView.showTop5MastList(mastList);
    }
    
}
