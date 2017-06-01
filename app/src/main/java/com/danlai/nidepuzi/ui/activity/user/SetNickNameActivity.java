package com.danlai.nidepuzi.ui.activity.user;

import android.os.Bundle;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivitySetNickNameBinding;
import com.danlai.nidepuzi.entity.NicknameBean;
import com.danlai.nidepuzi.entity.UserBean;
import com.danlai.nidepuzi.entity.event.UserInfoEvent;
import com.danlai.nidepuzi.service.ServiceResponse;

import org.greenrobot.eventbus.EventBus;

/**
 * @author wisdom
 * @date 2017年06月01日 下午5:08
 */

public class SetNickNameActivity extends BaseMVVMActivity<ActivitySetNickNameBinding>
    implements View.OnClickListener {

    private int id;
    private String nick;
    private NicknameBean mNicknameBean = new NicknameBean();

    @Override
    protected void setListener() {
        b.commit.setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        b.etName.setText(nick);
        b.etName.setSelection(nick.length());
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        id = extras.getInt("id");
        nick = extras.getString("nick");
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_set_nick_name;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commit:
                String trim = b.etName.getText().toString().trim();
                if (trim.length() < 2) {
                    JUtils.Toast("请按照要求填写姓名");
                } else {
                    mNicknameBean.setNick(trim);
                    BaseApp.getUserInteractor(mBaseActivity)
                        .setNickName(id, mNicknameBean, new ServiceResponse<UserBean>(mBaseActivity) {
                            @Override
                            public void onNext(UserBean userBean) {
                                EventBus.getDefault().post(new UserInfoEvent());
                                if (userBean.getCode() == 0) {
                                    JUtils.Toast("修改成功");
                                    finish();
                                } else {
                                    JUtils.Toast("修改失败");
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                JUtils.Toast("修改失败!");
                            }
                        });
                }
                break;
        }
    }
}
