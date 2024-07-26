package com.company;

import java.util.ArrayList;
import java.util.List;

public class Element {
    private int [][] randomNums;
    private int x;
    private int y;

    public Element(int x, int y) {
        this.x = x;
        this.y = y;

        initRandomNums(x, y);
    }

    public List<Integer> getValues(int x, int y) throws OutOfLines {
        validate(x, y);
        return getValuesFromArr(x, y);
    }

    private List<Integer> getValuesFromArr(int x, int y) {
        List<Integer> points = new ArrayList<>();
        int lowX = x - 1 > 0 ? x - 1 : 0;
        int lowY = y - 1 > 0 ? y - 1 : 0;
        int highX = x + 1 < randomNums.length ? x + 2 : randomNums.length;
        int highY = y + 1 < randomNums[0].length ? y + 2 : randomNums[0].length;
        for (int i = lowX; i < highX; i++) {
            for (int j = lowY; j < highY; j++) {
                points.add(randomNums[i][j]);
            }
        }
        return points;
    }

    private void validate(int x, int y) throws OutOfLines {
        if(x >= this.randomNums.length || y >= this.randomNums[0].length) {
            String message = String.format("The coordinates should be less than {X: %d, Y: %d}",
                    this.randomNums.length, this.randomNums[0].length);
            throw new OutOfLines(message);
        } else if(x < 0 || y < 0) {
            String message = "The coordinates should be bigger than 0";
            throw new OutOfLines(message);
        }
    }

    private void initRandomNums(int x, int y) {
        this.randomNums = new int[x][y];
        for(int i = 0; i < x; i++) {
            for(int j = 0; j < y; j++) {
                this.randomNums[i][j] = (int) (Math.random() * 100);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < randomNums.length; i++) {
            for(int j = 0; j < randomNums[i].length; j++) {
                sb.append(randomNums[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
