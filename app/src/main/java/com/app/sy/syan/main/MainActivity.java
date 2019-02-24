package com.app.sy.syan.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sy.syan.R;
import com.app.sy.syan.SyanApplication;
import com.app.sy.syan.base.BaseActivity;
import com.app.sy.syan.home.HomeFragment;
import com.app.sy.syan.love.LoveFragment;
import com.app.sy.syan.mine.MineFragment;
import com.app.sy.syan.note.NoteFragment;
import com.app.sy.syan.util.ActivityManager;
import com.app.sy.syan.welfare.WelfareFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainContract.View {
    private final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.fragment_content)
    FrameLayout fragmentContent;
    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.ll_note)
    LinearLayout llNote;
    @BindView(R.id.ll_welfare)
    LinearLayout llWelfare;
    @BindView(R.id.ll_mine)
    LinearLayout llMine;
    @BindView(R.id.ll_main_bar)
    LinearLayout llMainBar;
    @BindView(R.id.bottom_layout)
    LinearLayout bottomLayout;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.btn_home)
    ImageView btnHome;
    @BindView(R.id.btn_note)
    ImageView btnNote;
    @BindView(R.id.btn_welfare)
    ImageView btnWelfare;
    @BindView(R.id.btn_mine)
    ImageView btnMine;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.tv_note)
    TextView tvNote;
    @BindView(R.id.tv_welfare)
    TextView tvWelfare;
    @BindView(R.id.tv_mine)
    TextView tvMine;
    @BindView(R.id.btn_love)
    ImageView btnLove;
    @BindView(R.id.ll_love)
    LinearLayout llLove;

    private HomeFragment mHomeFragment;
    private NoteFragment mNoteFragment;
    private LoveFragment mLoveFragment;
    private WelfareFragment mWelfareFragment;
    private MineFragment mMineFragment;

    private int index, currentIndex = -1;//用于记录当前位置
    private long mExitTime;
    private FragmentTransaction ft;
    private FragmentManager fm;
    private Fragment[] mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DaggerMainComponent.builder()
                .applicationComponent(SyanApplication.get(this).getAppComponent())
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
        initView();
    }

    private void initView() {
        mHomeFragment = HomeFragment.newInstance();
        mNoteFragment = NoteFragment.newInstance();
        mLoveFragment = LoveFragment.newInstance();
        mWelfareFragment = WelfareFragment.newInstance();
        mMineFragment = MineFragment.newInstance();

        fm = getSupportFragmentManager();
        mFragments = new Fragment[]{mHomeFragment, mNoteFragment, mLoveFragment, mWelfareFragment, mMineFragment};

        index = 0;
        switchSkip();
    }

    /**
     * 点击跳转
     */
    private void switchSkip() {
        if (index != currentIndex) {
            ft = fm.beginTransaction();
            if (!mFragments[index].isAdded()) {
                ft.add(R.id.fragment_content, mFragments[index]);
            }
            if (currentIndex != -1) {
                ft.hide(mFragments[currentIndex]);
            }
            ft.show(mFragments[index]).commit();
            currentIndex = index;
        }
    }

    @OnClick({R.id.ll_home, R.id.ll_note,R.id.ll_love, R.id.ll_welfare, R.id.ll_mine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_home:
                index = 0;
                btnHome.setImageResource(R.drawable.icon_goods_s);
                btnNote.setImageResource(R.drawable.icon_note_d);
                btnLove.setImageResource(R.drawable.icon_xin);
                btnWelfare.setImageResource(R.drawable.icon_wel_d);
                btnMine.setImageResource(R.drawable.icon_ren_d);

                tvHome.setTextColor(getResources().getColor(R.color.dfc289));
                tvNote.setTextColor(getResources().getColor(R.color.text_default_color));
                tvWelfare.setTextColor(getResources().getColor(R.color.text_default_color));
                tvMine.setTextColor(getResources().getColor(R.color.text_default_color));
                break;
            case R.id.ll_note:
                index = 1;

                btnHome.setImageResource(R.drawable.icon_goods);
                btnNote.setImageResource(R.drawable.icon_note_s);
                btnLove.setImageResource(R.drawable.icon_xin);
                btnWelfare.setImageResource(R.drawable.icon_wel_d);
                btnMine.setImageResource(R.drawable.icon_ren_d);

                tvHome.setTextColor(getResources().getColor(R.color.text_default_color));
                tvNote.setTextColor(getResources().getColor(R.color.dfc289));
                tvWelfare.setTextColor(getResources().getColor(R.color.text_default_color));
                tvMine.setTextColor(getResources().getColor(R.color.text_default_color));
                break;
                case R.id.ll_love:
                index = 2;

                btnHome.setImageResource(R.drawable.icon_goods);
                btnNote.setImageResource(R.drawable.icon_note_d);
                btnLove.setImageResource(R.drawable.icon_xin_s);
                btnWelfare.setImageResource(R.drawable.icon_wel_d);
                btnMine.setImageResource(R.drawable.icon_ren_d);

                tvHome.setTextColor(getResources().getColor(R.color.text_default_color));
                tvNote.setTextColor(getResources().getColor(R.color.text_default_color));
                tvWelfare.setTextColor(getResources().getColor(R.color.text_default_color));
                tvMine.setTextColor(getResources().getColor(R.color.text_default_color));
                break;
            case R.id.ll_welfare:
                index = 3;

                btnHome.setImageResource(R.drawable.icon_goods);
                btnNote.setImageResource(R.drawable.icon_note_d);
                btnLove.setImageResource(R.drawable.icon_xin);
                btnWelfare.setImageResource(R.drawable.icon_wel_s);
                btnMine.setImageResource(R.drawable.icon_ren_d);

                tvHome.setTextColor(getResources().getColor(R.color.text_default_color));
                tvNote.setTextColor(getResources().getColor(R.color.text_default_color));
                tvWelfare.setTextColor(getResources().getColor(R.color.dfc289));
                tvMine.setTextColor(getResources().getColor(R.color.text_default_color));
                break;
            case R.id.ll_mine:
                index = 4;

                btnHome.setImageResource(R.drawable.icon_goods);
                btnNote.setImageResource(R.drawable.icon_note_d);
                btnLove.setImageResource(R.drawable.icon_xin);
                btnWelfare.setImageResource(R.drawable.icon_wel_d);
                btnMine.setImageResource(R.drawable.icon_ren_s);

                tvHome.setTextColor(getResources().getColor(R.color.text_default_color));
                tvNote.setTextColor(getResources().getColor(R.color.text_default_color));
                tvWelfare.setTextColor(getResources().getColor(R.color.text_default_color));
                tvMine.setTextColor(getResources().getColor(R.color.dfc289));
                break;
            default:
                break;
        }

        switchSkip();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            ActivityManager.instance().finishActivities();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
