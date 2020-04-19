package com.example.quiztest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mbtn1, mbtn2,mbtn3,mbtn4,mbtn_Start;
    private TextView timer,score,question,bottom;
    private ProgressBar progTimer;

   int secondsRemaining = 30;
    Game g = new Game();
    // the time is 30 s , 1 s is minus
    CountDownTimer timers = new CountDownTimer(30000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            secondsRemaining--;

            timer.setText(Integer.toString(secondsRemaining) +"sc");
            progTimer.setProgress(30 - secondsRemaining);
        }

        @Override
        public void onFinish() {
            mbtn1.setEnabled(false);
            mbtn2.setEnabled(false);
            mbtn3.setEnabled(false);
            mbtn4.setEnabled(false);
            bottom.setText("Times up " +g.getNumberCorrect() + "/" + (g.getTotalQuestion()- 1));

            // this is to delay when it is finished and to run again
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mbtn_Start.setVisibility(View.VISIBLE);
                }
            },4000);

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uiSet();

    }
    public void uiID(){
        mbtn1 = findViewById(R.id.btn_ans0);
        mbtn2 = findViewById(R.id.btn_ans1);
        mbtn3 = findViewById(R.id.btn_ans2);
        mbtn4 = findViewById(R.id.btn_ans03);
        mbtn_Start = findViewById(R.id.btn_Start);

        timer= findViewById(R.id.tv_timer);
        score = findViewById(R.id.tv_Score);
        question = findViewById(R.id.tv_Questions);
        bottom = findViewById(R.id.tv_bottom);

        progTimer = findViewById(R.id.prog_timer);

    }
    public void uiSet(){
        uiID();
        timer.setText("0sec");
        question.setText("");
        bottom.setText("press go ");
        score.setText("0pts");

        View.OnClickListener startButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button start_button = (Button) v;
                start_button.setVisibility(View.INVISIBLE);
                // to restart the timer ( so it is not min)
                secondsRemaining = 30;
                // to make a new game so not the same
                g = new Game();
                nextTurn();
                timers.start();
            }
        };

        View.OnClickListener answerBtnListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button buttonClicked = (Button) v;
                int answerSelected = Integer.parseInt(buttonClicked.getText().toString());

                // to check if the button works ( super useful)
                //Toast.makeText(MainActivity.this,"answerSelected = " + answerSelected,Toast.LENGTH_SHORT).show();

                g.checkAnswer(answerSelected);
                score.setText(Integer.toString(g.getScore()) + "pts");
                nextTurn();
            }
        };
        mbtn_Start.setOnClickListener(startButtonClickListener);
        mbtn1.setOnClickListener(answerBtnListner);
        mbtn2.setOnClickListener(answerBtnListner);
        mbtn3.setOnClickListener(answerBtnListner);
        mbtn4.setOnClickListener(answerBtnListner);

    }
    private void nextTurn() {
        g.makeNewQuestion();
        int [] answer = g.getCurrentQuestion().getAnswerArray();
        mbtn1.setText(Integer.toString(answer[0]));
        mbtn2.setText(Integer.toString(answer[1]));
        mbtn3.setText(Integer.toString(answer[2]));
        mbtn4.setText(Integer.toString(answer[3]));

        mbtn1.setEnabled(true);
        mbtn2.setEnabled(true);
        mbtn3.setEnabled(true);
        mbtn4.setEnabled(true);

        question.setText(g.getCurrentQuestion().getQuestionPhrase());
        bottom.setText(g.getNumberCorrect() + "/" + (g.getTotalQuestion()- 1));
    }
}
