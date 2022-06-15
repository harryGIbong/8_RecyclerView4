package com.example.taylordesroches.a8_recyclerview4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//Adaptors provide a binding from an app specific data set to the views display in the recycler view

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    String[] SubjectValues; // for string array
    ArrayList<Person> personArrayList;
    Context context;
    View view1;
    ViewHolder viewHolder1;
    TextView textView;

    public MyAdapter(Context context1, ArrayList<Person> SubjectValues1){

        personArrayList = SubjectValues1;
        context = context1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewID;
        public TextView textViewName;
        public TextView textViewEmail;
        public TextView textViewAge;


        public ViewHolder(View v){

            super(v);

            textViewID = (TextView)v.findViewById(R.id.textViewRecyclerItem);
            textViewName = (TextView)v.findViewById(R.id.textViewRecyclerName);
            textViewEmail = (TextView)v.findViewById(R.id.textViewRecyclerEmail);
            textViewAge = (TextView)v.findViewById(R.id.textViewRecyclerAge);
        }
    }

    /*RecyclerView는 ViewHolder를 데이터와 연결할 때 이 메서드를 호출합니다.
    이 메서드는 적절한 데이터를 가져와서 그 데이터를 사용하여 뷰 홀더의 레이아웃을 채웁니다.
    예를 들어 RecyclerView가 이름 목록을 표시하는 경우
    메서드는 목록에서 적절한 이름을 찾아 뷰 홀더의 TextView 위젯을 채울 수 있습니다.*/
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        view1 = LayoutInflater.from(context).inflate(R.layout.recyclerview_layout,parent,false);
        viewHolder1 = new ViewHolder(view1);
        // set size of items here
        viewHolder1.itemView.getLayoutParams().height=512;

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

//        holder.textView.setText(SubjectValues[position]);
        holder.textViewID.setText(String.valueOf(personArrayList.get(position).getId_()));
        holder.textViewName.setText(personArrayList.get(position).getsName_());
        holder.textViewEmail.setText(personArrayList.get(position).getsEmail_());
        holder.textViewAge.setText(personArrayList.get(position).getsAge_());

    }

    @Override
    public int getItemCount(){

//        return SubjectValues.length;
        return personArrayList.size();
    }
}
