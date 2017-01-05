package com.example.tobias.ctest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.TintTypedArray;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.TextureView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.szugyi.circlemenu.view.CircleImageView;
import com.szugyi.circlemenu.view.CircleLayout;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements CircleLayout.OnItemSelectedListener,
        CircleLayout.OnItemClickListener, CircleLayout.OnRotationFinishedListener{

    protected CircleLayout circleLayout;
    protected TextView selectedTextView;

    //private SeekBar seekBarTime;
    //TextView seekBarTimeValue;
    //SeekBar seekBarDifficulty;
    //TextView seekBarDifficultyValue;
    private SeekBar seekBar,seekBar2;
    private TextView textView,textView2,tidLaese,svaerhedsgrad;
    private Button las;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        las = (Button) findViewById(R.id.btnStart);
        las.setVisibility(GONE);

        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        seekBar.setVisibility(GONE);

        textView = (TextView) findViewById(R.id.textView1);

        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        seekBar2.setVisibility(GONE);

        textView2 = (TextView) findViewById(R.id.textView2);

        tidLaese = (TextView) findViewById(R.id.TidLaese) ;

        svaerhedsgrad = (TextView) findViewById(R.id.TextSvaerhedsgrad);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView.setText("Covered: " + progress + "/" + seekBar.getMax());
                Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
            }
        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView2.setText("Covered: " + progress + "/" + seekBar.getMax());
                Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
            }
        });


            // set listeners
        circleLayout = (CircleLayout) findViewById(R.id.circle_layout);
        circleLayout.setOnItemSelectedListener(this);
        circleLayout.setOnItemClickListener(this);
        circleLayout.setOnRotationFinishedListener(this);


        selectedTextView = (TextView) findViewById(R.id.selected_textView);

        String name = null;
        View view = circleLayout.getSelectedItem();
        if(view instanceof CircleImageView){
            name = ((CircleImageView) view).getName();
        }
        selectedTextView.setText(name);



    }



    @Override
    public void onItemClick(View view) {

        String name = null;
        if(view instanceof CircleImageView){
            name = ((CircleImageView) view).getName();
        }
        String text = getResources().getString(R.string.start_app, name);
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

        switch (view.getId()) {
            case R.id.historie:
                if (circleLayout.getChildCount() > 1) {

                    for (int i = 0; i < 5; i++) {
                        circleLayout.removeViewAt(circleLayout.getChildCount() - 1);

                    }
                }

               ImageView img = (ImageView) findViewById(R.id.historie);
                circleLayout.setEnabled(false);
               moveViewToScreenCenter(img);
                selectedTextView.setText("Du har valgt : "+((CircleImageView) view).getName());
                moveViewToScreenCenter(selectedTextView);
                setTxtAndRotate(tidLaese,svaerhedsgrad);
                circleLayout.setRotating(false);

                seekBar.setVisibility(VISIBLE);
                rotateSeekBar(seekBar);

                seekBar2.setVisibility(VISIBLE);
                rotateSeekBar(seekBar2);

                las.setVisibility(VISIBLE);
               // selectedTextView.setText("");




                break;
            case R.id.krimi:

                break;
            case R.id.mad:

                break;
            case R.id.natur:

                break;
            case R.id.sport:

                break;
            case R.id.random:

                break;
        }

    }

    @Override
    public void onItemSelected(View view) {
    final String name;
        if(view instanceof CircleImageView){
            name = ((CircleImageView) view).getName();
        }else{
            name = null;
        }

        selectedTextView.setText(name);

        switch (view.getId()) {
            case R.id.historie:

                break;
            case R.id.krimi:

                break;
            case R.id.mad:

                break;
            case R.id.natur:

                break;
            case R.id.sport:

                break;
            case R.id.random:

                break;
        }
    }

    @Override
    public void onRotationFinished(View view) {

        Animation animation = new RotateAnimation(0, 360, view.getWidth() / 2, view.getHeight() / 2);
        animation.setDuration(250);
        view.startAnimation(animation);

    }

    private void moveViewToScreenCenter( View view )
    {
        if(view instanceof TextView){
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)view.getLayoutParams();
            params.setMargins(0, 0, 0, 0); //substitute parameters for left, top, right, bottom
            view.setLayoutParams(params);
        }

        RelativeLayout root = (RelativeLayout) findViewById( R.id.rootLayout);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics( dm );
        int statusBarOffset = dm.heightPixels - root.getMeasuredHeight();

        int originalPos[] = new int[2];
        view.getLocationOnScreen( originalPos );

        int xDest = dm.widthPixels/2;
        xDest -= (view.getMeasuredWidth()/2);
        int yDest = dm.heightPixels/2 - (view.getMeasuredHeight()/2) - statusBarOffset;

        TranslateAnimation anim = new TranslateAnimation( 0, xDest - originalPos[0] , 0, (yDest - originalPos[1])-((yDest - originalPos[1])/4) );
        anim.setDuration(1000);
        anim.setFillAfter( true );
        int x = xDest - originalPos[0];
        int y = yDest - (originalPos[1])/2;
        view.startAnimation(anim);
    }

    private void rotateSeekBar(SeekBar s){
        s.setDrawingCacheEnabled(true);
        Animation animation = new RotateAnimation(200, 360, s.getWidth() , s.getHeight() );
        animation.setDuration(1250);
        s.startAnimation(animation);
    }

    private void rotateTextView(TextView t){
        t.setDrawingCacheEnabled(true);
        Animation animation = new RotateAnimation(200, 360, t.getWidth() , t.getHeight() );
        animation.setDuration(1250);
        t.startAnimation(animation);
    }

    private  void setTxtAndRotate( TextView t, TextView t1){
        tidLaese.setText("Hvor lang tid vil du læse? (I minutter)");
        svaerhedsgrad.setText("Hvilken særhedsgrad skal teksten have?");
        t.setDrawingCacheEnabled(true);
        Animation animation = new RotateAnimation(200, 360, t.getWidth() , t.getHeight() );
        animation.setDuration(1250);
        t.startAnimation(animation);
        t1.startAnimation(animation);
    }

}
