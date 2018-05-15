package com.example.zeeshanjaved.fyp_app;

/**
 * Created by Zeeshan Javed on 1/11/2018.
 */

public class UserAnswerModel {

    private int id;
    private String  useranswer;

    public String getUseranswer() {
        return useranswer;
    }

    public void setUseranswer(String useranswer) {
        this.useranswer = useranswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
