package tests.lineales;

import java.util.HashMap;

import lineales.estaticas.*;

public class TestingLinPer {
    public static void main(String[] args) {
        Cola c1=new Cola();
        Cola c2=new Cola();
        Cola f3;

        c1.poner(1);
        c1.poner(2);
        c1.poner(3);

        System.out.println(c1.toString());
        
    }
}
