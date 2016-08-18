package com.codealig.androidallexamples;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.codealig.androidallexamples.fabric.FabricPluginActivity;
import com.codealig.androidallexamples.sharelayout.ShareLayoutActivity;
import com.codealig.androidallexamples.slidingtabs.SlidingTabsActivity;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;

/*
    All examples are in the list below. You can follow the examples that you want to see
    by choosing related list row
 */
public class MainActivity extends AppCompatActivity {
    private ListView lstAll;
    private ArrayList<String> exampleTopics =new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        lstAll = (ListView) findViewById(R.id.lstAll);
        exampleTopics.add(Indexes.SHARE_LAYOUT, "Share Layout");
        exampleTopics.add(Indexes.SLIDING_TABS, "Sliding Tabs");
        exampleTopics.add(Indexes.FABRIC, "Fabric Plugin Test");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, exampleTopics);
        lstAll.setAdapter(adapter);
        lstAll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position) {
                    case Indexes.SHARE_LAYOUT:
                        intent = new Intent(MainActivity.this, ShareLayoutActivity.class);
                        break;
                    case Indexes.SLIDING_TABS:
                        intent = new Intent(MainActivity.this, SlidingTabsActivity.class);
                        break;
                    case Indexes.FABRIC:
                        intent = new Intent(MainActivity.this, FabricPluginActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }
}
