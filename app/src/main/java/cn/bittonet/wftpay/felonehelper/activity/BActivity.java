package cn.bittonet.wftpay.felonehelper.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;
import cn.bittonet.wftpay.felonehelper.R;
import cn.bittonet.wftpay.felonehelper.utils.Sidebar.ClearEditText;
import cn.bittonet.wftpay.felonehelper.utils.Sidebar.PinyinComparator;
import cn.bittonet.wftpay.felonehelper.utils.Sidebar.PinyinUtils;
import cn.bittonet.wftpay.felonehelper.utils.Sidebar.SortAdapter;
import cn.bittonet.wftpay.felonehelper.utils.Sidebar.SortModel;
import cn.bittonet.wftpay.felonehelper.utils.Sidebar.TitleItemDecoration;
import cn.bittonet.wftpay.wavesidebar.WaveSideBar;
import com.chad.library.adapter.base.BaseQuickAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BActivity extends AppCompatActivity {
    private WaveSideBar   mSideBar;
    private RecyclerView  mRecyclerView;
    private ClearEditText mClearEditText;
    private String[] data = {"裘豆豆","B李莎","jb","Jobs","动力火车","伍佰","#蔡依林","$797835344$",
            "Jack","9527","戚薇","齐期浩二","齐天大圣","品冠","吴克群","贲素琴","缪丝","成龙",
            "王力宏","汪峰","王菲","那英","张伟","~夏先生","阿aaa",
            "阿李珊","陈奕迅","周杰伦","曾一鸣","哈林","高进","高雷","阮金天","龚琳娜",
            "苏醒","陈奕迅","周杰伦","张学友","哈林","李德华","高雷","白亮","龚琳娜",};
    /**
     * 根据拼音来排列RecyclerView里面的数据类
     */
    private PinyinComparator    mComparator;
    private List<SortModel>     mDateList;
    private LinearLayoutManager manager;
    private SortAdapter         mAdapter;
    private TitleItemDecoration mDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        initView();
    }

    private void initView() {
        mSideBar = (WaveSideBar) findViewById(R.id.sideBar);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);
        mComparator = new PinyinComparator();
        //设置右侧SideBar触摸监听
        mSideBar.setOnTouchLetterChangeListener(new WaveSideBar.OnTouchLetterChangeListener() {
            @Override
            public void onLetterChange(String letter) {
                //该字母首次出现的位置
                int position = mAdapter.getPositionForSection(letter.charAt(0));
                if (position != -1) {
                    manager.scrollToPositionWithOffset(position, 0);
                }
            }
        });
        mDateList = filledData(data);

        // 根据a-z进行排序源数据
        Collections.sort(mDateList, mComparator);

        //RecyclerView设置manager
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new SortAdapter(mDateList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(BActivity.this, mDateList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        mDecoration = new TitleItemDecoration(this, mDateList);
        //如果add两个，那么按照先后顺序，依次渲染。
        mRecyclerView.addItemDecoration(mDecoration);
            mRecyclerView.addItemDecoration(new DividerItemDecoration(  BActivity.this, DividerItemDecoration.VERTICAL));


        mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);

        //根据输入框输入值的改变来过滤搜索
        mClearEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                    int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * 为RecyclerView填充数据
     *
     * @param date
     * @return
     */
    private List<SortModel> filledData(String[] date) {
        List<SortModel> mSortList = new ArrayList<>();

        for (int i = 0; i < date.length; i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(date[i]);
            //汉字转换成拼音
            String pinyin = PinyinUtils.getPingYin(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setLetters(sortString.toUpperCase());
            } else {
                sortModel.setLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;
    }

    /**
     * 根据输入框中的值来过滤数据并更新RecyclerView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<SortModel> filterDateList = new ArrayList<>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = filledData(data);
        } else {
            filterDateList.clear();
            for (SortModel sortModel : mDateList) {
                String name = sortModel.getName();
                if (name.indexOf(filterStr.toString()) != -1 ||
                        PinyinUtils.getFirstSpell(name).startsWith(filterStr.toString())
                        //不区分大小写
                        || PinyinUtils.getFirstSpell(name).toLowerCase().startsWith(filterStr.toString())
                        || PinyinUtils.getFirstSpell(name).toUpperCase().startsWith(filterStr.toString())
                        ) {
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, mComparator);
        mDateList.clear();
        mDateList.addAll(filterDateList);
        mAdapter.notifyDataSetChanged();
    }
}
