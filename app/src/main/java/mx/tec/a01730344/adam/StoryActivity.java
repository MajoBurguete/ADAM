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

/*Esta clase representa el Controlador para los 3 diferentes niveles del juego "Te Cuento un Cuento"*/

public class StoryActivity extends AppCompatActivity {

    //Se instancian todas las variables necesarias para el sistema del Controlador
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

        //Se carga la información de los usuarios registrados en la Properties List
        user.loadProfiles();
        /*Se hacen las dos instancias de los modelos relacionados, uno para tener la lógica del nivel
          de dificultad "fácil" y otro para "intermedio" y "difícil"*/
        model1 = new StoryModelNVL1(this);
        model2And3 = new StoryModelNVL2AndNVL3(this);
        //Se vinculan todos los elementos existentes del Layout para poder interactuar con ellos
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
        /*El nivel de dificulta se determina gracias al valor del parámetro "key" recibido en el
          Intent para inicializar la Actividad*/
        difficulty = getIntent().getExtras().getInt("difficulty");

        /*Se crea la funcinalidad del botón de pausa, desplegando el Frame Layout con los elementos
          correspondientes del Fragmento de Pausa*/
        ibPauseS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.flGameStory, new PauseFragment()).commit();
            }
        });

        //Se crea la funcionalidad del botón "Continuar", que cambia dependiendo del nivel de dificultad
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Debido a que el nivel "fácil" solo presenta una pregunta, el botón continuar directamente hace
                  los cambios necesarios en el Layout para pasar a la sección de preguntas*/
                if (difficulty == 1) {
                    //Se oculta el Constraint Layout de la sección de la historia
                    clStory.setVisibility(View.GONE);
                    //Se muestra el Constraint Layout con la sección de preguntas
                    clQuestion.setVisibility(View.VISIBLE);
                    /*Se llama al método "generateQuestionAndAnswers() para actualizar los valores y modificar los
                      elementos correspondientes del Layout con la primera pregunta*/
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
                    /*En caso de ser nivel "intermedio" o "dificil", se checa el número actual de la párrafo,
                      pues deben presentarse 3 antes de pasar a la sección de preguntas*/
                    if (model2And3.actualParagraph <= 2) {
                        //Se modifica el texto con la pregunta actual del modelo
                        tvStory.setText(model2And3.generateNewParagraph(difficulty));
                    }
                    else {
                        /*Al llegar a la sección de preguntas, se actualizan los valores correspondientes y se cambian
                          en el Layout*/
                        //Se oculta el Constraint Layout de la sección de la historia
                        clStory.setVisibility(View.GONE);
                        //Se muestra el Constraint Layout con la sección de preguntas
                        clQuestion.setVisibility(View.VISIBLE);
                        /*Se llama al método "generateQuestionAndAnswers() para actualizar los valores y modificar los
                          elementos correspondientes del Layout con la primera pregunta*/
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

        //Se crea la funcionalidad del botón "Siguiente pregunta", que cambia dependiendo del nivel de dificultad*/
        btnNextQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Si el nivel de dificultad es "fácil" se trabaja con el modelo correspondiente
                if (difficulty == 1) {
                    //Se checa que el jugador aún cuente con vidas y no se haya llegado a la última pregunta de la partida
                    if (lives > 0 && model1.actualQuestion <= 2) {
                        /*Se llama al método "generateQuestionAndAnswers() para actualizar los valores y modificar los
                          elementos correspondientes del Layout*/
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
                        /*En caso de haberse agotado las vidas o haber pasado la última pregunta, se obtienen los
                          parámetros necesarios para poder hacer la evaluación de la Puntuación*/
                        Bundle bundle = new Bundle();
                        bundle.putInt("game", 1);
                        bundle.putInt("score", score);
                        /*Si la puntuación obtenida es más alta que la guardada actualmente en el perfil, la llave "high"
                          recibe el valor verdadero, indicando que debe haber una actualización*/
                        if (user.getCurrentUserScoreC() < score) {
                            bundle.putBoolean("high", true);
                        } else {
                            bundle.putBoolean("high", false);
                        }
                        //Se redirige al Fragmento del Game Over con los argumentos importantes del Bundle
                        GameOverFragment fragment = new GameOverFragment();
                        fragment.setArguments(bundle);
                        user.updateScore(score,user.getCurrentUserNumber(),1);
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.flGameStory, fragment).commit();
                    }
                }
                //Si el nivel de dificultad es "intermedio" o "difícil" se trabaja con el modelo correspondiente
                else {
                    //Se checa que el jugador aún cuente con vidas y no se haya llegado a la última pregunta de la partida
                    if (lives > 0 && model2And3.actualQuestion <= 2) {
                        /*Se llama al método "generateQuestionAndAnswers() para actualizar los valores y modificar los
                          elementos correspondientes del Layout*/
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
                    /*En caso de haberse agotado las vidas o haber pasado la última pregunta, se obtienen los
                      parámetros necesarios para poder hacer la evlaución de la Puntuación*/
                    else {
                        Bundle bundle = new Bundle();
                        bundle.putInt("game", 1);
                        bundle.putInt("score", score);
                        /*Si la puntuación obtenida es más alta que la guardada actualmente en el perfil, la llave "high"
                          recibe el valor verdadero, indicando que debe haber una actualización*/
                        if (user.getCurrentUserScoreC() < score) {
                            bundle.putBoolean("high", true);
                        } else {
                            bundle.putBoolean("high", false);
                        }
                        //Se redirige al Fragmento del Game Over con los argumentos importantes del Bundle
                        GameOverFragment fragment = new GameOverFragment();
                        fragment.setArguments(bundle);
                        user.updateScore(score,user.getCurrentUserNumber(),1);
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.flGameStory, fragment).commit();
                    }
                }
            }
        });

        /*Cada uno de los 3 botones disponibles para responder llama al método "checkAnswer" con el
          entero que le corresponde*/
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

        /*Justo al iniciar la Actividad se llama al método generateParagraph del modelo en curso, que varía
          de acuerdo a la dificultad*/
        if (difficulty == 1) {
            tvStory.setText(model1.generateParagraph());
        }
        else {
            tvStory.setText(model2And3.generateNewParagraph(difficulty));
        }
    }

    /*El método "checAnswer" recibe un entero como entrada, para evaluar si la respuesta seleccionada por el usuario
      corresponde o no con la respuesta a la pregunta actual*/
    private void checkAnswer(int option) {
        Button btnOption1 = btnFirstOption, btnOption2 = btnSecondOption, btnOption3 = btnThirdOption;
        /*Dependiendo del valor recibido, se declaran los valores de btnOption1, btnOption2 y btnOption3, con el
          fin de simplificar las referencias más adelante*/
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
        /*Para todos los niveles de dificultad, se compara la respuesta mandada por el input del usuario y se compara con
          los valores de respuesta establecidos por el modelo. En caso de coincidir, el texto del TextView muestra "¡Muy bien!,
          de no ser así, cambia a "¡Intenta de nuevo!"*/
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
        /*Si la respuesta fue correcta, se aumenta el valor de la puntuación, que incrementa dependiendo
          del número actual de vidas y el nivel de dificultad*/
        if (trueOrFalse.equals("¡Muy bien!")) {
            score += lives * difficulty * 100;
            strScore = "Puntuación: " + score;
            tvScoreS.setText(strScore);
            /*Los colores de los botones de opciones se modifican, mostrando las incorrectas en rojo y la correcta en verde*/
            btnOption1.setBackgroundColor(getResources().getColor(R.color.verde));
            btnOption2.setBackgroundColor(getResources().getColor(R.color.r_rojo));
            btnOption3.setBackgroundColor(getResources().getColor(R.color.r_rojo));
            //Los 3 botones se desabilitan para solo permitir pasar a la siguiente pregunta
            btnOption1.setEnabled(false);
            btnOption2.setEnabled(false);
            btnOption3.setEnabled(false);
            btnNextQ.setEnabled(true);
            //El número de pregunta del modelo se aumenta en una unidad
            model1.actualQuestion ++;
            model2And3.actualQuestion ++;
        }
        /*Si la respuesta fue incorrecta, se hace la modificación correspondiente de las vidas*/
        else {
            //El valor de las vidas se disminuye en una unidad
            lives --;
            //Se checa el valor específico para cambiar el recurso de las las ImageViews
            if (lives == 2) {
                ivLife3S.setImageResource(R.drawable.ic_adam_dead);
            }
            if (lives == 1) {
                ivLife2S.setImageResource(R.drawable.ic_adam_dead);
            }
            /*Si el valor de las vidas llega a cero, además de cambiar el recurso, se hace la evaluación
              para mostrar la respuesta correcta*/
            if (lives == 0) {
                ivLife1S.setImageResource(R.drawable.ic_adam_dead);
                String gameOver = "¡Juego Terminado!";
                tvTrueFalse.setText(gameOver);
                btnOption1.setEnabled(false);
                btnOption2.setEnabled(false);
                btnOption3.setEnabled(false);
                btnOption1.setBackgroundColor(getResources().getColor(R.color.r_rojo));
                //Se checa la lógica para cada pregunta y las respuestas correctas existentes en el modelo
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
                //Se habilita el botón de "Siguiente pregunta" para permitir la finalización de la partida
                btnNextQ.setEnabled(true);
            }
            /*Si a pesar de equivocarse el usuario cuenta con vidas disponibles, solo de desabilita el botón de la opción
              marcada, evitando que pueda cometer el mismo error*/
            else {
                btnOption1.setEnabled(false);
            }
        }
    }
}