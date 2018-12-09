package com.example.crazynet.simulationtask;

/**
 * Created by medhat on 09/12/2018.
 */

public class customer {

    int come ;
    int period ;
    int start ;
    int end ;
    int wait ;

    public customer(int come, int period) {
        this.come = come;
        this.period = period;
    }

    public customer() {
    }

    public int getCome() {
        return come;
    }

    public void setCome(int come) {
        this.come = come;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getWait() {
        return wait;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }
}
