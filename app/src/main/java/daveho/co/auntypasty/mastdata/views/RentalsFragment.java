package daveho.co.auntypasty.mastdata.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import daveho.co.auntypasty.mastdata.R;
import daveho.co.auntypasty.mastdata.models.MastDataItem;
import daveho.co.auntypasty.mastdata.presenters.RentalsPresenter;

/**
 * Fragment to show the list of items within the required date range
 */
public class RentalsFragment extends Fragment implements RentalsView {

    private static final String TAG = RentalsFragment.class.getSimpleName();

    protected RecyclerView recyclerView;
    protected LinearLayoutManager linearLayoutManager;
    private MastListViewAdapter mMastListViewAdapter;
    private ArrayList<MastDataItem> mMastList = new ArrayList<>();

    private RentalsPresenter mRentalsPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.rentals_list_fragment, container, false);

        recyclerView = v.findViewById(R.id.rentals_list);
        //recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new ListDividerItemDecoration(getActivity()));

        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        if (mMastListViewAdapter == null) {
            mMastListViewAdapter = new MastListViewAdapter(getActivity(), mMastList);
        }
        recyclerView.setAdapter(mMastListViewAdapter);

        mRentalsPresenter = new RentalsPresenter(this);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        mRentalsPresenter.showListWithinDateRange();
    }

    @Override
    public void showItemsInGivenDateRange(ArrayList<MastDataItem> list) {
        mMastList.clear();
        mMastList.addAll(list);

        mMastListViewAdapter.notifyDataSetChanged();
    }

    public void updateContents() {
        Log.d(TAG, "UpdateContents called");
        mRentalsPresenter.showListWithinDateRange();
    }
}
