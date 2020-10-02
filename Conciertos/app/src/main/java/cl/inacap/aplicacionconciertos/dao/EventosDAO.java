package cl.inacap.aplicacionconciertos.dao;

import java.util.List;

import cl.inacap.aplicacionconciertos.dto.Evento;

public interface EventosDAO  {

    List<Evento> getAll();
    void add(Evento e);


}
