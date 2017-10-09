package com.shuai.model.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jiangyingjun on 2017/10/9.
 */
@Entity
public class User {
    @Id(autoincrement = true)
    private long id;

    private String name;

    private int age;

    private String password;

    private int gender;

    private String address;

    @Generated(hash = 1305094618)
    public User(long id, String name, int age, String password, int gender,
            String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.password = password;
        this.gender = gender;
        this.address = address;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



}
