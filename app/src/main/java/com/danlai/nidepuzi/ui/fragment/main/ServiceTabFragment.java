package com.danlai.nidepuzi.ui.fragment.main;

import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.databinding.FragmentServiceTabBinding;
import com.danlai.nidepuzi.entity.UserInfoBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFUserInfo;

/**
 * @author wisdom
 * @date 2017年04月25日 下午4:59
 */

public class ServiceTabFragment extends BaseFragment<FragmentServiceTabBinding> implements View.OnClickListener {
    private String userId, phone, nick, avatar, email;

    public static ServiceTabFragment newInstance() {
        return new ServiceTabFragment();
    }

    @Override
    public void setListener() {
        b.img.setOnClickListener(this);
    }

    @Override
    public View getLoadingView() {
        return b.loadingView;
    }

    @Override
    public void initData() {
        BaseApp.getMainInteractor(mActivity)
            .getProfile(new ServiceResponse<UserInfoBean>(mFragment) {
                @Override
                public void onNext(UserInfoBean userInfoBean) {
                    userId = userInfoBean.getUser_id();
                    phone = userInfoBean.getPhone();
                    nick = userInfoBean.getNick();
                    email = userInfoBean.getEmail();
                    avatar = userInfoBean.getThumbnail();
                    hideIndeterminateProgressDialog();
                }

                @Override
                public void onError(Throwable e) {
                    JUtils.Toast("数据加载失败");
                    initDataError();
                    hideIndeterminateProgressDialog();
                    initDataError();
                }
            });
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_service_tab;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img:
                String title = "铺子客服";
                YSFUserInfo ysfUserInfo = new YSFUserInfo();
                ysfUserInfo.userId = userId;
                ysfUserInfo.data = "[ " +
                    "{\"key\":\"real_name\", \"value\":\"" + nick + "\"}, " +
                    "{\"key\":\"mobile_phone\", \"value\":\"" + phone + "\"}, " +
                    "{\"key\":\"email\", \"value\":\"" + email + "\"}, " +
                    "{\"key\":\"avatar\", \"value\": \"" + avatar + "\"}]";
                Unicorn.setUserInfo(ysfUserInfo);
                ConsultSource source = new ConsultSource("http://m.nidepuzi.com", "Android客户端", "Android客户端");
                Unicorn.openServiceActivity(mActivity, title, source);
                break;
        }
    }
}
