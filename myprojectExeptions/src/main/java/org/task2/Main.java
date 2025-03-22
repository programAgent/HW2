package main.java.org.task2;

public class Main {
    public static void callMyException() throws MyException {
        System.out.println("Вызываем ДЗ№2 исключение");
        throw new MyException();
    }

    public static void main(String[] args) {
        try {
            callMyException();
        } catch (MyException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Выполнение блока finally");
        }

        System.out.println("Исключение обработано");
    }
}