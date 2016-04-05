1.SortContact简介
一个组合控件，你只需要传递你的数据列表（目前只支持对String的转换），此控件就可以对他进行排列，搜索，点击右侧字母将列表数据做出相应改变。
经常适用于联系人列表等
2.build.gradle配置
compile 'com.xiong.sortcontact.library:sort:1.0'
3.怎么使用
具体使用例子我放在app里，很简单的
//先把SortLayout放入你的layout文件
<com.xiong.library.SortLayout
    android:id="@+id/sortLayout"
    android:layout_height="match_parent"
    android:layout_width="match_parent" />
//然后在activity里找到控件
SortLayout sortLayout = (SortLayout) findViewById(R.id.sortLayout);
//把数据dataList跟控件绑定
sortLayout.setData(dataList); 


