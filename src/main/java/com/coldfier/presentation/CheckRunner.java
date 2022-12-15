package com.coldfier.presentation;

import com.coldfier.di.ServiceLocator;
import com.coldfier.domain.models.Check;
import com.coldfier.utils.reactive.Subscriber;

public class CheckRunner {

    private static CheckViewModel checkViewModel;
    private static final Subscriber<Check> checkSubscriber = result -> System.out.println(result.toString());
    private static final Subscriber<String> errorMessageSubscriber = System.out::println;

    public static void main(String[] args) {

        checkViewModel = ServiceLocator.getInstance().getCheckViewModel();
        initObservers();
        checkViewModel.receiveInput(args);
    }

    private static void initObservers() {
        checkViewModel.checkObservable.subscribe(checkSubscriber);
        checkViewModel.errorObservable.subscribe(errorMessageSubscriber);
    }

    private static void removeObservers() {
        checkViewModel.checkObservable.unsubscribe(checkSubscriber);
        checkViewModel.errorObservable.unsubscribe(errorMessageSubscriber);
    }
}