package com.user.auth.entity.user;

@FunctionalInterface
public interface UserCustomizer<T> {
    T customize(T t);
}