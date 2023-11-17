package com.example.taskmanager.listas;

import java.io.Serializable;

public class Nodo<T> implements Serializable {
    T dato;
    Nodo<T> siguiente;

    public Nodo(T valor) {
        this.dato = valor;
        this.siguiente = null;
    }
}