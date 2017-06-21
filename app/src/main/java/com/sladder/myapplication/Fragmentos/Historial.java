package com.sladder.myapplication.Fragmentos;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.sladder.myapplication.ControlBD;
import com.sladder.myapplication.R;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by Andre on 16/07/2015.
 */
public class Historial extends Fragment{

    static public int id_carrito_seleccionado;

    ListView listview3;
    ControlBD helper;
    int[] cantidad_de_carritos;
    private RelativeLayout mRoot2;

    /** Para el ListView */
    ListViewAdapterHistorial adapter;
    ListViewAdapterError adapter_error;

    private View.OnClickListener mSnackBarClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.frag_historial, container, false);

        listview3 = (ListView) rootView.findViewById(R.id.listView3);
        helper = new ControlBD(this.getActivity());


        cantidad_de_carritos = helper.Cantidad_carritos();
        final ArrayList<String> nombres_carrito_array = new ArrayList<String>();
        final BigDecimal[] precio_total = new BigDecimal[cantidad_de_carritos.length];
        final String[] fecha = new String[cantidad_de_carritos.length];
        mRoot2 = (RelativeLayout) rootView.findViewById(R.id.frag_historial);

        for (int i = 0; i < cantidad_de_carritos.length; i++) {
            helper.abrir();
            Cursor cursor = helper.Listar_historial(String.valueOf(cantidad_de_carritos[i]));
            helper.cerrar();
            nombres_carrito_array.add(i,cursor.getString(0));
            fecha[i] = cursor.getString(1);
            precio_total[i] = BigDecimal.valueOf(cursor.getDouble(2));
            //precio_total = precio_total+precios_productos[i];
        }
        adapter = new ListViewAdapterHistorial(this.getActivity(), nombres_carrito_array, precio_total, fecha);

        final AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(adapter);
        animationAdapter.setAbsListView(listview3);
        listview3.setAdapter(animationAdapter);


        /**CLICK EN UN ITEM DEL LISTVIEW*/
        listview3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {

                id_carrito_seleccionado = cantidad_de_carritos[i];
                try{
                    Class<?>clase=Class.forName("com.sladder.myapplication.Mi_carrito_sl");
                    Intent inte = new Intent(getActivity(),clase);
                    startActivity(inte);
                }
                catch(ClassNotFoundException e){
                    e.printStackTrace();
                }
            }
        });


        return rootView;
    }
}
