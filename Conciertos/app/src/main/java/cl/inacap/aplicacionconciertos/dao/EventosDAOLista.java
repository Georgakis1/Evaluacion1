package cl.inacap.aplicacionconciertos.dao;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.aplicacionconciertos.dto.Evento;

public class EventosDAOLista implements EventosDAO {

    private static ArrayList<Evento> listaEventos = new ArrayList<>();

    @Override
    public List<Evento> getAll() {
        return listaEventos;
    }

    @Override
    public void add(Evento e) {
        listaEventos.add(e);

    }
}
