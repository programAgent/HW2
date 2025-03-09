package org.task2;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void callMyExeption() throws MyExeption {
        System.out.println("Вызываем ДЗ№2 исключение");
        throw new MyExeption();

    }
    public static void main(String[] args) {

        try {
            callMyExeption();
        } catch (MyExeption e) {
            System.out.println(e.getMessage());

        } finally {
            System.out.println("Выполнение блока finally");
        }
        System.out.println("Исключение обработано");
    }
}