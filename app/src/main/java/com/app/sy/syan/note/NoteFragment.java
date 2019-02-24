package com.app.sy.syan.note;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sy.syan.R;
import com.app.sy.syan.SyanApplication;
import com.jakewharton.rxbinding.view.RxView;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class NoteFragment extends Fragment implements NoteContract.View {
    private static final String TAG = NoteFragment.class.getSimpleName();

    @BindView(R.id.et_note)
    EditText etNote;
    @BindView(R.id.btn_note)
    TextView btnNote;

    @Inject
    NotePresenter mNotePresenter;

    public static NoteFragment newInstance() {
        NoteFragment fragment = new NoteFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        ButterKnife.bind(this, view);

        DaggerNoteComponent.builder()
                .applicationComponent(SyanApplication.get(getContext()).getAppComponent())
                .noteModule(new NoteModule(this))
                .build()
                .inject(this);

        RxView.clicks(btnNote)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        String str = etNote.getText().toString();
                        if (TextUtils.isEmpty(str)) {
                            showToast("请输入留言信息");
                            return;
                        }
                        if (mNotePresenter != null) {
                            mNotePresenter.getDate(str);
                        }
                    }
                });

        return view;
    }

    @Override
    public void noteSuccess() {
        etNote.setText("");
        showToast("留言成功");
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
