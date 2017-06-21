package com.sladder.myapplication.Fragmentos;

import android.content.Context;
import android.content.res.ColorStateList;
import android.inputmethodservice.Keyboard;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.sladder.myapplication.R;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Andre on 18/07/2015.
 */
public class ListViewAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> titulos;
    int[] imagenes;
    BigDecimal[] precio;
    LayoutInflater inflater;

    public ListViewAdapter(Context context, ArrayList<String> titulos, int[] imagenes, BigDecimal[] precio) {
        this.context = context;
        this.titulos = titulos;
        this.imagenes = imagenes;
        this.precio = precio;
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
        ImageView imgImg;

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_row, parent, false);

        txtId = (TextView) itemView.findViewById(R.id.list_row_id);
        txtTitle = (TextView) itemView.findViewById(R.id.list_row_title);
        imgImg = (ImageView) itemView.findViewById(R.id.list_row_image);
        tv_precio=(TextView) itemView.findViewById(R.id.tv_precio);

        // Capture position and set to the TextViews
        /**Tambien estoy numerando los items de la ListView*/
        txtId.setText(String.valueOf(position+1)+".");
        txtTitle.setText(titulos.get(position));
        imgImg.setImageResource(imagenes[position]);
        tv_precio.setText("S/. "+precio[position]);

        return itemView;
    }
}
