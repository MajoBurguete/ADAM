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

public class ShapesModelNVL1 {

    int figure1Resource;
    int figure2Resource;
    int figure3Resource;
    int drag1Resource;
    int drag2Resource;
    int drag3Resource;
    boolean state = false;
    List<Integer> figures = Arrays.asList(R.drawable.ic_circulito_amarillo, R.drawable.ic_circulito_celeste, R.drawable.ic_circulito_naranja, R.drawable.ic_circulito_rosa, R.drawable.ic_circulito_verde, R.drawable.ic_cuadrado_amarillo, R.drawable.ic_cuadrado_celeste, R.drawable.ic_cuadrado_naranja, R.drawable.ic_cuadrado_rosa, R.drawable.ic_cuadrado_verde, R.drawable.ic_estrella_amarilla, R.drawable.ic_estrella_celeste, R.drawable.ic_estrella_naranja, R.drawable.ic_estrella_rosa, R.drawable.ic_estrella_verde, R.drawable.ic_hex_amarillo, R.drawable.ic_hex_celeste, R.drawable.ic_hex_naranja, R.drawable.ic_hex_rosa, R.drawable.ic_hex_verde, R.drawable.ic_triangulo_amarillo, R.drawable.ic_triangulo_celeste, R.drawable.ic_triangulo_naranja, R.drawable.ic_triangulo_rosa, R.drawable.ic_triangulo_verde);
    List<Integer> drags = new ArrayList<>();

    public void startNewRound() {
        Collections.shuffle(figures);
        figure1Resource = figures.get(0);
        figure2Resource = figures.get(1);
        figure3Resource = figures.get(2);
        drags.clear();
        drags.addAll(figures.subList(0, 3));
        Collections.shuffle(drags);
        drag1Resource = drags.get(0);
        drag2Resource = drags.get(1);
        drag3Resource = drags.get(2);
        state = true;
    }

}
