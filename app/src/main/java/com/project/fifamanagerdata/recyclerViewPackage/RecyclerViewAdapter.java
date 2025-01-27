package com.project.fifamanagerdata.recyclerViewPackage;

import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.fifamanagerdata.R;
import com.project.fifamanagerdata.dataClass.MatchData;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Viewholder> {
    private ArrayList<RecylerViewData> recylerViewData;

    public RecyclerViewAdapter(ArrayList<RecylerViewData> arrayList) {
        this.recylerViewData = arrayList;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        Viewholder holder = new Viewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.Viewholder holder, int position) {
        RecylerViewData data = recylerViewData.get(position);

        // 데이터 설정
        holder.tv_date.setText(data.getMatchDate());
        holder.tv_player1.setText(data.getPlayer1());
        holder.tv_player2.setText(data.getPlayer2());
        holder.tv_score.setText(data.getScore());

        // btn_detail 클릭 시 다이얼로그 표시
        holder.btn_detail.setOnClickListener(view -> {
            int currentPosition = holder.getAdapterPosition();
            if (currentPosition != RecyclerView.NO_POSITION) {
                Log.d("asdgsdafsadfsf", "asdf"+currentPosition);
                // 다이얼로그 초기화
                LayoutInflater inflater = LayoutInflater.from(view.getContext());
                View dialogView = inflater.inflate(R.layout.matchdialog_item, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setView(dialogView);
                dlg.setPositiveButton("닫기", null);

                // 다이얼로그 내부 UI 요소 참조
                TextView tv_playerVs = dialogView.findViewById(R.id.tv_playerVs);
                TextView tv_possession = dialogView.findViewById(R.id.tv_possession);
                TextView tv_shootTotal = dialogView.findViewById(R.id.tv_shootTotal);
                TextView tv_effectiveShootTotal = dialogView.findViewById(R.id.tv_effectiveShootTotal);
                TextView tv_dribble = dialogView.findViewById(R.id.tv_dribble);
                TextView tv_offsideCount = dialogView.findViewById(R.id.tv_offsideCount);
                TextView tv_yellow = dialogView.findViewById(R.id.tv_yellow);
                TextView tv_red = dialogView.findViewById(R.id.tv_red);

                // 데이터 가져오기
                MatchData matchData = data.getMatchData().get(0); // 첫 번째 매치 데이터 사용
                MatchData.MatchInfo matchInfo1 = matchData.getMatchInfo().get(0);
                MatchData.MatchInfo matchInfo2 = matchData.getMatchInfo().get(1);

                // 다이얼로그에 데이터 세팅
                tv_playerVs.setText(data.getPlayer1() + "   vs   " + data.getPlayer2());
                tv_possession.setText(matchInfo1.getMatchDetail().getPossession() + "%  점유율  " +
                        matchInfo2.getMatchDetail().getPossession() + "%");
                tv_shootTotal.setText(matchInfo1.getShoot().getShootTotal() + "   슈팅   " +
                        matchInfo2.getShoot().getShootTotal());
                tv_effectiveShootTotal.setText(matchInfo1.getShoot().getEffectiveShootTotal() + "  유효슈팅  " +
                        matchInfo2.getShoot().getEffectiveShootTotal());
                tv_dribble.setText(matchInfo1.getMatchDetail().getDribble() + "  드리블  " +
                        matchInfo2.getMatchDetail().getDribble());
                tv_offsideCount.setText(matchInfo1.getMatchDetail().getOffsideCount() + "  오프사이드  " +
                        matchInfo2.getMatchDetail().getOffsideCount());
                tv_yellow.setText(matchInfo1.getMatchDetail().getYellowCards() + "  옐로우카드  " +
                        matchInfo2.getMatchDetail().getYellowCards());
                tv_red.setText(matchInfo1.getMatchDetail().getRedCards() + "  레드카드  " +
                        matchInfo2.getMatchDetail().getRedCards());

                // 다이얼로그 표시
                dlg.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return (null != recylerViewData ? recylerViewData.size() : 0);
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        TextView tv_date, tv_player1, tv_player2, tv_score;
        Button btn_detail;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            tv_player1 = (TextView) itemView.findViewById(R.id.tv_player1);
            tv_player2 = (TextView) itemView.findViewById(R.id.tv_player2);
            tv_score = (TextView) itemView.findViewById(R.id.tv_score);
            btn_detail = (Button) itemView.findViewById(R.id.btn_detail);
        }
    }


}
