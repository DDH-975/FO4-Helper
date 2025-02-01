package com.project.fifamanagerdata.favoritesPlayerRoomDB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PlayerDataModel {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String nickname;
    private String Ouid;

    public PlayerDataModel() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOuid() {
        return Ouid;
    }

    public void setOuid(String ouid) {
        Ouid = ouid;
    }
}
