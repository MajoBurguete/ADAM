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

public class PauseFragment extends Fragment {

    Button btnSeguirJugando;
    Button btnSalirDelJuego;
    FrameLayout flPause;

    public PauseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pause, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnSeguirJugando = view.findViewById(R.id.btnSeguirJugando);
        btnSalirDelJuego = view.findViewById(R.id.btnSalirDelJuego);
        flPause = view.findViewById(R.id.flPauseFragment);

        btnSeguirJugando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(PauseFragment.this).commit();
                flPause.setClickable(false);
            }
        });

        btnSalirDelJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHome = new Intent(getActivity(), HomeActivity.class);
                startActivity(toHome);
            }
        });

    }
}