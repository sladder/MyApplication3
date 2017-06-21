package com.sladder.myapplication.Fragmentos;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sladder.myapplication.MainActivity;
import com.sladder.myapplication.ControlBD;
import com.sladder.myapplication.R;

/**
 * Created by Andre on 04/07/2015.
 */
public class Inicio extends Fragment {
    ControlBD helper;
    static boolean primera_vez = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_inicio, container, false);

        helper = new ControlBD(this.getActivity());

        if (primera_vez==false) {
            helper.abrir();
            helper.llenarBDCarnet();
            helper.cerrar();
        }
        primera_vez=true;

        return rootView;
    }
}
