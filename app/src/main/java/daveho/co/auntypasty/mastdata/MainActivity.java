package daveho.co.auntypasty.mastdata;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import daveho.co.auntypasty.mastdata.models.MastDataItem;
import daveho.co.auntypasty.mastdata.modules.ApplicationModule;
import daveho.co.auntypasty.mastdata.presenters.MastDataPresenter;
import daveho.co.auntypasty.mastdata.views.MastListFragment;
import daveho.co.auntypasty.mastdata.views.NewMastDataFragment;
import daveho.co.auntypasty.mastdata.views.RentalsFragment;
import daveho.co.auntypasty.mastdata.views.SubmitNewMastListener;
import daveho.co.auntypasty.mastdata.views.TenantsFragment;

import static daveho.co.auntypasty.mastdata.modules.MastDataRepositoryModule.mastDataRepository;

public class MainActivity extends AppCompatActivity implements SubmitNewMastListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private MastListFragment mMastListFragment;
    private RentalsFragment mRentalsFragment;
    private TenantsFragment mTenantsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ApplicationModule.init(getApplication());

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                NewMastDataFragment newMastDataFragment = new NewMastDataFragment();

                newMastDataFragment.show(fragmentManager, "new_mast_data_fragment");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mMastListFragment = null;
        mRentalsFragment = null;
        mTenantsFragment = null;
    }

    @Override
    public void onSubmitMast(MastDataItem item) {
        // A new mast has been submitted. Put it in the repository
        mastDataRepository().addNewMast(item);

        //Refresh the fragment
        if (mMastListFragment != null) {
            mMastListFragment.updateContents();
        }
        if (mTenantsFragment != null) {
            mTenantsFragment.updateContents();
        }
        if (mRentalsFragment != null) {
            mRentalsFragment.updateContents();
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if (position == 0) {
                if (mMastListFragment == null) {
                    mMastListFragment = new MastListFragment();
                }
                return mMastListFragment;
            } else if (position == 1) {
                if (mTenantsFragment == null) {
                    mTenantsFragment = new TenantsFragment();
                }
                return mTenantsFragment;
            } else if (position == 2) {
                if (mRentalsFragment == null) {
                    mRentalsFragment = new RentalsFragment();
                }
                return mRentalsFragment;
            }
            else {
                Log.e(TAG, "Page Out of range.");
                if (mMastListFragment == null) {
                    mMastListFragment = new MastListFragment();
                }
                return mMastListFragment;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
