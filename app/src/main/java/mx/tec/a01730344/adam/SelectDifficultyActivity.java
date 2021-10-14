package mx.tec.a01730344.adam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SelectDifficultyActivity extends AppCompatActivity {

    ConstraintLayout clArcoirisDifficulty, clFormitasDifficulty, clTeCuentoUnCuentoDifficulty;
    Button easy, medium, hard;
    ImageButton ibBack;
    int game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_difficulty);

        clArcoirisDifficulty = findViewById(R.id.clArcoirisDifficulty);
        clFormitasDifficulty = findViewById(R.id.clFormitasDifficulty);
        clTeCuentoUnCuentoDifficulty = findViewById(R.id.clTeCuentoUnCuentoDifficulty);
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

        chooseGame(game);

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHome = new Intent(SelectDifficultyActivity.this, HomeActivity.class);
                startActivity(toHome);
            }
        });
    }



    private void chooseGame(int game) {
        Intent toGame = new Intent(SelectDifficultyActivity.this, RainbowActivityNVL1.class);
        if (game == 0) {
            clArcoirisDifficulty.setVisibility(View.VISIBLE);
            clFormitasDifficulty.setVisibility(View.GONE);
            clTeCuentoUnCuentoDifficulty.setVisibility(View.GONE);
            easy = findViewById(R.id.btnEasyArcoiris);
            medium = findViewById(R.id.btnMediumArcoiris);

            easy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(toGame);
                    finish();
                }
            });

            medium.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toGame.setClass(SelectDifficultyActivity.this, RainbowActivityNVL2.class);
                    startActivity(toGame);
                    finish();
                }
            });

        }
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