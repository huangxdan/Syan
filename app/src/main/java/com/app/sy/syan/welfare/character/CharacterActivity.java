package com.app.sy.syan.welfare.character;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sy.syan.R;
import com.app.sy.syan.SyanApplication;
import com.app.sy.syan.base.BaseActivity;
import com.app.sy.syan.data.CharacterInfo;
import com.app.sy.syan.data.StaffInfo;
import com.app.sy.syan.util.Constant;
import com.app.sy.syan.util.PreferenceUtils;
import com.app.sy.syan.view.NavigationBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 人物关系
 *
 * @author admin
 */
public class CharacterActivity extends BaseActivity implements NavigationBar.NavigationBarInteface, CharacterContract.View {

    @BindView(R.id.pub_navi_left_item_icon)
    ImageView pubNaviLeftItemIcon;
    @BindView(R.id.pub_navi_left_item_title)
    TextView pubNaviLeftItemTitle;
    @BindView(R.id.pub_navi_left_item)
    RelativeLayout pubNaviLeftItem;
    @BindView(R.id.pub_navi_center_item_title)
    TextView pubNaviCenterItemTitle;
    @BindView(R.id.pub_navi_center_item_icon)
    ImageView pubNaviCenterItemIcon;
    @BindView(R.id.pub_navi_center_item)
    RelativeLayout pubNaviCenterItem;
    @BindView(R.id.pub_navi_right_item_icon)
    ImageView pubNaviRightItemIcon;
    @BindView(R.id.pub_navi_right_item_title)
    TextView pubNaviRightItemTitle;
    @BindView(R.id.pub_navi_right_item)
    RelativeLayout pubNaviRightItem;
    @BindView(R.id.ll_navi_root)
    LinearLayout llNaviRoot;
    @BindView(R.id.tv_tgr)
    TextView tvTgr;
    @BindView(R.id.tv_tjr_number)
    TextView tvTjrNumber;
    @BindView(R.id.tv_jdr)
    TextView tvJdr;
    @BindView(R.id.tv_jdr_number)
    TextView tvJdrNumber;
    @BindView(R.id.tv_br)
    TextView tvBr;
    @BindView(R.id.tv_br_number)
    TextView tvBrNumber;
    @BindView(R.id.tv_br_money)
    TextView tvBrMoney;
    @BindView(R.id.tv_jdr_money)
    TextView tvJdrMoney;
    @BindView(R.id.tv_tjr_money)
    TextView tvTjrMoney;
    @BindView(R.id.rl_tgr)
    RelativeLayout rlTgr;
    @BindView(R.id.rl_jdr)
    RelativeLayout rljRr;

    @Inject
    CharacterPresenter characterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        ButterKnife.bind(this);
        DaggerCharacterComponent.builder()
                .applicationComponent(SyanApplication.get(this).getAppComponent())
                .characterModule(new CharacterModule(this))
                .build()
                .inject(this);
        initView();

        if (characterPresenter != null) {
            characterPresenter.getData(PreferenceUtils.getPrefString(CharacterActivity.this, Constant.STAFF_NUMBER, ""));
        }
    }


    private void initView() {
        NavigationBar navigationBar = new NavigationBar(this, this);
        navigationBar.setCenterItemIconShow();
        navigationBar.setLeftItemTitleHidden();
        navigationBar.setRightItemHidden();
        navigationBar.setCenterTitle("我的经销商");
        navigationBar.setCenterItemIconHidden();
    }

    @Override
    public void onNavigationLeftItemClick() {
        finish();
    }

    @Override
    public void onNavigationRightItemClick() {

    }

    @Override
    public void onNavigationCenterItemClick() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(CharacterActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bindData(CharacterInfo characterInfo) {
        if (characterInfo != null) {
            //本人
            tvBr.setText(characterInfo.getStaffName());
            tvBrNumber.setText(characterInfo.getStaffNumber());
            tvBrMoney.setText("累计：" + characterInfo.getPersonCountTotal() + "枚");

            if(!TextUtils.isEmpty(characterInfo.getContactPersonA())){
                rlTgr.setVisibility(View.VISIBLE);
                tvTgr.setText(characterInfo.getContactPersonA());
                tvTjrNumber.setText(characterInfo.getContactPersonNumberA());
                tvTjrMoney.setText("累计：" + characterInfo.getPersonCountA() + "枚");
            }
            if(!TextUtils.isEmpty(characterInfo.getContactPersonB())){
                rljRr.setVisibility(View.VISIBLE);
                tvJdr.setText(characterInfo.getContactPersonB());
                tvJdrNumber.setText(characterInfo.getContactPersonNumberB());
                tvJdrMoney.setText("累计：" + characterInfo.getPersonCountB() + "枚");
            }

        }
    }
}
