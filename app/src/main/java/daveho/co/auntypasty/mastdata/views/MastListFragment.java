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

import daveho.co.auntypasty.mastdata.R;
import daveho.co.auntypasty.mastdata.models.MastDataItem;

public class MastListFragment extends Fragment implements MastListView {

    protected RecyclerView recyclerView;
    protected LinearLayoutManager linearLayoutManager;
    private MastListViewAdapter mMastListViewAdapter;
    private ArrayList<MastDataItem> mMastist = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.mast_list_fragment, container, false);
        TextView textView = v.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, 1));

        recyclerView = v.findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        if (mMastListViewAdapter == null) {
            mMastListViewAdapter = new MastListViewAdapter(mMastist);
        }
        recyclerView.setAdapter(mMastListViewAdapter);


        return v;
    }

    @Override
    public void showTop5MastList(List<MastDataItem> list) {
        mMastist = new ArrayList<>(list);
        mMastListViewAdapter.notifyDataSetChanged();

    }
}
