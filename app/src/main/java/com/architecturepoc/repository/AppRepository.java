package com.architecturepoc.repository;

import android.arch.lifecycle.LiveData;
import android.os.Bundle;

import com.architecturepoc.db.AppDatabase;
import com.architecturepoc.db.entity.EntityUser;

import java.util.List;

/**
 * Created by gursewaksingh on 27/01/18.
 */

public class AppRepository {

    private static AppRepository mAppRepository;
    private AppDatabase appDatabase;

    private AppRepository(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }

    public static AppRepository newInstance(AppDatabase appDatabase) {
        if (mAppRepository == null) {
            synchronized (AppRepository.class) {
                mAppRepository = new AppRepository(appDatabase);
            }
        }
        return mAppRepository;
    }

    public LiveData<List<EntityUser>> getAllUsers(){
        return appDatabase.userDAO().getAllUsers();
    }

    public void addUser(List<EntityUser> userList){
        appDatabase.userDAO().insertUser(userList);
    }
}
