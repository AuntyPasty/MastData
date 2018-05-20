package daveho.co.auntypasty.mastdata.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import daveho.co.auntypasty.mastdata.R;
import daveho.co.auntypasty.mastdata.models.TenantMast;

public class TenantsFragment extends Fragment implements TenantsView {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, 2));
        return rootView;
    }

    @Override
    public void showTenantMastCountList(ArrayList<TenantMast> list) {
        //TODO Create a layout for this.
    }
}
