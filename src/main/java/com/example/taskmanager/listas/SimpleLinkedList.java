package com.example.taskmanager.listas;

import java.io.Serializable;

public class SimpleLinkedList<T> implements Serializable {
    private Nodo<T> cabeza;
    private int tamanio; // Tamaño de la lista

    public SimpleLinkedList() {
        this.cabeza = null;
        this.tamanio = 0;
    }

    public boolean contains(T valor) {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            if (actual.dato.equals(valor)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public void addEnd(T valor) {
        Nodo<T> nuevoNodo = new Nodo<>(valor);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamanio++;
    }

    public void addBeginning(T valor) {
        Nodo<T> nuevoNodo = new Nodo<>(valor);
        nuevoNodo.siguiente = cabeza;
        cabeza = nuevoNodo;
        tamanio++;
    }

    public void remove(T valor) {
        if (cabeza != null) {
            if (cabeza.dato.equals(valor)) {
                cabeza = cabeza.siguiente;
                tamanio--;
                return;
            }

            Nodo<T> actual = cabeza;
            while (actual.siguiente != null && !actual.siguiente.dato.equals(valor)) {
                actual = actual.siguiente;
            }

            if (actual.siguiente != null) {
                actual.siguiente = actual.siguiente.siguiente;
                tamanio--;
            }
        }
    }

    public T getByIndex(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }

        return actual.dato;
    }

    public void print() {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.siguiente;
        }
        System.out.println();
    }

    public int size() {
        return tamanio;
    }
}