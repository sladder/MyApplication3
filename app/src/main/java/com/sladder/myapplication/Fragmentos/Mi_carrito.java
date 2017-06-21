package com.sladder.myapplication.Fragmentos;

import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.sladder.myapplication.ControlBD;
import com.sladder.myapplication.R;
import com.sladder.myapplication.SwipeListViewTouchListener;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Andre on 16/07/2015.
 */
public class Mi_carrito extends Fragment {

    private LinearLayout mRoot2;
    private TextInputLayout nombre_layout;
    private EditText et_nombre;
    private TextView tv_cantidad;
    private TextView tv_precio_cantidad;
    private TextView tv_precio_total;
    static public BigInteger[] cantidad;

    static public BigDecimal precio_total=new BigDecimal(BigInteger.ZERO);
    /**De la BD*/
    static public BigDecimal[] precios_productos;
    static public BigDecimal[] precio_producto_cantidad;
    static public int puntero = -1;
    /**Para obtener la fecha actual del sistema*/
    Calendar c;
    int anio;
    int dia;
    int mes;

    ListView listview2;
    ControlBD helper;

    /** Para el ListView */
    ListViewAdapter adapter;
    ListViewAdapterError adapter_error;

    int[] imagenes = {0,R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10, R.drawable.a11, R.drawable.a12, R.drawable.a13, R.drawable.a14, R.drawable.a15, R.drawable.a16, R.drawable.a17, R.drawable.a18, R.drawable.a19, R.drawable.a20, R.drawable.a21, R.drawable.a22, R.drawable.a23, R.drawable.a24, R.drawable.a25, R.drawable.a26, R.drawable.a27, R.drawable.a28, R.drawable.a29, R.drawable.a30, R.drawable.a31, R.drawable.a32, R.drawable.a33, R.drawable.a34, R.drawable.a35, R.drawable.a36, R.drawable.a37, R.drawable.a38, R.drawable.a39, R.drawable.a40};


    private View.OnClickListener mSnackBarClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.frag_mi_carrito, container, false);

        mRoot2 = (LinearLayout) rootView.findViewById(R.id.frag_carrito);
        nombre_layout = (TextInputLayout) rootView.findViewById(R.id.nombre_layout);
        tv_cantidad = (TextView) rootView.findViewById(R.id.tv_cantidad);
        tv_precio_cantidad = (TextView) rootView.findViewById(R.id.tv_precio_cantidad);
        tv_precio_total=(TextView) rootView.findViewById(R.id.tv_precio_total);
        et_nombre = (EditText) rootView.findViewById(R.id.et_nombre);
        listview2 = (ListView) rootView.findViewById(R.id.listView2);
        helper = new ControlBD(this.getActivity());

        /** Para implementar OnClick en un fragmento */
        final Button button = (Button) rootView.findViewById(R.id.bt_guardar2);
        AppCompatButton b = (AppCompatButton) button;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonClicked(v);
            }
        });


        /**Color de fondo del boton*/
        ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xffff700c});
        b.setSupportBackgroundTintList(csl);


        final Button button2 = (Button) rootView.findViewById(R.id.bt_agregar2);
        AppCompatButton b2 = (AppCompatButton) button2;
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonClicked2(v);
            }
        });

        /**Color de fondo del boton*/
        b2.setSupportBackgroundTintList(csl);


        final Button button3 = (Button) rootView.findViewById(R.id.bt_quitar2);
        AppCompatButton b3 = (AppCompatButton) button3;
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonClicked3(v);
            }
        });

        /**Color de fondo del boton*/
        b3.setSupportBackgroundTintList(csl);


        final Button button4 = (Button) rootView.findViewById(R.id.bt_cancelar2);
        AppCompatButton b4 = (AppCompatButton) button4;
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonClicked4(v);
            }
        });

        /**Color de fondo del boton*/
        b4.setSupportBackgroundTintList(csl);



        if (Busqueda_productos.hiciste_click_en_guardar==false) {

            adapter_error = new ListViewAdapterError(this.getActivity());
            listview2.setEnabled(false);
            listview2.setAdapter(adapter_error);

        } else {
            cantidad = new BigInteger[Busqueda_productos.contador];
            for (int z=0; z<Busqueda_productos.contador;z++) {
                cantidad[z]=BigInteger.ONE;
            }

            final String[] nombres_productos = new String[Busqueda_productos.contador];
            precios_productos = new BigDecimal[Busqueda_productos.contador];
            precio_producto_cantidad = new BigDecimal[Busqueda_productos.contador];
            Busqueda_productos.imagenes_aux = new int[Busqueda_productos.contador];
            final ArrayList<String> nombres_producto_array = new ArrayList<String>();


            for (int i = 0; i < Busqueda_productos.contador; i++) {
                helper.abrir();
                Cursor cursor = helper.Listar2(String.valueOf(Busqueda_productos.id_productos_seleccionados_ordenados[i]));
                helper.cerrar();
                nombres_productos[i] = cursor.getString(0);
                nombres_producto_array.add(i,cursor.getString(0));
                precios_productos[i] = BigDecimal.valueOf(cursor.getDouble(1));
                //precio_total = precio_total+precios_productos[i];
                precio_total = precio_total.add(precios_productos[i]);
                Busqueda_productos.imagenes_aux[i]=imagenes[Busqueda_productos.id_productos_seleccionados_ordenados[i]];
            }
            adapter = new ListViewAdapter(this.getActivity(), nombres_producto_array, Busqueda_productos.imagenes_aux, precios_productos);

            final AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(adapter);
            animationAdapter.setAbsListView(listview2);
            listview2.setAdapter(animationAdapter);

            tv_precio_total.setText("Precio Total: S/. " + precio_total);

            for (int z=0; z<Busqueda_productos.contador; z++) {
                precio_producto_cantidad[z] = precios_productos[z];
            }

            /**CLICK EN UN ITEM DEL LISTVIEW*/
            listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView adapterView, View view, int i, long l) {

                    puntero = i;
                    tv_cantidad.setText("Cantidad: " + cantidad[puntero]);
                    tv_precio_cantidad.setText("Precio: S/. " + precio_producto_cantidad[puntero]);
                    Snackbar.make(mRoot2, "Selecciono: " + nombres_productos[i], Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.text_dismiss), mSnackBarClickListener)
                            .show();

                }
            });

        }

        return rootView;

    }

    public void buttonClicked (View view) {

        boolean existe_un_producto = false;
        for (int i = 0; i < Busqueda_productos.contador; i++) {
            if (cantidad[i].compareTo(BigInteger.ONE)>=0)
                existe_un_producto = true;
        }
        if (existe_un_producto == true) {
            int id_carrito;
            c = Calendar.getInstance();
            anio = c.get(Calendar.YEAR);
            mes = c.get(Calendar.MONTH);
            dia = c.get(Calendar.DAY_OF_MONTH);

            mes = mes + 1;

            String fecha = anio + "-" + mes + "-" + dia;

            helper.abrir();
            String resultado = helper.Guardar(et_nombre.getText().toString(), fecha, precio_total);
            String resultado2 = "";
            id_carrito = helper.Id_carrito();
            helper.cerrar();

            for (int i = 0; i < Busqueda_productos.contador; i++) {
                helper.abrir();
                resultado2 = helper.Guardar(Busqueda_productos.id_productos_seleccionados_ordenados[i], id_carrito, cantidad[i], precio_producto_cantidad[i]);
                helper.cerrar();
            }

            for (int i = 0; i < Busqueda_productos.contador; i++) {
                /**Reinicioamos las variables*/
                cantidad[i] = BigInteger.ZERO;
                precios_productos[i] = BigDecimal.ZERO;
                precio_total = BigDecimal.ZERO;
                Busqueda_productos.id_productos_seleccionados[i] = 0;
                Busqueda_productos.id_productos_seleccionados_ordenados[i] = 0;
            }

            Busqueda_productos.cantidad_de_productos_seleccionados = 0;
            Busqueda_productos.contador = 0;
            Busqueda_productos.hiciste_click_en_guardar = false;

            listview2.setAdapter(null);

            tv_precio_total.setText("Precio Total: S/. 0.00");
            tv_cantidad.setText("Seleccione un producto");
            tv_precio_cantidad.setText("Precio: S/. 0.00");

            Snackbar.make(mRoot2, "GUARDADO CORRECTAMENTE", Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.text_dismiss), mSnackBarClickListener)
                    .show();
        } else {
            for (int i = 0; i < Busqueda_productos.contador; i++) {
                /**Reinicioamos las variables*/
                cantidad[i] = BigInteger.ZERO;
                precios_productos[i] = BigDecimal.ZERO;
                precio_total = BigDecimal.ZERO;
                Busqueda_productos.id_productos_seleccionados[i] = 0;
                Busqueda_productos.id_productos_seleccionados_ordenados[i] = 0;
            }

            Busqueda_productos.cantidad_de_productos_seleccionados = 0;
            Busqueda_productos.contador = 0;
            Busqueda_productos.hiciste_click_en_guardar = false;

            Snackbar.make(mRoot2, "NO EXISTEN PRODUCTOS EN SU CARRITO", Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.text_dismiss), mSnackBarClickListener)
                    .show();
        }
    }

    public void buttonClicked2 (View view) {

        if (puntero == -1) {
            Snackbar.make(mRoot2, "Seleccione un producto", Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.text_dismiss), mSnackBarClickListener)
                    .show();
        } /*else if (cantidad[puntero] <= 0) {
            Snackbar.make(mRoot2, "El producto ha sido eliminado", Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.text_dismiss), mSnackBarClickListener)
                    .show();
        }*/ else {
            cantidad[puntero] = cantidad[puntero].add(BigInteger.ONE);
            /**Creamos una varialbe temporal para poner multiplicar un BigDecimal con un BigInteger*/
            BigDecimal temporal = new BigDecimal(cantidad[puntero]);
            precio_producto_cantidad[puntero] = precios_productos[puntero].multiply(temporal);
            precio_total=precio_total.add(precios_productos[puntero]);

            tv_cantidad.setText("Cantidad: " + cantidad[puntero]);
            tv_precio_cantidad.setText("Precio: S/. "+precio_producto_cantidad[puntero]);
            tv_precio_total.setText("Precio Total: S/. " + precio_total);
        }
    }


    public void buttonClicked3 (View view) {
        if (puntero==-1) {
            Snackbar.make(mRoot2, "Seleccione un producto", Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.text_dismiss), mSnackBarClickListener)
                    .show();
        } else if (cantidad[puntero].compareTo(BigInteger.ONE)<1) {
            Snackbar.make(mRoot2, "Cantidad minima de 1 producto", Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.text_dismiss), mSnackBarClickListener)
                    .show();

            /**IDEA PONER UN ARRAY DE BANDERAS PARA SABER QUE PRODUCTO HA SIDO ELIMINADO CUANDO LA CANTIDAD LLEGO A CERO*/
            /**NO ES NECESARIO PORQUE PODEMOS USAR CANTIDAD[] DONDE SEA 0 NO HABRA PRODUCTO A GUARDAR*/

        } else {
            cantidad[puntero] = cantidad[puntero].subtract(BigInteger.ONE);
            BigDecimal temporal = new BigDecimal(cantidad[puntero]);
            precio_producto_cantidad[puntero] = precios_productos[puntero].multiply(temporal);
            precio_total=precio_total.subtract(precios_productos[puntero]);

            tv_cantidad.setText("Cantidad: " + cantidad[puntero]);
            tv_precio_cantidad.setText("Precio: S/. "+precio_producto_cantidad[puntero]);
            tv_precio_total.setText("Precio Total: S/. "+precio_total);
        }
    }

    public void buttonClicked4 (View view) {

        for (int i = 0; i < Busqueda_productos.contador; i++) {
            /**Reinicioamos las variables*/
            cantidad[i] = BigInteger.ZERO;
            precios_productos[i] = BigDecimal.ZERO;
            precio_total = BigDecimal.ZERO;
            Busqueda_productos.id_productos_seleccionados[i] = 0;
            Busqueda_productos.id_productos_seleccionados_ordenados[i] = 0;
        }

        Busqueda_productos.cantidad_de_productos_seleccionados = 0;
        Busqueda_productos.contador = 0;
        Busqueda_productos.hiciste_click_en_guardar = false;

        tv_precio_total.setText("Precio Total: S/. 0.00");
        tv_cantidad.setText("Seleccione un producto");
        tv_precio_cantidad.setText("Precio: S/. 0.00");


        Snackbar.make(mRoot2, "CANCELADO", Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.text_dismiss), mSnackBarClickListener)
                .show();
    }
}
