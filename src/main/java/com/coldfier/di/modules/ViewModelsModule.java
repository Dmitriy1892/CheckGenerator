package com.coldfier.di.modules;

import com.coldfier.presentation.CheckViewModel;

public class ViewModelsModule {
    private final DomainModule domainModule = new DomainModule();
    public CheckViewModel provideCheckViewModel() {

        return new CheckViewModel(
                domainModule.provideGenerateCheckUseCase(),
                domainModule.provideSaveCheckUseCase()
        );
    }
}
