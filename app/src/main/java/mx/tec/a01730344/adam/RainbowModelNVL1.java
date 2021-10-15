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
import android.os.CountDownTimer;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RainbowModelNVL1 {

    int score = 0;
    int globalAnswer = -1;
    int globalLives = 3;
    String colorText;
    int colorValue;
    int imageView1;
    int imageView2;
    int imageView3;
    List<String> colors = Arrays.asList("Amarillo", "Azul", "Café", "Morado", "Naranja", "Rojo", "Rosa", "Verde");
    List<Integer> colorValues = Arrays.asList(R.color.r_amarillo, R.color.r_azul_osc, R.color.r_cafe, R.color.r_morado, R.color.r_naranja, R.color.r_rojo, R.color.r_rosa, R.color.r_verde);
    List<Integer> figures = Arrays.asList(R.drawable.ic_boton_amarillo, R.drawable.ic_boton_azul_osc, R.drawable.ic_boton_cafe, R.drawable.ic_boton_morado, R.drawable.ic_boton_naranja, R.drawable.ic_boton_rojo, R.drawable.ic_boton_rosa, R.drawable.ic_boton_verde);

    private int randomIndex(int size) {
        return new Random().nextInt(size);
    }

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

    public void checkAnswer(int answer) {
        if (answer == globalAnswer) {
            score += 10 * globalLives;
        }
        else {
            globalLives --;
        }
        globalAnswer = -1;
    }
}
