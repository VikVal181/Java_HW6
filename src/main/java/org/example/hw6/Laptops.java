package org.example.hw6;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Laptops {
    List<Laptop> laptops = new ArrayList<>();
    public Laptops() {
        Laptop laptop1 = new Laptop("ASUS", "black", "Intel", 2.4, 8, 256, 56444.0);
        Laptop laptop2 = new Laptop("ASUS", "black", "AMD", 2.1, 16, 256, 50222.0);
        Laptop laptop3 = new Laptop("ASUS", "gray", "Intel", 2.4, 16, 512, 67000.0);
        Laptop laptop4 = new Laptop("Honor", "gray", "Intel", 1.9, 8, 256, 45999.0);
        Laptop laptop5 = new Laptop("Honor", "blue", "AMD", 2.4, 16, 512, 70000.0);
        Laptop laptop6 = new Laptop("ThinkPad", "black", "Intel", 2.8, 16, 1024, 120000.0);
        Laptop laptop7 = new Laptop("MSI", "black", "Intel", 2.7, 16, 512, 75000.0);
        Laptop laptop8 = new Laptop("HP", "black", "Intel", 1.3, 8, 256, 48000.0);

        laptops.add(laptop1);
        laptops.add(laptop2);
        laptops.add(laptop3);
        laptops.add(laptop4);
        laptops.add(laptop5);
        laptops.add(laptop6);
        laptops.add(laptop7);
        laptops.add(laptop8);
    }

    public void printFilterLaptops(Map<String, String> param) {
        Laptop laptopFilterParam = new Laptop();
        Double minPrice = 0.0, maxPrice = 9999999.9;
        for (Map.Entry<String, String> item : param.entrySet()) {
            switch (item.getKey()) {
                case "Название":
                    laptopFilterParam.setName(item.getValue().trim());
                    break;
                case "Цвет":
                    laptopFilterParam.setColor(item.getValue().trim());
                    break;
                case "Процессор":
                    laptopFilterParam.setCpu_name(item.getValue().trim());
                    break;
                case "Частота":
                    laptopFilterParam.setCpu_frequency(convertStrToDb(item.getValue().trim()));
                    break;
                case "ОЗУ":
                    laptopFilterParam.setRam_size(convertStrToInt(item.getValue().trim()));
                    break;
                case "Диск":
                    laptopFilterParam.setHdd_size(convertStrToInt(item.getValue().trim()));
                    break;
                case "Цена":
                    String[] split = item.getValue().trim().split(" ");
                    if (split.length == 2) {
                        minPrice = convertStrToDb(split[0].trim());
                        maxPrice = convertStrToDb(split[1].trim());
                    }
                    else if (split.length == 1) {
                        maxPrice = convertStrToDb(split[0].trim());
                    }
                    else {
                        System.out.println("Не корректно задан диапазон цен");
                        return;
                    }
                    break;
            }
        }

        List<Laptop> filterList = new ArrayList<>();
//        filterList.add(laptopFilterParam);


        for (Laptop i : laptops) {
            if (laptopFilterParam.isEquals(i)) {
                if (minPrice != 0 || maxPrice != 9999999.9) {
                    if (i.getPrice() >= minPrice && i.getPrice() <= maxPrice) {
                        filterList.add(i);
                    }
                }
                else {
                    filterList.add(i);
                }
            }
        }

        if (filterList.size() != 0) {
            printAny(filterList);
        }
        else {
            System.out.println("По заданным параметрам ничего не найдено!");
        }
    }

    private Double convertStrToDb(String str) {
        double d = 0;
        try {
            d = Double.parseDouble(str.trim());
        }
        catch (NumberFormatException nfe) {
            System.out.println("Ошибка ввода" + nfe.getMessage());
        }
        return d;
    }

    private Integer convertStrToInt(String str) {
        int i = 0;
        try {
            i = Integer.parseInt(str.trim());
        }
        catch (NumberFormatException nfe) {
            System.out.println("Ошибка ввода" + nfe.getMessage());
        }

        return i;
    }
    public void printAll() {
        printAny(laptops);
    }
    private void printAny(List<Laptop> list) {
        System.out.printf("%8s %5s %5s %5s %5s %5s %8s\n", "Название", "Цвет", "CPU", "МГц", "ОЗУ", "HDD", "Цена");
        for (Laptop l : list) {
            l.print();
//            System.out.printf("%-8s %-5s %-5s %5s %5s %5s %8s\n", l.getName(), l.getColor(), l.getCpu_name(),
//                    l.getCpu_frequency(), l.getRam_size(), l.getHdd_size(), l.getPrice());
        }
    }
}
