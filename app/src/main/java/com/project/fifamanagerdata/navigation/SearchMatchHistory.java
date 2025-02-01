package com.project.fifamanagerdata.navigation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.fifamanagerdata.DetailMatchHistory;
import com.project.fifamanagerdata.NetworkClient;
import com.project.fifamanagerdata.R;
import com.project.fifamanagerdata.RetrofitApi;
import com.project.fifamanagerdata.UoidRequester;

import retrofit2.Retrofit;

public class SearchMatchHistory extends Fragment {
    private View view;
    private ImageButton btn_search;
    private Retrofit retrofit;
    private RetrofitApi retrofitApi;
    private EditText tv_searchPlayer;
    private UoidRequester uoidRequester;
    private RadioButton officialFriendly, classic_1on1, managerMode, officialMatch;
    private int matchtype = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search_match_history, container, false);
        return view;
    }

    public void ChangeRadioBtn() {
        officialMatch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                officialMatch.setTextColor(Color.parseColor("#1EB31E"));
                matchtype = 50;
            } else {
                officialMatch.setTextColor(Color.WHITE);
            }
            resetOtherButtons();
        });

        officialFriendly.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                officialFriendly.setTextColor(Color.parseColor("#1EB31E"));
                matchtype = 60;
            } else {
                officialFriendly.setTextColor(Color.WHITE);
            }
            resetOtherButtons();
        });

        classic_1on1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                classic_1on1.setTextColor(Color.parseColor("#1EB31E"));
                matchtype = 40;
            } else {
                classic_1on1.setTextColor(Color.WHITE);
            }
            resetOtherButtons();
        });

        managerMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                managerMode.setTextColor(Color.parseColor("#1EB31E"));
                matchtype = 52;
            } else {
                managerMode.setTextColor(Color.WHITE);
            }
            resetOtherButtons();
        });

    }

    // 다른 버튼들의 색상을 초기화하는 메서드
    private void resetOtherButtons() {
        if (!officialMatch.isChecked()) {
            officialMatch.setTextColor(Color.WHITE);
        }
        if (!officialFriendly.isChecked()) {
            officialFriendly.setTextColor(Color.WHITE);
        }
        if (!classic_1on1.isChecked()) {
            classic_1on1.setTextColor(Color.WHITE);
        }
        if (!managerMode.isChecked()) {
            managerMode.setTextColor(Color.WHITE);
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_search = (ImageButton) view.findViewById(R.id.btn_search);
        tv_searchPlayer = (EditText) view.findViewById(R.id.tv_searchPlayer);

        retrofit = NetworkClient.getRetrofitClient(view.getContext());
        retrofitApi = retrofit.create(RetrofitApi.class);

        //officialFriendly : 공식친선,  classic_1on1 : 클래식 1on1 , managerMode : 감독모드, officialMatch : 공식경기
        officialFriendly = (RadioButton) view.findViewById(R.id.officialFriendly);
        classic_1on1 = (RadioButton) view.findViewById(R.id.classic_1on1);
        managerMode = (RadioButton) view.findViewById(R.id.managerMode);
        officialMatch = (RadioButton) view.findViewById(R.id.officialMatch);

        ChangeRadioBtn();


        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nickname = tv_searchPlayer.getText().toString();
                String noSpaces = nickname.replaceAll("\\s", "");
                uoidRequester = new UoidRequester(noSpaces, retrofitApi);
                uoidRequester.getuoid(new UoidRequester.UoidCallback() {
                    @Override
                    public void onSuccess(String ouid) {
                        if (matchtype != 0) {
                            String getUoid = ouid;
                            Log.d("SearchMatchHistory", "받은 uoid: " + getUoid);
                            Intent intent = new Intent(view.getContext(), DetailMatchHistory.class);
                            intent.putExtra("nickname",noSpaces);
                            intent.putExtra("ouid", getUoid);
                            intent.putExtra("matchtype", matchtype);
                            startActivity(intent);
                        } else {
                            Toast.makeText(view.getContext(), "경기모드를 선택해주세요.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Toast.makeText(view.getContext(), "존재하지 않는 구단주입니다.",
                                Toast.LENGTH_SHORT).show();
                        Log.e("SearchMatchHistory", "에러 발생: " + errorMessage);
                    }
                });
            }
        });


    }
}
