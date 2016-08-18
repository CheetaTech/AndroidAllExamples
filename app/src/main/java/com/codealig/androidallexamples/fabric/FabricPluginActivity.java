package com.codealig.androidallexamples.fabric;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.codealig.androidallexamples.R;

/**
 * Created by Ali.Guvenbas on 18.8.2016.
 */
public class FabricPluginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabric);
    }

    public void makeNullPointerException(View v) {
        throw new NullPointerException();
    }

    public void makeIndexOutOfBoundsException(View v) {
        throw new IndexOutOfBoundsException();
    }
}
