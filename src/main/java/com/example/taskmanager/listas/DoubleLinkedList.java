package com.example.taskmanager.listas;

import java.io.Serializable;

public class DoubleLinkedList<T> implements Serializable {
    private NodoDoble<T> cabeza;
    private NodoDoble<T> cola;
    private int tamanio;

    public DoubleLinkedList() {
        this.cabeza = null;
        this.cola = null;
        this.tamanio = 0;
    }

    public void addEnd(T valor) {
        NodoDoble<T> nuevoNodoDoble = new NodoDoble<>(valor);
        if (cabeza == null) {
            cabeza = nuevoNodoDoble;
            cola = nuevoNodoDoble;
        } else {
            nuevoNodoDoble.anterior = cola;
            cola.siguiente = nuevoNodoDoble;
            cola = nuevoNodoDoble;
        }
        tamanio++;
    }

    public void addBeginning(T valor) {
        NodoDoble<T> nuevoNodoDoble = new NodoDoble<>(valor);
        if (cabeza == null) {
            cabeza = nuevoNodoDoble;
            cola = nuevoNodoDoble;
        } else {
            nuevoNodoDoble.siguiente = cabeza;
            cabeza.anterior = nuevoNodoDoble;
            cabeza = nuevoNodoDoble;
        }
        tamanio++;
    }

    public void remove(T valor) {
        NodoDoble<T> actual = cabeza;
        while (actual != null) {
            if (actual.dato.equals(valor)) {
                if (actual.anterior != null) {
                    actual.anterior.siguiente = actual.siguiente;
                } else {
                    cabeza = actual.siguiente;
                }

                if (actual.siguiente != null) {
                    actual.siguiente.anterior = actual.anterior;
                } else {
                    cola = actual.anterior;
                }

                tamanio--;
                return;
            }
            actual = actual.siguiente;
        }
    }

    public int getIndex(T valor) {
        NodoDoble<T> actual = cabeza;
        int posicion = 0;
        while (actual != null) {
            if (actual.dato.equals(valor)) {
                return posicion;
            }
            actual = actual.siguiente;
            posicion++;
        }
        // Si el elemento no se encuentra en la lista, retornar -1
        return -1;
    }
    public void print() {
        NodoDoble<T> actual = cabeza;
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.siguiente;
        }
        System.out.println();
    }

    public T get(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        NodoDoble<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }

        return actual.dato;
    }

    public int size() {
        return tamanio;
    }
    public boolean contains(T valor) {
        NodoDoble<T> actual = cabeza;
        while (actual != null) {
            if (actual.dato.equals(valor)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }


    public void addByIndex(int indice, T valor) {
        if (indice < 0 || indice > tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        NodoDoble<T> nuevoNodo = new NodoDoble<>(valor);
        if (indice == 0) {
            nuevoNodo.siguiente = cabeza;
            cabeza.anterior = nuevoNodo;
            cabeza = nuevoNodo;
        } else if (indice == tamanio) {
            nuevoNodo.anterior = cola;
            cola.siguiente = nuevoNodo;
            cola = nuevoNodo;
        } else {
            NodoDoble<T> actual = cabeza;
            for (int i = 0; i < indice - 1; i++) {
                actual = actual.siguiente;
            }
            nuevoNodo.siguiente = actual.siguiente;
            nuevoNodo.anterior = actual;
            actual.siguiente.anterior = nuevoNodo;
            actual.siguiente = nuevoNodo;
        }
        tamanio++;
    }
    
}