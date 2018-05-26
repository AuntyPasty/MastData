package daveho.co.auntypasty.mastdata.presenters;

import java.util.ArrayList;
import java.util.HashMap;

import daveho.co.auntypasty.mastdata.models.MastDataItem;
import daveho.co.auntypasty.mastdata.models.TenantMast;
import daveho.co.auntypasty.mastdata.repository.MastDataRepository;
import daveho.co.auntypasty.mastdata.views.TenantsView;

import static daveho.co.auntypasty.mastdata.modules.MastDataRepositoryModule.mastDataRepository;

/**
 * Presenter for managing the tenants with mast count data
 */
public class TenantMastCountPresenter {

    private TenantsView mTenantsView;
    private MastDataRepository mMastDataRepository;

    public TenantMastCountPresenter(TenantsView tenantsView) {
        this(tenantsView, mastDataRepository());
    }
    public TenantMastCountPresenter(TenantsView tenantsView, MastDataRepository mastDataRepository) {
        this.mTenantsView = tenantsView;
        this.mMastDataRepository = mastDataRepository;
    }

    public void getTenantsMastCountList() {

        ArrayList<TenantMast> resultList = createTenantsMastCountList();
        mTenantsView.showTenantMastCountList(resultList);
    }

    public ArrayList<TenantMast> createTenantsMastCountList() {

        ArrayList<MastDataItem> mastList = mMastDataRepository.getMastDataList();
        ArrayList<TenantMast> tenentList = new ArrayList<>();

        //Generate a hashmap
        HashMap<String, Integer> hashMap = new HashMap<>();
        // Look around the mast list and for each tenant, add them to the hashmap
        //Increment their mast count if already there.

        for (MastDataItem mastItem : mastList) {
            String name = mastItem.getTenantName();

            if (hashMap.containsKey(name)) {
                //Already in the map
                int currentMastCount = hashMap.get(name);
                hashMap.put(name, currentMastCount + 1);
            } else {
                // It's a new tenant
                hashMap.put(name, 1);
            }
        }

        // Now create a list
        for (HashMap.Entry<String, Integer> entry: hashMap.entrySet()) {
            TenantMast newItem = new TenantMast();

            newItem.setTenantName(entry.getKey());
            newItem.setMastCount(entry.getValue());

            tenentList.add(newItem);
        }

        return tenentList;

    }
}
