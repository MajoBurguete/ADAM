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

//fragmento creado para el carrusel de juegos en la pantalla de HomeActivity para que el usuario pueda escoger el juego de En Formitas.
public class FormitasFragment extends Fragment {

    Button btnJugarFormitas;

    //funcion necesaria para que el fragmento se ejecute de manera correcta.
    public FormitasFragment() {
        // Required empty public constructor
    }

    //funcion necesaria para que el fragmento se ejecute de manera correcta.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_formitas, container, false);
    }

    //funcion añadida para realizar acciones al crear el fragmento.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //declaracion de boton y el elemento en el layout al que corresponde.
        btnJugarFormitas = view.findViewById(R.id.btnJugarFormitas);

        //Funcion para detectar la interaccion del usuario con el boton y asi poder mandarlo al juego.
        btnJugarFormitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toSelectDifficulty = new Intent(getActivity(), InstructionsActivity.class);
                //Elemento que se manda a la siguiente pantalla para poder identificar a que juego se fue y desplegar las instrucciones apropiadas
                toSelectDifficulty.putExtra("game", 1);
                startActivity(toSelectDifficulty);
            }
        });
    }
}