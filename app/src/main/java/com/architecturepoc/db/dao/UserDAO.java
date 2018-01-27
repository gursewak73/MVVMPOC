package com.architecturepoc.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.architecturepoc.db.entity.EntityUser;

import java.util.List;

/**
 * Created by gursewaksingh on 27/01/18.
 */

@Dao
public interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(List<EntityUser> userList);

    @Query("select * from user")
    LiveData<List<EntityUser>> getAllUsers();
}
