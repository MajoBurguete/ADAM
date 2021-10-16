/* Integración de seguridad informática en redes y sistemas de software (TC2007B.1)
   ADAM: Aplicación para el Desarrollo de Atención y Memoria
   Fecha: 17/10/2021
   Creado por: María José Burguete Euán
               Aarón Cortés García
               Marco Flamenco Andrade
               Daniela Hernández y Hernández
*/

package mx.tec.a01730344.adam.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import mx.tec.a01730344.adam.Activities.HomeActivity;
import mx.tec.a01730344.adam.R;

//Fragmento creado para pausar el juego y desplegar los botones de salir del juego o continuar con la partida
public class PauseFragment extends Fragment {

    //Declaración de todos los componentes necesarios para el funcionamiento del fragmento.
    Button btnSeguirJugando;
    Button btnSalirDelJuego;
    FrameLayout flPause;

    //Funciones necesarias para que el fragmento se ejecute de manera correcta.
    public PauseFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pause, container, false);
    }

    //Función añadida para realizar acciones al crear el fragmento.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Enlace entre las variables declaradas y los componentes del layout
        btnSeguirJugando = view.findViewById(R.id.btnSeguirJugando);
        btnSalirDelJuego = view.findViewById(R.id.btnSalirDelJuego);
        flPause = view.findViewById(R.id.flPauseFragment);

        //Función para detectar la interacción del usuario con el botón para remover el fragmento y seguir con su partida
        btnSeguirJugando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(PauseFragment.this).commit();
                flPause.setClickable(false);
            }
        });

        //Función para detectar la interaccion del usuario con el botón para terminar la partida y regresarlo a la pantalla de HomeActivity.
        btnSalirDelJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHome = new Intent(getActivity(), HomeActivity.class);
                startActivity(toHome);
            }
        });

    }
}