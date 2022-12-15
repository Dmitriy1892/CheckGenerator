package com.coldfier.utils.reactive;

import java.util.concurrent.CopyOnWriteArraySet;

public class DefaultObservableSource<T> implements ObservableSource<T> {

    private final CopyOnWriteArraySet<Subscriber<T>> subscribers = new CopyOnWriteArraySet<>();

    @Override
    public void emit(T item) {
        subscribers.forEach(subscriber -> subscriber.receiveResult(item));
    }

    @Override
    public void subscribe(Subscriber<T> subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(Subscriber<T> subscriber) {
        subscribers.remove(subscriber);
    }
}
