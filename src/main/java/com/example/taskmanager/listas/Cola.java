package com.example.taskmanager.listas;
import java.util.NoSuchElementException;

public class Cola<T> {
    private final SimpleLinkedList<T> lista;

    public Cola() {
        this.lista = new SimpleLinkedList<>();
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

    // Verificar si la cola está vacía
    public boolean isEmpty() {
        return lista.size() == 0;
    }

    // Obtener el tamaño de la cola
    public int size() {
        return lista.size();
    }

    // Imprimir la cola
    public void imprimir() {
        lista.print();
    }
}