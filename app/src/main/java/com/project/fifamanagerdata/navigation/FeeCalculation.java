package com.project.fifamanagerdata.navigation;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.fifamanagerdata.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class FeeCalculation extends Fragment {
    private List<EditText> editTextList1 = new ArrayList<>();
    private List<EditText> editTextList2 = new ArrayList<>();

    private EditText inputAmount, inputCoupon;
    private LinearLayout parentLayout;
    private Button addEtBtn, resultBtn;
    private TextView amount, fee, resultTV;
    private View view;
    private CheckBox pcSale, topSale;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fee_calculation, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inputAmount = (EditText) view.findViewById(R.id.inputAmount);
        inputCoupon = (EditText) view.findViewById(R.id.inputCoupon);
        editTextList1.add(inputAmount);
        editTextList2.add(inputCoupon);
        inputAmount.addTextChangedListener(new NumberTextWatcher(inputAmount));
        inputCoupon.addTextChangedListener(new NumberTextWatcher(inputCoupon));

        parentLayout = (LinearLayout) view.findViewById(R.id.parentLayout);
        addEtBtn = (Button) view.findViewById(R.id.addBtnEt);
        resultBtn = (Button) view.findViewById(R.id.resultBtn);
        resultTV = (TextView) view.findViewById(R.id.resultTV);
        amount = (TextView) view.findViewById(R.id.amount);
        fee = (TextView) view.findViewById(R.id.fee);
        topSale = (CheckBox) view.findViewById(R.id.topsale);
        pcSale = (CheckBox) view.findViewById(R.id.pcsale);
        NumberFormat numberFormat = NumberFormat.getInstance();

        addEtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewInputLayout();
            }
        });

        resultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str1, str2;
                int amountint = 0;
                double couponDiscount = 0;
                double coupon = 0;
                double Basicfee = 0;
                double totalfee = 0;
                double feetotal = 0;
                double finalAmount = 0;
                double finalFee = 0;
                int total = 0;
                double PC = 0;
                double TOPCLASS = 0;

                //pc방 할인, top클래스 할인 모두 선택했을때
                if (pcSale.isChecked() && topSale.isChecked()) {
                    for (int i = 0; i < editTextList1.size(); i++) {
                        str1 = editTextList1.get(i).getText().toString().replace(",", "");
                        str2 = editTextList2.get(i).getText().toString().replace(",", "");

                        if (!str1.isEmpty() && str2.isEmpty()) {
                            total += Integer.parseInt(str1);
                            amountint = Integer.parseInt(str1);

                            // 기본수수료
                            Basicfee = amountint * 0.4;

                            // PC 및 TOPCLASS 할인을 적용
                            PC = Basicfee * 0.3; // PC 할인 금액
                            TOPCLASS = Basicfee * 0.2; // TOPCLASS 할인 금액


                            // 최종 수수료 계산
                            finalFee = Basicfee - (PC + TOPCLASS);
                            totalfee += finalFee;

                            // 최종 금액 계산
                            finalAmount += amountint - finalFee;
                        } else if (!str1.isEmpty() && !str2.isEmpty()) {
                            total += Integer.parseInt(str1);
                            amountint = Integer.parseInt(str1);

                            // 기본수수료
                            Basicfee = amountint * 0.4;

                            // 쿠폰 할인
                            couponDiscount = Integer.parseInt(str2) / 100.0; // 쿠폰 할인율
                            coupon = Basicfee * couponDiscount; // 쿠폰 할인 금액

                            // PC 및 TOPCLASS 할인을 적용
                            PC = Basicfee * 0.3; // PC 할인 금액
                            TOPCLASS = Basicfee * 0.2; // TOPCLASS 할인 금액

                            // 최종 수수료 계산
                            finalFee = Basicfee - (PC + TOPCLASS + coupon);
                            totalfee += finalFee;

                            // 최종 금액 계산
                            finalAmount += amountint - finalFee;
                        }
                    }

                    //pc방 할인만 선택했을때
                } else if (pcSale.isChecked()) {
                    for (int i = 0; i < editTextList1.size(); i++) {
                        str1 = editTextList1.get(i).getText().toString().replace(",", "");
                        str2 = editTextList2.get(i).getText().toString().replace(",", "");
                        if (!str1.isEmpty() && str2.isEmpty()) {
                            total += Integer.parseInt(str1);
                            amountint = Integer.parseInt(str1);

                            // 기본수수료
                            Basicfee = amountint * 0.4;

                            // PC 할인을 적용
                            PC = Basicfee * 0.3; // PC 할인 금액


                            // 최종 수수료 계산
                            finalFee = Basicfee - PC;
                            totalfee += finalFee;

                            // 최종 금액 계산
                            finalAmount += amountint - finalFee;
                        } else if (!str1.isEmpty() && !str2.isEmpty()) {
                            total += Integer.parseInt(str1);
                            amountint = Integer.parseInt(str1);

                            // 기본수수료
                            Basicfee = amountint * 0.4;

                            // 쿠폰 할인
                            couponDiscount = Integer.parseInt(str2) / 100.0; // 쿠폰 할인율
                            coupon = Basicfee * couponDiscount; // 쿠폰 할인 금액

                            // PC 할인을 적용
                            PC = Basicfee * 0.3; // PC 할인 금액


                            // 최종 수수료 계산
                            finalFee = Basicfee - (PC + coupon);
                            totalfee += finalFee;

                            // 최종 금액 계산
                            finalAmount += amountint - finalFee;
                        }
                    }

                    // top클래스 할인만 선택했을때
                } else if (topSale.isChecked()) {
                    for (int i = 0; i < editTextList1.size(); i++) {
                        str1 = editTextList1.get(i).getText().toString().replace(",", "");
                        str2 = editTextList2.get(i).getText().toString().replace(",", "");
                        if (!str1.isEmpty() && str2.isEmpty()) {
                            total += Integer.parseInt(str1);
                            amountint = Integer.parseInt(str1);

                            // 기본수수료
                            Basicfee = amountint * 0.4;

                            //TOPCLASS 할인을 적용
                            TOPCLASS = Basicfee * 0.2; // TOPCLASS 할인 금액


                            // 최종 수수료 계산
                            finalFee = Basicfee - TOPCLASS;
                            totalfee += finalFee;

                            // 최종 금액 계산
                            finalAmount += amountint - finalFee;
                        } else if (!str1.isEmpty() && !str2.isEmpty()) {
                            total += Integer.parseInt(str1);
                            amountint = Integer.parseInt(str1);

                            // 기본수수료
                            Basicfee = amountint * 0.4;

                            // 쿠폰 할인
                            couponDiscount = Integer.parseInt(str2) / 100.0; // 쿠폰 할인율
                            coupon = Basicfee * couponDiscount; // 쿠폰 할인 금액

                            // TOPCLASS 할인을 적용
                            TOPCLASS = Basicfee * 0.2; // TOPCLASS 할인 금액

                            // 최종 수수료 계산
                            finalFee = Basicfee - (TOPCLASS + coupon);
                            totalfee += finalFee;

                            // 최종 금액 계산
                            finalAmount += amountint - finalFee;
                        }
                    }

                    // pc방, top클래스 모두 선택 안했을때
                } else {
                    for (int i = 0; i < editTextList1.size(); i++) {
                        str1 = editTextList1.get(i).getText().toString().replace(",", "");
                        str2 = editTextList2.get(i).getText().toString().replace(",", "");
                        if (!str1.isEmpty() && str2.isEmpty()) {
                            total += Integer.parseInt(str1);
                            amountint = Integer.parseInt(str1);
                            Basicfee = amountint * 0.4;
                            totalfee += Basicfee;
                            finalAmount += amountint - Basicfee;
                        } else if (!str1.isEmpty() && !str2.isEmpty()) {
                            total += Integer.parseInt(str1);
                            amountint = Integer.parseInt(str1);

                            // 기본수수료
                            Basicfee = amountint * 0.4;

                            // 쿠폰 할인
                            couponDiscount = Integer.parseInt(str2) / 100.0;
                            feetotal = Basicfee * (1 - couponDiscount);

                            totalfee += feetotal;
                            finalAmount += amountint - feetotal;
                        }
                    }
                }

                amount.setText("원래 금액 : " + numberFormat.format(total) + "BP");
                fee.setText("총 수수료 : " + numberFormat.format((int) totalfee) + "BP");
                resultTV.setText("최종 받는 금액 : " + numberFormat.format((int) finalAmount) + "BP");

            }
        });


    }


    /***************************************************************
     *새로운 거래 금액 입력창 추가 메서드
     ****************************************************************/
    private void addNewInputLayout() {
        //새로운 레이아웃 생성
        LinearLayout newLayout = new LinearLayout(view.getContext());
        newLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        newLayout.setOrientation(LinearLayout.HORIZONTAL);

        // EditText 1: 금액 입력
        EditText newET = new EditText(view.getContext());
        newET.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 9f));
        newET.setInputType(InputType.TYPE_CLASS_NUMBER);
        newET.setHint("금액을 입력하세요");
        newET.addTextChangedListener(new NumberTextWatcher(newET));

        // EditText 2: 쿠폰입력
        EditText newET2 = new EditText(view.getContext());
        newET2.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2f));
        newET2.setInputType(InputType.TYPE_CLASS_NUMBER);
        newET2.setHint("쿠폰");
        newET2.setGravity(Gravity.CENTER);
        newET2.addTextChangedListener(new NumberTextWatcher(newET2));

        editTextList1.add(newET);
        editTextList2.add(newET2);

        // ImageButton: 삭제 버튼
        ImageButton deleteBtn = new ImageButton(view.getContext());
        deleteBtn.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2f));
        deleteBtn.setImageResource(R.drawable.baseline_clear_24);
        deleteBtn.setBackgroundColor(android.graphics.Color.TRANSPARENT);

        //삭제버튼 클릭리스너
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentLayout.removeView(newLayout);
                editTextList1.remove(newET);
                editTextList2.remove(newET2);
            }
        });

        // 레이아웃에 View 추가
        newLayout.addView(newET);
        newLayout.addView(newET2);
        newLayout.addView(deleteBtn);

        //parentLayout에 새로운 레이아웃 추가
        parentLayout.addView(newLayout);

    }


    /***************************************************************
     *사용자가 값 입력시 1000단위마다 , 찍어주는 클래스 ex)1,000
     ****************************************************************/
    public class NumberTextWatcher implements TextWatcher {
        private EditText editText;

        public NumberTextWatcher(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            try {
                // 현재 입력된 값을 숫자로 변환
                String originalString = s.toString().replace(",", "");
                if (originalString.isEmpty()) return;

                long input = Long.parseLong(originalString);

                // 쉼표 형식 적용
                DecimalFormat formatter = new DecimalFormat("#,###");
                String formattedString = formatter.format(input);

                // 변경된 텍스트 적용
                editText.removeTextChangedListener(this); // 무한 루프 방지
                editText.setText(formattedString);
                editText.setSelection(formattedString.length()); // 커서를 맨 뒤로 이동
                editText.addTextChangedListener(this); // 리스너 다시 추가
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }


}
