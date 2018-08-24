package com.hysea.database.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Objects;

/**
 * Created by hysea on 2018/7/31.
 */
@Entity
public class User {
    @Id
    private long id;  // Long 与 long  不同之处，一个会覆盖数据，另一个不会覆盖数据
    private String username;
    private int age;
    private String uid;

    public User(String username, int age, String uid) {
        this.username = username;
        this.age = age;
        this.uid = uid;
    }

    @Generated(hash = 168751211)
    public User(long id, String username, int age, String uid) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.uid = uid;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(uid, user.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", uid='" + uid + '\'' +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }
}
