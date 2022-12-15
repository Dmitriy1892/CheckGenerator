package com.coldfier.di.modules;

import com.coldfier.domain.factories.CheckPositionFactory;
import com.coldfier.domain.use_cases.GenerateCheckUseCase;
import com.coldfier.domain.use_cases.SaveCheckUseCase;

public class DomainModule {

    private final RepositoriesModule repositoriesModule = new RepositoriesModule();

    public CheckPositionFactory provideCheckPositionFactory() {
        return new CheckPositionFactory(repositoriesModule.provideStorageRepository());
    }

    public GenerateCheckUseCase provideGenerateCheckUseCase() {
        return new GenerateCheckUseCase(
                repositoriesModule.provideStorageRepository(),
                provideCheckPositionFactory()
        );
    }

    public SaveCheckUseCase provideSaveCheckUseCase() {
        return new SaveCheckUseCase();
    }

}
