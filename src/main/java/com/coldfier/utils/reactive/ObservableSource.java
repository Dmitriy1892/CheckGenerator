package com.coldfier.utils.reactive;

public interface ObservableSource<T> {

    void emit(T item);

    void subscribe(Subscriber<T> subscriber);

    void unsubscribe(Subscriber<T> subscriber);
}
