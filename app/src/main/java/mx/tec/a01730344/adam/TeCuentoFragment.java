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
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

//Fragmento creado para el carrusel de juegos en la pantalla de HomeActivity para que el usuario pueda escoger el juego de arcoíris.
public class TeCuentoFragment extends Fragment {

    Button btnJugarTeCuento;

    //Funciones necesarias para que el fragmento se ejecute de manera correcta.
    public TeCuentoFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_te_cuento, container, false);
    }

    //Función añadida para realizar acciones al crear el fragmento.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Declaración de botón y el elemento en el layout al que corresponde.
        btnJugarTeCuento = view.findViewById(R.id.btnJugarTeCuento);

        //Función para detectar la interacción del usuario con el botón y asi poder mandarlo al juego.
        btnJugarTeCuento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toSelectDifficulty = new Intent(getActivity(), InstructionsActivity.class);
                //Elemento que se manda a la siguiente pantalla para poder identificar a qué juego se fue y desplegar las instrucciones apropiadas
                toSelectDifficulty.putExtra("game", 2);
                startActivity(toSelectDifficulty);
            }
        });
    }
}