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

    public int getLenghtX() {
        return this.randomNums.length;
    }

    public int getLenghtY() {
        return this.randomNums[0].length;
    }

    public int getValue(int x, int y) {
        return this.randomNums[x][y];
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
