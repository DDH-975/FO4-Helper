package com.project.fifamanagerdata.navigation;

import static androidx.core.app.ActivityCompat.recreate;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.fifamanagerdata.R;
import com.project.fifamanagerdata.favoritePlayerRecylerView.FavoritePlayerRecylerViewAdapter;
import com.project.fifamanagerdata.favoritePlayerRecylerView.FavoritePlayerRecylerViewData;
import com.project.fifamanagerdata.favoritesPlayerRoomDB.PlayerDB;
import com.project.fifamanagerdata.favoritesPlayerRoomDB.PlayerDataModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FavoritesPlayer extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private FavoritePlayerRecylerViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<FavoritePlayerRecylerViewData> recylerData;
    private PlayerDB playerDB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.favorites_player, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.Rv_favorites);
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        recylerData = new ArrayList<>();
        adapter = new FavoritePlayerRecylerViewAdapter(recylerData);
        recyclerView.setAdapter(adapter);

        playerDB = PlayerDB.getInstance(view.getContext());
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                // Room DB에 접근하는 코드
                List<PlayerDataModel> playerDataModelsList = playerDB.playerDao().getAllDataSortedByNickname();

                // UI 업데이트는 메인 스레드에서
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (PlayerDataModel data : playerDataModelsList) {
                            FavoritePlayerRecylerViewData favoritePlayer = new FavoritePlayerRecylerViewData();
                            favoritePlayer.setFBplayer(data.getNickname());
                            favoritePlayer.setFBOuid(data.getOuid()); // Ouid 필드가 있다면, 그 값을 설정

                            recylerData.add(favoritePlayer); // 변환된 데이터를 리스트에 추가
                        }

                        // 어댑터에 데이터 갱신
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });



    }
}
