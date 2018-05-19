package daveho.co.auntypasty.mastdata;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class MastCsvParserTest {

    MastCsvParser sut;
    Context mContext;

    @Before
    public void setup() throws Exception {
        mContext = RuntimeEnvironment.application.getApplicationContext();
        sut = new MastCsvParser(mContext);
    }

    @After
    public void tearDown() throws Exception {
        sut = null;
    }

    @Test
    public void parseFileInAssetsFolder() {

        //TODO Doesn't work. May need to mock asset manager.
        List<String[]> result = sut.parseCsvFile();

        assertThat(result).isNotEmpty();
    }
}
