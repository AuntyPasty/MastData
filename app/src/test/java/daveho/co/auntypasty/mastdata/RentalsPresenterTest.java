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
import daveho.co.auntypasty.mastdata.presenters.RentalsPresenter;
import daveho.co.auntypasty.mastdata.repository.MastDataRepository;
import daveho.co.auntypasty.mastdata.views.RentalsView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class RentalsPresenterTest {

    Context mContext;
    RentalsPresenter sut;

    MastDataItem testItem1 = new MastDataItem();
    MastDataItem testItem2 = new MastDataItem();
    MastDataItem testItem3 = new MastDataItem();
    MastDataItem testItem4 = new MastDataItem();
    MastDataItem testItem5 = new MastDataItem();

    ArrayList<MastDataItem> testList = new ArrayList<>();

    @Mock
    RentalsView mockView;

    @Mock
    private MastDataRepository mockMastRepository;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        mContext = RuntimeEnvironment.application.getApplicationContext();

        sut = new RentalsPresenter(mContext, mockView, mockMastRepository);
    }

    @After
    public void tearDown() throws Exception {
        sut = null;
        testList.clear();
    }

    @Test
    public void testDateFormatIsConvertedCorrectly() {

        MastDataItem testItem = new MastDataItem();
        testItem.setLeaseStart("20 May 2008");
        testItem.setLeaseEnd("31 Jul 2019");

        MastDataItem result = sut.convertDatesWithinItem(testItem);

        assertThat(result.getLeaseStart()).isEqualToIgnoringCase("20/05/2008");
        assertThat(result.getLeaseEnd()).isEqualToIgnoringCase("31/07/2019");
    }

    @Test
    public void testForItemsWithinDateRange() {

        testItem1.setLeaseStart("20 Jan 1988");
        testItem2.setLeaseStart("20 Jan 2001");
        testItem3.setLeaseStart("20 May 2008");
        testItem4.setLeaseStart("18 Jan 2002");
        testItem5.setLeaseStart("04 dec 2006");

        testList.add(testItem1);
        testList.add(testItem2);
        testList.add(testItem3);
        testList.add(testItem4);
        testList.add(testItem5);

        when(mockMastRepository.getMastDataList()).thenReturn(testList);

        ArrayList<MastDataItem> result = sut.getRentalsFromListInDateRange();

        assertThat(result.size()).isEqualTo(3);

    }

}
