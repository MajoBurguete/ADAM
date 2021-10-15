package mx.tec.a01730344.adam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/*Esta clase maneja la Actividad de la Selección de Dificultad*/

public class SelectDifficultyActivity extends AppCompatActivity {

    //Se instancian todas las variables necesarias
    ConstraintLayout clArcoirisDifficulty, clFormitasDifficulty, clTeCuentoUnCuentoDifficulty;
    Button easy, medium, hard;
    ImageButton ibBack;
    int game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_difficulty);

        //Se vinculan todos los elementos existentes del Layout para poder interactuar con ellos
        clArcoirisDifficulty = findViewById(R.id.clArcoirisDifficulty);
        clFormitasDifficulty = findViewById(R.id.clFormitasDifficulty);
        clTeCuentoUnCuentoDifficulty = findViewById(R.id.clTeCuentoUnCuentoDifficulty);
        /*El valor del juego se determina gracias al valor del parámetro "game" recibido en el
          Intent para inicializar la Actividad. 0 - Arcoiris, 1 - En Formitas, 2 - Te Cuento un Cuento*/
        game = getIntent().getExtras().getInt("game");
        if (game== 0){
            ibBack = findViewById(R.id.ibBackArcoiris);
        }
        else if (game== 1){
            ibBack = findViewById(R.id.ibBackFormitas);
        }
        else {
            ibBack = findViewById(R.id.ibBackCuento);
        }

        //Se llama al método "chooseGame" con el valor del juego previamente obtenido
        chooseGame(game);

        /*Se crea la funcinalidad del botón para regresar, realizando el Intent hacia Home*/
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHome = new Intent(SelectDifficultyActivity.this, HomeActivity.class);
                startActivity(toHome);
            }
        });
    }

    /*El método "chooseGame" recibe un entero como argumento con el fin de mostrar el Constraint Layout correcto*/
    private void chooseGame(int game) {
        Intent toGame = new Intent(SelectDifficultyActivity.this, RainbowActivityNVL1.class);
        //Si el valor del juego es cero, se muestra el ConstraintLayout correspondiente al juego "Arcoiris" y se ocultan los otros dos
        if (game == 0) {
            clArcoirisDifficulty.setVisibility(View.VISIBLE);
            clFormitasDifficulty.setVisibility(View.GONE);
            clTeCuentoUnCuentoDifficulty.setVisibility(View.GONE);
            //Se referencían los botones para la dificultad "fácil" e "intermedio"
            easy = findViewById(R.id.btnEasyArcoiris);
            medium = findViewById(R.id.btnMediumArcoiris);

            //Se asigna la funcionalidad del botón "easy", ejecutando el Intent adecuado
            easy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(toGame);
                    finish();
                }
            });

            //Se asigna la funcionalidad del botón "medium", cambiando la clase correspondiente y ejecutando el Intent adecuado
            medium.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toGame.setClass(SelectDifficultyActivity.this, RainbowActivityNVL2.class);
                    startActivity(toGame);
                    finish();
                }
            });

        }
        /*Este proceso se repite para cada uno de los juegos, únicmamente variando los ConstraintLayout y las clases hacia las que
          redirigirá el Intent*/
        if (game == 1) {
            clFormitasDifficulty.setVisibility(View.VISIBLE);
            clArcoirisDifficulty.setVisibility(View.GONE);
            clTeCuentoUnCuentoDifficulty.setVisibility(View.GONE);
            easy = findViewById(R.id.btnEasyFormitas);
            medium = findViewById(R.id.btnMediumFormitas);
            hard = findViewById(R.id.btnHardFormitas);

            easy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toGame.setClass(SelectDifficultyActivity.this, ShapesActivity.class);
                    startActivity(toGame);
                    finish();
                }
            });

            medium.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toGame.setClass(SelectDifficultyActivity.this, ShapesActivityNVL2.class);
                    startActivity(toGame);
                    finish();
                }
            });

            hard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toGame.setClass(SelectDifficultyActivity.this, ShapesActivityNVL3.class);
                    startActivity(toGame);
                    finish();
                }
            });
        }
        if (game == 2) {
            clTeCuentoUnCuentoDifficulty.setVisibility(View.VISIBLE);
            clArcoirisDifficulty.setVisibility(View.GONE);
            clFormitasDifficulty.setVisibility(View.GONE);
            easy = findViewById(R.id.btnEasyTeCuento);
            medium = findViewById(R.id.btnMediumTeCuento);
            hard = findViewById(R.id.btnHardTeCuento);

            easy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toGame.setClass(SelectDifficultyActivity.this, StoryActivity.class);
                    toGame.putExtra("difficulty", 1);
                    startActivity(toGame);
                    finish();
                }
            });

            medium.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toGame.setClass(SelectDifficultyActivity.this, StoryActivity.class);
                    toGame.putExtra("difficulty", 2);
                    startActivity(toGame);
                    finish();
                }
            });

            hard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toGame.setClass(SelectDifficultyActivity.this, StoryActivity.class);
                    toGame.putExtra("difficulty", 3);
                    startActivity(toGame);
                    finish();
                }
            });
        }
    }

}