package com.sladder.myapplication;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.sladder.myapplication.Fragmentos.Busqueda_productos;
import com.sladder.myapplication.Fragmentos.Historial;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;


public class Mi_carrito_sl extends ActionBarActivity {
    private CharSequence mTitle;
    ListView listview4;
    ControlBD helper;
    TextView precio_total;

    /** Para el ListView */
    ListViewAdapterDetallesCarrito adapter;

    int[] imagenes = {0,R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10, R.drawable.a11, R.drawable.a12, R.drawable.a13, R.drawable.a14, R.drawable.a15, R.drawable.a16, R.drawable.a17, R.drawable.a18, R.drawable.a19, R.drawable.a20, R.drawable.a21, R.drawable.a22, R.drawable.a23, R.drawable.a24, R.drawable.a25, R.drawable.a26, R.drawable.a27, R.drawable.a28, R.drawable.a29, R.drawable.a30, R.drawable.a31, R.drawable.a32, R.drawable.a33, R.drawable.a34, R.drawable.a35, R.drawable.a36, R.drawable.a37, R.drawable.a38, R.drawable.a39, R.drawable.a40};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_carrito_sl);

        listview4 = (ListView) findViewById(R.id.listView22);
        precio_total = (TextView) findViewById(R.id.tv_precio_total2);
        helper = new ControlBD(this);

        helper.abrir();
        Cursor cursor = helper.Listar_detalles_carrito(String.valueOf(Historial.id_carrito_seleccionado));
        helper.cerrar();

        cursor.moveToFirst();
        setTitle(cursor.getString(0));

        precio_total.setText("Precio Total: S/. "+cursor.getString(5));


        final ArrayList<String> nombre_producto_sl = new ArrayList<String>();
        final BigInteger[] cantidad_sl = new BigInteger[cursor.getCount()];
        final BigDecimal[] precio_uni_sl = new BigDecimal[cursor.getCount()];
        final BigDecimal[] precio_cantidad_sl = new BigDecimal[cursor.getCount()];
        Busqueda_productos.imagenes_aux = new int[cursor.getCount()];

        for (int i=0; i<cursor.getCount(); i++) {
            nombre_producto_sl.add(i, cursor.getString(1));
            cantidad_sl[i] = BigInteger.valueOf(cursor.getInt(2));
            precio_uni_sl[i] = BigDecimal.valueOf(cursor.getDouble(3));
            precio_cantidad_sl[i] = BigDecimal.valueOf(cursor.getDouble(4));
            Busqueda_productos.imagenes_aux[i]=imagenes[cursor.getInt(6)];
            cursor.moveToNext();
        }

        adapter = new ListViewAdapterDetallesCarrito(this, nombre_producto_sl, Busqueda_productos.imagenes_aux, cantidad_sl, precio_uni_sl, precio_cantidad_sl);

        final AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(adapter);
        animationAdapter.setAbsListView(listview4);
        listview4.setAdapter(animationAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mi_carrito_sl, menu);
        return true;
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
