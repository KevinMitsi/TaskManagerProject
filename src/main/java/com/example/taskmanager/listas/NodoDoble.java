package com.example.taskmanager.listas;

import java.io.Serializable;

public class NodoDoble<T> implements Serializable {
    T dato;
    NodoDoble<T> siguiente;
    NodoDoble<T> anterior;

    public NodoDoble(T valor) {
        this.dato = valor;
        this.siguiente = null;
        this.anterior = null;
    }
}
