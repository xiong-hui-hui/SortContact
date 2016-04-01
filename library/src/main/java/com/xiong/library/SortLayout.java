package com.xiong.library;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by hui.xiong
 */
public class SortLayout extends LinearLayout implements SideBar.OnTouchTextListener{

    private RecyclerView mRecycleView;
    private EditText search;
    private RvAdapter mAdapter;
    private List<SortModel> sortList;
    private SideBar sideBar;
    private Context mContext;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(View view);
    }

    public SortLayout(Context context) {
        this(context,null);
    }
    public SortLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public SortLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext =context ;
        LayoutInflater.from(context).inflate(R.layout.layout_sort,this,true);
        mRecycleView = (RecyclerView) findViewById(R.id.rv);
        search = (EditText) findViewById(R.id.editSearch);
        sideBar = (SideBar) findViewById(R.id.sidebar);
        sideBar.setOnTouchTextListener(this);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (sortList != null)
                mAdapter.updateRecyclerView(Sort.filterData(s.toString(),sortList));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * must be required
     * @param list
     */
    public void setData(List<String> list){
        this.sortList = Sort.sortData(list);
        sideBar.setVisibility(View.VISIBLE);
        mAdapter = new RvAdapter(sortList);
        mRecycleView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycleView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null)
                mListener.onItemClick(v);
            }
        });
    }

    @Override
    public void onTouchText(String text) {
        int position = mAdapter.getPositionForSection(text.charAt(0));
        if (position != -1) {
           ((LinearLayoutManager) mRecycleView.getLayoutManager()).scrollToPositionWithOffset(position,0);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){
       this.mListener = listener;
    }
}
