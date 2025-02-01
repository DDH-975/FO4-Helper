package com.project.fifamanagerdata.favoritesPlayerRoomDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setInsertFavoritePlayer(PlayerDataModel playerDataModel);

    @Update
    void setUpdateFavoritePlayer(PlayerDataModel playerDataModel);

    @Delete
    void setDeleteFavoritePlayer(PlayerDataModel playerDataModel);

    @Query("SELECT * FROM PlayerDataModel ORDER BY nickname ASC")
    List<PlayerDataModel> getAllDataSortedByNickname();

    @Query("DELETE FROM Playerdatamodel WHERE nickname = :nickname")
    void deletePlayerByNickname(String nickname);

    @Query("SELECT COUNT(*) FROM playerdatamodel WHERE nickname = :nickname")
    int isPlayerExists(String nickname);



}
