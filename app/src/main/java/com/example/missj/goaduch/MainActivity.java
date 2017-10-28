package com.example.missj.goaduch;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new main_fragment();
    }
}
