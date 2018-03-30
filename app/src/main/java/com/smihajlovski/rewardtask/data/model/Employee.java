package com.smihajlovski.rewardtask.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.smihajlovski.rewardtask.common.Constants;

/**
 * Created by Stefan on 29-Mar-18.
 */

@Entity(tableName = Constants.EMPLOYEE_TABLE_NAME)
public class Employee {
    @PrimaryKey
    @SerializedName("uuid")
    @ColumnInfo(name = "uuid")
    @NonNull
    String uuid;
    @SerializedName("company")
    @ColumnInfo(name = "company")
    String company;
    @SerializedName("bio")
    @ColumnInfo(name = "bio")
    String bio;
    @SerializedName("name")
    @ColumnInfo(name = "name")
    String name;
    @SerializedName("title")
    @ColumnInfo(name = "title")
    String title;
    @SerializedName("avatar")
    @ColumnInfo(name = "avatar")
    String avatar;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
