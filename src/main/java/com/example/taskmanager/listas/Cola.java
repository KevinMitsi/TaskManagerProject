package com.example.taskmanager.listas;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Cola<T> implements Serializable, Iterable<T>{
    private final SimpleLinkedList<T> lista;

    public Cola() {
        this.lista = new SimpleLinkedList<>();
    }

    @Override
    public Iterator<T> iterator() {
        return new ColaIterator();
    }

    // Clase interna que implementa la interfaz Iterator
    private class ColaIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < lista.size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return lista.getByIndex(currentIndex++);
        }
    }

    // Encolar un elemento al final de la cola
    public void enqueue(T elemento) {
        lista.addEnd(elemento);
    }

    // Desencolar el elemento del frente de la cola
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("La cola está vacía");
        }
        T elementoFrontal = lista.getByIndex(0);
        lista.remove(elementoFrontal);
        return elementoFrontal;
    }

    // Obtener el elemento del frente de la cola sin eliminarlo
    public T front() {
        if (isEmpty()) {
            throw new NoSuchElementException("La cola está vacía");
        }
        return lista.getByIndex(0);
    }

    public void insertAt(T elemento, int posicion) {
        if (posicion < 0 || posicion > lista.size()) {
            throw new IndexOutOfBoundsException("Posición fuera de límites");
        }
        lista.add(posicion, elemento);
    }

    public boolean contains(T elemento) {
        for (T actual : this) {
            if (actual.equals(elemento)) {
                return true;
            }
        }
        return false;
    }

    // Verificar si la cola está vacía
    public boolean isEmpty() {
        return lista.size() == 0;
    }

    // Obtener el tamaño de la cola
    public int size() {
        return lista.size();
    }

    // Imprimir la cola
    public void print() {
        lista.print();
    }
}