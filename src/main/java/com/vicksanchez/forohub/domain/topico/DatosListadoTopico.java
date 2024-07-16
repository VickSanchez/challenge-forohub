package com.vicksanchez.forohub.domain.topico;

import java.time.LocalDateTime;

public record DatosListadoTopico(

        Long id,

        String titulo,

        String mensaje,

        LocalDateTime fechaDeCreacion,

        Boolean estatus,

        String autor,

        String curso
) {
    public DatosListadoTopico( Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaDeCreacion(), topico.getEstatus(), topico.getAutor(), topico.getCurso());
    }
}
