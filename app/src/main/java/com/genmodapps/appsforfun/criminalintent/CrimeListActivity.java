package com.genmodapps.appsforfun.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by Asus on 13.03.2018.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
