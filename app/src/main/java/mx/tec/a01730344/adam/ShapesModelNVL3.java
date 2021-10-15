/* Integración de seguridad informática en redes y sistemas de software (TC2007B.1)
   ADAM: Aplicación para el Desarrollo de Atención y Memoria
   Fecha: 17/10/2021
   Creado por: María José Burguete Euán
               Aarón Cortés García
               Marco Flamenco Andrade
               Daniela Hernández y Hernández
*/

package mx.tec.a01730344.adam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*Esta clase representa el Modelo para el nivel de dificultad "fácil" del juego "En Formitas"*/

public class ShapesModelNVL3 {

    //Se instancian todas las variables necesarias para el sistema del Modelo
    int figure1Resource;
    int figure2Resource;
    int figure3Resource;
    int figure4Resource;
    int figure5Resource;
    int drag1Resource;
    int drag2Resource;
    int drag3Resource;
    int drag4Resource;
    int drag5Resource;
    //El estado del modelo es inicializado con un valor false
    boolean state = false;
    //"Figures" almacena los drawables disponibles para las figuras del juego
    List<Integer> figures = Arrays.asList(R.drawable.ic_circulito_amarillo, R.drawable.ic_circulito_celeste, R.drawable.ic_circulito_naranja, R.drawable.ic_circulito_rosa, R.drawable.ic_circulito_verde, R.drawable.ic_cuadrado_amarillo, R.drawable.ic_cuadrado_celeste, R.drawable.ic_cuadrado_naranja, R.drawable.ic_cuadrado_rosa, R.drawable.ic_cuadrado_verde, R.drawable.ic_estrella_amarilla, R.drawable.ic_estrella_celeste, R.drawable.ic_estrella_naranja, R.drawable.ic_estrella_rosa, R.drawable.ic_estrella_verde, R.drawable.ic_hex_amarillo, R.drawable.ic_hex_celeste, R.drawable.ic_hex_naranja, R.drawable.ic_hex_rosa, R.drawable.ic_hex_verde, R.drawable.ic_triangulo_amarillo, R.drawable.ic_triangulo_celeste, R.drawable.ic_triangulo_naranja, R.drawable.ic_triangulo_rosa, R.drawable.ic_triangulo_verde);
    //"Drags" se inicializa como un arreglo vacío, que posteriormente recibirá elementos al crear una nueva ronda
    List<Integer> drags = new ArrayList<>();

    //El método "startNewRound" asigna valores aleatorios a las figuras y a los elementos de arrastre
    public void startNewRound() {
        //Los elementos en el arreglo "figures" se revuelven a través del método shuffle
        Collections.shuffle(figures);
        //Los 5 enteros que hacen referencia a los recursos de las figuras reciben su valor de los elementos en el arreglo "figures"
        figure1Resource = figures.get(0);
        figure2Resource = figures.get(1);
        figure3Resource = figures.get(2);
        figure4Resource = figures.get(3);
        figure5Resource = figures.get(4);
        //Se remueven todos los elementos actuales en el arreglo "drags", evitando arrastrar recursos de rondas pasadas
        drags.clear();
        //Se añaden los primeros 5 valores del arreglo "figures" en "drags"
        drags.addAll(figures.subList(0, 5));
        //Los elementos en el arreglo "drags" se revuelven a través del método shuffle
        Collections.shuffle(drags);
        //Los 5 enteros que hacen referencia a los recursos de los elementos de arrastre reciben su valor de los elementos en el arreglo "drags"
        drag1Resource = drags.get(0);
        drag2Resource = drags.get(1);
        drag3Resource = drags.get(2);
        drag4Resource = drags.get(3);
        drag5Resource = drags.get(4);
        //El estado es cambiado a un valor true
        state = true;
    }

}
