package daveho.co.auntypasty.mastdata.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import daveho.co.auntypasty.mastdata.MastCsvParser;
import daveho.co.auntypasty.mastdata.MastDataPresenter;
import daveho.co.auntypasty.mastdata.R;
import daveho.co.auntypasty.mastdata.models.MastDataItem;

public class MastListFragment extends Fragment implements MastListView {

    protected RecyclerView recyclerView;
    protected LinearLayoutManager linearLayoutManager;
    private MastListViewAdapter mMastListViewAdapter;
    private ArrayList<MastDataItem> mMastList = new ArrayList<>();

    private MastDataPresenter mMastDataPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.mast_list_fragment, container, false);

        recyclerView = v.findViewById(R.id.list);
        recyclerView.setHasFixedSize(false);

        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        if (mMastListViewAdapter == null) {
            mMastListViewAdapter = new MastListViewAdapter(mMastList);
        }
        recyclerView.setAdapter(mMastListViewAdapter);

        mMastDataPresenter = new MastDataPresenter(getActivity(), this, null, null);

        MastCsvParser mastCsvParser = new MastCsvParser(getActivity());
        List<String[]> csvList = mastCsvParser.parseCsvFile();

        mMastDataPresenter.getListFromDataFile(csvList);

        return v;
    }

    @Override
    public void showTop5MastList(ArrayList<MastDataItem> list) {
        mMastList = list;
        mMastListViewAdapter.notifyDataSetChanged();

    }
}
