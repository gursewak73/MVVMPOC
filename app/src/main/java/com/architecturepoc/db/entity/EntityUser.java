package com.architecturepoc.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

/**
 * Created by gursewaksingh on 27/01/18.
 */


@Entity(tableName = "user")
public class EntityUser {

    @PrimaryKey
    @NotNull
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
