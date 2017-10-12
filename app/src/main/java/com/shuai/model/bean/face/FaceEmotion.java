package com.shuai.model.bean.face;

/**
 * Created by jiangyingjun on 2017/10/12.
 *
 * 表情评分值
 */

public class FaceEmotion {


    private long anger;

    private long disgust;

    private long fear;

    private long happines;

    private long neutral;

    private long sadness;

    private long surprise;


    public long getAnger() {
        return anger;
    }

    public void setAnger(long anger) {
        this.anger = anger;
    }

    public long getDisgust() {
        return disgust;
    }

    public void setDisgust(long disgust) {
        this.disgust = disgust;
    }

    public long getFear() {
        return fear;
    }

    public void setFear(long fear) {
        this.fear = fear;
    }

    public long getHappines() {
        return happines;
    }

    public void setHappines(long happines) {
        this.happines = happines;
    }

    public long getNeutral() {
        return neutral;
    }

    public void setNeutral(long neutral) {
        this.neutral = neutral;
    }

    public long getSadness() {
        return sadness;
    }

    public void setSadness(long sadness) {
        this.sadness = sadness;
    }

    public long getSurprise() {
        return surprise;
    }

    public void setSurprise(long surprise) {
        this.surprise = surprise;
    }
}
