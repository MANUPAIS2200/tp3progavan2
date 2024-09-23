package com.example.tp3progavan2;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tp3progavan2.clases.Parking;

import java.util.List;

public class ParkingAdapter extends BaseAdapter {
    private Context context;
    private List<Parking> listaParking;

    public ParkingAdapter(Context context, List<Parking> listaParking) {
        this.context = context;
        this.listaParking = listaParking;
    }

    @Override
    public int getCount() {
        return listaParking.size();
    }

    @Override
    public Object getItem(int position) {
        return listaParking.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_parking, parent, false);
        }

        // Obtener los datos del parking actual
        Parking parking = listaParking.get(position);

        // Referencias a los TextView de la tarjeta
        TextView matriculaTextView = convertView.findViewById(R.id.textViewMatricula);
        TextView tiempoTextView = convertView.findViewById(R.id.textViewTiempo);

        // Establecer los valores en los TextView
        matriculaTextView.setText(parking.getMatricula());
        tiempoTextView.setText(parking.getTiempo());

        return convertView;
    }
}
