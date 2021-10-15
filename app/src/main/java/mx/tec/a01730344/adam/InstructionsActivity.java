package mx.tec.a01730344.adam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InstructionsActivity extends AppCompatActivity {

    ConstraintLayout clRainbow, clFormitas, clCuento, clCuento2;
    Button btnNext, btnPlay;
    int game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        clRainbow = findViewById(R.id.clRainbow);
        clFormitas = findViewById(R.id.clFormitas);
        clCuento = findViewById(R.id.clCuento);
        clCuento2 = findViewById(R.id.clCuento2);
        game = getIntent().getExtras().getInt("game");

        chooseGame(game);
    }

    private void chooseGame(int game) {
        Intent toGame = new Intent(InstructionsActivity.this, SelectDifficultyActivity.class);
        if (game == 0) {
            clRainbow.setVisibility(View.VISIBLE);
            clFormitas.setVisibility(View.GONE);
            clCuento.setVisibility(View.GONE);
            clCuento2.setVisibility(View.GONE);
            btnPlay = findViewById(R.id.btnVamosArcoiris);

            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toGame.putExtra("game", 0);
                    startActivity(toGame);

                }
            });
        }
        if (game == 1) {
            clFormitas.setVisibility(View.VISIBLE);
            clRainbow.setVisibility(View.GONE);
            clCuento.setVisibility(View.GONE);
            clCuento2.setVisibility(View.GONE);
            btnPlay = findViewById(R.id.btnVamosFormitas);

            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toGame.putExtra("game", 1);
                    startActivity(toGame);
                }
            });
        }
        if (game == 2) {
            clCuento.setVisibility(View.VISIBLE);
            clRainbow.setVisibility(View.GONE);
            clFormitas.setVisibility(View.GONE);
            clCuento2.setVisibility(View.GONE);
            btnNext = findViewById(R.id.btnSeguirCuento);
            btnPlay = findViewById(R.id.btnJugarCuento);

            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clCuento.setVisibility(View.GONE);
                    clCuento2.setVisibility(View.VISIBLE);
                }
            });

            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toGame.putExtra("game", 2);
                    startActivity(toGame);
                }
            });
        }
    }
}