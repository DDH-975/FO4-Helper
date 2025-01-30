package com.project.fifamanagerdata.recyclerViewPackage;

import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
        if(data.getMatchEndType()==0){
            holder.tv_score.setText(data.getScore());
            holder.btn_detail.setVisibility(View.VISIBLE);
        } else if (data.getPlayer1().equals(data.getNickname())) {
            if(data.getMatchEndType()==1){
                holder.tv_score.setText("몰수승");
                holder.btn_detail.setVisibility(View.GONE);
            } else if (data.getMatchEndType()==2) {
                holder.tv_score.setText("몰수패");
                holder.btn_detail.setVisibility(View.GONE);
            }
        } else if (data.getPlayer2().equals(data.getNickname())) {
            if(data.getMatchEndType()==1){
                holder.tv_score.setText("몰수패");
                holder.btn_detail.setVisibility(View.GONE);
            } else if (data.getMatchEndType()==2) {
                holder.tv_score.setText("몰수승");
                holder.btn_detail.setVisibility(View.GONE);
            }
        }

        // btn_detail 클릭 시 다이얼로그 표시
        holder.btn_detail.setOnClickListener(view -> {
            int currentPosition = holder.getAdapterPosition();
            if (currentPosition != RecyclerView.NO_POSITION) {

                //다이얼로그 설정 메소드
                setDialogMethod(data, view);

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


    //다이얼로그 세팅 메소드
    public void setDialogMethod(RecylerViewData data, View view) {
        // 다이얼로그 초기화
        LayoutInflater inflater = LayoutInflater.from(view.getContext());
        View dialogView = inflater.inflate(R.layout.matchdialog_item, null);
        AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
        dlg.setView(dialogView);
        dlg.setPositiveButton("닫기", null);

        String getImageUrl = "https://fco.dn.nexoncdn.co.kr/live/externalAssets/common/playersAction/p";

        TextView tv_squadPlayer1 = (TextView) dialogView.findViewById(R.id.tv_squadPlayer1);
        TextView tv_squadPlayer2 = (TextView) dialogView.findViewById(R.id.tv_squadPlayer2);

        ImageView[] squadPlayerImage1 = new ImageView[28];
        ImageView[] squadPlayerImage2 = new ImageView[28];

        int[] id_SquadPlayerImage1 = {R.id.img_GK, R.id.img_SW, R.id.img_RWB, R.id.img_RB, R.id.img_RCB, R.id.img_CB, R.id.img_LCB,
                R.id.img_LB, R.id.img_LWB, R.id.img_RDM, R.id.img_DM, R.id.img_LDM, R.id.img_RM, R.id.img_RCM, R.id.img_CM, R.id.img_LCM,
                R.id.img_LM, R.id.img_RAM, R.id.img_AM,R.id.img_LAM, R.id.img_RF, R.id.img_CF, R.id.img_LF, R.id.img_RW, R.id.img_RS,
                R.id.img_ST, R.id.img_LS, R.id.img_LW};

        int[] id_SquadPlayerImage2 = {R.id.img_GK2, R.id.img_SW2, R.id.img_RWB2, R.id.img_RB2, R.id.img_RCB2, R.id.img_CB2, R.id.img_LCB2,
                R.id.img_LB2, R.id.img_LWB2, R.id.img_RDM2, R.id.img_DM2, R.id.img_LDM2, R.id.img_RM2, R.id.img_RCM2, R.id.img_CM2, R.id.img_LCM2,
                R.id.img_LM2, R.id.img_RAM2, R.id.img_AM2,R.id.img_LAM2, R.id.img_RF2, R.id.img_CF2, R.id.img_LF2, R.id.img_RW2, R.id.img_RS2,
                R.id.img_ST2, R.id.img_LS2, R.id.img_LW2};

        // 다이얼로그 내부 UI 요소 참조
        TextView tv_playerVs = dialogView.findViewById(R.id.tv_playerVs);
        TextView tv_possession = dialogView.findViewById(R.id.tv_possession);
        TextView tv_shootTotal = dialogView.findViewById(R.id.tv_shootTotal);
        TextView tv_effectiveShootTotal = dialogView.findViewById(R.id.tv_effectiveShootTotal);
        TextView tv_dribble = dialogView.findViewById(R.id.tv_dribble);
        TextView tv_offsideCount = dialogView.findViewById(R.id.tv_offsideCount);
        TextView tv_yellow = dialogView.findViewById(R.id.tv_yellow);
        TextView tv_red = dialogView.findViewById(R.id.tv_red);

        for(int i =0; i<squadPlayerImage1.length;i++){
            squadPlayerImage1[i] = (ImageView) dialogView.findViewById(id_SquadPlayerImage1[i]);
            squadPlayerImage2[i] = (ImageView) dialogView.findViewById(id_SquadPlayerImage2[i]);
        }


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

        tv_squadPlayer1.setText(data.getPlayer1());
        tv_squadPlayer2.setText(data.getPlayer2());

        Log.i("스쿼드 사진 설정","player1 시작");
        for(int i = 0; i<17; i++){
            int position1 = matchInfo1.getPlayer().get(i).getSpPosition();
            Log.d("스쿼드 사진 설정","position1 : " +position1);
            Log.d("스쿼드 사진 설정","position1 : "+ matchInfo1.getPlayer().get(i).getSpId());
            if(position1 == 28){continue;}

            Log.i("스쿼드 사진 설정", position1+"번 사진 가져오기 시작");
            Glide.with(view.getContext())
                    .load( getImageUrl+ matchInfo1.getPlayer().get(i).getSpId() + ".png")
                    .placeholder(R.drawable.outline_downloading_24)
                    .error(R.drawable.noimage)
                    .into(squadPlayerImage1[position1]);
            Log.i("스쿼드 사진 설정", position1+"번 사진 가져오기 종료");

        }
        Log.i("스쿼드 사진 설정","player1 종료");



        Log.i("스쿼드 사진 설정","player2 시작");
        for(int i =0; i<17; i++){
            int position2 = matchInfo2.getPlayer().get(i).getSpPosition();
            Log.d("스쿼드 사진 설정","position2 : " +position2);
            Log.d("스쿼드 사진 설정","position2 : "+ matchInfo2.getPlayer().get(i).getSpId());
            if(position2 == 28){continue;}

            Log.i("스쿼드 사진 설정", position2+"번 사진 가져오기 시작");
            Glide.with(view.getContext())
                    .load(getImageUrl + matchInfo2.getPlayer().get(i).getSpId() + ".png")
                    .placeholder(R.drawable.outline_downloading_24)
                    .error(R.drawable.noimage)
                    .into(squadPlayerImage2[position2]);
            Log.i("스쿼드 사진 설정", position2+"번 사진 가져오기 종료");
        }
        Log.i("스쿼드 사진 설정","player2 종료");

        // 다이얼로그 표시
        AlertDialog dialog = dlg.create();
        dialog.show();

    }
}
