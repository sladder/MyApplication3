package com.sladder.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by Andre on 27/07/2015.
 */
public class ListViewAdapterDetallesCarrito extends BaseAdapter {
    Context context;
    ArrayList<String> titulos;
    int[] imagenes;
    BigInteger[] cantidad;
    BigDecimal[] precio_uni;
    BigDecimal[] precio_total;
    LayoutInflater inflater;

    public ListViewAdapterDetallesCarrito(Context context, ArrayList<String> titulos, int[] imagenes,BigInteger[] cantidad, BigDecimal[] precio_uni, BigDecimal[] precio_total) {
        this.context = context;
        this.titulos = titulos;
        this.imagenes = imagenes;
        this.precio_uni = precio_uni;
        this.precio_total = precio_total;
        this.cantidad = cantidad;
    }

    @Override
    public int getCount() {
        return titulos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        TextView txtId;
        TextView txtTitle;
        TextView tv_precio_uni;
        TextView tv_precio_total;
        TextView tv_cantidad;
        ImageView imgImg;

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_row_detalles_carrito, parent, false);

        txtId = (TextView) itemView.findViewById(R.id.list_row_id3);
        txtTitle = (TextView) itemView.findViewById(R.id.tv_titulo3);
        imgImg = (ImageView) itemView.findViewById(R.id.list_row_image3);
        tv_precio_uni=(TextView) itemView.findViewById(R.id.tv_precio_uni3);
        tv_precio_total=(TextView) itemView.findViewById(R.id.tv_precio_total3);
        tv_cantidad = (TextView) itemView.findViewById(R.id.tv_cantidad3);

        // Capture position and set to the TextViews
        /**Tambien estoy numerando los items de la ListView*/
        txtId.setText(String.valueOf(position + 1) + ".");
        txtTitle.setText(titulos.get(position));
        imgImg.setImageResource(imagenes[position]);
        tv_precio_uni.setText("Prec. Uni: S/. " + precio_uni[position]);
        tv_precio_total.setText("Prec. Total: S/. "+precio_total[position]);
        tv_cantidad.setText("Cantidad: "+cantidad[position]);

        return itemView;
    }
}
