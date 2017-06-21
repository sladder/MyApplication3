package com.sladder.myapplication.Fragmentos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sladder.myapplication.R;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Andre on 27/07/2015.
 */
public class ListViewAdapterHistorial extends BaseAdapter {

    Context context;
    ArrayList<String> titulos;
    BigDecimal[] precio;
    String[] fecha;
    LayoutInflater inflater;

    public ListViewAdapterHistorial(Context context, ArrayList<String> titulos, BigDecimal[] precio, String[] fecha) {
        this.context = context;
        this.titulos = titulos;
        this.precio = precio;
        this.fecha = fecha;
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
        TextView tv_precio;
        TextView tv_fecha;

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_row_historial, parent, false);

        txtId = (TextView) itemView.findViewById(R.id.list_row_id);
        txtTitle = (TextView) itemView.findViewById(R.id.list_row_title);
        tv_precio=(TextView) itemView.findViewById(R.id.tv_precio);
        tv_fecha=(TextView) itemView.findViewById(R.id.tv_fecha);

        // Capture position and set to the TextViews
        /**Tambien estoy numerando los items de la ListView*/
        txtId.setText(String.valueOf(position+1)+".");
        txtTitle.setText(titulos.get(position));
        tv_precio.setText("S/. "+precio[position]);
        tv_fecha.setText(fecha[position]);

        return itemView;
    }
}
