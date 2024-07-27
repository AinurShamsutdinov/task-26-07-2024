package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int AMOUNT_COORDINATES = 2;
    private static final int FIRST_COORDINATE = 0;
    private static final int SECOND_COORDINATE = 1;
    private static final String CONTINUE_YES = "yes";
    private static int X;
    private static int Y;
    public static void main(String[] args) throws IOException {

        System.out.println("Enter size:");
        int[] size = getSize();
        X = size[FIRST_COORDINATE];
        Y = size[SECOND_COORDINATE];
        Element element = new Element(X, Y);
        System.out.println("Generated elements:");
        System.out.println(element);

        List<Integer> elements = getValues(element);
        System.out.println("Final elements: \n" + elements);
    }

    private static int[] getSize() throws IOException {
        int[] size = null;
        boolean readSize = true;
        // Reading data using readLine
        while (readSize) {
            System.out.println("Enter positive integers bigger than ZERO for X and Y divided by space: (i.e. 48 32)");
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

    private static int[] getCoordinates() throws IOException, OutOfLines {
        int[] coord = null;
        boolean readSize = true;
        // Reading data using readLine
        while (readSize) {
            System.out.println("Enter positive integers for X and Y divided by space: (i.e. 32 48)");
            try {
                coord = getInput();
                readSize = !validateCoordinates(coord);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return coord;
    }

    private static boolean validateCoordinates(int[] input) throws OutOfLines {
        if (input.length != AMOUNT_COORDINATES) {
            throw new IllegalArgumentException("Coordinates should be 2 positive integers");
        }
        for (int i = 0; i < input.length; i++) {
            if (input[i] < 0) {
                throw new IllegalArgumentException("Negative coordinate: " + input[i]);
            }
        }

        int x = input[FIRST_COORDINATE];
        int y = input[SECOND_COORDINATE];
        if (x >= X || y >= Y) {
            String message = String.format("The coordinates should be less than {X: %d, Y: %d}", X, Y);
            throw new OutOfLines(message);
        } else if (x < 0 || y < 0) {
            throw new OutOfLines("The coordinates should be bigger than 0");
        }
        return true;
    }

    private static int[] getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int[] coords = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            coords[i] = Integer.parseInt(input[i]);
        }
        return coords;
    }

    public static List<Integer> getValues(Element element) throws IOException {
        List<Integer> elements = null;
        boolean contExec = true;
        while (contExec) {
            try {
                int[] coords = getCoordinates();
                validateCoordinates(coords);
                elements = getValuesFromArr(element, coords[FIRST_COORDINATE], coords[SECOND_COORDINATE]);
                System.out.println(elements);
            } catch (OutOfLines e) {
                System.out.println(e.getMessage());
            }
            contExec = continueExec();
        }

        return elements;
    }

    private static List<Integer> getValuesFromArr(Element element, int x, int y) {
        List<Integer> points = new ArrayList<>();
        int lowX = x - 1 > 0 ? x - 1 : 0;
        int lowY = y - 1 > 0 ? y - 1 : 0;
        int highX = x + 1 < element.getLenghtX() ? x + 2 : element.getLenghtX();
        int highY = y + 1 < element.getLenghtY() ? y + 2 : element.getLenghtY();
        for (int i = lowX; i < highX; i++) {
            for (int j = lowY; j < highY; j++) {
                points.add(element.getValue(i, j));
            }
        }
        return points;
    }

    private static boolean continueExec() throws IllegalArgumentException, IOException {
        System.out.println("Do you want to continue? (yes to continue)");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        return input.equals(CONTINUE_YES);
    }
}
