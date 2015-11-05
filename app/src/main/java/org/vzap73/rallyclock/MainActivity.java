package org.vzap73.rallyclock;

import android.app.Activity;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends Activity {
    private boolean isTimerStarted = false;
    private CountDownTimer countTimer;
    private String timerStart = "Start";
    private String timerStop = "Stop";

    private Button startTimer;
    private EditText minutes;
    private EditText seconds;
    private Button incMin;
    private Button decMin;
    private Button incSec;
    private Button decSec;

    private TextView timeLeft;
    private TextView timeIdeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtons();
        this.findViewById(R.id.textClock).requestFocus();
    }

    private void initButtons() {
        startTimer = (Button)this.findViewById(R.id.toggleTimer);
        timeLeft = (TextView)this.findViewById(R.id.timeLeft);
        timeIdeal = (TextView)this.findViewById(R.id.timeIdeal);

        startTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTimerStarted) {

                    countTimer.cancel();
                    stopTimerIndication();
                } else {
                    int timerValue = Integer.valueOf(minutes.getText().toString())*60*1000
                                            + Integer.valueOf(seconds.getText().toString())*1000;
                    Calendar idealTimeValue = Calendar.getInstance();
                    idealTimeValue.add(Calendar.MILLISECOND, timerValue);
                    timeIdeal.setText(new SimpleDateFormat("HH:mm:ss").format(idealTimeValue.getTime()));
                    //String timeIdealValue = new Tim

                    countTimer = new CountDownTimer(timerValue, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            long secondsLeft = millisUntilFinished / 1000;
                            String minutesLeft = String.format("%02d",secondsLeft / 60) +
                                                        ":" + String.format("%02d",secondsLeft%60);
                            timeLeft.setText(minutesLeft);
                        }

                        @Override
                        public void onFinish() {
                            stopTimerIndication();
                        }
                    };
                    countTimer.start();
                    startTimer.setText(timerStop);
                    isTimerStarted = true;
                }
            }

            private void stopTimerIndication() {
                timeLeft.setText("");
                startTimer.setText(timerStart);
                isTimerStarted = false;

            }
        });

        minutes = (EditText)this.findViewById(R.id.minutes);
        seconds = (EditText)this.findViewById(R.id.seconds);

        incMin = (Button)this.findViewById(R.id.addMin);
        decMin = (Button)this.findViewById(R.id.decMin);
        incSec = (Button)this.findViewById(R.id.addSec);
        decSec = (Button)this.findViewById(R.id.decSec);

        incMin.setOnClickListener(new IncDecListener(minutes, 1));
        decMin.setOnClickListener(new IncDecListener(minutes, -1));
        incSec.setOnClickListener(new IncDecListener(seconds, 1));
        decSec.setOnClickListener(new IncDecListener(seconds, -1));
    }

}
