package com.project.fifamanagerdata;

import com.project.fifamanagerdata.dataClass.MatchData;
import com.project.fifamanagerdata.dataClass.Ouid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RetrofitApi {
    @GET("/fconline/v1/id")
    Call<Ouid> getOuid(
            @Query("nickname") String nickname,
            @Header("x-nxopen-api-key") String apikey
    );


    @GET("/fconline/v1/user/match")
    Call<List<String>> getMatchId(
            @Header("x-nxopen-api-key") String apiKey,
            @Query("ouid") String ouid,
            @Query("matchtype") int matchtype,
            @Query("offset") int offset,
            @Query("limit") int limit
    );


    @GET("/fconline/v1/match-detail")
    Call<MatchData> getMatchDetail(
      @Header("x-nxopen-api-key") String apikey,
      @Query("matchid") String matchid
    );
}
