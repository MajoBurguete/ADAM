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

//fragmento creado para Desplegar al terminar las sesiones de cada juego.
public class GameOverFragment extends Fragment {

    //declaracion de todos los componentes necesarios para el funcionamiento del fragmento.
    Button btnExit;
    Button btnPlayAgain;
    TextView tvScore;
    TextView tvNewHigh;
    ConstraintLayout clRainbow;
    ConstraintLayout clStory;
    ConstraintLayout clShapes;

    //funcion necesarias para que el fragmento se ejecute de manera correcta.
    public GameOverFragment() {
    }

    //funcion necesarias para que el fragmento se ejecute de manera correcta.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_over, container, false);
    }

    //funcion añadida para realizar acciones al crear el fragmento.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //declaracion del Bundle para poder recibir informacion de la pantalla de donde viene
        Bundle bundle = this.getArguments();
        int game = bundle.getInt("game");
        int score = bundle.getInt("score");
        boolean high = bundle.getBoolean("high");
        //enlace entre las variables declaradas y los componentes del layout
        clRainbow = view.findViewById(R.id.clGameOverArcoiris);
        clStory = view.findViewById(R.id.clGameOverTeCuento);
        clShapes = view.findViewById(R.id.clGameOverFormitas);

        //condicionales que determinan los componentes a desplegar dependiendo del juego del que se proviene
        // 0: Arcoiris, 1: Te cuento Un Cuento, 2: En Formitas
        if (game == 0) {
            clRainbow.setVisibility(View.VISIBLE);
            clStory.setVisibility(View.GONE);
            clShapes.setVisibility(View.GONE);
            btnExit = view.findViewById(R.id.btnExitArcoiris);
            btnPlayAgain = view.findViewById(R.id.btnPlayAgainArcoiris);
            tvScore = view.findViewById(R.id.tvIntScoreGameOverArcoiris);
            tvNewHigh = view.findViewById(R.id.tvNewHighscoreArcoiris);

        }
        else if (game == 1){
            clRainbow.setVisibility(View.GONE);
            clStory.setVisibility(View.VISIBLE);
            clShapes.setVisibility(View.GONE);
            btnExit = view.findViewById(R.id.btnExitTeCuento);
            btnPlayAgain = view.findViewById(R.id.btnPlayAgainTeCuento);
            tvScore = view.findViewById(R.id.tvIntScoreGameOverTeCuento);
            tvNewHigh = view.findViewById(R.id.tvNewHighscoreTeCuento);

        }
        else {
            clRainbow.setVisibility(View.GONE);
            clStory.setVisibility(View.GONE);
            clShapes.setVisibility(View.VISIBLE);
            btnExit = view.findViewById(R.id.btnExitFormitas);
            btnPlayAgain = view.findViewById(R.id.btnPlayAgainFormitas);
            tvScore = view.findViewById(R.id.tvIntScoreGameOverFormitas);
            tvNewHigh = view.findViewById(R.id.tvNewHighscoreFormitas);

        }
        //se cambia el texto del TextView para desplegar el puntaje alcanzado
        tvScore.setText(String.valueOf(score));
        //con las variables recibidas, se checa si es un nuevo record o no y se despliega el mensaje nitificando esto de acuerdo a ella.
        if (high){
            tvNewHigh.setVisibility(View.VISIBLE);
        }
        else {
            tvNewHigh.setVisibility(View.GONE);
        }

        //Funcion para detectar la interaccion del usuario con el boton de salida y asi poder regresarlo a la pantalla de HomeActivity.
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHome = new Intent(getActivity(), HomeActivity.class);
                startActivity(toHome);
            }
        });

        //Funcion para detectar la interaccion del usuario con el boton de salida y asi poder regresarlo a la pantalla de instrucciones del juego del que provino.
        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (game == 0){
                    Intent toIntructionsR = new Intent(getActivity(), InstructionsActivity.class);
                    toIntructionsR.putExtra("game",0);
                    startActivity(toIntructionsR);
                }
                else if (game == 1){
                    Intent toIntructionsC = new Intent(getActivity(), InstructionsActivity.class);
                    toIntructionsC.putExtra("game",2);
                    startActivity(toIntructionsC);
                }
                else {
                    Intent toIntructionsF = new Intent(getActivity(), InstructionsActivity.class);
                    toIntructionsF.putExtra("game",1);
                    startActivity(toIntructionsF);
                }
            }
        });

    }





}