package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    private static final String REGEX_POZITIVE_SIZE = "^\\d*$";
    private static final int AMOUNT_COORDINATES = 2;
    private static final int FIRST_COORDINATE = 0;
    private static final int SECOND_COORDINATE = 1;
    private static final String CONTINUE_YES = "yes";
    public static void main(String[] args) throws IOException {

        System.out.println("Enter size:");
        int[] size = getSize();
        
        Element element = new Element(size[FIRST_COORDINATE], size[SECOND_COORDINATE]);
        System.out.println("Generated elements:");
        System.out.println(element);

        boolean contExec = true;
        List<Integer> elements;
        while (contExec) {
            try {
                System.out.println("Enter coordinates for elements:");
                int[] coords = getCoordinates();
                elements = element.getValues(coords[FIRST_COORDINATE], coords[SECOND_COORDINATE]);
                System.out.println(elements);
            } catch (OutOfLines e) {
                System.out.println(e.getMessage());
            }
            contExec = continueExec();
        }
    }

    private static int[] getSize() throws IOException {
        int[] size = null;
        boolean readSize = true;
        // Reading data using readLine
        while (readSize) {
            System.out.println("Enter positive integers bigger than ZERO for X and Y divided by space:");
            try {
                size = getInput();
                readSize = !validateSize(size);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return size;
    }

    private static boolean validateSize(int[] size) {
        if (size.length != AMOUNT_COORDINATES) {
            throw new IllegalArgumentException("Size should be 2 positive integers bigger than ZERO");
        }
        for (int i = 0; i < size.length; i++) {
            if (size[i] <= 0) {
                throw new IllegalArgumentException("Negative or ZERO size: " + size[i]);
            }
        }
        return true;
    }

    private static int[] getCoordinates() throws IOException {
        int[] coord = null;
        boolean readSize = true;
        // Reading data using readLine
        while (readSize) {
            System.out.println("Enter positive integers bigger for X and Y divided by space:");
            try {
                coord = getInput();
                readSize = !validateCoordinates(coord);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return coord;
    }

    private static boolean validateCoordinates(int[] size) {
        if (size.length != AMOUNT_COORDINATES) {
            throw new IllegalArgumentException("Coordinates should be 2 positive integers");
        }
        for (int i = 0; i < size.length; i++) {
            if (size[i] < 0) {
                throw new IllegalArgumentException("Negative coordinate: " + size[i]);
            }
        }
        return true;
    }

    private static int[] getInput() throws IllegalArgumentException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int[] coords = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            coords[i] = Integer.parseInt(input[i]);
        }
        return coords;
    }

    private static boolean continueExec() throws IllegalArgumentException, IOException {
        System.out.println("Do you want to continue? (yes to continue)");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        return input.equals(CONTINUE_YES);
    }
}
