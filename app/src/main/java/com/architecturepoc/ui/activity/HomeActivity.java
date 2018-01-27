package com.architecturepoc.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.architecturepoc.AppApplication;
import com.architecturepoc.R;
import com.architecturepoc.db.entity.EntityUser;
import com.architecturepoc.ui.adapter.RecyclerAdapterUserData;
import com.architecturepoc.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by gursewaksingh on 27/01/18.
 */

public class HomeActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private EditText etId,etName;
    private Button btnInsert;
    private RecyclerView rvData;
    private RecyclerAdapterUserData adapterUserData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();
        initViewModel();
    }

    private void initViews(){
        etId = findViewById(R.id.etID);
        etName = findViewById(R.id.etName);
        btnInsert = findViewById(R.id.btnInsert);
        rvData = findViewById(R.id.rvData);
        rvData.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initListeners(){
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etId.getText().toString();
                String name = etName.getText().toString();
                if (TextUtils.isEmpty(id) || TextUtils.isEmpty(name)) {
                    return;
                }
                final List<EntityUser> userList = new ArrayList<>();
                EntityUser user = new EntityUser();
                user.setId(id);
                user.setName(name);
                userList.add(user);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        userViewModel.insertUserList(userList);
                    }
                }).start();
            }
        });
    }

    private void initViewModel(){
        UserViewModel.Factory factory = new UserViewModel.Factory((AppApplication) getApplication());
        userViewModel = factory.create(UserViewModel.class);

        subscribeUserModel();
    }

    private void subscribeUserModel(){
        userViewModel.getUsers().observe(this, new Observer<List<EntityUser>>() {
            @Override
            public void onChanged(@Nullable List<EntityUser> entityUsers) {
                setAdapter(entityUsers);
            }
        });
    }

    private void setAdapter(List<EntityUser> entityUsers){
        if (adapterUserData == null) {
            adapterUserData = new RecyclerAdapterUserData(entityUsers,HomeActivity.this);
        } else {
            adapterUserData.refresh(entityUsers);
        }
        rvData.setAdapter(adapterUserData);
    }
}
