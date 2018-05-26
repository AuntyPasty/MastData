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
import daveho.co.auntypasty.mastdata.models.TenantMast;
import daveho.co.auntypasty.mastdata.presenters.TenantMastCountPresenter;

import static daveho.co.auntypasty.mastdata.modules.ApplicationModule.applicationContext;

/**
 * Fragment to show teh list of tenants and their mast count.
 */
public class TenantsFragment extends Fragment implements TenantsView {

    private static final String TAG = TenantsFragment.class.getSimpleName();

    protected RecyclerView recyclerView;
    protected LinearLayoutManager linearLayoutManager;
    private TenantsListViewAdapter mTenantsListViewAdapter;
    private ArrayList<TenantMast> mTenantList = new ArrayList<>();

    private TenantMastCountPresenter mTenantMastCountPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tenant_list_fragment, container, false);

        recyclerView = v.findViewById(R.id.tenant_list);
        recyclerView.addItemDecoration(new ListDividerItemDecoration(getActivity()));

        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        if (mTenantsListViewAdapter == null) {
            mTenantsListViewAdapter = new TenantsListViewAdapter(applicationContext(), mTenantList);
        }
        recyclerView.setAdapter(mTenantsListViewAdapter);

        mTenantMastCountPresenter = new TenantMastCountPresenter( this);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        mTenantMastCountPresenter.getTenantsMastCountList();
    }

    @Override
    public void showTenantMastCountList(ArrayList<TenantMast> list) {
        mTenantList.clear();
        mTenantList.addAll(list);
        mTenantsListViewAdapter.notifyDataSetChanged();
    }

    public void updateContents() {
        Log.d(TAG, "UpdateContents called");
        mTenantMastCountPresenter.getTenantsMastCountList();
    }
}
