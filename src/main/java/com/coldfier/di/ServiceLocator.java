package com.coldfier.di;

import com.coldfier.di.modules.ViewModelsModule;
import com.coldfier.presentation.CheckViewModel;

public class ServiceLocator {

    private static final Object lock = new Object();

    private static volatile ServiceLocator INSTANCE = null;

    private final ViewModelsModule viewModelsModule = new ViewModelsModule();

    private ServiceLocator() {}

    public static ServiceLocator getInstance() {
        if (INSTANCE == null) {
            synchronized (lock) {
                if (INSTANCE == null) {
                    INSTANCE = new ServiceLocator();
                }

                return INSTANCE;
            }
        } else  {
            return INSTANCE;
        }
    }

    public CheckViewModel getCheckViewModel() {
        return viewModelsModule.provideCheckViewModel();
    }
}
