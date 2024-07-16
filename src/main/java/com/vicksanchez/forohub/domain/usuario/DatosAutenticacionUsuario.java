package com.vicksanchez.forohub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuario(

        @NotBlank
        String login,

        @NotBlank
        String clave
) {
}