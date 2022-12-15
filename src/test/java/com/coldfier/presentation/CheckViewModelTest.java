package com.coldfier.presentation;

import com.coldfier.di.ServiceLocator;
import com.coldfier.domain.models.Check;
import com.coldfier.utils.reactive.Subscriber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CheckViewModelTest {

    CheckViewModel checkViewModel = ServiceLocator.getInstance().getCheckViewModel();

    @Test
    void test_receiveInput_if_input_only_item_pairs_return_correct() {
        checkViewModel.checkObservable.subscribe(Assertions::assertNotNull);
        checkViewModel.receiveInput(new String[]{ "5-1", "3-4", "121413-1" });
    }

    @Test
    void test_receiveInput_if_input_only_item_pair_return_error() {
        checkViewModel.errorObservable.subscribe(Assertions::assertNotNull);
        Subscriber<Check> notCalledCallback = (Subscriber<Check>) Mockito.mock(Subscriber.class);
        checkViewModel.checkObservable.subscribe(notCalledCallback);

        checkViewModel.receiveInput(new String[]{ "121413-1" });
        Mockito.verify(notCalledCallback, Mockito.never()).receiveResult(Mockito.any());
    }

    @Test
    void test_receiveInput_if_input_item_pair_with_discount_card_return_error() {
        checkViewModel.errorObservable.subscribe(Assertions::assertNotNull);
        Subscriber<Check> notCalledCallback = (Subscriber<Check>) Mockito.mock(Subscriber.class);
        checkViewModel.checkObservable.subscribe(notCalledCallback);

        checkViewModel.receiveInput(new String[]{ "1-1", "5-3", "carda-1234" });
        Mockito.verify(notCalledCallback, Mockito.never()).receiveResult(Mockito.any());
    }
}