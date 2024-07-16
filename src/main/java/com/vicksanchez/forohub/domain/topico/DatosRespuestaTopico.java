package com.vicksanchez.forohub.domain.topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,

        String titulo,

        String mensaje,

        LocalDateTime fechaDeCreacion,

        Boolean estatus,

        String autor,

        String curso
) {
}
