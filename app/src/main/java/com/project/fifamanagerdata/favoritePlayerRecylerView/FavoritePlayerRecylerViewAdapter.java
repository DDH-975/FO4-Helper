package com.project.fifamanagerdata.favoritePlayerRecylerView;

import static androidx.core.app.ActivityCompat.recreate;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.project.fifamanagerdata.DetailMatchHistory;
import com.project.fifamanagerdata.R;
import com.project.fifamanagerdata.favoritesPlayerRoomDB.PlayerDB;
import com.project.fifamanagerdata.favoritesPlayerRoomDB.PlayerDataModel;
import com.project.fifamanagerdata.navigation.FavoritesPlayer;

import java.util.ArrayList;
import java.util.List;

public class FavoritePlayerRecylerViewAdapter extends RecyclerView.Adapter<FavoritePlayerRecylerViewAdapter.Viewholder> {
    private ArrayList<FavoritePlayerRecylerViewData> recylerViewData;
    private PlayerDB playerDB;
    private int matchtype;

    public FavoritePlayerRecylerViewAdapter(ArrayList<FavoritePlayerRecylerViewData> arrayList) {
        this.recylerViewData = arrayList;
    }

    @NonNull
    @Override
    public FavoritePlayerRecylerViewAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_player_recyler_items,
                parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }


    @Override
    public void onBindViewHolder(@NonNull FavoritePlayerRecylerViewAdapter.Viewholder holder, int position) {
        FavoritePlayerRecylerViewData data = recylerViewData.get(position);
        holder.tv_player.setText(data.getFBplayer().toString());

        PlayerDataModel playerDataModel = new PlayerDataModel();
        playerDB = PlayerDB.getInstance(holder.itemView.getContext());


        holder.CB_star.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    handleCheckBoxChecked(playerDataModel, playerDB, data.getFBplayer(),
                            data.getFBOuid(), holder.itemView.getContext());

                } else if (b == false) {
                    handleCheckBoxUnchecked(playerDB, data.getFBplayer(), holder.itemView.getContext());
                }
            }
        });


        ChangeRadioBtn(holder);


        holder.search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (matchtype != 0) {
                    String getUoid = data.getFBOuid();
                    String getnickname = data.getFBplayer();
                    Log.d("favortiePlayerReculerAdpater", "uoid: " + getUoid);
                    Log.d("favortiePlayerReculerAdpater", "getnickname: " + getnickname);

                    Intent intent = new Intent(holder.itemView.getContext(), DetailMatchHistory.class);
                    intent.putExtra("nickname", getnickname);
                    intent.putExtra("ouid", getUoid);
                    intent.putExtra("matchtype", matchtype);
                    holder.itemView.getContext().startActivity(intent);

                } else {
                    Toast.makeText(holder.itemView.getContext(), "경기모드를 선택해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return (null != recylerViewData ? recylerViewData.size() : 0);
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        TextView tv_player;
        CheckBox CB_star;
        RadioButton officialMatch, managerMode, classic_1on1, officialFriendly;
        ImageButton search_btn;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tv_player = (TextView) itemView.findViewById(R.id.tv_player);
            CB_star = (CheckBox) itemView.findViewById(R.id.CB_star);
            officialFriendly = (RadioButton) itemView.findViewById(R.id.official_Friendly);
            classic_1on1 = (RadioButton) itemView.findViewById(R.id.classic_1_on_1);
            managerMode = (RadioButton) itemView.findViewById(R.id.manager_Mode);
            officialMatch = (RadioButton) itemView.findViewById(R.id.official_Match);
            search_btn = (ImageButton) itemView.findViewById(R.id.search_btn);

        }
    }


    public void ChangeRadioBtn(Viewholder holder) {
        holder.officialMatch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                holder.officialMatch.setTextColor(Color.parseColor("#1EB31E"));
                matchtype = 50;
            } else {
                holder.officialMatch.setTextColor(Color.WHITE);
            }
            resetOtherButtons(holder);
        });

        holder.officialFriendly.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                holder.officialFriendly.setTextColor(Color.parseColor("#1EB31E"));
                matchtype = 60;
            } else {
                holder.officialFriendly.setTextColor(Color.WHITE);
            }
            resetOtherButtons(holder);
        });

        holder.classic_1on1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                holder.classic_1on1.setTextColor(Color.parseColor("#1EB31E"));
                matchtype = 40;
            } else {
                holder.classic_1on1.setTextColor(Color.WHITE);
            }
            resetOtherButtons(holder);
        });

        holder.managerMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                holder.managerMode.setTextColor(Color.parseColor("#1EB31E"));
                matchtype = 52;
            } else {
                holder.managerMode.setTextColor(Color.BLACK);
            }
            resetOtherButtons(holder);
        });

    }

    // 다른 버튼들의 색상을 초기화하는 메서드
    private void resetOtherButtons(Viewholder holder) {
        if (!holder.officialMatch.isChecked()) {
            holder.officialMatch.setTextColor(Color.BLACK);
        }
        if (!holder.officialFriendly.isChecked()) {
            holder.officialFriendly.setTextColor(Color.BLACK);
        }
        if (!holder.classic_1on1.isChecked()) {
            holder.classic_1on1.setTextColor(Color.BLACK);
        }
        if (!holder.managerMode.isChecked()) {
            holder.managerMode.setTextColor(Color.BLACK);
        }
    }


    //roomDB에 즐겨찾기 플레이어 저장
    private void handleCheckBoxChecked(PlayerDataModel playerDataModel, PlayerDB playerDB,
                                       String nickname, String Ouid, Context context) {
        playerDataModel.setNickname(nickname);
        playerDataModel.setOuid(Ouid);
        Log.d("PlayerDataModel", "Nickname: " + playerDataModel.getNickname());
        Log.d("PlayerDataModel", "Ouid: " + playerDataModel.getOuid());

        new Thread(() -> {
            if (playerDB.playerDao().isPlayerExists(playerDataModel.getNickname()) == 0) {
                playerDB.playerDao().setInsertFavoritePlayer(playerDataModel);

                ((AppCompatActivity) context).runOnUiThread(() ->
                        Toast.makeText(context, nickname + " 저장되었습니다!", Toast.LENGTH_SHORT).show()
                );
            } else {
                ((AppCompatActivity) context).runOnUiThread(() ->
                        Toast.makeText(context, nickname + "은(는) 이미 즐겨찾기에 있습니다.", Toast.LENGTH_SHORT).show()
                );
            }

            List<PlayerDataModel> allPlayers = playerDB.playerDao().getAllDataSortedByNickname();
            for (PlayerDataModel player : allPlayers) {
                Log.d("DB_Check", "ID: " + player.getId() + ", Nickname: " + player.getNickname() + ", Ouid: " + player.getOuid());
            }

        }).start();

    }

    //roomDB에 즐겨찾기 플레이어 삭제
    private void handleCheckBoxUnchecked(PlayerDB playerDB, String nickname, Context context) {
        new Thread(() -> {
            try {
                playerDB.playerDao().deletePlayerByNickname(nickname);

                List<PlayerDataModel> allPlayers = playerDB.playerDao().getAllDataSortedByNickname();
                for (PlayerDataModel player : allPlayers) {
                    Log.d("DB_Check", "ID: " + player.getId() + ", Nickname: " + player.getNickname() + ", Ouid: " + player.getOuid());
                }
                ((AppCompatActivity) context).runOnUiThread(() ->
                        Toast.makeText(context, nickname + " 삭제되었습니다", Toast.LENGTH_SHORT).show()
                );
            } catch (Exception e) {
                Log.e("checked Room Data", "no data at room");
            }
        }).start();

    }

}
