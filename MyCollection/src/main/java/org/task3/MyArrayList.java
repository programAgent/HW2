package org.task3;
import java.util.Comparator;

public class MyArrayList <E> implements MyList <E>{

    private int size;
    private E[] data;
    private static final int DEFAULT_SIZE = 10;

    // Конструктор с перегрузкой метода
    public MyArrayList() {
        this.data = (E[]) new Object[DEFAULT_SIZE];
        this.size = 0;
    }

    public MyArrayList(int size) {
        this.data = (E[]) new Object[size];
        this.size = 0;
    }

    public MyArrayList(E [] data) {
        if (data == null) throw new IllegalArgumentException("Массив не может быть равен null");
        this.data = (E[]) new Object [data.length];
        System.arraycopy(data,0, this.data, 0, data.length);
        this.size = data.length;
    }

    //Внутренний метод создания увеличенного массива (х1,5)
    private void grow() {
        int newCapacity = data.length == 0 ? DEFAULT_SIZE : (int) (data.length * 1.5);
        E[] newData = (E[]) new Object[(int) (newCapacity)];
        System.arraycopy(data, 0, newData, 0, data.length);
        this.data = newData;
    }

    // Метод проверки длины массива и его увеличение в 1,5 раза
    private void checkingSize() {
        if (size == data.length) {
            grow();
        }
    }

    // внутренний метод, выбрасывающий ошибку при неверном значении индекса
    private void checkPositionIndex(int index) {
        if (index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException("неверный индекс");
    }

    // Метод проверки наличия свободного места по индексу (освободение индекса)
    private void makeFreeIndex(int index) {
        checkingSize();
        System.arraycopy(data, index, data, index + 1, size - index);
    }


    @Override
    public void add(E element) {
        checkingSize();
        data[size++] = element;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) throw new ArrayIndexOutOfBoundsException("неверный индекс");
        makeFreeIndex(index);
        data[index] = element;
        size++;
    }

    @Override
    public E get(int index) {
        checkPositionIndex(index);
        return data[index];
    }

    @Override
    public void remove(int index) {
        checkPositionIndex(index);
        System.arraycopy(data, index+1, data, index, size - 1 - index);
        data[size--] = null;
    }

    @Override
    public void clear() {
        this.data = (E[]) new Object[DEFAULT_SIZE];
        size = 0;
    }

    @Override
    public void sort() {
        mergeSort(data, null);
    }

    @Override
    public void sort(Comparator <? super E> comparator) {
        mergeSort (data, comparator);
    }

    // реализация алгоритма merge-sort
    private void mergeSort (E[] array, Comparator <? super E> comparator) {
        if ((array == null) || (size <= 1)) {
            return;
        }
        mergeSort(array, 0, size-1, comparator);
    }

    //рекурсия методов разделения и объединения
    private void mergeSort (E[] array, int left, int right, Comparator <? super E> comparator) {
        if (left<right) {
            int mid = left + (right-left) / 2;
            mergeSort(array, left, mid, comparator);
            mergeSort(array, mid+1, right, comparator);
            merge(array, left, mid, right, comparator);
        }
    }

    // метод объединения, с использованием временных массивов
    private void merge(E[] array, int left, int mid, int right, Comparator<? super E> comparator) {
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        E[] leftArr = (E[]) new Object[leftSize];
        E[] rightArr = (E[]) new Object[rightSize];

        System.arraycopy(array, left, leftArr, 0, leftSize);
        System.arraycopy(array, mid + 1, rightArr, 0, rightSize);

        int i = 0, j = 0, k = left;

        while (i < leftSize && j < rightSize) {
            if (compare(leftArr[i], rightArr[j], comparator) <= 0) {
                array[k++] = leftArr[i++];
            } else {
                array[k++] = rightArr[j++];
            }
        }

        while (i < leftSize) {
            array[k++] = leftArr[i++];
        }

        while (j < rightSize) {
            array[k++] = rightArr[j++];
        }
    }

    //метод сравнения в зависимости от переданного Comparator
    private int compare(E a, E b, Comparator<? super E> comparator) {
        if (comparator != null) {
            return comparator.compare(a, b);
        } else if (a instanceof Comparable) {
            return ((Comparable<? super E>) a).compareTo(b);
        } else {
            throw new IllegalArgumentException("Элементы списка не реализуют Comparable и Comparator");
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            System.out.print( get(i) + "; ");
        }
        return "]";
    }
}
