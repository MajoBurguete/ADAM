package mx.tec.a01730344.adam;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

//fragmento creado para pausar el juego y desplegar los botones de salir del juego o continuar con la partida
public class PauseFragment extends Fragment {

    //declaracion de todos los componentes necesarios para el funcionamiento del fragmento.
    Button btnSeguirJugando;
    Button btnSalirDelJuego;
    FrameLayout flPause;

    //funcion necesaria para que el fragmento se ejecute de manera correcta.
    public PauseFragment() {
        // Required empty public constructor
    }

    //funcion necesaria para que el fragmento se ejecute de manera correcta.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pause, container, false);
    }

    //funcion añadida para realizar acciones al crear el fragmento.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //enlace entre las variables declaradas y los componentes del layout
        btnSeguirJugando = view.findViewById(R.id.btnSeguirJugando);
        btnSalirDelJuego = view.findViewById(R.id.btnSalirDelJuego);
        flPause = view.findViewById(R.id.flPauseFragment);

        //Funcion para detectar la interaccion del usuario con el boton para remover el fragmento y seguir con su partida
        btnSeguirJugando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(PauseFragment.this).commit();
                flPause.setClickable(false);
            }
        });

        //Funcion para detectar la interaccion del usuario con el boton para terminar la partida y regresarlo a la pantalla de HomeActivity.
        btnSalirDelJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHome = new Intent(getActivity(), HomeActivity.class);
                startActivity(toHome);
            }
        });

    }
}