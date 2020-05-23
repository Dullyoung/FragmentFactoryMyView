package com.yc.fragmentfactorydemo;

import android.util.SparseArray;

import androidx.fragment.app.Fragment;

import com.yc.fragmentfactorydemo.Fragment.BlankFragment;
import com.yc.fragmentfactorydemo.Fragment.BlankFragment2;
import com.yc.fragmentfactorydemo.Fragment.BlankFragment3;
import com.yc.fragmentfactorydemo.Fragment.BlankFragment4;
import com.yc.fragmentfactorydemo.Fragment.BlankFragment5;



class FragmentFactory {

    private static final int FRAGMENT_0 = 0;
    private static final int FRAGMENT_1 = 1;
    private static final int FRAGMENT_2 = 2;
    private static final int FRAGMENT_3 = 3;
    private static final int FRAGMENT_4 = 4;

    static SparseArray<Fragment> fragments = new SparseArray();

    static Fragment create(int position) {
        Fragment fragment = fragments.get(position);
        if (fragment != null) {
            return fragment;
        }
        switch (position) {

            case FRAGMENT_0:
                fragment = new BlankFragment();
                fragments.put(FRAGMENT_0, fragment);
                break;
            case FRAGMENT_1:
                fragment = new BlankFragment2();
                fragments.put(FRAGMENT_1, fragment);
                break;
            case FRAGMENT_2:
                fragment = new BlankFragment3();
                fragments.put(FRAGMENT_2, fragment);
                break;
            case FRAGMENT_3:
                fragment = new BlankFragment4();
                fragments.put(FRAGMENT_3, fragment);
                break;
            case FRAGMENT_4:
                fragment = new BlankFragment5();
                fragments.put(FRAGMENT_4, fragment);
                break;
        }

        return fragment;
    }
}
