package com.example.mybatis.common.approval.lock;

public interface Lock<T> {

    void lock(T target);

    void unlock(T target);

}
