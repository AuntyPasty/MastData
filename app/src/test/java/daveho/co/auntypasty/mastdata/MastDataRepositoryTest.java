package daveho.co.auntypasty.mastdata;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import daveho.co.auntypasty.mastdata.models.MastDataItem;
import daveho.co.auntypasty.mastdata.repository.MastDataRepository;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class MastDataRepositoryTest {

    private MastDataRepository sut;
    private Context mContext;

    private List<String[]> testData = new ArrayList<>();

    private String[] testLine1 = {"Property name", "Address 1", "Address 2", "Address 3", "Address 4",
            "Unit Name", "Tenant Name", "Lease Start Data", "Lease End Data", "Lease Years", "Current Rent"};

    private String[] testLine2 = {"test1 Property name", "Test1 Address line 1", "test1 Address 2 ", "test1 Address 3", "test1 Address 4",
            "test1 Unit Name", "test1 Tenant Name", "test1 Lease Start Data", "test1 Lease End Data", "test1 Lease Years", "test1 Current Rent"};

    private String[] testLine3 = {"test2 Property name", "test2 Address 1", "test2 Address 2", "test2 Address 3", "test2 Address 4",
            "test2 Unit Name", "test2 Tenant Name", "test2 Lease Start Data", "test2 Lease End Data", "test2 Lease Years", "test2 Current Rent"};

    @Before
    public void setup() {

        mContext = RuntimeEnvironment.application.getApplicationContext();
        sut = new MastDataRepository(mContext);

        testData.add(testLine1);
        testData.add(testLine2);
        testData.add(testLine3);
    }

    @After
    public void tearDown()  {
        sut = null;
        testData.clear();
    }

    @Test
    public void getListShouldReturnListOfObjects() {
        ArrayList<MastDataItem> result = sut.getListFromDataFile(testData);

        assertThat(result).isNotEmpty();

        // We remove the first line from the array so expected is 2 rather than 3.
        assertThat(result.size()).isEqualTo(2);
    }



}
