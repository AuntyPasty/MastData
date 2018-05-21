package daveho.co.auntypasty.mastdata.views;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import daveho.co.auntypasty.mastdata.R;
import daveho.co.auntypasty.mastdata.models.MastDataItem;
import io.reactivex.annotations.Nullable;

/**
 * Dialog fragment which appears when the FAB is pressed. It is used to
 * submit data for a new mast.
 */
public class NewMastDataFragment extends DialogFragment {

    private EditText nameEdit;
    private EditText address1Edit;
    private EditText address2Edit;
    private EditText address3Edit;
    private EditText address4Edit;
    private EditText unitNameEdit;
    private EditText tenantNameEdit;
    private EditText leaseStartEdit;
    private EditText leaseEndEdit;
    private EditText leaseYearsEdit;
    private EditText rentEdit;

    private Button submitButton;

    private SubmitNewMastListener mCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.new_mast_data_fragment, container);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().setTitle("Submit New Mast");

        nameEdit = view.findViewById(R.id.edit_name);
        address1Edit = view.findViewById(R.id.edit_address1);
        address2Edit = view.findViewById(R.id.edit_address2);
        address3Edit = view.findViewById(R.id.edit_address3);
        address4Edit = view.findViewById(R.id.edit_address4);
        unitNameEdit = view.findViewById(R.id.edit_unit_name);
        tenantNameEdit = view.findViewById(R.id.edit_tenant_name);
        leaseStartEdit = view.findViewById(R.id.edit_lease_start);
        leaseEndEdit = view.findViewById(R.id.edit_lease_end);
        leaseYearsEdit = view.findViewById(R.id.edit_lease_years);
        rentEdit = view.findViewById(R.id.edit_rent);

        // Set focus on the first field
        nameEdit.requestFocus();


        submitButton = view.findViewById(R.id.submit_new_mast);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEdit.getText().toString();
                String rent = rentEdit.getText().toString();
                String address1 = address1Edit.getText().toString();
                String address2 = address2Edit.getText().toString();
                String address3 = address3Edit.getText().toString();
                String address4 = address4Edit.getText().toString();
                String unitName = unitNameEdit.getText().toString();
                String tenantName = tenantNameEdit.getText().toString();
                String leaseStart = leaseStartEdit.getText().toString();
                String leaseEnd = leaseEndEdit.getText().toString();
                String leaseYears = leaseYearsEdit.getText().toString();

                MastDataItem mastDataItem = new MastDataItem();
                mastDataItem.setPropertyName(name);
                mastDataItem.setAddress1(address1);
                mastDataItem.setAddress2(address2);
                mastDataItem.setAddress3(address3);
                mastDataItem.setAddress4(address4);
                mastDataItem.setUnitName(unitName);
                mastDataItem.setTenantName(tenantName);
                mastDataItem.setLeaseStart(leaseStart);
                mastDataItem.setLeaseEnd(leaseEnd);
                mastDataItem.setLeaseYears(leaseYears);
                mastDataItem.setCurrentRent(rent);

                mCallback.onSubmitMast(mastDataItem);

                dismiss();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        // This is to make the dialog fo full width.
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallback = (SubmitNewMastListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }
}
