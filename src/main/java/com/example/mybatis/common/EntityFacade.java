package com.example.mybatis.common;

public interface EntityFacade<E, I> {

    E findEntity(I id);

    void create(E entity);

    void update(E entity);

}
