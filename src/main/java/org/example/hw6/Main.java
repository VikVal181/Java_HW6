package org.example.hw6;
//• Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
//        • Создать множество ноутбуков.
//        • Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и выведет ноутбуки, отвечающие фильтру. Критерии фильтрации можно хранить в Map. Например:
//        “Введите цифру, соответствующую необходимому критерию:
//        1 - ОЗУ
//        2 - Объем ЖД
//        3 - Операционная система
//        4 - Цвет …
//        • Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно также в Map.
//        • Отфильтровать ноутбуки их первоначального множества и вывести проходящие по условиям.

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Laptops list = new Laptops();
        list.printAll();
        laptopFilter(list);
    }

    public static void laptopFilter(Laptops list) {
        String comand = new String();
        Map<Integer, String> search = new HashMap<>();
        search.put(1, "Название");
        search.put(2, "Цвет");
        search.put(3, "Процессор");
        search.put(4, "Частота");
        search.put(5, "ОЗУ");
        search.put(6, "Диск");
        search.put(7, "Цена");
        while (!comand.equals("q")) {
            Scanner input = new Scanner(System.in);
            System.out.println("Введите один или несколько параметров по которым необходимо отфильтровать ноутбуки");

            for (Map.Entry<Integer, String> item : search.entrySet()) {
                System.out.println(item.getKey() + " - " + item.getValue());
            }

            String query = input.nextLine();

            if (query.length() == 0) {
                System.out.println("Не верный запрос");
            } else {
                Map<String, String> filter_param = parser(query, search);
                if (filter_param.size() > 0) {
                    list.printFilterLaptops(filter_param);
                }
            }

            System.out.println("Для нового поиска нажмите \"Entre\", для выхода введите - Q");
            comand = input.nextLine().toLowerCase();
        }
    }

    public static Map<String, String> parser(String query, Map<Integer, String> search) {
        Map<String, String> filter_param = new HashMap<>();
        Scanner param = new Scanner(System.in);
        for (String s : query.split(" ")) {
            try {
                int i = Integer.parseInt(s.trim());
                if (search.get(i) == null) {
                    System.out.println("Введен не верный запрос");
                    filter_param.clear();
                    return filter_param;
                }

                if (i == 7) System.out.print("Введите максимальную цену или диапозон цен через пробел:");
                else System.out.print("Введите значение поля " + search.get(i) + ": ");

                String value = param.nextLine();
                filter_param.put(search.get(i), value);
            }
            catch (NumberFormatException nfe) {
                System.out.println("Не верный ввод: " + nfe.getMessage());
            }
        }
        return filter_param;
    }
}