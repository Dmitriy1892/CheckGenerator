package com.coldfier.di.modules;

import com.coldfier.presentation.CheckViewModel;

public class ViewModelsModule {
    private static volatile CheckViewModel checkViewModelInstance;
    private final DomainModule domainModule = new DomainModule();
    public CheckViewModel provideCheckViewModel() {

        if (checkViewModelInstance == null) {
            synchronized (this) {
                if (checkViewModelInstance == null) {
                    checkViewModelInstance = new CheckViewModel(
                            domainModule.provideGenerateCheckUseCase(),
                            domainModule.provideSaveCheckUseCase()
                    );
                }

                return checkViewModelInstance;
            }
        } else {
            return checkViewModelInstance;
        }
    }
}
