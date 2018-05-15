package com.example.zeeshanjaved.fyp_app;

/**
 * Created by Zeeshan Javed on 1/14/2018.
 */

public class Joiner {

    String corrctanswer;
    String actualanswer;

    public Joiner(String corrctanswer, String actualanswer) {
        this.corrctanswer = corrctanswer;
        this.actualanswer = actualanswer;
    }

    public String getCorrctanswer() {
        return corrctanswer;
    }

    public void setCorrctanswer(String corrctanswer) {
        this.corrctanswer = corrctanswer;
    }

    public String getActualanswer() {
        return actualanswer;
    }

    public void setActualanswer(String actualanswer) {
        this.actualanswer = actualanswer;
    }
}
