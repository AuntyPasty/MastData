package daveho.co.auntypasty.mastdata;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import daveho.co.auntypasty.mastdata.models.MastDataItem;
import daveho.co.auntypasty.mastdata.presenters.MastSubmissionPresenter;
import daveho.co.auntypasty.mastdata.repository.MastDataRepository;
import daveho.co.auntypasty.mastdata.views.MastDataSubmissionView;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class MastSubmissionPresenterTest {

    private MastSubmissionPresenter sut;

    @Mock
    private MastDataRepository mockMastRepository;

    @Mock
    private MastDataSubmissionView mockView;

    private MastDataItem testItem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        sut = new MastSubmissionPresenter(mockView, mockMastRepository);
        testItem = new MastDataItem();
        testItem.setPropertyName("My Property");
        testItem.setTenantName("The tenant");
        testItem.setLeaseStart("01 Mar 1996");
        testItem.setLeaseEnd("03 Jun 2022");
        testItem.setCurrentRent("2739.4");
    }

    @After
    public void tearDown() {
        sut = null;
        testItem = null;
    }

    @Test
    public void testCorrectDataReturnsSuccess() {
        boolean result = sut.mastDataIsValid(testItem);

        assertThat(result).isTrue();
    }

    @Test
    public void testMissingFieldsReturnsFailure() {

        testItem.setCurrentRent("");

        boolean result = sut.mastDataIsValid(testItem);

        assertThat(result).isFalse();
    }

    @Test
    public void testIncorrectDateFormatReturnsFailure() {
        testItem.setLeaseStart("Mar 15 1999");

        boolean result = sut.mastDataIsValid(testItem);

        assertThat(result).isFalse();
    }

    @Test
    public void testIncorrectRentFormatReturnsFailure() {
        testItem.setCurrentRent("Twenty Pounds");

        boolean result = sut.mastDataIsValid(testItem);

        assertThat(result).isFalse();
    }
}
