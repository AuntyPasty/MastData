package daveho.co.auntypasty.mastdata;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.Collections;

import daveho.co.auntypasty.mastdata.models.MastDataItem;
import daveho.co.auntypasty.mastdata.presenters.LeaseAmountOrderingComparator;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class LeaseAmountOrderingComparatorTest {

    private MastDataItem testItem1 = new MastDataItem();
    private MastDataItem testItem2 = new MastDataItem();
    private MastDataItem testItem3 = new MastDataItem();
    private MastDataItem testItem4 = new MastDataItem();
    private MastDataItem testItem5 = new MastDataItem();
    private MastDataItem testItem6 = new MastDataItem();
    private MastDataItem testItem7 = new MastDataItem();

    private ArrayList<MastDataItem> testList = new ArrayList<>();

    private LeaseAmountOrderingComparator sut;

    @Before
    public void setup() {
        sut = new LeaseAmountOrderingComparator();

        testItem1.setCurrentRent("2.2");
        testItem2.setCurrentRent("24.3");
        testItem3.setCurrentRent("18.6");
        testItem4.setCurrentRent("1.0");
        testItem5.setCurrentRent("99.3");
        testItem6.setCurrentRent("3.6");
        testItem7.setCurrentRent("64.6");

        testList.add(testItem1);
        testList.add(testItem2);
        testList.add(testItem3);
        testList.add(testItem4);
        testList.add(testItem5);
        testList.add(testItem6);
        testList.add(testItem7);
    }

    @After
    public void tearDown() {
        sut = null;
        testList.clear();
    }

    @Test
    public void comparatorShallSortAscending() {
        Collections.sort(testList, sut);

        assertThat(testList.get(0)).isEqualTo(testItem4);
    }

    @Test
    public void comparatorShallSortDescending() {

        Collections.sort(testList, sut);
        Collections.reverse(testList);

        assertThat(testList.get(0)).isEqualTo(testItem5);
    }
}
