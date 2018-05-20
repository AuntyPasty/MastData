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
import daveho.co.auntypasty.mastdata.presenters.MastDataPresenter;
import daveho.co.auntypasty.mastdata.repository.MastDataRepository;
import daveho.co.auntypasty.mastdata.views.MastListFragment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class MastDataPresenterTest {

    Context mContext;

    MastDataPresenter sut;

    MastDataItem testItem1 = new MastDataItem();
    MastDataItem testItem2 = new MastDataItem();
    MastDataItem testItem3 = new MastDataItem();
    MastDataItem testItem4 = new MastDataItem();
    MastDataItem testItem5 = new MastDataItem();
    MastDataItem testItem6 = new MastDataItem();
    MastDataItem testItem7 = new MastDataItem();

    ArrayList<MastDataItem> testList = new ArrayList<>();

    @Mock
    private MastListFragment mockMastListView;

    @Mock
    private MastDataRepository mockMastRepository;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        mContext = RuntimeEnvironment.application.getApplicationContext();
        sut = new MastDataPresenter(mContext, mockMastListView, mockMastRepository);

        testItem1.setCurrentRent("1.0");
        testItem2.setCurrentRent("2.0");
        testItem3.setCurrentRent("3.0");
        testItem4.setCurrentRent("4.0");
        testItem5.setCurrentRent("5.0");
        testItem6.setCurrentRent("6.0");
        testItem7.setCurrentRent("7.5");

        testList.add(testItem1);
        testList.add(testItem2);
        testList.add(testItem3);
        testList.add(testItem4);
        testList.add(testItem5);
        testList.add(testItem6);
        testList.add(testItem7);
    }

    @After
    public void tearDown() throws Exception {
        sut = null;
        testList.clear();
    }

    @Test
    public void testGetListToShowContainsMax5items() {
        ArrayList<MastDataItem> result = sut.getTopFiveFromList(testList);

        assertThat(result.size()).isEqualTo(5);
    }

    @Test
    public void testGetTotalRentFromList() {

        float result = sut.getTotalRentFromList(testList);

        assertThat(result).isEqualTo(28.5f);
    }
}
