package com.hieunghia.dmt.appnghenhac.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.NoCopySpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.hieunghia.dmt.appnghenhac.Activity.DanhsachbaihatActivity;
import com.hieunghia.dmt.appnghenhac.Activity.DanhsachtatcachudeActivity;
import com.hieunghia.dmt.appnghenhac.Model.ChuDe;
import com.hieunghia.dmt.appnghenhac.Model.TheLoai;
import com.hieunghia.dmt.appnghenhac.Model.TheLoaiHomNay;
import com.hieunghia.dmt.appnghenhac.R;
import com.hieunghia.dmt.appnghenhac.Service.APIService;
import com.hieunghia.dmt.appnghenhac.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Chu_De_The_Loai extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtxemthechudetheloai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chu_de_the_loai,container,false);
        horizontalScrollView = view.findViewById(R.id.horizontalScrollview);
        txtxemthechudetheloai = view.findViewById(R.id.textviewxemthemchudevatheloai);
        txtxemthechudetheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhsachtatcachudeActivity.class);
                startActivity(intent);


            }
        });
        GetData();
        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<TheLoaiHomNay> callback = dataService.GetCateGoryMusic();
        callback.enqueue(new Callback<TheLoaiHomNay>() {
            @Override
            public void onResponse(Call<TheLoaiHomNay> call, Response<TheLoaiHomNay> response) {
                TheLoaiHomNay theLoaiHomNay = response.body();
                final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
                chuDeArrayList.addAll(theLoaiHomNay.getChuDe());

                final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
                theLoaiArrayList.addAll(theLoaiHomNay.getTheLoai());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(500, 250); // nếu kích thước to quá set lại
                layoutParams.setMargins(10, 20, 10, 30);
                for (int i = 0; i < (chuDeArrayList.size()); i++) // dùng vòng lặp for để gắn ảnh vào
                {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);                             // bo tròn lại
                    ImageView imageView = new ImageView(getActivity());  // đưa hình ảnh vào trong cardview
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);  //nếu bức ảnh nhỏ quá sẽ làm vừa lại
                    if (chuDeArrayList.get(i).getHinhChuDe() != null)
                    {
                        Picasso.with(getActivity()).load(chuDeArrayList.get(i).getHinhChuDe()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                }

                for (int j = 0; j < (theLoaiArrayList.size()); j++) // dùng vòng lặp for để gắn ảnh vào
                {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);                             // bo tròn lại
                    ImageView imageView = new ImageView(getActivity());  // đưa hình ảnh vào trong cardview
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);  //nếu bức ảnh nhỏ quá sẽ làm vừa lại
                    if (theLoaiArrayList.get(j).getHinhTheLoai() != null)
                    {
                        Picasso.with(getActivity()).load(theLoaiArrayList.get(j).getHinhTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
                            intent.putExtra("idtheloai", theLoaiArrayList.get(finalJ) );
                            startActivity(intent);
                        }
                    });
                }
                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<TheLoaiHomNay> call, Throwable t) {

            }
        });
    }
}
