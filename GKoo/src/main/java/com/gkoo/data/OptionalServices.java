package com.gkoo.data;

public class OptionalServices {
    private boolean mergingBox;
    private int numberMerging;
    private final double MERGING_FEE = 5000;

    public boolean isMergingBox() {
        return mergingBox;
    }

    public void setMergingBox(boolean mergingBox) {
        this.mergingBox = mergingBox;
    }

    public int getNumberMerging() {
        return numberMerging;
    }

    public void setNumberMerging(int numberMerging) {
        this.numberMerging = numberMerging;
    }
}
