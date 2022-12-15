package com.coldfier;

import com.coldfier.di.ServiceLocator;
import com.coldfier.domain.models.Check;
import com.coldfier.presentation.CheckViewModel;
import com.coldfier.utils.Utils;
import com.coldfier.utils.reactive.Subscriber;

public class CheckRunner {

    private static CheckViewModel checkViewModel;
    private static final Subscriber<Check> checkSubscriber = result -> System.out.println(result.toString());
    private static final Subscriber<String> errorMessageSubscriber = message -> {
        String divider = Utils.getSymbolsString('-', 150);
        String emptyString = "\n" + Utils.formatStringsWithSpaceBetween("|", "|", 150) + "\n";
        String output = divider + emptyString +
                "|" + Utils.formatStringWithStartEndSpaces(message, 148) + "|" +
                emptyString + divider;
        System.out.println(output);
    };

    public static void main(String[] args) {

        checkViewModel = ServiceLocator.getInstance().getCheckViewModel();
        initObservers();
        checkViewModel.receiveInput(args);
    }

    private static void initObservers() {
        checkViewModel.checkObservable.subscribe(checkSubscriber);
        checkViewModel.errorObservable.subscribe(errorMessageSubscriber);
    }
}