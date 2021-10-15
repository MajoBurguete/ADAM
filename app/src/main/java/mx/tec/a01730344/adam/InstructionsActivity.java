package mx.tec.a01730344.adam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//Actividad creada para Desplegar las intrucciones de cada juego antes de iniciar una partida
public class InstructionsActivity extends AppCompatActivity {

    //declaracion de todos los componentes necesarios para el funcionamiento del fragmento.
    ConstraintLayout clRainbow, clFormitas, clCuento, clCuento2;
    Button btnNext, btnPlay;
    int game;

    //funcion que realiza acciones al crear la actividad.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //enlace entre las variables declaradas y los componentes del layout
        setContentView(R.layout.activity_instructions);
        clRainbow = findViewById(R.id.clRainbow);
        clFormitas = findViewById(R.id.clFormitas);
        clCuento = findViewById(R.id.clCuento);
        clCuento2 = findViewById(R.id.clCuento2);
        //declaracion de la variable adjuntada al cambio de pantalla para saber de que juego viene.
        game = getIntent().getExtras().getInt("game");
        chooseGame(game);
    }

    //funcion creada para desplegar los compontentes acordes al juego que el usuario eligio, lo cual se verifica con la variable recibida
    //0: Arcoiris, 1: En formitas, 2: Te Cuento Un Cuento
    private void chooseGame(int game) {
        Intent toGame = new Intent(InstructionsActivity.this, SelectDifficultyActivity.class);
        if (game == 0) {
            //se modifica la visibilidad de los elementos de acuerdo al juego
            clRainbow.setVisibility(View.VISIBLE);
            clFormitas.setVisibility(View.GONE);
            clCuento.setVisibility(View.GONE);
            clCuento2.setVisibility(View.GONE);
            btnPlay = findViewById(R.id.btnVamosArcoiris);

            //Funcion para detectar la interaccion del usuario con el boton para avanzar al juego seleccionado
            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Elemento que se manda a la siguiente pantalla para poder identificar que juego se selecciono
                    toGame.putExtra("game", 0);
                    startActivity(toGame);

                }
            });
        }
        if (game == 1) {
            //se modifica la visibilidad de los elementos de acuerdo al juego
            clFormitas.setVisibility(View.VISIBLE);
            clRainbow.setVisibility(View.GONE);
            clCuento.setVisibility(View.GONE);
            clCuento2.setVisibility(View.GONE);
            btnPlay = findViewById(R.id.btnVamosFormitas);

            //Funcion para detectar la interaccion del usuario con el boton para avanzar al juego seleccionado
            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Elemento que se manda a la siguiente pantalla para poder identificar que juego se selecciono
                    toGame.putExtra("game", 1);
                    startActivity(toGame);
                }
            });
        }
        if (game == 2) {
            //se modifica la visibilidad de los elementos de acuerdo al juego
            clCuento.setVisibility(View.VISIBLE);
            clRainbow.setVisibility(View.GONE);
            clFormitas.setVisibility(View.GONE);
            clCuento2.setVisibility(View.GONE);
            btnNext = findViewById(R.id.btnSeguirCuento);
            btnPlay = findViewById(R.id.btnJugarCuento);

            //Funcion para detectar la interaccion del usuario con el boton para avanzar a la segunda pagina de instrucciones
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clCuento.setVisibility(View.GONE);
                    clCuento2.setVisibility(View.VISIBLE);
                }
            });

            //Funcion para detectar la interaccion del usuario con el boton para avanzar al juego seleccionado
            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Elemento que se manda a la siguiente pantalla para poder identificar que juego se selecciono
                    toGame.putExtra("game", 2);
                    startActivity(toGame);
                }
            });
        }
    }
}