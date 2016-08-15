package com.codealig.androidallexamples.sharelayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.codealig.androidallexamples.R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Ali.Guvenbas on 15.8.2016.
 */
public class ShareLayoutActivity extends AppCompatActivity {

    private String imagePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_layout);
    }

    public void share(View v){
        takeScreenshot();
        shareAtSomewhere();
    }
    private void takeScreenshot() {
        //open a folder named 'AndroidExamples', if not exist
        File file = new File(Environment.getExternalStorageDirectory().toString() + "/" + "AndroidExamples");
        if (!file.exists()) {
            if (file.mkdir()) {
                Log.i("Util", "AndroidExamples" + " folder created at " + Environment.getExternalStorageDirectory().toString());
            }else{
                Log.i("Util", "AndroidExamples" + " folder can not created at " + Environment.getExternalStorageDirectory().toString());
            }
        }

        //Define screenshot name and place where will be saved
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
        imagePath = Environment.getExternalStorageDirectory().toString() + "/AndroidExamples/" + now + ".png";

        View v1 = findViewById(R.id.lytScreenShot);//get screenShot object
        v1.setDrawingCacheEnabled(true);
        //Size of screenShot equals choosen layout sizes
        v1.layout(0, 0, v1.getMeasuredWidth(), v1.getMeasuredHeight());
        v1.buildDrawingCache(true);
        Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
        v1.setDrawingCacheEnabled(false);

        //Save layout screenshot to our folder named 'AndroidExamples'
        File imageFile = new File(imagePath);
        try {
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void shareAtSomewhere() {
        Intent share = new Intent(Intent.ACTION_SEND);//share intent
        // setType("image/png"); OR for jpeg: setType("image/jpeg");
        share.setType("image/*");
        File imageFileToShare = new File(imagePath);
        Uri uri = Uri.fromFile(imageFileToShare);
        share.putExtra(Intent.EXTRA_STREAM, uri);// uri of what we want to share
        startActivity(Intent.createChooser(share, "Nerede Paylaşmak İstiyorsun?"));
    }
}
