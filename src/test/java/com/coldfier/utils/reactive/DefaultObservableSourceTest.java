package com.coldfier.utils.reactive;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.concurrent.CopyOnWriteArraySet;

public class DefaultObservableSourceTest {

    DefaultObservableSource<Integer> observableSource = new DefaultObservableSource<>();
    private final SubscriberFactory factory = new SubscriberFactory();

    @BeforeEach
    public void beforeAnyTest() {
        observableSource = new DefaultObservableSource<>();
    }
    @Test
    public void test_subscribe_is_all_subscribers_saves() throws NoSuchFieldException, IllegalAccessException {

        Subscriber<Integer> subscriber1 = factory.createSubscriber();
        Subscriber<Integer> subscriber2 = factory.createSubscriber();

        observableSource.subscribe(subscriber1);
        observableSource.subscribe(subscriber2);

        CopyOnWriteArraySet<Integer> subscribers = getSubscribers(observableSource);

        Assertions.assertEquals(2, subscribers.size());
    }

    @Test
    public void test_subscribe_is_one_subscriber_saves() throws NoSuchFieldException, IllegalAccessException {

        Subscriber<Integer> subscriber = factory.createSubscriber();

        observableSource.subscribe(subscriber);
        observableSource.subscribe(subscriber);

        CopyOnWriteArraySet<Integer> subscribers = getSubscribers(observableSource);

        Assertions.assertEquals(1, subscribers.size());
    }

    @Test
    public void test_emit_is_value_delivers_to_all_subscribers() {

        Subscriber<Integer> subscriber1 = factory.createSubscriber();
        Subscriber<Integer> subscriber2 = factory.createSubscriber();

        observableSource.subscribe(subscriber1);
        observableSource.subscribe(subscriber2);

        observableSource.emit(1);

        Mockito.verify(subscriber1).receiveResult(Mockito.anyInt());
        Mockito.verify(subscriber2).receiveResult(Mockito.anyInt());
    }

    @Test
    public void test_emit_is_value_delivers_to_one_subscribers() {

        Subscriber<Integer> subscriber1 = factory.createSubscriber();
        Subscriber<Integer> subscriber2 = factory.createSubscriber();

        observableSource.subscribe(subscriber1);
        observableSource.subscribe(subscriber2);

        observableSource.unsubscribe(subscriber2);

        observableSource.emit(1);

        Mockito.verify(subscriber1).receiveResult(Mockito.anyInt());
        Mockito.verify(subscriber2, Mockito.never()).receiveResult(Mockito.anyInt());
    }

    private CopyOnWriteArraySet<Integer> getSubscribers(
            DefaultObservableSource<Integer> observable
    ) throws NoSuchFieldException, IllegalAccessException {
        Field subscribersField = observable.getClass().getDeclaredField("subscribers");
        subscribersField.setAccessible(true);
        return (CopyOnWriteArraySet<Integer>) subscribersField.get(observable);
    }

    static class SubscriberFactory {
        Subscriber<Integer> createSubscriber() {
            return (Subscriber<Integer>) Mockito.mock(Subscriber.class);
        }
    }

}
