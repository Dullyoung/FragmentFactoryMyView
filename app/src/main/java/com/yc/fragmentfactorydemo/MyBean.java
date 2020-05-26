package com.yc.fragmentfactorydemo;

import android.util.Log;

public class MyBean {
    private int a;
    private int b;
    private int c;
    private String name;

    public String getName() {
        return name;
    }

    public MyBean(){

}

    private MyBean(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
        Log.i("aaaa", "MyBean: a"+this.a+"b"+this.b+"c"+this.c);
    }

    public int getA() {
        return a;
    }

    private void setA(int a) {
        this.a = a;
    }

    private int getB() {
        return b;
    }

    private void setB(int b) {
        this.b = b;
    }

    private int getC() {
        return c;
    }

    private void setC(int c) {
        this.c = c;
    }


}
