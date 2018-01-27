package com.architecturepoc.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.architecturepoc.AppApplication;
import com.architecturepoc.db.entity.EntityUser;
import com.architecturepoc.repository.AppRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by gursewaksingh on 27/01/18.
 */

public class UserViewModel extends ViewModel {

    private LiveData<List<EntityUser>> userList;
    private AppRepository appRepository;

    public UserViewModel(@NotNull AppApplication appApplication, AppRepository appRepository) {
        this.appRepository = appRepository;
        userList = appRepository.getAllUsers();
    }

    public void insertUserList(List<EntityUser> userList){
        appRepository.addUser(userList);
    }

    public LiveData<List<EntityUser>> getUsers() {
        return userList;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NotNull
        private AppApplication appApplication;
        private AppRepository appRepository;

        public Factory(AppApplication appApplication) {
            this.appApplication = appApplication;
            appRepository = appApplication.getAppRepository();
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new UserViewModel(appApplication, appRepository);
        }
    }

}
