package com.example.admin.menu_online.formula;

import java.text.DecimalFormat;

/**
 * Created by Yep on 6/6/2017.
 */

public class formular {
    private int views, like;

    public int getView() {
        return views;
    }

    public void setView(int views) {
        this.views = views;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public formular() {
    }

    public formular(int view, int like) {
        this.views = view;
        this.like = like;
    }

    private float scoreCal() {
        float score = 0;
        float score1 = (this.views + this.like) / 2;
        if (score1 > 0 && score1 <= 100) {
            score = 1;
        } else if (score1 > 100 && score1 <= 200) {
            score = 2;
        } else if (score1 > 200 && score1 <= 300) {
            score = 3;
        } else if (score1 > 300 && score1 <= 400) {
            score = 4;
        } else if (score1 > 400 && score1 <= 500) {
            score = 5;
        } else if (score1 > 500 && score1 <= 600) {
            score = 6;
        } else if (score1 > 600 && score1 <= 700) {
            score = 7;
        } else if (score1 > 700 && score1 <= 800) {
            score = 8;
        } else if (score1 > 800 && score1 <= 900) {
            score = 9;
        } else if (score1 > 800 && score1 <= 1000) {
            score = 10;
        }

        if (score < 10) {
            score = score + divNum(this.views) + divNum(this.like);
            if (score > 10)
                score = 10;
        }

        return score;
    }

    public String score() {
        return new DecimalFormat("#.#").format(scoreCal());
    }

    private float divNum(int viewlike) {
        int div = 1;
        if (viewlike > 0 && viewlike <= 10)
            div = 10;
        else if (viewlike > 10 && viewlike <= 100)
            div = 100;
        else if (viewlike > 100 && viewlike <= 1000)
            div = 1000;
        return (float) viewlike / div;
    }
}
