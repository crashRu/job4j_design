package ru.job4j.assertj;

import java.util.Arrays;

public class Box {
    private static final String UNKNOWN = "Unknown object";
    private int vertex;
    private final int edge;
    private String type = "";

    public Box(int vertex, int edge) {
        this.vertex = vertex;
        this.edge = edge;
        init();
    }

    private void init() {
        type = switch (vertex) {
            case 0 -> "Sphere";
            case 4 -> "Tetrahedron";
            case 8 -> "Cube";
            default -> UNKNOWN;
        };
        if (UNKNOWN.equals(type)) {
            vertex = -1;
        }
        if (edge <= 0) {
            vertex = -1;
            type = UNKNOWN;
        }
    }

    public String whatsThis() {
        return this.type;
    }

    public int getNumberOfVertices() {
        return this.vertex;
    }

    public boolean isExist() {
        return this.vertex != -1;
    }

    public void text(int n) {
        int[] array = new int[n];
        for (int i = 0; n > 0; n--) {
            array[i] = n;
            i++;
        }
        Arrays.stream(array).forEach(System.out::println);
    }

    public double getArea() {
        double a = edge;
        return switch (vertex) {
            case 0 -> 4 * Math.PI * (a * a);
            case 4 -> Math.sqrt(3) * (a * a);
            case 8 -> 6 * (a * a);
            default -> 0;
        };
    }
}