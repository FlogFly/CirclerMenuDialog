package com.chint.circlemenudialog;

import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Project:ChintDataH5
 * Author:dyping
 * Date:2017/6/26 14:54
 */

public class DialogMenuFragment extends DialogFragment {

    @BindView(R.id.circle_layout)
    CircleLayout circleLayout;

    DialogMenuBean menuBean;


    MenuItemInterface listener;

    Unbinder unbinder;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (MenuItemInterface) activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            menuBean = (DialogMenuBean) bundle.getSerializable("MenuBean");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Material_Light_Dialog);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        if (menuBean != null) {

            circleLayout = (CircleLayout)view.findViewById(R.id.circle_layout);
            circleLayout.removeAllViews();
            if (menuBean.getItem() != null && menuBean.getItem().size() > 0) {
                for (int i = 0; i < menuBean.getItem().size(); i++) {
                    final DialogMenuBean.ItemBean bean = menuBean.getItem().get(i);
                    TextView secondTv = new TextView(getActivity());
                    secondTv.setTextColor(Color.parseColor("#FFFFFF"));
                    secondTv.setTag(bean.getTypeId());
                    secondTv.setText(bean.getName());
                    secondTv.setGravity(Gravity.CENTER);
                    if (!TextUtils.isEmpty(bean.getIconName())) {
                        int resID = getActivity().getResources().getIdentifier(bean.getIconName(), "drawable", getActivity().getPackageName());
                        Drawable topIcon = ContextCompat.getDrawable(getActivity(), resID);
                        topIcon.setBounds(0, 0, topIcon.getMinimumWidth(), topIcon.getMinimumHeight());
                        secondTv.setCompoundDrawables(null, topIcon, null, null);
                    }
                    secondTv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (listener != null) {
                                listener.onMenuItemClick(Integer.parseInt(v.getTag().toString()));
                            }
                        }
                    });
                    circleLayout.addView(secondTv);
                }
            }
        }
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    public interface MenuItemInterface {
        void onMenuItemClick(int itemId);
    }
}
