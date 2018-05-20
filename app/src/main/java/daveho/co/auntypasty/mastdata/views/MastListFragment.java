package daveho.co.auntypasty.mastdata.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import daveho.co.auntypasty.mastdata.presenters.MastDataPresenter;
import daveho.co.auntypasty.mastdata.R;
import daveho.co.auntypasty.mastdata.models.MastDataItem;

import static daveho.co.auntypasty.mastdata.modules.ApplicationModule.applicationContext;
import static daveho.co.auntypasty.mastdata.modules.MastDataRepositoryModule.mastDataRepository;

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

        recyclerView = v.findViewById(R.id.mast_list);
        recyclerView.addItemDecoration(new ListDividerItemDecoration(getActivity()));

        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        if (mMastListViewAdapter == null) {
            mMastListViewAdapter = new MastListViewAdapter(getActivity(), mMastList);
        }
        recyclerView.setAdapter(mMastListViewAdapter);

        mMastDataPresenter = new MastDataPresenter(applicationContext(), this, mastDataRepository());

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
}
