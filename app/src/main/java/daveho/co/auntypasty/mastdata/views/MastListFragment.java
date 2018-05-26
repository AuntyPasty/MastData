package daveho.co.auntypasty.mastdata.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import daveho.co.auntypasty.mastdata.presenters.MastDataPresenter;
import daveho.co.auntypasty.mastdata.R;
import daveho.co.auntypasty.mastdata.models.MastDataItem;

/**
 * Fragment to display the main mast data list. Only the top 5
 * items are required. Also displays teh total rent of the top five items and a
 * toggle button to change the ordering of the list
 */
public class MastListFragment extends Fragment implements MastListView {

    private static final String TAG = MastListFragment.class.getSimpleName();

    protected RecyclerView recyclerView;
    protected LinearLayoutManager linearLayoutManager;
    private MastListViewAdapter mMastListViewAdapter;
    private ArrayList<MastDataItem> mMastList = new ArrayList<>();

    private Button sortToggleButton;
    private TextView rentView;

    private MastDataPresenter mMastDataPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.mast_list_fragment, container, false);

        recyclerView = v.findViewById(R.id.mast_list);

        rentView = v.findViewById(R.id.total_rent);

        sortToggleButton = v.findViewById(R.id.sort_toggle);

        sortToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sortToggleButton.getText().toString().equalsIgnoreCase("Ascending")) {
                    mMastDataPresenter.getSortedShortList(true);
                    sortToggleButton.setText("Descending");
                } else {
                    mMastDataPresenter.getSortedShortList(false);
                    sortToggleButton.setText("Ascending");
                }
            }
        });

        recyclerView.addItemDecoration(new ListDividerItemDecoration(getActivity()));

        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        if (mMastListViewAdapter == null) {
            mMastListViewAdapter = new MastListViewAdapter(getActivity(), mMastList);
        }
        recyclerView.setAdapter(mMastListViewAdapter);

        mMastDataPresenter = new MastDataPresenter(this);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        mMastDataPresenter.getMastListFromStorageToShow();
    }

    @Override
    public void showTop5MastList(ArrayList<MastDataItem> list) {

        mMastList.clear();
        mMastList.addAll(list);

        mMastListViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showTotalRent(float rent) {
        rentView.setText("Total Rent: " + Float.toString(rent));
    }

    public void updateContents() {
        Log.d(TAG, "UpdateContents called");
        mMastDataPresenter.getMastListFromStorageToShow();
    }
}
