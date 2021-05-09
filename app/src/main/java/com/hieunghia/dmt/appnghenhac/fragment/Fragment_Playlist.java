package com.hieunghia.dmt.appnghenhac.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hieunghia.dmt.appnghenhac.Adapter.PlayListAdapter;
import com.hieunghia.dmt.appnghenhac.Model.PlayList;
import com.hieunghia.dmt.appnghenhac.R;
import com.hieunghia.dmt.appnghenhac.Service.APIRetrofitClient;
import com.hieunghia.dmt.appnghenhac.Service.APIService;
import com.hieunghia.dmt.appnghenhac.Service.DataService;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Playlist extends Fragment {

    View view;
    ListView lvplaylist;
    TextView txttitleplaylist,txtviewxemthemplaylist;
    PlayListAdapter playListAdapter;
    ArrayList<PlayList> mangplaylist;
    @Nullable
    @Override // onCreateView de gan layout vao` fragment nay`
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmentplaylist, container, false);
        lvplaylist = view.findViewById(R.id.listviewplaylist);
        txttitleplaylist = view.findViewById(R.id.textviewtitleplaylist);
        txtviewxemthemplaylist = view.findViewById(R.id.textviewmoreplaylist);
        GetData();
        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService();      // doc du lieu, gui len service nhung cau hinh` de cho no tuong tac vs du lieu nhu the nao
        Call<List<PlayList>> callback = dataService.GetPlayListCurrentDay(); // gui phuong thuc getplaylistcurrentday();
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) { // su kien lang nghe
                mangplaylist = (ArrayList<PlayList>) response.body();
                playListAdapter = new PlayListAdapter(getActivity(), android.R.layout.simple_list_item_1, mangplaylist);
                lvplaylist.setAdapter(playListAdapter);
                setListViewHeightBasedOnChildren(lvplaylist);
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }
    // stackoverflow https://stackoverflow.com/questions/21620764/android-listview-measure-height lam` cho view dep hon
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
