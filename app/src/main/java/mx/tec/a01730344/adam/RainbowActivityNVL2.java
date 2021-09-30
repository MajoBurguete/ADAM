package mx.tec.a01730344.adam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RainbowActivityNVL2 extends AppCompatActivity {

    TextView tvInstruction;
    TextView tvColor;
    TextView tvScoreR;
    ImageButton ibOption1;
    ImageButton ibOption2;
    ImageButton ibOption3;
    ImageButton ibOption4;
    ImageView ivLife1;
    ImageView ivLife2;
    ImageView ivLife3;
    int score = 0;
    int globalAnswer = -1;
    int globalLives = 3;
    List<String> colors = Arrays.asList("Amarillo", "Azul", "Café", "Morado", "Naranja", "Rojo", "Rosa", "Verde", "Celeste", "Beige", "Fucsia", "Menta");
    List<Integer> colorValues = Arrays.asList(R.color.r_amarillo, R.color.r_azul_osc, R.color.r_cafe, R.color.r_morado, R.color.r_naranja, R.color.r_rojo, R.color.r_rosa, R.color.r_verde, R.color.r_celeste, R.color.r_beige, R.color.r_fucsia, R.color.r_menta);
    List<Integer> figures = Arrays.asList(R.drawable.ic_boton_amarillo, R.drawable.ic_boton_azul_osc, R.drawable.ic_boton_cafe, R.drawable.ic_boton_morado, R.drawable.ic_boton_naranja, R.drawable.ic_boton_rojo, R.drawable.ic_boton_rosa, R.drawable.ic_boton_verde, R.drawable.ic_boton_celeste,R.drawable.ic_boton_beige, R.drawable.ic_boton_fucsia, R.drawable.ic_boton_menta);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rainbow_nvl2);

        tvInstruction = findViewById(R.id.tvInstruction);
        tvColor = findViewById(R.id.tvColor);
        tvScoreR = findViewById(R.id.tvScoreR);
        ibOption1 = findViewById(R.id.ibOption1);
        ibOption2 = findViewById(R.id.ibOption2);
        ibOption3 = findViewById(R.id.ibOption3);
        ibOption4 = findViewById(R.id.ibOption4);
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

        ibOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(3);
            }
        });

        startGame();
    }

    private void startGame() {
        gameRound();
        new CountDownTimer(900000000, 1000) {
            public void onFinish() {
                Intent toHome = new Intent(RainbowActivityNVL2.this, HomeActivity.class);
                startActivity(toHome);
            }
            public void onTick(long millisUntilFinished) {
                if (globalLives <= 0) {
                    //Se extrae el highScoreRainbow del usuario y se compara con el score, se guarda solo si este último es mayor
                    Intent toHome = new Intent(RainbowActivityNVL2.this, HomeActivity.class);
                    startActivity(toHome);
                }
            }
        }.start();
    }

    private void gameRound() {
        List<Integer> options = Arrays.asList(0, 1, 2, 3);
        Collections.shuffle(options);
        int randomAnswerA = options.get(0);
        globalAnswer = randomAnswerA;
        int randomAnswerB = options.get(1);
        int randomAnswerC = options.get(2);
        int randomAnswerD = options.get(3);
        int randomColor = new Random().nextInt(12);
        tvColor.setText(colors.get(randomColor));
        int randomValue = new Random().nextInt(12);
        while (randomValue == randomColor) {
            randomValue = new Random().nextInt(12);
        }
        tvColor.setTextColor(getResources().getColor(colorValues.get(randomValue)));
        int randomOption1 = new Random().nextInt(12);
        while (randomOption1 == randomColor || randomOption1 == randomValue) {
            randomOption1 = new Random().nextInt(12);
        }
        int randomOption2 = new Random().nextInt(12);
        while (randomOption2 == randomColor || randomOption2 == randomValue || randomOption2 == randomOption1) {
            randomOption2 = new Random().nextInt(12);
        }
        colorChange(randomAnswerA, randomColor);
        colorChange(randomAnswerB, randomValue);
        colorChange(randomAnswerC, randomOption1);
        colorChange(randomAnswerD, randomOption2);
    }

    private void checkAnswer(int answer) {
        if (answer == globalAnswer) {
            score += 20 * globalLives;
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

    private void colorChange(int answer, int color) {
        if (answer == 0) {
            ibOption1.setImageResource(figures.get(color));
        }
        if (answer == 1) {
            ibOption2.setImageResource(figures.get(color));
        }
        if (answer == 2) {
            ibOption3.setImageResource(figures.get(color));
        }
        if (answer == 3) {
            ibOption4.setImageResource(figures.get(color));
        }
    }

}