package mx.tec.a01730344.adam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RainbowActivityNVL1 extends AppCompatActivity {

    RainbowModelNVL1 model;
    TextView tvInstruction;
    TextView tvColor;
    TextView tvScoreR;
    ImageButton ibOption1;
    ImageButton ibOption2;
    ImageButton ibOption3;
    ImageView ivLife1;
    ImageView ivLife2;
    ImageView ivLife3;
    String scoreString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rainbow_nvl1);

        tvInstruction = findViewById(R.id.tvInstruction);
        tvColor = findViewById(R.id.tvColor);
        tvScoreR = findViewById(R.id.tvScoreR);
        ibOption1 = findViewById(R.id.ibOption1);
        ibOption2 = findViewById(R.id.ibOption2);
        ibOption3 = findViewById(R.id.ibOption3);
        ivLife1 = findViewById(R.id.ivLife1);
        ivLife2 = findViewById(R.id.ivLife2);
        ivLife3 = findViewById(R.id.ivLife3);
        model = new RainbowModelNVL1();

        ibOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.checkAnswer(0);
                scoreString = "Puntuación: " + model.score;
                tvScoreR.setText(scoreString);
                checkLives();
                setLayoutAttributes();
            }
        });

        ibOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.checkAnswer(1);
                scoreString = "Puntuación: " + model.score;
                tvScoreR.setText(scoreString);
                checkLives();
                setLayoutAttributes();
            }
        });

        ibOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.checkAnswer(2);
                scoreString = "Puntuación: " + model.score;
                tvScoreR.setText(scoreString);
                checkLives();
                setLayoutAttributes();
            }
        });

        startGame();
    }

    private void startGame() {
        setLayoutAttributes();
        new CountDownTimer(900000000, 500) {
            public void onFinish() {
                Intent toHome = new Intent(RainbowActivityNVL1.this, HomeActivity.class);
                startActivity(toHome);
            }
            public void onTick(long millisUntilFinished) {
                if (model.globalLives == 0) {
                    Intent toHome = new Intent(RainbowActivityNVL1.this, HomeActivity.class);
                    startActivity(toHome);
                    model.globalLives --;
                    cancel();
                }
            }
        }.start();
    }

    private void setLayoutAttributes() {
        model.gameRound();
        tvColor.setText(model.colorText);
        tvColor.setTextColor(getResources().getColor(model.colorValue));
        ibOption1.setImageResource(model.imageView1);
        ibOption2.setImageResource(model.imageView2);
        ibOption3.setImageResource(model.imageView3);
    }

    private void checkLives() {
        if (model.globalLives == 2) {
            ivLife3.setImageResource(R.drawable.ic_adam_dead);
        }
        if (model.globalLives == 1) {
            ivLife2.setImageResource(R.drawable.ic_adam_dead);
        }
        if (model.globalLives == 0) {
            ivLife1.setImageResource(R.drawable.ic_adam_dead);
            ibOption1.setEnabled(false);
            ibOption2.setEnabled(false);
            ibOption3.setEnabled(false);
        }
    }

}