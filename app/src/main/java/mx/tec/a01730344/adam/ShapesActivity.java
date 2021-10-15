package mx.tec.a01730344.adam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*Esta clase representa el Controlador para el primer nivel del juego "En Formitas"*/

public class ShapesActivity extends AppCompatActivity {

    //Se instancian todas las variables necesarias para el sistema del Controlador
    ShapesModelNVL1 model;
    ImageButton ibPauseShapes1;
    ImageView ivNVL1Figure1;
    ImageView ivNVL1Figure2;
    ImageView ivNVL1Figure3;
    ImageView ivNVL1Drag1;
    ImageView ivNVL1Drag2;
    ImageView ivNVL1Drag3;
    ImageView ivLife1SH;
    ImageView ivLife2SH;
    ImageView ivLife3SH;
    TextView tvShapesNVL1;
    TextView tvScoreShapesNVL1;
    Button btnContinueNVL1;
    Button btnCheckNVL1;
    Button btnRestartNVL1;
    String strText;
    String strScore;
    int score = 0;
    int lives = 3;
    User user = new User(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shapes_layout);

        //Se carga la información de los usuarios registrados en la Properties List
        user.loadProfiles();
        /*Se hace la instancia del modelo relacionado"*/
        model = new ShapesModelNVL1();
        //Se vinculan todos los elementos existentes del Layout para poder interactuar con ellos
        ibPauseShapes1 = findViewById(R.id.ibPauseShapes1);
        ivNVL1Figure1 = findViewById(R.id.ivNVL1Figure1);
        ivNVL1Figure2 = findViewById(R.id.ivNVL1Figure2);
        ivNVL1Figure3 = findViewById(R.id.ivNVL1Figure3);
        ivNVL1Drag1 = findViewById(R.id.ivNVL1Drag1);
        ivNVL1Drag2 = findViewById(R.id.ivNVL1Drag2);
        ivNVL1Drag3 = findViewById(R.id.ivNVL1Drag3);
        ivLife1SH = findViewById(R.id.ivLife1SH);
        ivLife2SH = findViewById(R.id.ivLife2SH);
        ivLife3SH = findViewById(R.id.ivLife3SH);
        tvShapesNVL1 = findViewById(R.id.tvShapesNVL1);
        tvScoreShapesNVL1 = findViewById(R.id.tvScoreShapesNVL1);
        btnContinueNVL1 = findViewById(R.id.btnContinueNVL1);
        btnCheckNVL1 = findViewById(R.id.btnCheckNVL1);
        btnRestartNVL1 = findViewById(R.id.btnRestartNVL1);

        //Se aplican los DragListener y OnTouchListener adecuados para la lógica de los movimientos en pantalla
        ivNVL1Figure1.setOnDragListener(new myDragListener());
        ivNVL1Figure2.setOnDragListener(new myDragListener());
        ivNVL1Figure3.setOnDragListener(new myDragListener());
        ivNVL1Drag1.setOnTouchListener(new myClickListener());
        ivNVL1Drag2.setOnTouchListener(new myClickListener());
        ivNVL1Drag3.setOnTouchListener(new myClickListener());

        /*Se crea la funcinalidad del botón de pausa, desplegando el Frame Layout con los elementos
          correspondientes del Fragmento de Pausa*/
        ibPauseShapes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.flPauseShapes1, new PauseFragment()).commit();
            }
        });

        //Se crea la funcionalidad del botón "Continuar"
        btnContinueNVL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*En caso de haberse agotado las vidas, se obtienen los parámetros necesarios para poder hacer
                  la evaluación de la Puntuación*/
                if (lives == 0) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("game", 2);
                    bundle.putInt("score", score);
                    /*Si la puntuación obtenida es más alta que la guardada actualmente en el perfil, la llave "high"
                      recibe el valor verdadero, indicando que debe haber una actualización*/
                    if (user.getCurrentUserScoreF() < score) {
                        bundle.putBoolean("high", true);
                    } else {
                        bundle.putBoolean("high", false);
                    }
                    //Se redirige al Fragmento del Game Over con los argumentos importantes del Bundle
                    GameOverFragment fragment = new GameOverFragment();
                    fragment.setArguments(bundle);
                    user.updateScore(score,user.getCurrentUserNumber(),2);
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.flPauseShapes1, fragment).commit();
                }
                //Si no, si el estado del modelo es verdadero, se llama al método "hideSequence"
                else if (model.state) {
                    hideSequence();
                }
                //Si no, se crea una nueva ronda y se cambian los atributos del Layout
                else {
                    model.startNewRound();
                    setLayoutAttributes();
                }
            }
        });

        //El botón "Chechar" evalúa si el input de respuesta es correcto o no y hace los cambios oportunos
        btnCheckNVL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //El número de respuestas correctas se inicializa en cero
                int correctAnswers = 0;
                //Se obtienen todos los tags de las figuras, haciendo el cambio a enteros
                int figure1Tag = Integer.parseInt(ivNVL1Figure1.getTag().toString());
                int figure2Tag = Integer.parseInt(ivNVL1Figure2.getTag().toString());
                int figure3Tag = Integer.parseInt(ivNVL1Figure3.getTag().toString());
                /*Cada recurso presente en el arreglo "figures" del modelo es comparado con el recurso que referencía
                  el tag de cada figura. De este modo, se puede identificar si la respuesta es correcta o incorrecta*/
                //Si los recursos coinciden, aumenta el contador de respuestas correctas
                if (model.figures.get(0).equals(model.drags.get(figure1Tag))) {
                    correctAnswers ++;
                }
                /*Si los recursos no coinciden, se quita la imagen actual para dar la oportunidad de recolocarla*/
                else {
                    ivNVL1Figure1.animate().alpha(0f).setDuration(500);
                    ivNVL1Figure1.setEnabled(true);
                    if (figure1Tag == 0) {
                        ivNVL1Drag1.animate().alpha(1f).setDuration(1000);
                        ivNVL1Drag1.setEnabled(true);
                    }
                    if (figure1Tag == 1) {
                        ivNVL1Drag2.animate().alpha(1f).setDuration(1000);
                        ivNVL1Drag2.setEnabled(true);
                    }
                    if (figure1Tag == 2) {
                        ivNVL1Drag3.animate().alpha(1f).setDuration(1000);
                        ivNVL1Drag3.setEnabled(true);
                    }
                }
                //Este proceso se realiza para cada una de las figuras del juego
                if (model.figures.get(1).equals(model.drags.get(figure2Tag))) {
                    correctAnswers ++;
                }
                else {
                    ivNVL1Figure2.animate().alpha(0f).setDuration(500);
                    ivNVL1Figure2.setEnabled(true);
                    if (figure2Tag == 0) {
                        ivNVL1Drag1.animate().alpha(1f).setDuration(1000);
                        ivNVL1Drag1.setEnabled(true);
                    }
                    if (figure2Tag == 1) {
                        ivNVL1Drag2.animate().alpha(1f).setDuration(1000);
                        ivNVL1Drag2.setEnabled(true);
                    }
                    if (figure2Tag == 2) {
                        ivNVL1Drag3.animate().alpha(1f).setDuration(1000);
                        ivNVL1Drag3.setEnabled(true);
                    }
                }
                if (model.figures.get(2).equals(model.drags.get(figure3Tag))) {
                    correctAnswers ++;
                }
                else {
                    ivNVL1Figure3.animate().alpha(0f).setDuration(500);
                    ivNVL1Figure3.setEnabled(true);
                    if (figure3Tag == 0) {
                        ivNVL1Drag1.animate().alpha(1f).setDuration(1000);
                        ivNVL1Drag1.setEnabled(true);
                    }
                    if (figure3Tag == 1) {
                        ivNVL1Drag2.animate().alpha(1f).setDuration(1000);
                        ivNVL1Drag2.setEnabled(true);
                    }
                    if (figure3Tag == 2) {
                        ivNVL1Drag3.animate().alpha(1f).setDuration(1000);
                        ivNVL1Drag3.setEnabled(true);
                    }
                }
                /*Si después de hacer la evaluación en todas las figuras el contador de respuestas correctas es 3,
                  significa que todas fueron colocadas adecuadamente y se aumenta la puntuación*/
                if (correctAnswers == 3) {
                    strText = "¡Muy bien!";
                    btnContinueNVL1.setEnabled(true);
                    btnRestartNVL1.setEnabled(false);
                    //La puntuación es actualizada
                    score += lives * 10;
                    strScore = "Puntuación: " + String.valueOf(score);
                    //El texto del TextView refleja la nueva puntuación
                    tvScoreShapesNVL1.setText(strScore);
                }
                /*Si la respuesta total fue incorrecta, se hace la modificación correspondiente de las vidas*/
                else {
                    //El valor de las vidas se disminuye en una unidad
                    lives --;
                    //Se checa el valor específico para cambiar el recurso de las las ImageViews
                    if (lives == 2) {
                        ivLife3SH.setImageResource(R.drawable.ic_adam_dead);
                    }
                    if (lives == 1) {
                        ivLife2SH.setImageResource(R.drawable.ic_adam_dead);
                    }
                    strText = "¡Intenta de nuevo!";
                    /*Si el valor de las vidas llega a cero, además de cambiar el recurso, se hace la evaluación
                      para mostrar la respuesta correcta*/
                    if (lives == 0) {
                        /*Se modifican los recursos de las figuras para cambiarlos a las respuestas correctas.
                          Solo se deja habilitado el botón de "Continuar" para permitir la finalización de la partida*/
                        ivLife1SH.setImageResource(R.drawable.ic_adam_dead);
                        strText = "¡Juego terminado!";
                        ivNVL1Figure1.setEnabled(false);
                        ivNVL1Figure2.setEnabled(false);
                        ivNVL1Figure3.setEnabled(false);
                        ivNVL1Figure1.setAlpha(0f);
                        ivNVL1Figure2.setAlpha(0f);
                        ivNVL1Figure3.setAlpha(0f);
                        ivNVL1Figure1.setImageResource(model.figures.get(0));
                        ivNVL1Figure2.setImageResource(model.figures.get(1));
                        ivNVL1Figure3.setImageResource(model.figures.get(2));
                        ivNVL1Figure1.animate().alpha(1f).setDuration(2000);
                        ivNVL1Figure2.animate().alpha(1f).setDuration(2000);
                        ivNVL1Figure3.animate().alpha(1f).setDuration(2000);
                        ivNVL1Drag1.setVisibility(View.INVISIBLE);
                        ivNVL1Drag2.setVisibility(View.INVISIBLE);
                        ivNVL1Drag3.setVisibility(View.INVISIBLE);
                        ivNVL1Drag1.setEnabled(false);
                        ivNVL1Drag2.setEnabled(false);
                        ivNVL1Drag3.setEnabled(false);
                        btnRestartNVL1.setEnabled(false);
                        btnContinueNVL1.setEnabled(true);
                    }
                }
                //Se vuelve a desabilitar el botón de "Checar"
                btnCheckNVL1.setEnabled(false);
                //El texto del TextView es actualizado
                tvShapesNVL1.setText(strText);
            }
        });

        /*El botón "Reiniciar" permite regresar las figuras a sus valores originales para hacer un reacomodo
          sin la necesidad de tener que "checar" la respuesta y perder una vida*/
        btnRestartNVL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivNVL1Figure1.animate().alpha(0f).setDuration(500);
                ivNVL1Figure2.animate().alpha(0f).setDuration(500);
                ivNVL1Figure3.animate().alpha(0f).setDuration(500);
                ivNVL1Figure1.setEnabled(true);
                ivNVL1Figure2.setEnabled(true);
                ivNVL1Figure3.setEnabled(true);
                ivNVL1Drag1.animate().alpha(1f).setDuration(1000);
                ivNVL1Drag2.animate().alpha(1f).setDuration(1000);
                ivNVL1Drag3.animate().alpha(1f).setDuration(1000);
                ivNVL1Drag1.setEnabled(true);
                ivNVL1Drag2.setEnabled(true);
                ivNVL1Drag3.setEnabled(true);
                btnCheckNVL1.setEnabled(false);
            }
        });

        /*Justo al iniciar la Actividad se llama al método startNewRound del modelo y se actualizan los valores del Layout*/
        model.startNewRound();
        setLayoutAttributes();

    }

    //El método setLayoutAttributes revisa los atributos actuales del modelo para actualizar los valores en el Layout
    private void setLayoutAttributes() {
        ivNVL1Figure1.setAlpha(0f);
        ivNVL1Figure2.setAlpha(0f);
        ivNVL1Figure3.setAlpha(0f);
        ivNVL1Drag1.setVisibility(View.GONE);
        ivNVL1Drag2.setVisibility(View.GONE);
        ivNVL1Drag3.setVisibility(View.GONE);
        btnCheckNVL1.setEnabled(false);
        btnRestartNVL1.setEnabled(false);
        //El string del TextView muestra el mensaje para indicar que el usuario observe las posiciones de las figuras
        strText = "¡Ve las posiciones!";
        tvShapesNVL1.setText(strText);
        ivNVL1Figure1.setImageResource(model.figure1Resource);
        ivNVL1Figure2.setImageResource(model.figure2Resource);
        ivNVL1Figure3.setImageResource(model.figure3Resource);
        ivNVL1Figure1.animate().alpha(1f).setDuration(500);
        ivNVL1Figure2.animate().alpha(1f).setDuration(500);
        ivNVL1Figure3.animate().alpha(1f).setDuration(500);
        ivNVL1Drag1.setImageResource(model.drag1Resource);
        ivNVL1Drag2.setImageResource(model.drag2Resource);
        ivNVL1Drag3.setImageResource(model.drag3Resource);
    }

    /*El método "hideSequence" procede a ocultar las figuras en los escalones y mostrarlas en la parte inferior de la pantalla.
      Permitiendo que el usuario pueda arrastrarlas hacia las posiciones que recuerda*/
    private void hideSequence() {
        ivNVL1Figure1.setEnabled(true);
        ivNVL1Figure2.setEnabled(true);
        ivNVL1Figure3.setEnabled(true);
        ivNVL1Drag1.setEnabled(true);
        ivNVL1Drag2.setEnabled(true);
        ivNVL1Drag3.setEnabled(true);
        ivNVL1Figure1.animate().alpha(0f).setDuration(500);
        ivNVL1Figure2.animate().alpha(0f).setDuration(500);
        ivNVL1Figure3.animate().alpha(0f).setDuration(500);
        btnRestartNVL1.setEnabled(true);
        btnContinueNVL1.setEnabled(false);
        //El string del TextView muestra el mensaje para indicar que el usuario debe arrastrar las figuras a su lugar
        strText = "¡Ponlas en su lugar!";
        tvShapesNVL1.setText(strText);
        ivNVL1Drag1.setVisibility(View.VISIBLE);
        ivNVL1Drag2.setVisibility(View.VISIBLE);
        ivNVL1Drag3.setVisibility(View.VISIBLE);
        ivNVL1Drag1.setAlpha(0f);
        ivNVL1Drag2.setAlpha(0f);
        ivNVL1Drag3.setAlpha(0f);
        ivNVL1Drag1.animate().alpha(1f).setDuration(1000);
        ivNVL1Drag2.animate().alpha(1f).setDuration(1000);
        ivNVL1Drag3.animate().alpha(1f).setDuration(1000);
        //El estado del modelo es cambiado a false, con el fin de que el siguiente paso sea la creación de una nueva ronda
        model.state = false;
    }

    /*El método "setCheckBtnEnablement" revisa si todas las posiciones han recibido un input de respuesta
      y proceder a habilitar el botón "Checar"*/
    private void setCheckBtnEnablement() {
        if (ivNVL1Figure1.getAlpha() == 1f && ivNVL1Figure2.getAlpha() == 1f && ivNVL1Figure3.getAlpha() == 1f) {
            btnCheckNVL1.setEnabled(true);
        }
    }

    //La clase "myClickListener" crea la lógica necesaria para detectar movimientos en pantalla
    private static class myClickListener implements View.OnTouchListener {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent motionEvent) {
            /*Si la acción de click se realiza en una figura con transparencia 1f, se crea el ClipData para
              almacenar su tag y generar la sombra de arrastre*/
            if(v.getAlpha() == 1f) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                ClipData dragData = new ClipData((CharSequence) v.getTag(), new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(dragData, shadowBuilder, v, 0);
                return true;
            }
            else {
                return false;
            }
        }
    }

    //La clase "myDragListener" crea la lógica necesaria para detectar el arrastre de elementos hacia ciertos elementos en pantalla
    private class myDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent dragEvent) {
            //El elemento que detecta el arrastre se representa como la "receiverView"
            ImageView receiverView = (ImageView) v;
            //La acción del evento se identifica en "action"
            final int action = dragEvent.getAction();
            switch(action) {
                case DragEvent.ACTION_DRAG_STARTED:
                case DragEvent.ACTION_DRAG_ENTERED:
                    return true;
                    /*Si la acción reconocida es un "soltar", se cambia el recurso de la imagen en dicha posición*/
                case DragEvent.ACTION_DROP:
                    //Se guarda el tag de la imagen que está siendo arrastrada como entero
                    int dragIndex = Integer.parseInt(String.valueOf(dragEvent.getClipDescription().getLabel()));
                    /*La imagen que detecta el "soltar" en ella cambia su recurso de acuerdo a la posición del tag en el
                      arreglo "drags" del modelo y también actualiza su tag a ese mismo valor*/
                    receiverView.setImageResource(model.drags.get(dragIndex));
                    receiverView.setAlpha(1f);
                    receiverView.setEnabled(false);
                    receiverView.setTag(dragIndex);
                    if (dragIndex == 0) {
                        ivNVL1Drag1.animate().alpha(0f).setDuration(1000);
                        ivNVL1Drag1.setEnabled(false);
                    }
                    if (dragIndex == 1) {
                        ivNVL1Drag2.animate().alpha(0f).setDuration(1000);
                        ivNVL1Drag2.setEnabled(false);
                    }
                    if (dragIndex == 2) {
                        ivNVL1Drag3.animate().alpha(0f).setDuration(1000);
                        ivNVL1Drag3.setEnabled(false);
                    }
                    setCheckBtnEnablement();
                    return true;
                default:
                    break;
            }
            return false;
        }
    }

}