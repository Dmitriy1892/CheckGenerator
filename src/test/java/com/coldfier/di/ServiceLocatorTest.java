package com.coldfier.di;

import com.coldfier.presentation.CheckViewModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceLocatorTest {

    @Test
    void test_getInstance_return_one_instance() {
        ServiceLocator instance1 = ServiceLocator.getInstance();
        ServiceLocator instance2 = ServiceLocator.getInstance();

        Assertions.assertEquals(instance1, instance2);
    }

    @Test
    void test_getCheckViewModel_return_different_instances() {
        ServiceLocator serviceLocator = ServiceLocator.getInstance();

        CheckViewModel checkViewModel1 = serviceLocator.getCheckViewModel();
        CheckViewModel checkViewModel2 = serviceLocator.getCheckViewModel();

        Assertions.assertNotEquals(checkViewModel1, checkViewModel2);
    }
}