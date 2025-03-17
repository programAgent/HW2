package org.task3;

import java.util.Comparator;

public interface MyList<E>  {

    // Добавление элемента в конец списка
    void add(E element);

    // Добавить элемент по индексу
    void add(int index, E element);

    // Получить элемент по индексу
    E get (int index);

    //удалить элемент по индексу
    void remove (int index);

    // очистить всю коллекцию
    void clear ();

    //сортировка коллекции
    void sort (Comparator<? super E> comparator);
    void sort ();

    //вывести колличество элементов
    int size ();
}
