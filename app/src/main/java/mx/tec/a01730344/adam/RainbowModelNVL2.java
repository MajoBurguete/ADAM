/* Integración de seguridad informática en redes y sistemas de software (TC2007B.1)
   ADAM: Aplicación para el Desarrollo de Atención y Memoria
   Fecha: 17/10/2021
   Creado por: María José Burguete Euán
               Aarón Cortés García
               Marco Flamenco Andrade
               Daniela Hernández y Hernández
*/

package mx.tec.a01730344.adam;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*Esta clase representa el Modelo para el nivel de dificultad "intermedio" del juego "Arcoiris"*/

public class RainbowModelNVL2 {

    //Se instancian todas las variables necesarias para el sistema del Modelo
    int score = 0;
    int globalAnswer = -1;
    int globalLives = 3;
    String colorText;
    int colorValue;
    int imageView1;
    int imageView2;
    int imageView3;
    int imageView4;
    //"Colors" almacena los nombres de los posibles colores
    List<String> colors = Arrays.asList("Amarillo", "Azul", "Café", "Morado", "Naranja", "Rojo", "Rosa", "Verde", "Celeste", "Beige", "Fucsia", "Menta");
    //"ColorValues" almacena todos los valores enteros existentes en el xml de colores
    List<Integer> colorValues = Arrays.asList(R.color.r_amarillo, R.color.r_azul_osc, R.color.r_cafe, R.color.r_morado, R.color.r_naranja, R.color.r_rojo, R.color.r_rosa, R.color.r_verde, R.color.r_celeste, R.color.r_beige, R.color.r_fucsia, R.color.r_menta);
    //"Figures" almacena los recursos drawables
    List<Integer> figures = Arrays.asList(R.drawable.ic_boton_amarillo, R.drawable.ic_boton_azul_osc, R.drawable.ic_boton_cafe, R.drawable.ic_boton_morado, R.drawable.ic_boton_naranja, R.drawable.ic_boton_rojo, R.drawable.ic_boton_rosa, R.drawable.ic_boton_verde, R.drawable.ic_boton_celeste,R.drawable.ic_boton_beige, R.drawable.ic_boton_fucsia, R.drawable.ic_boton_menta);

    //El método "randomIndex" recibe un entero y regresa un aleatorio desde cero hasta ese valor
    private int randomIndex() {
        return new Random().nextInt(12);
    }

    /*El método "gameRound" toma valores aleatorios para asignarlos a las variables de los recursos drawables,
      el nombre del color como la respuesta correcta y el color para la font*/
    public void gameRound() {
        List<Integer> options = Arrays.asList(0, 1, 2, 3);
        Collections.shuffle(options);
        int randomAnswerA = options.get(0);
        globalAnswer = randomAnswerA;
        int randomAnswerB = options.get(1);
        int randomAnswerC = options.get(2);
        int randomAnswerD = options.get(3);
        int randomColor = randomIndex();
        colorText = colors.get(randomColor);
        int randomValue = randomIndex();;
        while (randomValue == randomColor) {
            randomValue = randomIndex();;
        }
        colorValue = colorValues.get(randomValue);
        int randomOption1 = randomIndex();;
        while (randomOption1 == randomColor || randomOption1 == randomValue) {
            randomOption1 = randomIndex();;
        }
        int randomOption2 = randomIndex();;
        while (randomOption2 == randomColor || randomOption2 == randomValue || randomOption2 == randomOption1) {
            randomOption2 = randomIndex();;
        }
        colorChange(randomAnswerA, randomColor);
        colorChange(randomAnswerB, randomValue);
        colorChange(randomAnswerC, randomOption1);
        colorChange(randomAnswerD, randomOption2);
    }

    /*El método "colorChange" asigna el valor entero del color a la variable "answer" pasada como parámetro*/
    private void colorChange(int answer, int color) {
        if (answer == 0) {
            imageView1 = figures.get(color);
        }
        if (answer == 1) {
            imageView2 = figures.get(color);
        }
        if (answer == 2) {
            imageView3 = figures.get(color);
        }
        if (answer == 3) {
            imageView4 = figures.get(color);
        }
    }

    /*El método "checkAnswer" recibe un valor entero y lo compara con la respuesta global*/
    public void checkAnswer(int answer) {
        //Si la respuesta coincide con la global, se aumenta el valor de la puntuación
        if (answer == globalAnswer) {
            score += 20 * globalLives;
        }
        //En caso contrario, el número de vidas se reduce en una unidad
        else {
            globalLives --;
        }
        globalAnswer = -1;
    }

}
