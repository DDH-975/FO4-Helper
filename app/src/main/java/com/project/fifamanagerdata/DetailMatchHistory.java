package com.project.fifamanagerdata;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.fifamanagerdata.dataClass.MatchData;
import com.project.fifamanagerdata.recyclerViewPackage.RecyclerViewAdapter;
import com.project.fifamanagerdata.recyclerViewPackage.RecylerViewData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailMatchHistory extends AppCompatActivity {
    private RetrofitApi retrofitApi;
    private Retrofit retrofit;
    private String ouid, nickname;
    private int matchType;
    private String Apikey = BuildConfig.Apikey;
    private List<String> matchIds = new ArrayList<>();
    private ImageButton btn_back;
    private ImageView img_ball;
    private ArrayList<RecylerViewData> recylerViewData;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<String> matchIdList = new ArrayList<>();
    private TextView tv_noData;


    //매치 아이디 요청
    public void getMatchId() {
        // Retrofit을 사용하여 매치 ID 요청
        retrofitApi.getMatchId(Apikey, ouid, matchType, 0, 50).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    matchIds = response.body();  // 서버로부터 받은 매치 ID 리스트 저장

                    // 매치 ID가 없으면 "데이터 없음" 텍스트 표시
                    if (matchIds == null || matchIds.isEmpty()) {
                        tv_noData.setVisibility(View.VISIBLE);
                        img_ball.setVisibility(View.VISIBLE);
                        Log.d("MatchId", "matchIds가 null이거나 비어 있습니다.");
                    } else {
                        // 매치 ID가 있으면 RecyclerView 업데이트
                        tv_noData.setVisibility(View.INVISIBLE);
                        img_ball.setVisibility(View.INVISIBLE);
                        for (String matchId : matchIds) {
                            matchIdList.add(matchId);  // 매치 ID 리스트에 추가
                            Log.d("MatchId", "ID: " + matchId);
                        }

                        // 매치 상세 데이터를 가져오는 함수 호출
                        getDetailMatch(matchIdList);
                    }
                } else {
                    Log.e("API_ERROR", "Response failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.e("API_FAILURE", "API 요청 실패", t);
            }
        });
    }


    //매치 상세 기록 조회
    public void getDetailMatch(ArrayList<String> matchIdList) {
        // 매치 ID마다 상세 정보를 요청
        for (String matchIddata : matchIdList) {
            retrofitApi.getMatchDetail(Apikey, matchIddata).enqueue(new Callback<MatchData>() {
                @Override
                public void onResponse(Call<MatchData> call, Response<MatchData> response) {
                    MatchData data = response.body();
                    if (data != null) {
                        // 매치 결과 계산
                        String score = data.getMatchInfo().get(0).getShoot().getGoalTotalDisplay() + " : " +
                                data.getMatchInfo().get(1).getShoot().getGoalTotalDisplay();

                        // RecyclerView에 추가할 데이터 생성
                        RecylerViewData rvData = new RecylerViewData(data.getMatchDate(),
                                data.getMatchInfo().get(0).getNickname(),
                                data.getMatchInfo().get(1).getNickname(), score,
                                data, data.getMatchInfo().get(0).getMatchDetail().getMatchEndType(), nickname,
                                data.getMatchInfo().get(0).getOuid(),
                                data.getMatchInfo().get(1).getOuid());

                        // 데이터 리스트에 추가하고 RecyclerView 갱신
                        recylerViewData.add(0, rvData);
                        recyclerViewAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<MatchData> call, Throwable t) {
                    Log.i("getDeatailMatch", "실패");
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);  // 화면 가장자리까지 확장
        setContentView(R.layout.activity_detail_match_history);

        // 상태바와 네비게이션 바에 맞춰 padding 설정
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // "데이터 없음" 텍스트뷰, 이미지뷰 연결
        tv_noData = (TextView) findViewById(R.id.tv_noData);
        img_ball = (ImageView) findViewById(R.id.img_ball);

        // 뒤로가기 버튼 클릭 리스너 설정
        btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();  // 액티비티 종료
            }
        });

        // Intent로 받은 파라미터 값 저장
        Intent getIntent = getIntent();
        ouid = getIntent.getStringExtra("ouid");
        matchType = getIntent.getIntExtra("matchtype", -2);
        nickname = getIntent.getStringExtra("nickname");

        Log.d("DetailMatchHistory.class", "getUoid : " + ouid + " matchType : " + matchType);

        // Retrofit 클라이언트 초기화
        retrofit = NetworkClient.getRetrofitClient(DetailMatchHistory.this);
        retrofitApi = retrofit.create(RetrofitApi.class);


        // RecyclerView 설정
        recyclerView = (RecyclerView) findViewById(R.id.recylerV);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        // RecyclerView에 표시할 데이터 리스트 초기화
        recylerViewData = new ArrayList<>();
        recyclerViewAdapter = new RecyclerViewAdapter(recylerViewData);
        recyclerView.setAdapter(recyclerViewAdapter);

        // 매치 ID 요청
        getMatchId();


    }
}