package com.chint.circlemenudialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Project:RecycleWheelView
 * Author:dyping
 * Date:2017/8/3 14:33
 */

public class CircleActivity extends AppCompatActivity implements DialogMenuFragment.MenuItemInterface{

    private DialogMenuBean bean = new DialogMenuBean();
    DialogMenuFragment fragment ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_activity_new);
        ButterKnife.bind(this);

        fragment = new DialogMenuFragment();

    }

    @OnClick({R.id.dialog_btn, R.id.first, R.id.second, R.id.third, R.id.fourth})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dialog_btn:
                if (bean.getItem() == null || bean.getItem().isEmpty()) {
                    Toast.makeText(this, "请先选择公司后在点击菜单", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("MenuBean", bean);
                fragment.setArguments(bundle);
                fragment.show(getFragmentManager(),"Menu");

                break;
            case R.id.first:

                ArrayList<DialogMenuBean.ItemBean> list = new ArrayList<>();
                list.add(new DialogMenuBean.ItemBean("吃饭", "menu_jigai_cost", "0"));
                bean.setItem(list);

                break;
            case R.id.second:
                ArrayList<DialogMenuBean.ItemBean> list1 = new ArrayList<>();
                for (int i = 0; i < 2; i++) {
                    list1.add(new DialogMenuBean.ItemBean("睡觉", "menu_inland", "1" + i));
                }
                bean.setItem(list1);

                break;
            case R.id.third:

                ArrayList<DialogMenuBean.ItemBean> list2 = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list2.add(new DialogMenuBean.ItemBean("打豆豆", "menu_less_ratio", "2" + i));
                }
                bean.setItem(list2);

                break;
            case R.id.fourth:


                ArrayList<DialogMenuBean.ItemBean> list3 = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    list3.add(new DialogMenuBean.ItemBean("嘿嘿", "menu_jingying_cost", "3" + i));
                }
                bean.setItem(list3);

                break;
        }
    }

    @Override
    public void onMenuItemClick(int itemId) {
        Toast.makeText(this, "点击了ItemId:"+itemId+"的这一项", Toast.LENGTH_SHORT).show();
    }
}
