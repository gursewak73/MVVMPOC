package com.architecturepoc;

import android.app.Application;

import com.architecturepoc.db.AppDatabase;
import com.architecturepoc.repository.AppRepository;

/**
 * Created by gursewaksingh on 27/01/18.
 */

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public AppDatabase getDatabase(){
        return AppDatabase.getInstance(this);
    }

    public AppRepository getAppRepository(){
        return AppRepository.newInstance(getDatabase());
    }
}
