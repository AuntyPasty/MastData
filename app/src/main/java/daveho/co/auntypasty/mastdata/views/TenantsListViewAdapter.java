package daveho.co.auntypasty.mastdata.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import daveho.co.auntypasty.mastdata.R;
import daveho.co.auntypasty.mastdata.models.TenantMast;

/**
 * List Adapter for showing the tenants and the mast Count
 */
public class TenantsListViewAdapter extends RecyclerView.Adapter<TenantsListViewAdapter.TenantsViewHolder> {

    private TenantsViewHolder mTenantsViewHolder;
    private List<TenantMast> mTenantList;
    private Context mContext;

    public TenantsListViewAdapter(Context context, List<TenantMast> tenantList) {
        this.mTenantList = tenantList;
        this.mContext = context;
    }

    @Override
    public TenantsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.tenants_list_item, parent, false);

        mTenantsViewHolder = new TenantsViewHolder(view);

        return mTenantsViewHolder;
    }

    @Override
    public void onBindViewHolder(TenantsViewHolder holder, int position) {

        TenantMast tenantItem = mTenantList.get(position);

        holder.tenantNameView.setText(tenantItem.getTenantName());
        holder.mastCountView.setText(String.valueOf(tenantItem.getMastCount()));
    }

    @Override
    public int getItemCount() {
        return mTenantList.size();
    }

    static class TenantsViewHolder extends RecyclerView.ViewHolder {

        TextView tenantNameView;
        TextView mastCountView;


        TenantsViewHolder(View view) {
            super(view);

            tenantNameView = view.findViewById(R.id.tenant_name);
            mastCountView = view.findViewById(R.id.mast_count);

        }
    }
}
