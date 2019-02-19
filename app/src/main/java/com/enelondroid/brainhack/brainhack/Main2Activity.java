package com.enelondroid.brainhack.brainhack;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import static android.os.Environment.getExternalStorageDirectory;

public class Main2Activity extends AppCompatActivity {

    MediaPlayer mp;
    MediaPlayer mp1;
    MediaPlayer mp2;
    VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final ImageView stopbut = this.findViewById(R.id.stopbut);
        final ImageView playbut = this.findViewById(R.id.playbut);
        final ImageView pausebut = this.findViewById(R.id.pausebut);

        // <shared-storage>/Android/obb/<package-name>/

        String extstor = String.valueOf(getExternalStorageDirectory());
        String packagename = getPackageName();

        String extension = extstor + "/Android/obb/" + packagename + "/";

        String mppath = extension + "betawav";
        String mp1path = extension + "alphawav";
        String mp2path = extension + "thetawav";

        mp = MediaPlayer.create(this, Uri.parse(mppath));
        mp1 = MediaPlayer.create(this, Uri.parse(mp1path));
        mp2 = MediaPlayer.create(this, Uri.parse(mp2path));

        video = (VideoView) findViewById(R.id.video1);

        video.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.wavform);

        video.setVisibility(View.VISIBLE);

        // Video loop thread
        Runnable runnablevideo = new Runnable(){
            public void run(){
                video.start();
                video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer video) {
                        video.setLooping(true);
                    }
                });
            }
        };
        Thread videothread = new Thread(runnablevideo);
        videothread.start();

        Intent intent = getIntent();

        int but = intent.getIntExtra("buttonpre", 0);

        // What happens after different button clicks depending on intent value
        switch (but) {
            case 1:
                Runnable runnablemp = new Runnable(){
                    public void run(){
                        mp.start();
                    }
                };
                Thread mpthread = new Thread(runnablemp);
                mpthread.start();
                mp.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
                break;
            case 2:
                Runnable runnablemp1 = new Runnable(){
                    public void run(){
                        mp1.start();
                    }
                };
                Thread mp1thread = new Thread(runnablemp1);
                mp1thread.start();
                mp1.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
                break;
            case 3:
                Runnable runnablemp2 = new Runnable(){
                    public void run(){
                        mp2.start();
                    }
                };
                Thread mp2thread = new Thread(runnablemp2);
                mp2thread.start();
                mp2.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
                break;
            default:
                Context context1 = getApplicationContext();
                CharSequence text1 = "Error";
                int duration1 = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context1, text1, duration1);
                toast.show();
                break;
        }

        stopbut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mp.stop();
                mp1.stop();
                mp2.stop();
                mp.release();
                mp1.release();
                mp2.release();
                Intent intentback = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentback);
            }
        });
        pausebut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mp.pause();
                mp1.pause();
                mp2.pause();
                pausebut.setVisibility(View.GONE);
                playbut.setVisibility(View.VISIBLE);
            }
        });
        playbut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mp.start();
                mp1.start();
                mp2.start();
                playbut.setVisibility(View.GONE);
                pausebut.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        mp.stop();
        mp1.stop();
        mp2.stop();
        mp.release();
        mp1.release();
        mp2.release();

        //Return to MainActivity
        Intent intentback = new Intent(this, MainActivity.class);
        startActivity(intentback);

    }
}
