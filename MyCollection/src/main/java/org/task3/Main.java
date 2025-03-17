package org.task3;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {


        MyList<Integer> list = new MyArrayList<>();
        //MyList<Integer> list = new MyLinkedList<>();

        // Тест на добавление большого количества элементов
        System.out.println("Тест на добавление большого количества элементов...");
        int elementsCount = 100_000; // 100000 элементов
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < elementsCount; i++) {
            list.add(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Добавлено " + elementsCount + " элементов за " + (endTime - startTime) + " мс.");
        System.out.println("Размер списка: " + list.size());

        // Проверка корректности добавления элементов
        System.out.println("Проверка корректности добавления элементов...");
        boolean allElementsCorrect = true;
        for (int i = 0; i < elementsCount; i++) {
            if (list.get(i) != i) {
                allElementsCorrect = false;
                break;
            }
        }
        System.out.println("Все элементы корректны: " + allElementsCorrect);

        // Тест на добавление элементов в середину списка
        System.out.println("Тест на добавление элементов в середину списка...");
        int midIndex = elementsCount / 2;
        list.add(midIndex, -1);
        System.out.println("Элемент в середине списка: " + list.get(midIndex));
        System.out.println("Размер списка после добавления: " + list.size());

        // Тест на удаление элементов из середины списка
        System.out.println("Тест на удаление элементов из середины списка...");
        list.remove(midIndex);
        System.out.println("Элемент в середине списка после удаления: " + list.get(midIndex));
        System.out.println("Размер списка после удаления: " + list.size());

        // Тест на сортировку списка (естественный порядок)
        System.out.println("Тест на сортировку списка (естественный порядок)...");
        startTime = System.currentTimeMillis();
        list.sort();
        endTime = System.currentTimeMillis();
        System.out.println("Сортировка завершена за " + (endTime - startTime) + " мс.");

        // Проверка корректности сортировки
        boolean isSorted = true;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                isSorted = false;
                break;
            }
        }
        System.out.println("Список отсортирован: " + isSorted);

        // Тест на сортировку списка (обратный порядок)
        System.out.println("Тест на сортировку списка (обратный порядок)...");
        startTime = System.currentTimeMillis();
        list.sort(Comparator.reverseOrder());
        endTime = System.currentTimeMillis();
        System.out.println("Сортировка завершена за " + (endTime - startTime) + " мс.");

        // Проверка корректности сортировки
        isSorted = true;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) < list.get(i + 1)) {
                isSorted = false;
                break;
            }
        }
        System.out.println("Список отсортирован в обратном порядке: " + isSorted);

        // Тест на очистку списка
        System.out.println("Тест на очистку списка...");
        list.clear();
        System.out.println("Размер списка после очистки: " + list.size());


        // Тест на добавление элемента по неверному индексу
        System.out.println("Тест на добавление элемента по неверному индексу...");
        try {
            list.add(1, 1);
            System.out.println("Ошибка: добавление по неверному индексу прошло без исключения.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Исключение при добавлении по неверному индексу: " + e.getMessage());
        }

        // Тест на получение элемента по неверному индексу
        System.out.println("Тест на получение элемента по неверному индексу...");
        try {
            list.get(0);
            System.out.println("Ошибка: получение по неверному индексу прошло без исключения.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Исключение при получении по неверному индексу: " + e.getMessage());
        }

        // Тест на удаление элемента по неверному индексу
        System.out.println("Тест на удаление элемента по неверному индексу...");
        try {
            list.remove(0);
            System.out.println("Ошибка: удаление по неверному индексу прошло без исключения.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Исключение при удалении по неверному индексу: " + e.getMessage());
        }

        System.out.println("Тест завершен.");
    }
}