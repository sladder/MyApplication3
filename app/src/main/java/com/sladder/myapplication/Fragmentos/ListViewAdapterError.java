package com.sladder.myapplication.Fragmentos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sladder.myapplication.R;

/**
 * Created by Andre on 22/07/2015.
 */
public class ListViewAdapterError extends BaseAdapter{
    Context context;
    LayoutInflater inflater;

    public ListViewAdapterError(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        return 1;
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

        TextView txtTitle;
        ImageView imgImg;

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_row_error, parent, false);

        txtTitle = (TextView) itemView.findViewById(R.id.list_row_title);
        imgImg = (ImageView) itemView.findViewById(R.id.list_row_image);

        // Capture position and set to the TextViews
        /**Tambien estoy numerando los items de la ListView*/
        txtTitle.setText("Ningun producto guardado!");
        imgImg.setImageResource(R.drawable.error2);

        return itemView;
    }
}
