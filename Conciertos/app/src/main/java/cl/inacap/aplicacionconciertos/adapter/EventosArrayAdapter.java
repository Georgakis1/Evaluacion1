package cl.inacap.aplicacionconciertos.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import cl.inacap.aplicacionconciertos.R;
import cl.inacap.aplicacionconciertos.dto.Evento;

public class EventosArrayAdapter extends ArrayAdapter<Evento> {
    private Activity activity;
    private List<Evento> eventos;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.activity.getLayoutInflater();
        View fila = inflater.inflate(R.layout.eventos_list
                ,null
                ,true);
        TextView fechaTv =fila.findViewById(R.id.fechaLV);
        TextView nombreTv = fila.findViewById(R.id.nombreLV);
        TextView valorTv = fila.findViewById(R.id.valorLV);
        ImageView iconTv = fila.findViewById(R.id.iconLV);
        Evento actual = eventos.get(position);
        fechaTv.setText(actual.getFecha());
        nombreTv.setText(actual.getNombreArtista());
        valorTv.setText(actual.getValorEntrada());


        return fila;
    }

    public EventosArrayAdapter(@NonNull Activity context, int resource, @NonNull List<Evento> objects) {
        super(context, resource, objects);
        this.activity= context;
        this.eventos= objects;


    }
}
