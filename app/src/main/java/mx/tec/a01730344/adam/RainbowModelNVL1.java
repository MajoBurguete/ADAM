package mx.tec.a01730344.adam;

import android.content.Intent;
import android.os.CountDownTimer;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*Esta clase representa el Modelo para el nivel de dificultad "fácil" del juego "Arcoiris"*/

public class RainbowModelNVL1 {

    //Se instancian todas las variables necesarias para el sistema del Modelo
    int score = 0;
    int globalAnswer = -1;
    int globalLives = 3;
    String colorText;
    int colorValue;
    int imageView1;
    int imageView2;
    int imageView3;
    //"Colors" almacena los nombres de los posibles colores
    List<String> colors = Arrays.asList("Amarillo", "Azul", "Café", "Morado", "Naranja", "Rojo", "Rosa", "Verde");
    //"ColorValues" almacena todos los valores enteros existentes en el xml de colores
    List<Integer> colorValues = Arrays.asList(R.color.r_amarillo, R.color.r_azul_osc, R.color.r_cafe, R.color.r_morado, R.color.r_naranja, R.color.r_rojo, R.color.r_rosa, R.color.r_verde);
    //"Figures" almacena los recursos drawables
    List<Integer> figures = Arrays.asList(R.drawable.ic_boton_amarillo, R.drawable.ic_boton_azul_osc, R.drawable.ic_boton_cafe, R.drawable.ic_boton_morado, R.drawable.ic_boton_naranja, R.drawable.ic_boton_rojo, R.drawable.ic_boton_rosa, R.drawable.ic_boton_verde);

    //El método "randomIndex" recibe un entero y regresa un aleatorio desde cero hasta ese valor
    private int randomIndex(int size) {
        return new Random().nextInt(size);
    }

    /*El método "gameRound" toma valores aleatorios para asignarlos a las variables de los recursos drawables,
      el nombre del color como la respuesta correcta y el color para la font*/
    public void gameRound() {
        int randomColor = randomIndex(8);
        colorText = colors.get(randomColor);
        int randomValue = randomIndex(8);
        while (randomValue == randomColor) {
            randomValue = randomIndex(8);
        }
        colorValue = colorValues.get(randomValue);
        int randomAnswerA = randomIndex(3);
        globalAnswer = randomAnswerA;
        if (randomAnswerA == 0) {
            imageView1 = figures.get(randomColor);
        }
        if (randomAnswerA == 1) {
            imageView2 = figures.get(randomColor);
        }
        if (randomAnswerA == 2) {
            imageView3 = figures.get(randomColor);
        }
        int randomAnswerB = randomIndex(3);
        while (randomAnswerB == randomAnswerA) {
            randomAnswerB = randomIndex(3);
        }
        if (randomAnswerB == 0) {
            imageView1 = figures.get(randomValue);
        }
        if (randomAnswerB == 1) {
            imageView2 = figures.get(randomValue);
        }
        if (randomAnswerB == 2) {
            imageView3 = figures.get(randomValue);
        }
        int randomAnswerC = randomAnswerA + randomAnswerB;
        int randomOption = randomIndex(3);
        while (randomOption == randomValue || randomOption == randomColor) {
            randomOption = randomIndex(8);
        }
        if (randomAnswerC == 1) {
            imageView3 = figures.get(randomOption);
        }
        if (randomAnswerC == 2) {
            imageView2 = figures.get(randomOption);
        }
        if (randomAnswerC == 3) {
            imageView1 = figures.get(randomOption);
        }
    }

    /*El método "checkAnswer" recibe un valor entero y lo compara con la respuesta global*/
    public void checkAnswer(int answer) {
        //Si la respuesta coincide con la global, se aumenta el valor de la puntuación
        if (answer == globalAnswer) {
            score += 10 * globalLives;
        }
        //En caso contrario, el número de vidas se reduce en una unidad
        else {
            globalLives --;
        }
        globalAnswer = -1;
    }
}
