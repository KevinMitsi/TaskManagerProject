package com.example.taskmanager.listas;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList<T> implements Serializable, Iterable<T>{
    private Nodo<T> cabeza;
    private int size; // Tamaño de la lista

    public SimpleLinkedList() {
        this.cabeza = null;
        this.size = 0;
    }
    // Implementación de la interfaz Iterable
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    // Clase interna que implementa la interfaz Iterator
    private class LinkedListIterator implements Iterator<T> {
        private Nodo<T> actual = cabeza;

        @Override
        public boolean hasNext() {
            return actual != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T valor = actual.dato;
            actual = actual.siguiente;
            return valor;
        }
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
        size++;
    }

    public void addBeginning(T valor) {
        Nodo<T> nuevoNodo = new Nodo<>(valor);
        nuevoNodo.siguiente = cabeza;
        cabeza = nuevoNodo;
        size++;
    }

    public void remove(T valor) {
        if (cabeza != null) {
            if (cabeza.dato.equals(valor)) {
                cabeza = cabeza.siguiente;
                size--;
                return;
            }

            Nodo<T> actual = cabeza;
            while (actual.siguiente != null && !actual.siguiente.dato.equals(valor)) {
                actual = actual.siguiente;
            }

            if (actual.siguiente != null) {
                actual.siguiente = actual.siguiente.siguiente;
                size--;
            }
        }
    }

    public T getByIndex(int indice) {
        if (indice < 0 || indice >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }

        return actual.dato;
    }

    public void add(int posicion, T elemento) {
        if (posicion < 0 || posicion > size) {
            throw new IndexOutOfBoundsException("Posición fuera de límites");
        }

        Nodo<T> nuevoNodo = new Nodo<>(elemento);

        if (posicion == 0) {
            // Insertar al principio
            nuevoNodo.siguiente = cabeza;
            cabeza = nuevoNodo;
        } else {
            // Insertar en una posición diferente de la primera
            Nodo<T> actual = cabeza;
            for (int i = 0; i < posicion - 1; i++) {
                actual = actual.siguiente;
            }

            nuevoNodo.siguiente = actual.siguiente;
            actual.siguiente = nuevoNodo;
        }

        size++;
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
        return size;
    }
}