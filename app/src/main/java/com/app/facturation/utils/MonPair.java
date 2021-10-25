package com.app.facturation.utils;

public class MonPair<F, S> {

    private F first = null;
    private S second = null;

    public MonPair() {
    }

    public MonPair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public S getSecond() {
        return second;
    }

    public void setSecond(S second) {
        this.second = second;
    }
}
