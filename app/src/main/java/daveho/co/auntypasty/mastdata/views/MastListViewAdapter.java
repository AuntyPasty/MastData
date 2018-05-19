package daveho.co.auntypasty.mastdata.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import daveho.co.auntypasty.mastdata.R;
import daveho.co.auntypasty.mastdata.models.MastDataItem;

public class MastListViewAdapter extends RecyclerView.Adapter<MastListViewAdapter.MastViewHolder> {


    private MastViewHolder mMastViewHolder;
    private List<MastDataItem> mMastList;

    public MastListViewAdapter(List<MastDataItem> mMastList) {
        this.mMastList = mMastList;
    }

    @Override
    public MastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mast_item, parent, false);

        mMastViewHolder = new MastViewHolder(view);

        return mMastViewHolder;
    }

    @Override
    public void onBindViewHolder(MastViewHolder holder, int position) {

        MastDataItem mastDataItem = mMastList.get(position);

        holder.nameView.setText(mastDataItem.getPropertyName());
        holder.address1View.setText(mastDataItem.getAddress1());
        holder.address2View.setText(mastDataItem.getAddress2());
        holder.address3View.setText(mastDataItem.getAddress3());
        holder.address4View.setText(mastDataItem.getAddress4());
        holder.unitNameView.setText(mastDataItem.getUnitName());
        holder.tenantNameView.setText(mastDataItem.getTenantName());
        holder.leaseStartView.setText(mastDataItem.getLeaseStart());
        holder.leaseEndView.setText(mastDataItem.getLeaseEnd());
        holder.leaseYearsView.setText(mastDataItem.getLeaseYears());
        holder.rentView.setText(mastDataItem.getCurrentRent());
    }

    @Override
    public int getItemCount() {
        // Not really doing anything useful here yet.
        return mMastList.size();
    }

    static class MastViewHolder extends RecyclerView.ViewHolder {

        TextView nameView;
        TextView address1View;
        TextView address2View;
        TextView address3View;
        TextView address4View;
        TextView unitNameView;
        TextView tenantNameView;
        TextView leaseStartView;
        TextView leaseEndView;
        TextView leaseYearsView;
        TextView rentView;

        MastViewHolder(View view) {
            super(view);

            nameView = view.findViewById(R.id.name);
            address1View = view.findViewById(R.id.address1);
            address2View = view.findViewById(R.id.address2);
            address3View = view.findViewById(R.id.address3);
            address4View = view.findViewById(R.id.address4);
            unitNameView = view.findViewById(R.id.unit_name);
            tenantNameView = view.findViewById(R.id.tenant_name);
            leaseStartView = view.findViewById(R.id.lease_start);
            leaseEndView = view.findViewById(R.id.lease_end);
            leaseYearsView = view.findViewById(R.id.lease_years);
            rentView = view.findViewById(R.id.rent);
        }
    }
}
