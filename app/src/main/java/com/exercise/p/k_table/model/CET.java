package com.exercise.p.k_table.model;

import java.io.Serializable;

/**
 * Created by p on 2017/2/25.
 */

public class CET  implements Serializable{
    int listen;
    int read;
    int write;
    int all;
    public CET(int listen,int read,int write, int all){
        this.listen = listen;
        this.read = read;
        this.write = write;
        this.all = all;
    }

    public int getAll() {
        return all;
    }

    public int getListen() {
        return listen;
    }

    public int getRead() {
        return read;
    }

    public int getWrite() {
        return write;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public void setListen(int listen) {
        this.listen = listen;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public void setWrite(int write) {
        this.write = write;
    }

    @Override
    public String toString() {
        return "成绩" + listen + " " + read + " " + write + " " + all;
    }
}
