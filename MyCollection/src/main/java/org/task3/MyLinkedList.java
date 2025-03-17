package org.task3;

import java.util.Comparator;

public class MyLinkedList <E> implements MyList <E> {

// вложенный класс Нода
    private static class Node<E> {
        private E item;
        private Node next;
        private Node prev;

        Node(E item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

        public E getItem() {
            return item;
        }

        public void setItem(E item) {
            this.item = item;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    private int size;
    Node<E> first;
    Node<E> last;

    public MyLinkedList() {
    }

    // внутренний метод, выбрасывающий ошибку при неверном значении индекса
    private void checkPositionIndex(int index) {
        if (index < 0 || index >= size) throw new ArrayIndexOutOfBoundsException("неверный индекс");
    }

    // внутренний метод по поиску ноды по индексу
    private Node <E> getNode (int index) {

        if (index == 0) return first;

        if (index == size - 1) return last;

        Node<E> node;

        if (index < size / 2) {
            node = first;

            for (int i = 0; i < index; i++) {
                node = node.getNext();
            }

        } else {
            node = last;

            for (int i = size - 1; i > index; i--) {
                node = node.getPrev();
            }
        }
        return node;
    }

    @Override
    public void add (E element) {
        Node<E> newNode = new Node<>(element, null, this.last);

        if (this.first == null) {
            this.first = newNode;
        } else {
            this.last.setNext(newNode);
        }

        this.last = newNode;
        size++;
    }

    @Override
    public void add (int index, E element) {
        if (index < 0 || index > size) throw new ArrayIndexOutOfBoundsException("неверный индекс");

        if (index == 0) {
            Node<E> newNode = new Node<>(element, this.first, null);

            if (this.first != null) {
                this.first.setPrev(newNode);
            } else {
                this.last = newNode;
            }

            this.first = newNode;

        } else if (index == size) {
            Node<E> newNode = new Node<>(element, null, this.last);

            if (this.last != null) {
                this.last.setNext(newNode);
            } else {
                this.first = newNode;
            }

            this.last = newNode;

        } else {
            Node<E> oldIndexNode = getNode(index);
            Node<E> oldIndexPrevNode = oldIndexNode.getPrev();
            Node<E> newNode = new Node<>(element, oldIndexNode, oldIndexPrevNode);
            oldIndexPrevNode.setNext(newNode);
            oldIndexNode.setPrev(newNode);
        }
        size++;
    }

    @Override
    public E get(int index) {
        checkPositionIndex(index);
        Node <E> node = getNode(index);
            return node.getItem();
    }

    @Override
    public void remove(int index) {
        checkPositionIndex(index);

        if (index == 0) {
            Node<E> newFirst = this.first.getNext();

            if (newFirst != null) {
                newFirst.setPrev(null);
            } else {
                this.last = null;
            }

            this.first = newFirst;

        } else if (index == size - 1) {
            Node<E> newLast = this.last.getPrev();

            if (newLast != null) {
                newLast.setNext(null);
            } else {
                this.first = null;
            }

            this.last = newLast;

        } else {
            Node <E> removedNode = getNode(index);
            Node <E> prevNode = removedNode.getPrev();
            Node <E> nextNode = removedNode.getNext();
            prevNode.setNext(nextNode);
            nextNode.setPrev(prevNode);
        }
        size --;
    }

    @Override
    public void clear() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public void sort () {
        this.sort(null);
    }

    @Override
    public void sort(Comparator<? super E> comparator) {
        Object [] array = new Object [size];
        Node <E> current = first;

        for (int i = 0; i< size; i++) {
            array [i] = current.getItem();
            current = current.getNext();
        }

        quickSort(array, comparator);

        current = first;

        for (Object o : array) {
            current.setItem((E) o);
            current = current.getNext();
        }
    }

    // Алгоритм быстрой сортировки с использованием стека для хранения адресов границ массива
    private void quickSort(Object [] array, Comparator<? super E> comparator) {
        int[] stack = new int[array.length];
        int top = -1;
        stack[++top] = 0;
        stack[++top] = array.length - 1;

        while (top>=0) {
            int high = stack [top--];
            int low = stack [top--];
            int pivotIndex = partition(array, low, high, comparator);

            if (pivotIndex - 1 > low) {
                stack[++top] = low;
                stack[++top] = pivotIndex - 1;
            }

            if (pivotIndex + 1 < high) {
                stack[++top] = pivotIndex + 1;
                stack[++top] = high;
            }
        }
    }

    // определение местоположения разделителя
    private int partition(Object[] array, int low, int high, Comparator<? super E> comparator) {

        int pivotIndex = low + (int) (Math.random() * (high - low + 1));
        swap(array, pivotIndex, high);
        Object pivot =  array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(array[j], pivot, comparator) <= 0) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);

        return i + 1;
    }

    //метод сравнения в зависимости от переданного Comparator
    private int compare(Object a, Object b, Comparator<? super E> comparator) {
        if (comparator != null) {
            return comparator.compare((E) a, (E) b);
        } else if (a instanceof Comparable) {
            return ((Comparable<E>) a).compareTo((E) b);
        } else {
            throw new IllegalArgumentException("Элементы списка не реализуют Comparable и Comparator");
        }
    }

    // внутренний метод, меняющий элементы местами
    private void swap(Object[] array, int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = first;

        while (current != null) {
            sb.append(current.getItem());
            if (current.getNext() != null) sb.append(", ");
            current = current.getNext();
        }
        return sb.append("]").toString();
    }
}
