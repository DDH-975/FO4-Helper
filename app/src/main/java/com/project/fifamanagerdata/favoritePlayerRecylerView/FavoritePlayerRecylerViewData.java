package com.project.fifamanagerdata.favoritePlayerRecylerView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

public class FavoritePlayerRecylerViewData {
    private String FBplayer;
    private String FBOuid;


    public String getFBplayer() {

        return FBplayer;
    }

    public void setFBplayer(String FBplayer) {
        this.FBplayer = FBplayer;
    }

    public String getFBOuid() {
        return FBOuid;
    }

    public void setFBOuid(String FBOuid) {
        this.FBOuid = FBOuid;
    }
}
