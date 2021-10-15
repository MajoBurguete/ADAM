/* Integración de seguridad informática en redes y sistemas de software (TC2007B.1)
   ADAM: Aplicación para el Desarrollo de Atención y Memoria
   Fecha: 17/10/2021
   Creado por: María José Burguete Euán
               Aarón Cortés García
               Marco Flamenco Andrade
               Daniela Hernández y Hernández
*/

package mx.tec.a01730344.adam;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

//Fragmento creado para desplegar al terminar las sesiones de cada juego.

public class GameOverFragment extends Fragment {

    //Declaración de todos los componentes necesarios para el funcionamiento del fragmento.
    Button btnExit;
    Button btnPlayAgain;
    TextView tvScore;
    TextView tvNewHigh;
    ConstraintLayout clRainbow;
    ConstraintLayout clStory;
    ConstraintLayout clShapes;

    //Funciones necesarias para que el fragmento se ejecute de manera correcta.
    public GameOverFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_over, container, false);
    }

    //Función añadida para realizar acciones al crear el fragmento.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //DEclaración del Bundle para poder recibir informacion de la pantalla de donde viene
        Bundle bundle = this.getArguments();
        int game = bundle.getInt("game");
        int score = bundle.getInt("score");
        boolean high = bundle.getBoolean("high");
        //enlace entre las variables declaradas y los componentes del layout
        clRainbow = view.findViewById(R.id.clGameOverArcoiris);
        clStory = view.findViewById(R.id.clGameOverTeCuento);
        clShapes = view.findViewById(R.id.clGameOverFormitas);

        //Condicionales que determinan los componentes a desplegar dependiendo del juego del que se proviene
        // 0: Arcoiris, 1: Te cuento Un Cuento, 2: En Formitas
        if (game == 0) {
            clRainbow.setVisibility(View.VISIBLE);
            clStory.setVisibility(View.GONE);
            clShapes.setVisibility(View.GONE);
            btnExit = view.findViewById(R.id.btnExitArcoiris);
            btnPlayAgain = view.findViewById(R.id.btnPlayAgainArcoiris);
            tvScore = view.findViewById(R.id.tvIntScoreGameOverArcoiris);
            tvNewHigh = view.findViewById(R.id.tvNewHighscoreArcoiris);

        } else if (game == 1) {
            clRainbow.setVisibility(View.GONE);
            clStory.setVisibility(View.VISIBLE);
            clShapes.setVisibility(View.GONE);
            btnExit = view.findViewById(R.id.btnExitTeCuento);
            btnPlayAgain = view.findViewById(R.id.btnPlayAgainTeCuento);
            tvScore = view.findViewById(R.id.tvIntScoreGameOverTeCuento);
            tvNewHigh = view.findViewById(R.id.tvNewHighscoreTeCuento);

        } else {
            clRainbow.setVisibility(View.GONE);
            clStory.setVisibility(View.GONE);
            clShapes.setVisibility(View.VISIBLE);
            btnExit = view.findViewById(R.id.btnExitFormitas);
            btnPlayAgain = view.findViewById(R.id.btnPlayAgainFormitas);
            tvScore = view.findViewById(R.id.tvIntScoreGameOverFormitas);
            tvNewHigh = view.findViewById(R.id.tvNewHighscoreFormitas);

        }

        //Se cambia el texto del TextView para desplegar el puntaje alcanzado
        tvScore.setText(String.valueOf(score));

        //Con las variables recibidas, se checa si es un nuevo record o no y se despliega el mensaje notificando esto de acuerdo a ella.
        if (high){
            tvNewHigh.setVisibility(View.VISIBLE);
        } else {
            tvNewHigh.setVisibility(View.GONE);
        }

        //Función para detectar la interacción del usuario con el botón de salida y asi poder regresarlo a la pantalla de HomeActivity.
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHome = new Intent(getActivity(), HomeActivity.class);
                startActivity(toHome);
            }
        });

        //Función para detectar la interacción del usuario con el botón de salida y asi poder regresarlo a la pantalla de instrucciones del juego del que provino.
        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (game == 0) {
                    Intent toIntructionsR = new Intent(getActivity(), InstructionsActivity.class);
                    toIntructionsR.putExtra("game",0);
                    startActivity(toIntructionsR);
                } else if (game == 1) {
                    Intent toIntructionsC = new Intent(getActivity(), InstructionsActivity.class);
                    toIntructionsC.putExtra("game",2);
                    startActivity(toIntructionsC);
                } else {
                    Intent toIntructionsF = new Intent(getActivity(), InstructionsActivity.class);
                    toIntructionsF.putExtra("game",1);
                    startActivity(toIntructionsF);
                }
            }
        });

    }





}