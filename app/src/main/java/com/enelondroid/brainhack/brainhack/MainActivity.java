package com.enelondroid.brainhack.brainhack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /*
        //Runtime request permission to read external storage
        File obb = new File(obb_filename);
        boolean open_failed = false;

        try {
            BufferedReader br = new BufferedReader(new FileReader(obb));
            open_failed = false;
            ReadObbFile(br);
        } catch (IOException e) {
            open_failed = true;
        }

        if (open_failed) {
            // request READ_EXTERNAL_STORAGE permission before reading OBB file
            ReadObbFileWithPermission();
        }
        */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hide status bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        final ImageView but = this.findViewById(R.id.button1);
        final ImageView but1 = this.findViewById(R.id.button2);
        final ImageView but2 = this.findViewById(R.id.button3);



        but.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                intent.putExtra("buttonpre", 1);
                startActivity(intent);
            }
        });
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                intent.putExtra("buttonpre", 2);
                startActivity(intent);
            }
        });
        but2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                intent.putExtra("buttonpre", 3);
                startActivity(intent);
            }
        });
    }
}