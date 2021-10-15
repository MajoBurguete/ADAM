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

/*Esta clase representa el Controlador para el nivel de dificultad "difícil" del juego "En Formitas"*/

public class ShapesActivityNVL3 extends AppCompatActivity {

    //Se instancian todas las variables necesarias para el sistema del Controlador
    ShapesModelNVL3 model;
    ImageButton ibPauseShapes3;
    ImageView ivNVL3Figure1;
    ImageView ivNVL3Figure2;
    ImageView ivNVL3Figure3;
    ImageView ivNVL3Figure4;
    ImageView ivNVL3Figure5;
    ImageView ivNVL3Drag1;
    ImageView ivNVL3Drag2;
    ImageView ivNVL3Drag3;
    ImageView ivNVL3Drag4;
    ImageView ivNVL3Drag5;
    ImageView ivLife1SH3;
    ImageView ivLife2SH3;
    ImageView ivLife3SH3;
    TextView tvShapesNVL3;
    TextView tvScoreShapesNVL3;
    Button btnContinueNVL3;
    Button btnCheckNVL3;
    Button btnRestartNVL3;
    String strText;
    String strScore;
    int score = 0;
    int lives = 3;
    User user = new User(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shapes_layout_nvl_3);

        //Se carga la información de los usuarios registrados en la Properties List
        user.loadProfiles();
        /*Se hace la instancia del modelo relacionado"*/
        model = new ShapesModelNVL3();
        //Se vinculan todos los elementos existentes del Layout para poder interactuar con ellos
        ibPauseShapes3 = findViewById(R.id.ibPauseShapes3);
        ivNVL3Figure1 = findViewById(R.id.ivNVL3Figure1);
        ivNVL3Figure2 = findViewById(R.id.ivNVL3Figure2);
        ivNVL3Figure3 = findViewById(R.id.ivNVL3Figure3);
        ivNVL3Figure4 = findViewById(R.id.ivNVL3Figure4);
        ivNVL3Figure5 = findViewById(R.id.ivNVL3Figure5);
        ivNVL3Drag1 = findViewById(R.id.ivNVL3Drag1);
        ivNVL3Drag2 = findViewById(R.id.ivNVL3Drag2);
        ivNVL3Drag3 = findViewById(R.id.ivNVL3Drag3);
        ivNVL3Drag4 = findViewById(R.id.ivNVL3Drag4);
        ivNVL3Drag5 = findViewById(R.id.ivNVL3Drag5);
        ivLife1SH3 = findViewById(R.id.ivLife1SH3);
        ivLife2SH3 = findViewById(R.id.ivLife2SH3);
        ivLife3SH3 = findViewById(R.id.ivLife3SH3);
        tvShapesNVL3 = findViewById(R.id.tvShapesNVL3);
        tvScoreShapesNVL3 = findViewById(R.id.tvScoreShapesNVL3);
        btnContinueNVL3 = findViewById(R.id.btnContinueNVL3);
        btnCheckNVL3 = findViewById(R.id.btnCheckNVL3);
        btnRestartNVL3 = findViewById(R.id.btnRestartNVL3);

        //Se aplican los DragListener y OnTouchListener adecuados para la lógica de los movimientos en pantalla
        ivNVL3Figure1.setOnDragListener(new myDragListener());
        ivNVL3Figure2.setOnDragListener(new myDragListener());
        ivNVL3Figure3.setOnDragListener(new myDragListener());
        ivNVL3Figure4.setOnDragListener(new myDragListener());
        ivNVL3Figure5.setOnDragListener(new myDragListener());
        ivNVL3Drag1.setOnTouchListener(new myClickListener());
        ivNVL3Drag2.setOnTouchListener(new myClickListener());
        ivNVL3Drag3.setOnTouchListener(new myClickListener());
        ivNVL3Drag4.setOnTouchListener(new myClickListener());
        ivNVL3Drag5.setOnTouchListener(new myClickListener());

        /*Se crea la funcinalidad del botón de pausa, desplegando el Frame Layout con los elementos
          correspondientes del Fragmento de Pausa*/
        ibPauseShapes3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.flPauseShapes3, new PauseFragment()).commit();
            }
        });

        //Se crea la funcionalidad del botón "Continuar"
        btnContinueNVL3.setOnClickListener(new View.OnClickListener() {
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
                    ft.replace(R.id.flPauseShapes3, fragment).commit();
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
        btnCheckNVL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //El número de respuestas correctas se inicializa en cero
                int correctAnswers = 0;
                //Se obtienen todos los tags de las figuras, haciendo el cambio a enteros
                int figure1Tag = Integer.parseInt(ivNVL3Figure1.getTag().toString());
                int figure2Tag = Integer.parseInt(ivNVL3Figure2.getTag().toString());
                int figure3Tag = Integer.parseInt(ivNVL3Figure3.getTag().toString());
                int figure4Tag = Integer.parseInt(ivNVL3Figure4.getTag().toString());
                int figure5Tag = Integer.parseInt(ivNVL3Figure5.getTag().toString());
                /*Cada recurso presente en el arreglo "figures" del modelo es comparado con el recurso que referencía
                  el tag de cada figura. De este modo, se puede identificar si la respuesta es correcta o incorrecta*/
                //Si los recursos coinciden, aumenta el contador de respuestas correctas
                if (model.figures.get(0).equals(model.drags.get(figure1Tag))) {
                    correctAnswers ++;
                }
                /*Si los recursos no coinciden, se quita la imagen actual para dar la oportunidad de recolocarla*/
                else {
                    ivNVL3Figure1.animate().alpha(0f).setDuration(500);
                    ivNVL3Figure1.setEnabled(true);
                    if (figure1Tag == 0) {
                        ivNVL3Drag1.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag1.setEnabled(true);
                    }
                    if (figure1Tag == 1) {
                        ivNVL3Drag2.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag2.setEnabled(true);
                    }
                    if (figure1Tag == 2) {
                        ivNVL3Drag3.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag3.setEnabled(true);
                    }
                    if (figure1Tag == 3) {
                        ivNVL3Drag4.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag4.setEnabled(true);
                    }
                    if (figure1Tag == 4) {
                        ivNVL3Drag5.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag5.setEnabled(true);
                    }
                }
                //Este proceso se realiza para cada una de las figuras del juego
                if (model.figures.get(1).equals(model.drags.get(figure2Tag))) {
                    correctAnswers ++;
                }
                else {
                    ivNVL3Figure2.animate().alpha(0f).setDuration(500);
                    ivNVL3Figure2.setEnabled(true);
                    if (figure2Tag == 0) {
                        ivNVL3Drag1.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag1.setEnabled(true);
                    }
                    if (figure2Tag == 1) {
                        ivNVL3Drag2.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag2.setEnabled(true);
                    }
                    if (figure2Tag == 2) {
                        ivNVL3Drag3.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag3.setEnabled(true);
                    }
                    if (figure2Tag == 3) {
                        ivNVL3Drag4.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag4.setEnabled(true);
                    }
                    if (figure2Tag == 4) {
                        ivNVL3Drag5.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag5.setEnabled(true);
                    }
                }
                if (model.figures.get(2).equals(model.drags.get(figure3Tag))) {
                    correctAnswers ++;
                }
                else {
                    ivNVL3Figure3.animate().alpha(0f).setDuration(500);
                    ivNVL3Figure3.setEnabled(true);
                    if (figure3Tag == 0) {
                        ivNVL3Drag1.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag1.setEnabled(true);
                    }
                    if (figure3Tag == 1) {
                        ivNVL3Drag2.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag2.setEnabled(true);
                    }
                    if (figure3Tag == 2) {
                        ivNVL3Drag3.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag3.setEnabled(true);
                    }
                    if (figure3Tag == 3) {
                        ivNVL3Drag4.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag4.setEnabled(true);
                    }
                    if (figure3Tag == 4) {
                        ivNVL3Drag5.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag5.setEnabled(true);
                    }
                }
                if (model.figures.get(3).equals(model.drags.get(figure4Tag))) {
                    correctAnswers ++;
                }
                else {
                    ivNVL3Figure4.animate().alpha(0f).setDuration(500);
                    ivNVL3Figure4.setEnabled(true);
                    if (figure4Tag == 0) {
                        ivNVL3Drag1.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag1.setEnabled(true);
                    }
                    if (figure4Tag == 1) {
                        ivNVL3Drag2.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag2.setEnabled(true);
                    }
                    if (figure4Tag == 2) {
                        ivNVL3Drag3.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag3.setEnabled(true);
                    }
                    if (figure4Tag == 3) {
                        ivNVL3Drag4.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag4.setEnabled(true);
                    }
                    if (figure4Tag == 4) {
                        ivNVL3Drag5.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag5.setEnabled(true);
                    }
                }
                if (model.figures.get(4).equals(model.drags.get(figure5Tag))) {
                    correctAnswers ++;
                }
                else {
                    ivNVL3Figure5.animate().alpha(0f).setDuration(500);
                    ivNVL3Figure5.setEnabled(true);
                    if (figure5Tag == 0) {
                        ivNVL3Drag1.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag1.setEnabled(true);
                    }
                    if (figure5Tag == 1) {
                        ivNVL3Drag2.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag2.setEnabled(true);
                    }
                    if (figure5Tag == 2) {
                        ivNVL3Drag3.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag3.setEnabled(true);
                    }
                    if (figure5Tag == 3) {
                        ivNVL3Drag4.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag4.setEnabled(true);
                    }
                    if (figure5Tag == 4) {
                        ivNVL3Drag5.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag5.setEnabled(true);
                    }
                }
                /*Si después de hacer la evaluación en todas las figuras el contador de respuestas correctas es 5,
                  significa que todas fueron colocadas adecuadamente y se aumenta la puntuación*/
                if (correctAnswers == 5) {
                    strText = "¡Muy bien!";
                    btnContinueNVL3.setEnabled(true);
                    btnRestartNVL3.setEnabled(false);
                    score += lives * 20 * 3;
                    strScore = "Puntaje: " + score;
                    tvScoreShapesNVL3.setText(strScore);
                }
                /*Si la respuesta total fue incorrecta, se hace la modificación correspondiente de las vidas*/
                else {
                    //El valor de las vidas se disminuye en una unidad
                    lives --;
                    //Se checa el valor específico para cambiar el recurso de las las ImageViews
                    if (lives == 2) {
                        ivLife3SH3.setImageResource(R.drawable.ic_adam_dead);
                    }
                    if (lives == 1) {
                        ivLife2SH3.setImageResource(R.drawable.ic_adam_dead);
                    }
                    strText = "¡Intenta de nuevo!";
                    /*Si el valor de las vidas llega a cero, además de cambiar el recurso, se hace la evaluación
                      para mostrar la respuesta correcta*/
                    if (lives == 0) {
                        /*Se modifican los recursos de las figuras para cambiarlos a las respuestas correctas.
                          Solo se deja habilitado el botón de "Continuar" para permitir la finalización de la partida*/
                        ivLife1SH3.setImageResource(R.drawable.ic_adam_dead);
                        strText = "¡Juego terminado!";
                        ivNVL3Figure1.setEnabled(false);
                        ivNVL3Figure2.setEnabled(false);
                        ivNVL3Figure3.setEnabled(false);
                        ivNVL3Figure4.setEnabled(false);
                        ivNVL3Figure5.setEnabled(false);
                        ivNVL3Figure1.setAlpha(0f);
                        ivNVL3Figure2.setAlpha(0f);
                        ivNVL3Figure3.setAlpha(0f);
                        ivNVL3Figure4.setAlpha(0f);
                        ivNVL3Figure5.setAlpha(0f);
                        ivNVL3Figure1.setImageResource(model.figures.get(0));
                        ivNVL3Figure2.setImageResource(model.figures.get(1));
                        ivNVL3Figure3.setImageResource(model.figures.get(2));
                        ivNVL3Figure4.setImageResource(model.figures.get(3));
                        ivNVL3Figure5.setImageResource(model.figures.get(4));
                        ivNVL3Figure1.animate().alpha(1f).setDuration(2000);
                        ivNVL3Figure2.animate().alpha(1f).setDuration(2000);
                        ivNVL3Figure3.animate().alpha(1f).setDuration(2000);
                        ivNVL3Figure4.animate().alpha(1f).setDuration(2000);
                        ivNVL3Figure5.animate().alpha(1f).setDuration(2000);
                        ivNVL3Drag1.setVisibility(View.INVISIBLE);
                        ivNVL3Drag2.setVisibility(View.INVISIBLE);
                        ivNVL3Drag3.setVisibility(View.INVISIBLE);
                        ivNVL3Drag4.setVisibility(View.INVISIBLE);
                        ivNVL3Drag5.setVisibility(View.INVISIBLE);
                        ivNVL3Drag1.setEnabled(false);
                        ivNVL3Drag2.setEnabled(false);
                        ivNVL3Drag3.setEnabled(false);
                        ivNVL3Drag4.setEnabled(false);
                        ivNVL3Drag5.setEnabled(false);
                        btnRestartNVL3.setEnabled(false);
                        btnContinueNVL3.setEnabled(true);
                    }
                }
                //Se vuelve a desabilitar el botón de "Checar"
                btnCheckNVL3.setEnabled(false);
                //El texto del TextView es actualizado
                tvShapesNVL3.setText(strText);
            }
        });

        /*El botón "Reiniciar" permite regresar las figuras a sus valores originales para hacer un reacomodo
          sin la necesidad de tener que "checar" la respuesta y perder una vida*/
        btnRestartNVL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivNVL3Figure1.animate().alpha(0f).setDuration(500);
                ivNVL3Figure2.animate().alpha(0f).setDuration(500);
                ivNVL3Figure3.animate().alpha(0f).setDuration(500);
                ivNVL3Figure4.animate().alpha(0f).setDuration(500);
                ivNVL3Figure5.animate().alpha(0f).setDuration(500);
                ivNVL3Figure1.setEnabled(true);
                ivNVL3Figure2.setEnabled(true);
                ivNVL3Figure3.setEnabled(true);
                ivNVL3Figure4.setEnabled(true);
                ivNVL3Figure5.setEnabled(true);
                ivNVL3Drag1.animate().alpha(1f).setDuration(1000);
                ivNVL3Drag2.animate().alpha(1f).setDuration(1000);
                ivNVL3Drag3.animate().alpha(1f).setDuration(1000);
                ivNVL3Drag4.animate().alpha(1f).setDuration(1000);
                ivNVL3Drag5.animate().alpha(1f).setDuration(1000);
                ivNVL3Drag1.setEnabled(true);
                ivNVL3Drag2.setEnabled(true);
                ivNVL3Drag3.setEnabled(true);
                ivNVL3Drag4.setEnabled(true);
                ivNVL3Drag5.setEnabled(true);
                btnCheckNVL3.setEnabled(false);
            }
        });

        /*Justo al iniciar la Actividad se llama al método startNewRound del modelo y se actualizan los valores del Layout*/
        model.startNewRound();
        setLayoutAttributes();

    }

    //El método setLayoutAttributes revisa los atributos actuales del modelo para actualizar los valores en el Layout
    private void setLayoutAttributes() {
        ivNVL3Figure1.setAlpha(0f);
        ivNVL3Figure2.setAlpha(0f);
        ivNVL3Figure3.setAlpha(0f);
        ivNVL3Figure4.setAlpha(0f);
        ivNVL3Figure5.setAlpha(0f);
        ivNVL3Drag1.setVisibility(View.GONE);
        ivNVL3Drag2.setVisibility(View.GONE);
        ivNVL3Drag3.setVisibility(View.GONE);
        ivNVL3Drag4.setVisibility(View.GONE);
        ivNVL3Drag5.setVisibility(View.GONE);
        btnCheckNVL3.setEnabled(false);
        btnRestartNVL3.setEnabled(false);
        //El string del TextView muestra el mensaje para indicar que el usuario observe las posiciones de las figuras
        strText = "¡Ve las posiciones!";
        tvShapesNVL3.setText(strText);
        ivNVL3Figure1.setImageResource(model.figure1Resource);
        ivNVL3Figure2.setImageResource(model.figure2Resource);
        ivNVL3Figure3.setImageResource(model.figure3Resource);
        ivNVL3Figure4.setImageResource(model.figure4Resource);
        ivNVL3Figure5.setImageResource(model.figure5Resource);
        ivNVL3Figure1.animate().alpha(1f).setDuration(500);
        ivNVL3Figure2.animate().alpha(1f).setDuration(500);
        ivNVL3Figure3.animate().alpha(1f).setDuration(500);
        ivNVL3Figure4.animate().alpha(1f).setDuration(500);
        ivNVL3Figure5.animate().alpha(1f).setDuration(500);
        ivNVL3Drag1.setImageResource(model.drag1Resource);
        ivNVL3Drag2.setImageResource(model.drag2Resource);
        ivNVL3Drag3.setImageResource(model.drag3Resource);
        ivNVL3Drag4.setImageResource(model.drag4Resource);
        ivNVL3Drag5.setImageResource(model.drag5Resource);
    }

    /*El método "hideSequence" procede a ocultar las figuras en los escalones y mostrarlas en la parte inferior de la pantalla.
      Permitiendo que el usuario pueda arrastrarlas hacia las posiciones que recuerda*/
    private void hideSequence() {
        ivNVL3Figure1.setEnabled(true);
        ivNVL3Figure2.setEnabled(true);
        ivNVL3Figure3.setEnabled(true);
        ivNVL3Figure4.setEnabled(true);
        ivNVL3Figure5.setEnabled(true);
        ivNVL3Drag1.setEnabled(true);
        ivNVL3Drag2.setEnabled(true);
        ivNVL3Drag3.setEnabled(true);
        ivNVL3Drag4.setEnabled(true);
        ivNVL3Drag5.setEnabled(true);
        ivNVL3Figure1.animate().alpha(0f).setDuration(500);
        ivNVL3Figure2.animate().alpha(0f).setDuration(500);
        ivNVL3Figure3.animate().alpha(0f).setDuration(500);
        ivNVL3Figure4.animate().alpha(0f).setDuration(500);
        ivNVL3Figure5.animate().alpha(0f).setDuration(500);
        btnRestartNVL3.setEnabled(true);
        btnContinueNVL3.setEnabled(false);
        //El string del TextView muestra el mensaje para indicar que el usuario debe arrastrar las figuras a su lugar
        strText = "¡Ponlas en su lugar!";
        tvShapesNVL3.setText(strText);
        ivNVL3Drag1.setVisibility(View.VISIBLE);
        ivNVL3Drag2.setVisibility(View.VISIBLE);
        ivNVL3Drag3.setVisibility(View.VISIBLE);
        ivNVL3Drag4.setVisibility(View.VISIBLE);
        ivNVL3Drag5.setVisibility(View.VISIBLE);
        ivNVL3Drag1.setAlpha(0f);
        ivNVL3Drag2.setAlpha(0f);
        ivNVL3Drag3.setAlpha(0f);
        ivNVL3Drag4.setAlpha(0f);
        ivNVL3Drag5.setAlpha(0f);
        ivNVL3Drag1.animate().alpha(1f).setDuration(1000);
        ivNVL3Drag2.animate().alpha(1f).setDuration(1000);
        ivNVL3Drag3.animate().alpha(1f).setDuration(1000);
        ivNVL3Drag4.animate().alpha(1f).setDuration(1000);
        ivNVL3Drag5.animate().alpha(1f).setDuration(1000);
        //El estado del modelo es cambiado a false, con el fin de que el siguiente paso sea la creación de una nueva ronda
        model.state = false;
    }

    /*El método "setCheckBtnEnablement" revisa si todas las posiciones han recibido un input de respuesta
      y proceder a habilitar el botón "Checar"*/
    private void setCheckBtnEnablement() {
        if (ivNVL3Figure1.getAlpha() == 1f && ivNVL3Figure2.getAlpha() == 1f && ivNVL3Figure3.getAlpha() == 1f && ivNVL3Figure4.getAlpha() == 1f && ivNVL3Figure5.getAlpha() == 1f) {
            btnCheckNVL3.setEnabled(true);
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
                        ivNVL3Drag1.animate().alpha(0f).setDuration(1000);
                        ivNVL3Drag1.setEnabled(false);
                    }
                    if (dragIndex == 1) {
                        ivNVL3Drag2.animate().alpha(0f).setDuration(1000);
                        ivNVL3Drag2.setEnabled(false);
                    }
                    if (dragIndex == 2) {
                        ivNVL3Drag3.animate().alpha(0f).setDuration(1000);
                        ivNVL3Drag3.setEnabled(false);
                    }
                    if (dragIndex == 3) {
                        ivNVL3Drag4.animate().alpha(0f).setDuration(1000);
                        ivNVL3Drag4.setEnabled(false);
                    }
                    if (dragIndex == 4) {
                        ivNVL3Drag5.animate().alpha(0f).setDuration(1000);
                        ivNVL3Drag5.setEnabled(false);
                    }
                    setCheckBtnEnablement();
                    return true;
                default:
                    break;
            }
            return false;
        }
    }

    @Override
    public void onBackPressed() {
    }

}