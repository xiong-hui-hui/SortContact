package com.xiong.sortcontact;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xiong.library.SortLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String[] data = new String[] { "伊拉克","阿富汗","孟加拉国","文莱", "柬埔寨", "朝鲜",
            "印度", "伊朗", "以色列", "约旦", "老挝", "中国澳门", "马尔代夫", "尼泊尔", "巴基斯坦",
            "菲律宾", "沙特阿拉伯", "韩国", "叙利亚", "ss","SS","土耳其", "也门共和国", "中国", "东帝汶", "吉尔吉斯斯坦",
            "土库曼斯坦", "阿尔及利亚","开江","aa","bb","BB"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SortLayout sortLayout = (SortLayout) findViewById(R.id.sortLayout);
        List<String> dataList = new ArrayList<String>();
        for (int i = 0; i < data.length; i++) {
            dataList.add(data[i]);
        }
        sortLayout.setData(dataList); //must be used
        sortLayout.setOnItemClickListener(new SortLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                TextView tv = (TextView) view.findViewById(R.id.tvContent);
                Toast.makeText(MainActivity.this, "点击 "+tv.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
