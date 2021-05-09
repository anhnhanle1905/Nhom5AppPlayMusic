package com.hieunghia.dmt.appnghenhac.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.hieunghia.dmt.appnghenhac.Model.PlayList;
import com.hieunghia.dmt.appnghenhac.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.hieunghia.dmt.appnghenhac.R.layout.dong_playlist;

public class PlayListAdapter extends ArrayAdapter<PlayList> {
    public PlayListAdapter(@NonNull Context context, int resource, @NonNull List<PlayList> objects) {
        super(context, resource, objects);
    }
    class ViewHolder{
        TextView txttenplaylist;
        ImageView imgbackground, imgplaylist;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) { //gắn những layout bên phần item vào list
        ViewHolder viewHolder = null;
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dong_playlist, null);
            viewHolder = new ViewHolder();
            viewHolder.txttenplaylist = convertView.findViewById(R.id.textviewtenplaylist);
            viewHolder.imgplaylist = convertView.findViewById(R.id.imageviewplaylist);
            viewHolder.imgbackground = convertView.findViewById(R.id.imageviewbackgroundplaylist);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (PlayListAdapter.ViewHolder) convertView.getTag();
        }
        // gắn dữ liệu vào các view
        PlayList playList = getItem(position); // mỗi dòng item sẽ nhận 1 object thì t lấy các object đó về
        Picasso.with(getContext()).load(playList.getHinhNen()).into(viewHolder.imgbackground);
        Picasso.with(getContext()).load(playList.getHinhIcon()).into(viewHolder.imgplaylist);
        viewHolder.txttenplaylist.setText(playList.getTen());
        return convertView;
    }

}
