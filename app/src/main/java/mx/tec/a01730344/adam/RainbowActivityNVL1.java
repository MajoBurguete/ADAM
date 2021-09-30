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

    TextView tvInstruction;
    TextView tvColor;
    TextView tvScoreR;
    ImageButton ibOption1;
    ImageButton ibOption2;
    ImageButton ibOption3;
    ImageView ivLife1;
    ImageView ivLife2;
    ImageView ivLife3;
    int score = 0;
    int globalAnswer = -1;
    int globalLives = 3;
    List<String> colors = Arrays.asList("Amarillo", "Azul", "Café", "Morado", "Naranja", "Rojo", "Rosa", "Verde");
    List<Integer> colorValues = Arrays.asList(R.color.r_amarillo, R.color.r_azul_osc, R.color.r_cafe, R.color.r_morado, R.color.r_naranja, R.color.r_rojo, R.color.r_rosa, R.color.r_verde);
    List<Integer> figures = Arrays.asList(R.drawable.ic_boton_amarillo, R.drawable.ic_boton_azul_osc, R.drawable.ic_boton_cafe, R.drawable.ic_boton_morado, R.drawable.ic_boton_naranja, R.drawable.ic_boton_rojo, R.drawable.ic_boton_rosa, R.drawable.ic_boton_verde);

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

        ibOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(0);
            }
        });

        ibOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(1);
            }
        });

        ibOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(2);
            }
        });

        startGame();
    }

    private void startGame() {
        gameRound();
        new CountDownTimer(900000000, 1000) {
            public void onFinish() {
                Intent toHome = new Intent(RainbowActivityNVL1.this, HomeActivity.class);
                startActivity(toHome);
            }
            public void onTick(long millisUntilFinished) {
                if (globalLives <= 0) {
                    Intent toHome = new Intent(RainbowActivityNVL1.this, HomeActivity.class);
                    startActivity(toHome);
                }
            }
        }.start();
    }

    private void gameRound() {
        int randomColor = new Random().nextInt(8);
        tvColor.setText(colors.get(randomColor));
        int randomValue = new Random().nextInt(8);
        while (randomValue == randomColor) {
            randomValue = new Random().nextInt(8);
        }
        tvColor.setTextColor(getResources().getColor(colorValues.get(randomValue)));
        int randomAnswerA = new Random().nextInt(3);
        globalAnswer = randomAnswerA;
        if (randomAnswerA == 0) {
            ibOption1.setImageResource(figures.get(randomColor));
        }
        if (randomAnswerA == 1) {
            ibOption2.setImageResource(figures.get(randomColor));
        }
        if (randomAnswerA == 2) {
            ibOption3.setImageResource(figures.get(randomColor));
        }
        int randomAnswerB = new Random().nextInt(3);
        while (randomAnswerB == randomAnswerA) {
            randomAnswerB = new Random().nextInt(3);
        }
        if (randomAnswerB == 0) {
            ibOption1.setImageResource(figures.get(randomValue));
        }
        if (randomAnswerB == 1) {
            ibOption2.setImageResource(figures.get(randomValue));
        }
        if (randomAnswerB == 2) {
            ibOption3.setImageResource(figures.get(randomValue));
        }
        int randomAnswerC = randomAnswerA + randomAnswerB;
        int randomOption = new Random().nextInt(3);
        while (randomOption == randomValue || randomOption == randomColor) {
            randomOption = new Random().nextInt(8);
        }
        if (randomAnswerC == 1) {
            ibOption3.setImageResource(figures.get(randomOption));
        }
        if (randomAnswerC == 2) {
            ibOption2.setImageResource(figures.get(randomOption));
        }
        if (randomAnswerC == 3) {
            ibOption1.setImageResource(figures.get(randomOption));
        }
    }

    private void checkAnswer(int answer) {
        if (answer == globalAnswer) {
            score += 10 * globalLives;
            String scoreString = "Puntuación: " + String.valueOf(score);
            tvScoreR.setText(scoreString);
        }
        else {
            globalLives --;
        }
        if (globalLives == 2) {
            ivLife3.setImageResource(R.drawable.ic_adam_dead);
        }
        if (globalLives == 1) {
            ivLife2.setImageResource(R.drawable.ic_adam_dead);
        }
        if (globalLives == 0) {
            ivLife1.setImageResource(R.drawable.ic_adam_dead);
        }
        globalAnswer = -1;
        gameRound();
    }

}