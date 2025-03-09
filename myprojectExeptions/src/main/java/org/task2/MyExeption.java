package org.task2;

public class MyExeption extends Exception {
    public MyExeption () {
        super ("Выбросилось исключение ДЗ№2");
    }
    public MyExeption (String massage) {
        super(massage);
    }
}
