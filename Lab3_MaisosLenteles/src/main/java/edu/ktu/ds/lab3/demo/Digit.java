package edu.ktu.ds.lab3.demo;

import edu.ktu.ds.lab3.utils.Parsable;

public final class Digit implements Parsable<Integer> {
    public Integer data;
    public Digit(Integer d) {
        data = d;
    }
    public Digit() {}

    public Digit(String s) {
        data = Integer.parseInt(s);
    }

    @Override
    public void parse(String dataString) {
        data = Integer.parseInt(dataString);
    }

    @Override
    public String toString() {
        return "" + data;
    }
}
