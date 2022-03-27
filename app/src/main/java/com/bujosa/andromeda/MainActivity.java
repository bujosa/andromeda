package com.bujosa.andromeda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity {

    private TextView timerTextView, currentTapTextView, highScoreTextView, lastScoreTextView;
    private final long initialCountDown = 60000;
    private final int timerInterval = 1000;
    private int reamingTime = 60;
    private int lastScore = 0, highScore = 0;
    private boolean status = true;
    private int currentTap = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = findViewById(R.id.timerTextView);
        currentTapTextView = findViewById(R.id.currentTapTextView);
        lastScoreTextView = findViewById(R.id.lastScoreTextView);
        highScoreTextView = findViewById(R.id.highScoreTextView);

        currentTapTextView.setText(MessageFormat.format("{0}", currentTap));

        CountDownTimer countDownTime = new CountDownTimer(initialCountDown, timerInterval) {
            @Override
            public void onTick(long l) {
                reamingTime = (int) l/1000;
                timerTextView.setText(MessageFormat.format("{0}", reamingTime));
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "CountDown Finished", Toast.LENGTH_LONG).show();
                status = true;
                if(currentTap >= highScore){
                    highScore = currentTap;
                }
                lastScore = currentTap;
                currentTap = 0;
                reamingTime = 60;
                highScoreTextView.setText(MessageFormat.format("{0}", highScore));
                lastScoreTextView.setText(MessageFormat.format("{0}", lastScore));
                currentTapTextView.setText(MessageFormat.format("{0}", currentTap));
                timerTextView.setText(MessageFormat.format("{0}", reamingTime));

            }
        };

        findViewById(R.id.tapButton).setOnClickListener(view -> {
            if(status){
                countDownTime.start();
                status = false;
            }
            currentTap++;
            currentTapTextView.setText(MessageFormat.format("{0}", currentTap));
        });
    }
}