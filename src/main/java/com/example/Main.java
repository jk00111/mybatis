package com.example;

public class Main {

    public static void main(String[] args) {
        String regex = "^\\d{3}[-.\\s]?\\d{3,4}[-.\\s]?\\d{4}$";
        String tel1 = "010-1231-4342";
        String tel2 = "011-231-4342";
        String tel3 = "013-1231-4342";
        String tel4 = "018-131-4342";
        String tel5 = "018412412412";

        System.out.println(tel1.matches(regex));
        System.out.println(tel2.matches(regex));
        System.out.println(tel3.matches(regex));
        System.out.println(tel4.matches(regex));
        System.out.println(tel5.matches(regex));
    }
}
