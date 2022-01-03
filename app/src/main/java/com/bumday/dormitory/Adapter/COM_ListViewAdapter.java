package com.bumday.dormitory.Adapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumday.dormitory.DataBase.Posts;
import com.bumday.dormitory.LiskVO.ListCOM;
import com.bumday.dormitory.LiskVO.ListVO;
import com.bumday.dormitory.R;
import com.bumday.dormitory.Stu_0_main;
import com.bumday.dormitory.Stu_3_community;
import com.bumday.dormitory.Stu_3_community_view;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class COM_ListViewAdapter extends BaseAdapter {
    private ArrayList<ListCOM> list_com = new ArrayList<ListCOM>();
    private ArrayList<Posts> com_list;
    private Activity activity;

    public COM_ListViewAdapter(Activity activity,ArrayList<Posts> com_list)
    {
        this.activity = activity;
        this.com_list = com_list;
    }
    @Override
    public int getCount() {
        return list_com.size();
    }

    //** 이 부분에서 리스트뷰에 데이터를 넣어줌 **
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final int pos= position;
        final Context context = parent.getContext();

        if(convertView ==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.com_listview,parent,false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.com_title);
        TextView post = (TextView)convertView.findViewById(R.id.com_post);
        TextView time = (TextView)convertView.findViewById(R.id.com_time);

        ListCOM listViewItem = list_com.get(position);

        //아이템 내 각 위젯에 데이터 반영
        title.setText(listViewItem.getTitle());
        post.setText(listViewItem.getContext());
        time.setText(listViewItem.getTime());

        //리스트뷰 클릭 이벤트
        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Posts posts = new Posts();
                for(int i=0;i<com_list.size();i++)
                {
                    if((pos + 1)==com_list.get(i).getNum())
                    {
                        posts=com_list.get(i);
                        break;
                    }
                }
                Intent intent = new Intent(activity, Stu_3_community_view.class);
                intent.putExtra("게시글정보",posts);
                activity.startActivity(intent);
                activity.finish();
            }
        });

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position){
        return list_com.get(position);
    }

    //데이터값 넣어줌
    public void addItem(Posts posts){
        ListCOM item = new ListCOM();

        item.setTitle(posts.getTitle());
        item.setContext(posts.getPost());
        item.setTime(posts.getTime());
        list_com.add(item);
    }
}
