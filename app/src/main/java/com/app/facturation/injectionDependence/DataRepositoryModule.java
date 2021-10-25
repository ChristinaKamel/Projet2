package com.app.facturation.injectionDependence;

import com.app.facturation.repositories.IDataRepository;
import com.app.facturation.repositories.DataReprositoryFirestore;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(value = SingletonComponent.class)
public abstract class DataRepositoryModule {
    @Binds
    public abstract IDataRepository bindIDataRepository (DataReprositoryFirestore dataReprositoryFirestore);
}
