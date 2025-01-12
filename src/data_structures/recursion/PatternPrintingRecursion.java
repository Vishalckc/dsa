package data_structures.recursion;

import java.util.stream.IntStream;

public class PatternPrintingRecursion {
    public static void main(String[] args) {
        //****
        //***
        //**
        //*
        //Print in descending pattern
        pattern(4);
    }

    private static void pattern(int i) {
        if (i == 0) return;
        IntStream.range(0, i)
                .mapToObj(k -> "*")
                .forEach(System.out::print);
        System.out.println();
        pattern(i - 1);
    }
}
