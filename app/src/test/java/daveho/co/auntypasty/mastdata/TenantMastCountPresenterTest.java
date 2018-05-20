package daveho.co.auntypasty.mastdata;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import daveho.co.auntypasty.mastdata.models.MastDataItem;
import daveho.co.auntypasty.mastdata.models.TenantMast;
import daveho.co.auntypasty.mastdata.repository.MastDataRepository;
import daveho.co.auntypasty.mastdata.views.TenantsFragment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class TenantMastCountPresenterTest {

    Context mContext;
    TenantMastCountPresenter sut;

    MastDataItem testItem1 = new MastDataItem();
    MastDataItem testItem2 = new MastDataItem();
    MastDataItem testItem3 = new MastDataItem();
    MastDataItem testItem4 = new MastDataItem();
    MastDataItem testItem5 = new MastDataItem();

    ArrayList<MastDataItem> testList = new ArrayList<>();

    @Mock
    private TenantsFragment mockTenentView;

    @Mock
    private MastDataRepository mockMastRepository;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        testItem1.setTenantName("Tenant 1");
        testItem2.setTenantName("Tenant 2");
        testItem3.setTenantName("Tenant 1");
        testItem4.setTenantName("Tenant 3");
        testItem5.setTenantName("Tenant 3");

        testList.add(testItem1);
        testList.add(testItem2);
        testList.add(testItem3);
        testList.add(testItem4);
        testList.add(testItem5);

        mContext = RuntimeEnvironment.application.getApplicationContext();
        sut = new TenantMastCountPresenter(mContext, mockTenentView, mockMastRepository);
    }

    @After
    public void tearDown() throws Exception {
        sut = null;
        testList.clear();
    }

    @Test
    public void createTenantListShouldContain3Tenants() {
        when(mockMastRepository.getMastDataList()).thenReturn(testList);

        ArrayList<TenantMast> result = sut.createTenantsMastCountList();

        assertThat(result).isNotEmpty();

        assertThat(result.size()).isEqualTo(3);
    }

}
