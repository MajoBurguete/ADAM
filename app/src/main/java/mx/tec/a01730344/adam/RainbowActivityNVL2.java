/* Integración de seguridad informática en redes y sistemas de software (TC2007B.1)
   ADAM: Aplicación para el Desarrollo de Atención y Memoria
   Fecha: 17/10/2021
   Creado por: María José Burguete Euán
               Aarón Cortés García
               Marco Flamenco Andrade
               Daniela Hernández y Hernández
*/

package mx.tec.a01730344.adam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

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

/*Esta clase representa el Controlador para el primer nivel "intermedio" del juego "Arcoiris"*/

public class RainbowActivityNVL2 extends AppCompatActivity {

    //Se instancian todas las variables necesarias para el sistema del Controlador
    RainbowModelNVL2 model;
    TextView tvInstruction;
    TextView tvColor;
    TextView tvScoreR;
    ImageButton ibOption1;
    ImageButton ibOption2;
    ImageButton ibOption3;
    ImageButton ibOption4;
    ImageButton btnPause;
    ImageView ivLife1;
    ImageView ivLife2;
    ImageView ivLife3;
    String scoreString;
    User user = new User(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rainbow_nvl2);

        //Se carga la información de los usuarios registrados en la Properties List
        user.loadProfiles();
        /*Se hace la instancia del modelo relacionado"*/
        model = new RainbowModelNVL2();
        //Se vinculan todos los elementos existentes del Layout para poder interactuar con ellos
        tvInstruction = findViewById(R.id.tvInstruction);
        tvColor = findViewById(R.id.tvColor);
        tvScoreR = findViewById(R.id.tvScoreR);
        ibOption1 = findViewById(R.id.ibOption1);
        ibOption2 = findViewById(R.id.ibOption2);
        ibOption3 = findViewById(R.id.ibOption3);
        ibOption4 = findViewById(R.id.ibOption4);
        btnPause = findViewById(R.id.btnPause);
        ivLife1 = findViewById(R.id.ivLife1);
        ivLife2 = findViewById(R.id.ivLife2);
        ivLife3 = findViewById(R.id.ivLife3);

        /*Cada ImageButton que representa una opción de respuesta llama al método "checkAnswer" del modelo,
          pasando como parámetro el entero correspondiente*/
        ibOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.checkAnswer(0);
                scoreString = "Puntaje: " + model.score;

                //Se actualiza el texto del TextView de la puntuación reflejada por el modelo
                tvScoreR.setText(scoreString);
                //Se llama al método "checkLives" para evaluar si la partida debe continuar
                checkLives();
                //Se llama al método "setLayoutAttributes" para actualizar los valores del Layout conforme a los datos del modelo
                setLayoutAttributes();
            }
        });

        //El mismo proceso se aplica para todos los demás ImageButton que representan las opciones de respuesta
        ibOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.checkAnswer(1);
                scoreString = "Puntaje: " + model.score;
                tvScoreR.setText(scoreString);
                checkLives();
                setLayoutAttributes();
            }
        });

        ibOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.checkAnswer(2);
                scoreString = "Puntaje: " + model.score;
                tvScoreR.setText(scoreString);
                checkLives();
                setLayoutAttributes();
            }
        });

        ibOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.checkAnswer(3);
                scoreString = "Puntaje: " + model.score;
                tvScoreR.setText(scoreString);
                checkLives();
                setLayoutAttributes();
            }
        });

        /*Se crea la funcinalidad del botón de pausa, desplegando el Frame Layout con los elementos
          correspondientes del Fragmento de Pausa*/
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.flGameArcoiris2, new PauseFragment()).commit();
            }
        });

        /*Justo al iniciar la Actividad se llama al método "startGame"*/
        startGame();
    }

    /*El método "startGame" sirve para actualizar los valores del Layout tomando los atributos del modelo
      como referencia y evaluar con el número de vidas si la partida se debe finalizar*/
    private void startGame() {
        //Se actualizan los valores del Layout
        setLayoutAttributes();
        //El CountDownTimer está en constante escucha para poder ejecutar sus acciones en caso de pasar el tiempo definido
        new CountDownTimer(900000000, 500) {
            //Si el tiempo máximo de los 900000000 milisegundos es sobrepasado sin repuesta de input, se hace un Intent hacia Home
            public void onFinish() {
                Intent toHome = new Intent(RainbowActivityNVL2.this, HomeActivity.class);
                startActivity(toHome);
            }
            //Si el tiempo del medio segundo es sobrepasado, se procede a evaluar el número actual de vidas
            public void onTick(long millisUntilFinished) {
                /*Si el número de vidas es 0, se obtienen los parámetros necesarios para poder hacer
                  la evaluación de la Puntuación*/
                if (model.globalLives == 0) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("game", 0);
                    bundle.putInt("score", model.score);
                    /*Si la puntuación obtenida es más alta que la guardada actualmente en el perfil, la llave "high"
                      recibe el valor verdadero, indicando que debe haber una actualización*/
                    if (user.getCurrentUserScoreR() < model.score) {
                        bundle.putBoolean("high", true);
                    } else {
                        bundle.putBoolean("high", false);
                    }
                    //Se redirige al Fragmento del Game Over con los argumentos importantes del Bundle
                    GameOverFragment fragment = new GameOverFragment();
                    fragment.setArguments(bundle);
                    user.updateScore(model.score,user.getCurrentUserNumber(),0);
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.flGameArcoiris2, fragment).commit();
                    model.globalLives --;
                    //El CountDownTimer es finalizado
                    cancel();
                }
            }
        }.start();
    }

    /*El método "setLayoutAttributes" llama a "gameRound" del modelo para actualizar sus atributos y
      poder reflejarlos en el Layout*/
    private void setLayoutAttributes() {
        model.gameRound();
        tvColor.setText(model.colorText);
        tvColor.setTextColor(getResources().getColor(model.colorValue));
        ibOption1.setImageResource(model.imageView1);
        ibOption2.setImageResource(model.imageView2);
        ibOption3.setImageResource(model.imageView3);
        ibOption4.setImageResource(model.imageView4);
    }

    /*El método "checkLives" revisa el valor de las vidas en el modelo para hacer los cambios oportunos*/
    private void checkLives() {
        //Se checa el valor específico para cambiar el recurso de las las ImageViews
        if (model.globalLives == 2) {
            ivLife3.setImageResource(R.drawable.ic_adam_dead);
        }
        if (model.globalLives == 1) {
            ivLife2.setImageResource(R.drawable.ic_adam_dead);
        }
        /*Si el valor de las vidas llega a cero, además de cambiar el recurso, se desabilitan todos los
          botones de opción, dando tiempo al CountDownTimer de hacer la evaluación y realizar el Intent*/
        if (model.globalLives == 0) {
            ivLife1.setImageResource(R.drawable.ic_adam_dead);
            ibOption1.setEnabled(false);
            ibOption2.setEnabled(false);
            ibOption3.setEnabled(false);
            ibOption4.setEnabled(false);

        }
    }

    @Override
    public void onBackPressed() {
    }

}