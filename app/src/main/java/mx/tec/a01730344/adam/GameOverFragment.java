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

public class GameOverFragment extends Fragment {

    Button btnExit;
    Button btnPlayAgain;
    TextView tvScore;
    TextView tvNewHigh;
    ConstraintLayout clRainbow;
    ConstraintLayout clStory;
    ConstraintLayout clShapes;

    public GameOverFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_over, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = this.getArguments();
        int game = bundle.getInt("game");
        int score = bundle.getInt("score");
        boolean high = bundle.getBoolean("high");
        clRainbow = view.findViewById(R.id.clGameOverArcoiris);
        clStory = view.findViewById(R.id.clGameOverTeCuento);
        clShapes = view.findViewById(R.id.clGameOverFormitas);

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
        tvScore.setText(String.valueOf(score));
        if (high){
            tvNewHigh.setVisibility(View.VISIBLE);
        }
        else {
            tvNewHigh.setVisibility(View.GONE);
        }


        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHome = new Intent(getActivity(), HomeActivity.class);
                startActivity(toHome);
            }
        });

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