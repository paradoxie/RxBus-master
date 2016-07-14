package com.hwangjr.rxbus.app;

/**
 * Created by wangyd on 16/7/7.
 */
public class Person {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Produce(
//            thread = EventThread.NEW_THREAD,
//            tags = {@Tag}
//    )
//    public String tell() {
//        return "tell Produce " + Thread.currentThread();
//    }
}
