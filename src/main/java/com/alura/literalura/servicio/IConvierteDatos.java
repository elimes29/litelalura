package com.alura.literalura.servicio;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> Class);
}
