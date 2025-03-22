package main.java.org.task2;

public class MyException extends Exception {
    public MyException () {
        super("Выбросилось исключение ДЗ№2");
    }

    public MyException (String massage) {
        super(massage);
    }
}
