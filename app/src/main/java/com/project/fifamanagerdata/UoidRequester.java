package com.project.fifamanagerdata;

import android.util.Log;

import com.project.fifamanagerdata.dataClass.Ouid;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UoidRequester {
    private RetrofitApi retrofitApi;
    private String nickname;
    private String ouid;
    private String Apikey = BuildConfig.Apikey;

    public UoidRequester(String nickname, RetrofitApi retrofitApi) {
        this.nickname = nickname;
        this.retrofitApi = retrofitApi;

    }

    public String getuoid(UoidCallback callback) {
        retrofitApi.getOuid(nickname, Apikey).
                enqueue(new Callback<Ouid>() {
                    @Override
                    public void onResponse(Call<Ouid> call, Response<Ouid> response) {
                        if (response.isSuccessful()) {
                            Log.d("onResponse", " api 요청 성공");
                            Ouid data = response.body();
                            if (data != null) {
                                ouid = data.getOuid();
                                Log.d("onResponse", "ouid : " + ouid);
                                callback.onSuccess(ouid);

                            } else {
                                Log.d("onResponse", "데이터 없는디?");
                                callback.onFailure("데이터 없음");
                            }
                        } else {
                            Log.d("onResponse", "data에 데이터 없음");
                            callback.onFailure("데이터 없음");
                        }
                    }

                    @Override
                    public void onFailure(Call<Ouid> call, Throwable t) {
                        // 실패 로그 출력
                        Log.e("API_FAILURE", "API 요청 실패", t);

                        // 사용자 정의 콜백으로 에러 메시지 전달
                        callback.onFailure("API 요청 실패: " + t.getMessage());
                    }

                });
        return ouid;
    }

    public interface UoidCallback {
        void onSuccess(String ouid);

        void onFailure(String errorMessage);
    }

}
