package com.example.mybatis.common.approval.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
public class CustomSpinLock {

    private final AtomicInteger id = new AtomicInteger(0);
    private final AtomicBoolean lock = new AtomicBoolean(false);

    public void lock(Integer id) {
        log.info("LOCK 획득 시도");
        while (!lock.compareAndSet(false, true)) {
            log.info("LOCK 획득 대기");
        }
        log.info("LOCK 획득 성공");
    }

    public void occupy(Integer id) {
        this.id.compareAndSet(0, id);
    }

    public void unlock() {
        lock.set(false);
        this.id.set(0);
        log.info("LOCK 반납");
    }

    public boolean isWait() {
        return this.id.get() == 0;
    }

    public boolean isOccupy(Integer id) {
        return this.id.get() == id;
    }
}
