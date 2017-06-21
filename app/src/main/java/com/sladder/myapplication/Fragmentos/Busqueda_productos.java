package com.sladder.myapplication.Fragmentos;

/**
 * Created by Andre on 15/07/2015.
 */
import android.app.Fragment;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.OnDismissCallback;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.undo.SimpleSwipeUndoAdapter;
import com.sladder.myapplication.ControlBD;
import com.sladder.myapplication.R;

import java.math.BigDecimal;
import java.util.ArrayList;


/**
 * Created by user on 26/08/2014.
 */
public class Busqueda_productos extends Fragment {

    private LinearLayout mRoot;
    private TextInputLayout busqueda_layout;
    private EditText et_busqueda;
    ListView listview;
    ControlBD helper;
    static public int[] id_productos_seleccionados;
    static public int[] id_productos_seleccionados_ordenados;
    static public int cantidad_de_productos_seleccionados = 0;
    static public int contador = 0;
    static public boolean hiciste_click_en_guardar = false;
    Button button2;

    /** Para el ListView */
    ListViewAdapter adapter;


    int[] imagenes = {0,R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10, R.drawable.a11, R.drawable.a12, R.drawable.a13, R.drawable.a14, R.drawable.a15, R.drawable.a16, R.drawable.a17, R.drawable.a18, R.drawable.a19, R.drawable.a20, R.drawable.a21, R.drawable.a22, R.drawable.a23, R.drawable.a24, R.drawable.a25, R.drawable.a26, R.drawable.a27, R.drawable.a28, R.drawable.a29, R.drawable.a30, R.drawable.a31, R.drawable.a32, R.drawable.a33, R.drawable.a34, R.drawable.a35, R.drawable.a36, R.drawable.a37, R.drawable.a38, R.drawable.a39, R.drawable.a40};
    static public int[] imagenes_aux;

    private View.OnClickListener mSnackBarClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.frag_busqueda_productos, container, false);

        mRoot = (LinearLayout) rootView.findViewById(R.id.frag_busqueda);
        busqueda_layout = (TextInputLayout) rootView.findViewById(R.id.busqueda_layout);
        et_busqueda = (EditText) rootView.findViewById(R.id.et_busqueda);
        listview = (ListView) rootView.findViewById(R.id.listView);
        helper = new ControlBD(this.getActivity());

        /** Para implementar OnClick en un fragmento */
        final Button button = (Button) rootView.findViewById(R.id.bt_buscar);
        AppCompatButton b = (AppCompatButton) button;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonClicked(v);
            }
        });

        /**Color de fondo del boton*/
        ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xffff700c});
        b.setSupportBackgroundTintList(csl);

        button2 = (Button) rootView.findViewById(R.id.bt_llenar);
        AppCompatButton b2 = (AppCompatButton) button2;
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonClicked2(v);
            }
        });

        /**Color de fondo del boton*/
        b2.setSupportBackgroundTintList(csl);

        return rootView;
    }

    /** Para implementar OnClick en un fragmento */
    public void buttonClicked (View view) {
        boolean isEmptyBusqueda = isEmptyBusqueda();
        if (isEmptyBusqueda) {
            Snackbar.make(mRoot, "Campo de busqueda vacio", Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.text_dismiss), mSnackBarClickListener)
                    .show();
            listview.setAdapter(null);
        } else {
            if (hiciste_click_en_guardar == false) {
                helper.abrir();
                Cursor cursor = helper.Listar(et_busqueda.getText().toString());
                helper.cerrar();

                if (cursor == null) {
                    Snackbar.make(mRoot, "Producto no encontrado", Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.text_dismiss), mSnackBarClickListener)
                            .show();
                    listview.setAdapter(null);
                } else {
                    /** Separo indice de nombre de productos para luego direcciona a un activity*/
                    final int[] id_productos_listados = new int[cursor.getCount()];
                    final String[] productos_listados = new String[cursor.getCount()];
                    final ArrayList<String> productos_listados_array = new ArrayList<String>();
                    final BigDecimal[] precios_listados = new BigDecimal[cursor.getCount()];
                    final boolean[] aux = new boolean[cursor.getCount()];
                    imagenes_aux = new int[cursor.getCount()];


                    id_productos_seleccionados = new int[cursor.getCount()];
                    id_productos_seleccionados_ordenados = new int[cursor.getCount()];
                    //final int[] id_productos_seleccionados = new int[cursor.getCount()];

                    for (int i = 0; i < cursor.getCount(); i++) {
                        id_productos_listados[i] = cursor.getInt(0);
                        productos_listados[i] = cursor.getString(1);
                        productos_listados_array.add(i, cursor.getString(1));
                        precios_listados[i] = BigDecimal.valueOf(cursor.getDouble(2));

                        imagenes_aux[i]= imagenes[id_productos_listados[i]];
                        cursor.moveToNext();
                        //aux[i]=false;
                    }
                    /**Crear un array de imagenes_listadas*/
                    adapter = new ListViewAdapter(this.getActivity(), productos_listados_array, imagenes_aux, precios_listados);    /**AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII*/
                    //listview.setAdapter(adapter);

                    final AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(adapter);
                    animationAdapter.setAbsListView(listview);
                    listview.setAdapter(animationAdapter);


                    /**CLICK EN UN ITEM DEL LISTVIEW*/
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                            if (aux[i] == false) {
                                aux[i] = true;
                                Snackbar.make(mRoot, "Selecciono: " + productos_listados[i], Snackbar.LENGTH_LONG)
                                        .setAction(getString(R.string.text_dismiss), mSnackBarClickListener)
                                        .show();
                                id_productos_seleccionados[i] = id_productos_listados[i];
                            } else {
                                aux[i] = false;
                                Snackbar.make(mRoot, "Eliminado: " + productos_listados[i], Snackbar.LENGTH_LONG)
                                        .setAction(getString(R.string.text_dismiss), mSnackBarClickListener)
                                        .show();
                                id_productos_seleccionados[i] = 0;
                            }
                            contador = listview.getCheckedItemCount();
                            if (listview.getCheckedItemCount()>=1)
                                button2.setEnabled(true);
                            else
                                button2.setEnabled(false);
                            cantidad_de_productos_seleccionados=listview.getCount();
                        }
                    });
                }
            }
            else {
                Snackbar.make(mRoot, "Ya tienes tu carrito de compras", Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.text_dismiss), mSnackBarClickListener)
                        .show();
            }
        }
    }
    int cuenta = 0;
    public void buttonClicked2 (View view) {

        if (hiciste_click_en_guardar==false){
            hiciste_click_en_guardar=true;
            cuenta = 0;
            listview.setAdapter(null);
            for (int i=0; i<cantidad_de_productos_seleccionados; i++) {
                if (id_productos_seleccionados[i]!=0) {
                    id_productos_seleccionados_ordenados[cuenta]=id_productos_seleccionados[i];
                    cuenta = cuenta + 1;
                }
            }
            Snackbar.make(mRoot, "Guardado correctamente", Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.text_dismiss), mSnackBarClickListener)
                    .show();
        } else {
            Snackbar.make(mRoot, "Usted ya guardo su compra", Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.text_dismiss), mSnackBarClickListener)
                    .show();
        }
    }


    private boolean isEmptyBusqueda() {
        return et_busqueda.getText() == null
                || et_busqueda.getText().toString() == null
                || et_busqueda.getText().toString().isEmpty();

    }
}