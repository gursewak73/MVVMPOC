package com.architecturepoc.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.architecturepoc.db.dao.UserDAO;
import com.architecturepoc.db.entity.EntityUser;

/**
 * Created by gursewaksingh on 27/01/18.
 */

@Database(entities = {EntityUser.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "roompoc";
    private static AppDatabase mInstance;

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public abstract UserDAO userDAO();

    public static AppDatabase getInstance(Context context){
        if (mInstance == null) {
            synchronized (AppDatabase.class) {
                if (mInstance == null) {
                    mInstance = buildDatabase(context);
                    mInstance.updateDatabaseCreated(context);
                }
            }
            buildDatabase(context);
        }
        return mInstance;
    }

    private static AppDatabase buildDatabase(final Context context){
        return Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).addCallback(new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                AppDatabase database = AppDatabase.getInstance(context);
                database.setDatabaseCreated();
            }
        }).build();
    }

    public void updateDatabaseCreated(final Context context){
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
     }

    public void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> isDatabaseCreated(){
        return mIsDatabaseCreated;
    }

}
