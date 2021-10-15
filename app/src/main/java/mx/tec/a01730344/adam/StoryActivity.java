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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class StoryActivity extends AppCompatActivity {

    StoryModelNVL1 model1;
    StoryModelNVL2AndNVL3 model2And3;
    ConstraintLayout clStory;
    ConstraintLayout clQuestion;
    TextView tvStory;
    TextView tvQuestion;
    TextView tvTrueFalse;
    TextView tvScoreS;
    Button btnContinue;
    Button btnNextQ;
    Button btnFirstOption;
    Button btnSecondOption;
    Button btnThirdOption;
    ImageButton ibPauseS;

    ImageView ivLife1S;
    ImageView ivLife2S;
    ImageView ivLife3S;
    int lives = 3;
    int difficulty = 1;
    String strScore;
    int score = 0;
    User user = new User(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        user.loadProfiles();
        model1 = new StoryModelNVL1(this);
        model2And3 = new StoryModelNVL2AndNVL3(this);
        clStory = findViewById(R.id.clStory);
        clQuestion = findViewById(R.id.clQuestion);
        tvStory = findViewById(R.id.tvStory2);
        btnContinue = findViewById(R.id.btnContinue);
        btnNextQ = findViewById(R.id.btnNextQ);
        btnFirstOption = findViewById(R.id.btnOptionS1);
        btnSecondOption = findViewById(R.id.btnOptionS2);
        btnThirdOption = findViewById(R.id.btnOptionS3);
        tvQuestion = findViewById(R.id.tvQuestionS);
        tvTrueFalse = findViewById(R.id.tvResult);
        tvScoreS = findViewById(R.id.tvScoreS);
        ivLife1S = findViewById(R.id.ivLife1S);
        ivLife2S = findViewById(R.id.ivLife2S);
        ivLife3S = findViewById(R.id.ivLife3S);
        ibPauseS = findViewById(R.id.ibPauseS);
        difficulty = getIntent().getExtras().getInt("difficulty");

        ibPauseS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.flGameStory, new PauseFragment()).commit();
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (difficulty == 1) {
                    clStory.setVisibility(View.GONE);
                    clQuestion.setVisibility(View.VISIBLE);
                    model1.generateQuestionAndAnswers();
                    tvQuestion.setText(model1.question);
                    btnFirstOption.setText(model1.firstAnswerOption);
                    btnSecondOption.setText(model1.secondAnswerOption);
                    btnThirdOption.setText(model1.thirdAnswerOption);
                    btnNextQ.setEnabled(false);
                    btnFirstOption.setEnabled(true);
                    btnSecondOption.setEnabled(true);
                    btnThirdOption.setEnabled(true);
                    btnFirstOption.setBackgroundColor(getResources().getColor(R.color.verde_pasto));
                    btnSecondOption.setBackgroundColor(getResources().getColor(R.color.naranja_claro));
                    btnThirdOption.setBackgroundColor(getResources().getColor(R.color.azul_oceano));
                }
                else {
                    if (model2And3.actualParagraph <= 2) {
                        tvStory.setText(model2And3.generateNewParagraph(difficulty));
                    }
                    else {
                        clStory.setVisibility(View.GONE);
                        clQuestion.setVisibility(View.VISIBLE);
                        model2And3.generateQuestionAndAnswers();
                        tvQuestion.setText(model2And3.question);
                        btnFirstOption.setText(model2And3.firstAnswerOption);
                        btnSecondOption.setText(model2And3.secondAnswerOption);
                        btnThirdOption.setText(model2And3.thirdAnswerOption);
                        btnNextQ.setEnabled(false);
                        btnFirstOption.setEnabled(true);
                        btnSecondOption.setEnabled(true);
                        btnThirdOption.setEnabled(true);
                        btnFirstOption.setBackgroundColor(getResources().getColor(R.color.verde_pasto));
                        btnSecondOption.setBackgroundColor(getResources().getColor(R.color.naranja_claro));
                        btnThirdOption.setBackgroundColor(getResources().getColor(R.color.azul_oceano));
                    }
                }
            }
        });

        btnNextQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (difficulty == 1) {
                    if (lives > 0 && model1.actualQuestion <= 2) {
                        model1.generateQuestionAndAnswers();
                        tvTrueFalse.setText("");
                        tvQuestion.setText(model1.question);
                        btnFirstOption.setText(model1.firstAnswerOption);
                        btnSecondOption.setText(model1.secondAnswerOption);
                        btnThirdOption.setText(model1.thirdAnswerOption);
                        btnNextQ.setEnabled(false);
                        btnFirstOption.setEnabled(true);
                        btnSecondOption.setEnabled(true);
                        btnThirdOption.setEnabled(true);
                        btnFirstOption.setBackgroundColor(getResources().getColor(R.color.verde_pasto));
                        btnSecondOption.setBackgroundColor(getResources().getColor(R.color.naranja_claro));
                        btnThirdOption.setBackgroundColor(getResources().getColor(R.color.azul_oceano));
                    }
                    else {
                        Bundle bundle = new Bundle();
                        bundle.putInt("game", 1);
                        bundle.putInt("score", score);
                        if (user.getCurrentUserScoreC() < score) {
                            bundle.putBoolean("high", true);
                        } else {
                            bundle.putBoolean("high", false);
                        }
                        GameOverFragment fragment = new GameOverFragment();
                        fragment.setArguments(bundle);
                        user.updateScore(score,user.getCurrentUserNumber(),1);
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.flGameStory, fragment).commit();
                    }
                }
                else {
                    if (lives > 0 && model2And3.actualQuestion <= 2) {
                        model2And3.generateQuestionAndAnswers();
                        tvTrueFalse.setText("");
                        tvQuestion.setText(model2And3.question);
                        btnFirstOption.setText(model2And3.firstAnswerOption);
                        btnSecondOption.setText(model2And3.secondAnswerOption);
                        btnThirdOption.setText(model2And3.thirdAnswerOption);
                        btnNextQ.setEnabled(false);
                        btnFirstOption.setEnabled(true);
                        btnSecondOption.setEnabled(true);
                        btnThirdOption.setEnabled(true);
                        btnFirstOption.setBackgroundColor(getResources().getColor(R.color.verde_pasto));
                        btnSecondOption.setBackgroundColor(getResources().getColor(R.color.naranja_claro));
                        btnThirdOption.setBackgroundColor(getResources().getColor(R.color.azul_oceano));
                    }
                    else {
                        Bundle bundle = new Bundle();
                        bundle.putInt("game", 1);
                        bundle.putInt("score", score);
                        if (user.getCurrentUserScoreC() < score) {
                            bundle.putBoolean("high", true);
                        } else {
                            bundle.putBoolean("high", false);
                        }
                        GameOverFragment fragment = new GameOverFragment();
                        fragment.setArguments(bundle);
                        user.updateScore(score,user.getCurrentUserNumber(),1);
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.flGameStory, fragment).commit();
                    }
                }
            }
        });

        btnFirstOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(0);
            }
        });

        btnSecondOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(1);
            }
        });

        btnThirdOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(2);
            }
        });

        if (difficulty == 1) {
            tvStory.setText(model1.generateParagraph());
        }
        else {
            tvStory.setText(model2And3.generateNewParagraph(difficulty));
        }
    }

    private void checkAnswer(int option) {
        Button btnOption1 = btnFirstOption, btnOption2 = btnSecondOption, btnOption3 = btnThirdOption;
        if (option == 1) {
            btnOption1 = btnSecondOption;
            btnOption2 = btnFirstOption;
            btnOption3 = btnThirdOption;
        }
        if (option == 2) {
            btnOption1 = btnThirdOption;
            btnOption2 = btnFirstOption;
            btnOption3 = btnSecondOption;
        }
        String trueOrFalse = "";
        if (difficulty == 1) {
            if (model1.actualQuestion == 0) {
                if (btnOption1.getText() == model1.firstAns) {
                    trueOrFalse = "¡Muy bien!";
                } else {
                    trueOrFalse = "¡Intenta de nuevo!";
                }
            }
            if (model1.actualQuestion == 1) {
                if (btnOption1.getText() == model1.secondAns) {
                    trueOrFalse = "¡Muy bien!";
                } else {
                    trueOrFalse = "¡Intenta de nuevo!";
                }
            }
            if (model1.actualQuestion == 2) {
                if (btnOption1.getText() == model1.thirdAns) {
                    trueOrFalse = "¡Muy bien!";
                } else {
                    trueOrFalse = "¡Intenta de nuevo!";
                }
            }
        }
        else {
            if (model2And3.actualQuestion == 0) {
                if (btnOption1.getText() == model2And3.firstParagraphAns) {
                    trueOrFalse = "¡Muy bien!";
                } else {
                    trueOrFalse = "¡Intenta de nuevo!";
                }
            }
            if (model2And3.actualQuestion == 1) {
                if (btnOption1.getText() == model2And3.secondParagraphAns) {
                    trueOrFalse = "¡Muy bien!";
                } else {
                    trueOrFalse = "¡Intenta de nuevo!";
                }
            }
            if (model2And3.actualQuestion == 2) {
                if (btnOption1.getText() == model2And3.thirdParagraphAns) {
                    trueOrFalse = "¡Muy bien!";
                } else {
                    trueOrFalse = "¡Intenta de nuevo!";
                }
            }
        }
        tvTrueFalse.setText(trueOrFalse);
        if (trueOrFalse.equals("¡Muy bien!")) {
            score += lives * difficulty * 100;
            strScore = "Puntaje: " + score;
            tvScoreS.setText(strScore);
            btnOption1.setBackgroundColor(getResources().getColor(R.color.verde));
            btnOption2.setBackgroundColor(getResources().getColor(R.color.r_rojo));
            btnOption3.setBackgroundColor(getResources().getColor(R.color.r_rojo));
            btnOption1.setEnabled(false);
            btnOption2.setEnabled(false);
            btnOption3.setEnabled(false);
            btnNextQ.setEnabled(true);
            model1.actualQuestion ++;
            model2And3.actualQuestion ++;
        }
        else {
            lives --;
            if (lives == 2) {
                ivLife3S.setImageResource(R.drawable.ic_adam_dead);
            }
            if (lives == 1) {
                ivLife2S.setImageResource(R.drawable.ic_adam_dead);
            }
            if (lives == 0) {
                ivLife1S.setImageResource(R.drawable.ic_adam_dead);
                String gameOver = "¡Juego terminado!";
                tvTrueFalse.setText(gameOver);
                btnOption1.setEnabled(false);
                btnOption2.setEnabled(false);
                btnOption3.setEnabled(false);
                btnOption1.setBackgroundColor(getResources().getColor(R.color.r_rojo));
                if (difficulty == 1) {
                    if (model1.actualQuestion == 1) {
                        if (btnOption2.getText().equals(model1.secondAns)) {
                            btnOption2.setBackgroundColor(getResources().getColor(R.color.verde));
                            btnOption3.setBackgroundColor(getResources().getColor(R.color.r_rojo));
                        } else {
                            btnOption3.setBackgroundColor(getResources().getColor(R.color.verde));
                            btnOption2.setBackgroundColor(getResources().getColor(R.color.r_rojo));
                        }
                    } else {
                        if (btnOption2.getText().equals(model1.thirdAns)) {
                            btnOption2.setBackgroundColor(getResources().getColor(R.color.verde));
                            btnOption3.setBackgroundColor(getResources().getColor(R.color.r_rojo));
                        } else {
                            btnOption3.setBackgroundColor(getResources().getColor(R.color.verde));
                            btnOption2.setBackgroundColor(getResources().getColor(R.color.r_rojo));
                        }
                    }
                }
                else {
                    if (model2And3.actualQuestion == 1) {
                        if (btnOption2.getText().equals(model2And3.secondParagraphAns)) {
                            btnOption2.setBackgroundColor(getResources().getColor(R.color.verde));
                            btnOption3.setBackgroundColor(getResources().getColor(R.color.r_rojo));
                        } else {
                            btnOption3.setBackgroundColor(getResources().getColor(R.color.verde));
                            btnOption2.setBackgroundColor(getResources().getColor(R.color.r_rojo));
                        }
                    } else {
                        if (btnOption2.getText().equals(model2And3.thirdParagraphAns)) {
                            btnOption2.setBackgroundColor(getResources().getColor(R.color.verde));
                            btnOption3.setBackgroundColor(getResources().getColor(R.color.r_rojo));
                        } else {
                            btnOption3.setBackgroundColor(getResources().getColor(R.color.verde));
                            btnOption2.setBackgroundColor(getResources().getColor(R.color.r_rojo));
                        }
                    }
                }
                btnNextQ.setEnabled(true);
            }
            else {
                btnOption1.setEnabled(false);
            }
        }
    }

    @Override
    public void onBackPressed() {
    }
}