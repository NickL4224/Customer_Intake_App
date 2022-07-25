package me.nickligocki.customer_sign_up;

import java.util.concurrent.atomic.AtomicInteger;

public class IdMaker {
    private static final AtomicInteger counter = new AtomicInteger(1000);

    public static Integer makeId() {
        return (Integer)counter.addAndGet(1);
    }
}
