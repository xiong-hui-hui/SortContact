package com.xiong.library;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hui.xiong on 2016/3/31.
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> implements SectionIndexer{

    private List<SortModel> list;
    private View.OnClickListener mListener;

    public RvAdapter(List<SortModel> list){
        this.list = list;
    }

    public void updateRecyclerView(List<SortModel> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv,parent,false);
        view.setOnClickListener(mListener);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        int section = getSectionForPosition(position);
        SortModel content = list.get(position);
        if (position == getPositionForSection(section)){
            holder.tvLetter.setVisibility(View.VISIBLE);
            holder.tvLetter.setText(content.getSortLetters().charAt(0)+"");
        } else {
            holder.tvLetter.setVisibility(View.GONE);
        }
        holder.tvContent.setText(content.getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    /**
     *get position for Char ASCII
     */
    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i = 0; i < list.size(); i++){
//            Log.e("xx","    "+list.get(i).getSortLetters().charAt(0));
            if (list.get(i).getSortLetters().charAt(0) == sectionIndex) return i;
        }
        return -1;
    }
    /**
     * get first Char ASCII from position
     */
    @Override
    public int getSectionForPosition(int position) {
        return list.get(position).getSortLetters().charAt(0);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvLetter;
        TextView tvContent;
        View viewDivider;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvLetter = (TextView) itemView.findViewById(R.id.tvLetter);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
            viewDivider = itemView.findViewById(R.id.viewDivider);
        }
    }

    public void setOnItemClickListener(View.OnClickListener listener){
        mListener = listener;
    }

    @Override
    public Object[] getSections() {
        return null;
    }
}
