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
import java.util.List;

import daveho.co.auntypasty.mastdata.presenters.MastDataPresenter;
import daveho.co.auntypasty.mastdata.repository.MastDataRepository;
import daveho.co.auntypasty.mastdata.views.MastListFragment;
import daveho.co.auntypasty.mastdata.views.RentalsView;
import daveho.co.auntypasty.mastdata.views.TenantsView;


@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class MastDataPresenterTest {

    Context mContext;

    MastDataPresenter sut;

    List<String[]> testData = new ArrayList();
    String[] testLine1 = {"Property name", "Address 1", "Address 2", "Address 3", "Address 4",
            "Unit Name", "Tenant Name", "Lease Start Data", "Lease End Data", "Lease Years", "Current Rent"};

    String[] testLine2 = {"test1 Property name", "Test1 Address line 1", "test1 Address 2 ", "test1 Address 3", "test1 Address 4",
            "test1 Unit Name", "test1 Tenant Name", "test1 Lease Start Data", "test1 Lease End Data", "test1 Lease Years", "test1 Current Rent"};

    String[] testLine3 = {"test2 Property name", "test2 Address 1", "test2 Address 2", "test2 Address 3", "test2 Address 4",
            "test2 Unit Name", "test2 Tenant Name", "test2 Lease Start Data", "test2 Lease End Data", "test2 Lease Years", "test2 Current Rent"};

    @Mock
    private MastListFragment mockMastListView;

    @Mock
    private TenantsView mockTenantsView;

    @Mock
    private RentalsView mockRentalsView;

    @Mock
    private MastDataRepository mockMastRepository;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        mContext = RuntimeEnvironment.application.getApplicationContext();
        sut = new MastDataPresenter(mContext, mockMastListView, mockMastRepository);

        testData.add(testLine1);
        testData.add(testLine2);
        testData.add(testLine3);
    }

    @After
    public void tearDown() throws Exception {
        sut = null;
        testData.clear();
    }

    @Test
    public void getListShouldReturnListOfObjects() {
        sut.getMastListFromStorageToShow();

      //  verify(mockMastListView, times(1)).showTop5MastList(anyListOf(MastDataItem.class));
    }
}
