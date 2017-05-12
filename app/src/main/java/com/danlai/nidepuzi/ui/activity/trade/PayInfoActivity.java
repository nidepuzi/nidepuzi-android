package com.danlai.nidepuzi.ui.activity.trade;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.danlai.library.utils.CameraUtils;
import com.danlai.library.utils.IdCardChecker;
import com.danlai.library.utils.JUtils;
import com.danlai.library.widget.zxing.decode.Utils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.CartPayInfoAdapter;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.base.BaseWebViewActivity;
import com.danlai.nidepuzi.databinding.ActivityPayInfoBinding;
import com.danlai.nidepuzi.entity.AddressBean;
import com.danlai.nidepuzi.entity.AddressResultBean;
import com.danlai.nidepuzi.entity.CartsPayInfoBean;
import com.danlai.nidepuzi.entity.IdCardBean;
import com.danlai.nidepuzi.entity.PayInfoBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.main.TabActivity;
import com.danlai.nidepuzi.ui.activity.user.AddAddressActivity;
import com.danlai.nidepuzi.ui.activity.user.AddressSelectActivity;
import com.danlai.nidepuzi.ui.activity.user.SelectCouponActivity;
import com.danlai.nidepuzi.util.JumpUtils;
import com.danlai.nidepuzi.util.pay.PayUtils;
import com.google.gson.Gson;

import java.io.File;
import java.util.List;

/**
 * @author wisdom
 * @date 2017年04月13日 下午5:54
 */

public class PayInfoActivity extends BaseMVVMActivity<ActivityPayInfoBinding>
    implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private static final int REQUEST_CODE_COUPON = 2;
    private static final int REQUEST_CODE_ADDRESS = 3;
    private static final String APP_PAY = "pid:1:value:";
    private static final String COUPON_PAY = "pid:2:couponid:";
    private static final String BUDGET_PAY = "pid:3:budget:";
    private static final String COIN_PAY = "pid:4:budget:";
    private static final String ALIPAY = "alipay";
    private static final String WX = "wx";
    private static final String BUDGET = "budget";
    private File file;
    private String mCartIds, uuid, pay_extras, coupon_id, addressId, channel, id_no,
        state, city, district, address, name, mobile, face, back, side, card_facepath, card_backpath;
    private double payment, post_fee, total_fee, discount_fee, coupon_price, appCut, walletCash,
        coinCash, reallyPayment, realUseWallet, realUseCoin, totalDiscount;
    private boolean isCoupon, isBudget, isCoin, isHaveAddress,
        isWx, isAlipay, payFlag, isDefault;
    private CartPayInfoAdapter mCartPayInfoAdapter;
    private AlertDialog mRuleDialog;
    private int needLevel, personalInfoLevel, order_id;

    @Override
    public void getIntentUrl(Uri uri) {
        mCartIds = uri.getQueryParameter("cart_id");
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        mCartIds = extras.getString("ids");
    }

    @Override
    protected void initViews() {
        payFlag = false;
        isHaveAddress = false;
        order_id = -1;
        mCartPayInfoAdapter = new CartPayInfoAdapter(this);
        b.lvGoods.setAdapter(mCartPayInfoAdapter);
        mRuleDialog = new AlertDialog.Builder(this)
            .setTitle("购买须知")
            .setMessage(getResources().getString(R.string.buy_rule))
            .setPositiveButton("同意", (dialog, which) -> dialog.dismiss())
            .create();
        if (!((BaseApp) getApplication()).isShow()) {
            Dialog vipDialog = new Dialog(mBaseActivity, R.style.CustomDialog);
            vipDialog.setContentView(R.layout.pop_join_vip);
            vipDialog.setCancelable(true);
            ((TextView) vipDialog.findViewById(R.id.tv_btn)).setText("立刻使用");
            ((TextView) vipDialog.findViewById(R.id.tv_desc)).setText(Html.fromHtml("您还有<big>200</big>元购物礼券哦!"));
            vipDialog.findViewById(R.id.cancel).setOnClickListener(v -> vipDialog.dismiss());
            vipDialog.findViewById(R.id.tv_btn).setOnClickListener(v -> {
                vipDialog.dismiss();
                JumpUtils.jumpToWebViewWithCookies(mBaseActivity,
                    "https://m.nidepuzi.com/mall/boutiqueinvite2", -1, BaseWebViewActivity.class);
            });
            vipDialog.show();
            ((BaseApp) getApplication()).setShow(true);
        }
    }

    @Override
    protected void setListener() {
        b.addressLayout.setOnClickListener(this);
        b.confirm.setOnClickListener(this);
        b.cbWallet.setOnCheckedChangeListener(this);
        b.cbRule.setOnCheckedChangeListener(this);
        b.cbCoin.setOnCheckedChangeListener(this);
        b.tvRule.setOnClickListener(this);
        b.btnId.setOnClickListener(this);
        b.imageIdFace.setOnClickListener(this);
        b.imageIdBack.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        showIndeterminateProgressDialog(false);
        loadData(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isHaveAddress) {
            initAddress();
        }
        if (file != null) {
            showIndeterminateProgressDialog(false);
            setDialogContent("上传中...");
            new CompressTask().execute(file.getPath());
        }
    }

    private void initAddress() {
        BaseApp.getAddressInteractor(this)
            .getAddressList(new ServiceResponse<List<AddressBean>>(mBaseActivity) {
                @Override
                public void onNext(List<AddressBean> list) {
                    if (list != null && list.size() > 0) {
                        b.chooseAddress.setVisibility(View.GONE);
                        b.tvName.setVisibility(View.VISIBLE);
                        b.tvPhone.setVisibility(View.VISIBLE);
                        b.tvAddress.setVisibility(View.VISIBLE);
                        AddressBean addressBean = list.get(0);
                        personalInfoLevel = addressBean.getPersonalinfo_level();
                        addressId = addressBean.getId() + "";
                        state = addressBean.getReceiver_state();
                        city = addressBean.getReceiver_city();
                        district = addressBean.getReceiver_district();
                        address = addressBean.getReceiver_address();
                        name = addressBean.getReceiver_name();
                        mobile = addressBean.getReceiver_mobile();
                        isDefault = addressBean.isDefaultX();
                        face = addressBean.getIdcard().getFace();
                        back = addressBean.getIdcard().getBack();
                        loadIdImage();
                        b.tvName.setText(addressBean.getReceiver_name());
                        b.tvPhone.setText(addressBean.getReceiver_mobile());
                        b.tvAddress.setText(addressBean.getReceiver_state()
                            + addressBean.getReceiver_city()
                            + addressBean.getReceiver_district()
                            + addressBean.getReceiver_address());
                        id_no = addressBean.getIdentification_no();
                        setIdNo();
                        isHaveAddress = true;
                    } else {
                        hideAddress();
                    }
                }

                @Override
                public void onError(Throwable e) {
                    hideAddress();
                }
            });
    }

    private void loadIdImage() {
        if (face != null && !"".equals(face)) {
            Glide.with(mBaseActivity).load(face).into(b.imageIdFace);
        } else {
            Glide.with(mBaseActivity).load(R.drawable.icon_id_before).into(b.imageIdFace);
        }
        if (back != null && !"".equals(back)) {
            Glide.with(mBaseActivity).load(back).into(b.imageIdBack);
        } else {
            Glide.with(mBaseActivity).load(R.drawable.icon_id_after).into(b.imageIdBack);
        }
    }

    private void setIdNo() {
        if (id_no != null && !"".equals(id_no)) {
            b.tvId.setText(id_no.replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1**********$2"));
            b.etId.setText("");
            b.btnId.setText("修改");
            b.tvId.setVisibility(View.VISIBLE);
            b.etId.setVisibility(View.GONE);
        } else {
            b.btnId.setText("保存");
            b.tvId.setVisibility(View.GONE);
            b.etId.setVisibility(View.VISIBLE);
        }
    }

    private void hideAddress() {
        b.chooseAddress.setVisibility(View.VISIBLE);
        b.tvName.setVisibility(View.GONE);
        b.tvPhone.setVisibility(View.GONE);
        b.tvAddress.setVisibility(View.GONE);
        isHaveAddress = false;
    }

    private void loadData(boolean isFirst) {
        BaseApp.getCartsInteractor(this)
            .getCartsPayInfoListV2(mCartIds, new ServiceResponse<CartsPayInfoBean>(mBaseActivity) {
                @Override
                public void onNext(CartsPayInfoBean bean) {
                    if (bean != null) {
                        mCartIds = bean.getCartIds();
                        List<CartsPayInfoBean.CartListEntity> cartList = bean.getCartList();
                        mCartPayInfoAdapter.updateWithClear(cartList);
                        if (isFirst) {
                            boolean hideTop = true;
                            for (int i = 0; i < cartList.size(); i++) {
                                if (cartList.get(i).getProduct_type() != 1) {
                                    hideTop = false;
                                    break;
                                }
                            }
                            if (hideTop) {
                                b.topLayout.setVisibility(View.GONE);
                            }
                        }
                        post_fee = bean.getPostFee();
                        b.tvWallet.setText("¥" + JUtils.formatDouble(bean.getBudget_cash()));
                        b.tvPost.setText("¥" + JUtils.formatDouble(post_fee));
                        b.tvPrice.setText("¥" + JUtils.formatDouble(bean.getTotalFee()));
                        payment = bean.getTotalFee() + bean.getPostFee() - bean.getDiscountFee();
                        total_fee = bean.getTotalFee();
                        discount_fee = bean.getDiscountFee();
                        uuid = bean.getUuid();
                        needLevel = bean.getMaxPersonalInfoLevel();
                        if (needLevel >= 2) {
                            b.levelLayout.setVisibility(View.VISIBLE);
                            b.layoutId.setVisibility(View.VISIBLE);
                            if (needLevel >= 3) {
                                b.layoutIdImg.setVisibility(View.VISIBLE);
                            }
                        }
                        if (null != bean.getmPayExtras()) {
                            List<CartsPayInfoBean.payExtrasEntityApp> payExtras = bean.getmPayExtras();
                            for (int i = 0; i < payExtras.size(); i++) {
                                switch (payExtras.get(i).getPid()) {
                                    case 1:
                                        appCut = payExtras.get(i).getValue();
                                        b.tvAppDiscount.setText("¥" + JUtils.formatDouble(appCut));
                                        break;
                                    case 2:
                                        if (payExtras.get(i).getUseCouponAllowed() == 1) {
                                            b.couponLayout.setOnClickListener(PayInfoActivity.this);
                                        } else {
                                            b.couponLayout.setOnClickListener(null);
                                            b.tvCoupon.setText("无可用优惠券");
                                        }
                                        break;
                                    case 3:
                                        walletCash = payExtras.get(i).getValue();
                                        break;
                                    case 4:
                                        coinCash = payExtras.get(i).getValue();
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                        if (coinCash > 0 && (b.topLayout.getVisibility() != View.VISIBLE)) {
                            b.coinLayout.setVisibility(View.VISIBLE);
                            b.coinLine.setVisibility(View.VISIBLE);
                            b.tvCoin.setText("" + coinCash);
                        }
                        calcAllPrice();
                        hideIndeterminateProgressDialog();
                    } else {
                        hideIndeterminateProgressDialog();
                        JUtils.Toast("商品已过期,请重新选购");
                        readyGoThenKill(TabActivity.class);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    hideIndeterminateProgressDialog();
                    JUtils.Toast("数据加载失败，请重新购买!");
                    readyGoThenKill(TabActivity.class);
                }
            });
    }

    private void calcAllPrice() {
        totalDiscount = (double) (Math.round((discount_fee + coupon_price + appCut) * 100)) / 100;
        realUseCoin = 0;
        realUseWallet = 0;
        reallyPayment = 0;
        if (Double.compare(coupon_price + appCut - payment, 0) >= 0) {
            totalDiscount = payment;
        } else {
            if (isBudget) {
                if (Double.compare(walletCash, payment - coupon_price - appCut) >= 0) {
                    realUseWallet = (double) (Math.round((payment - coupon_price - appCut) * 100)) / 100;
                } else {
                    reallyPayment = (double) (Math.round((payment - coupon_price - appCut - walletCash) * 100)) / 100;
                    realUseWallet = walletCash;
                }
            } else if (isCoin) {
                if (Double.compare(coinCash, payment - coupon_price - appCut) >= 0) {
                    realUseCoin = (double) (Math.round((payment - coupon_price - appCut) * 100)) / 100;
                } else {
                    reallyPayment = (double) (Math.round((payment - coupon_price - appCut - coinCash) * 100)) / 100;
                    realUseCoin = coinCash;
                }
            } else {
                reallyPayment = (double) (Math.round((payment - coupon_price - appCut) * 100)) / 100;
            }
        }
        b.totalPrice.setText("¥" + (double) (Math.round(reallyPayment * 100)) / 100);
        b.savePrice.setText("¥" + (double) (Math.round(totalDiscount * 100)) / 100);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_pay_info;
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_rule:
                mRuleDialog.show();
                break;
            case R.id.address_layout:
                Bundle addressBundle = new Bundle();
                addressBundle.putString("addressId", addressId);
                addressBundle.putInt("needLevel", needLevel);
                if (isHaveAddress) {
                    readyGoForResult(AddressSelectActivity.class, REQUEST_CODE_ADDRESS, addressBundle);
                } else {
                    readyGo(AddAddressActivity.class, addressBundle);
                }
                break;
            case R.id.coupon_layout:
                Bundle bundle = new Bundle();
                if ((coupon_id != null) && (!coupon_id.isEmpty())) {
                    bundle.putString("coupon_id", coupon_id);
                }
                bundle.putString("cart_ids", mCartIds);
                readyGoForResult(SelectCouponActivity.class, REQUEST_CODE_COUPON, bundle);
                break;
            case R.id.confirm:
                if (personalInfoLevel < needLevel) {
                    checkIdNo();
                } else {
                    payWithDialog();
                }
                break;
            case R.id.btn_id:
                if (b.etId.getVisibility() == View.VISIBLE) {
                    String trim = b.etId.getText().toString().trim();
                    if (trim.length() == 18 && IdCardChecker.isValidatedAllIdcard(trim)) {
                        saveIdIfo(trim);
                    } else {
                        JUtils.Toast("请输入正确的身份证后保存。");
                    }
                } else {
                    b.tvId.setVisibility(View.GONE);
                    b.btnId.setText("保存");
                    b.etId.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.image_id_face:
                side = "face";
                CameraUtils.getSystemPicture(this);
                break;
            case R.id.image_id_back:
                side = "back";
                CameraUtils.getSystemPicture(this);
                break;
        }
    }

    private void saveIdIfo(final String trim) {
        showIndeterminateProgressDialog(false);
        BaseApp.getAddressInteractor(mBaseActivity)
            .update_addressWithId(addressId, state, city, district,
                address, name, mobile, isDefault + "", trim, card_facepath, card_backpath,
                new ServiceResponse<AddressResultBean>(mBaseActivity) {
                    @Override
                    public void onNext(AddressResultBean addressResultBean) {
                        hideIndeterminateProgressDialog();
                        JUtils.Toast(addressResultBean.getMsg());
                        if (addressResultBean.getCode() == 0) {
                            addressId = String.valueOf(addressResultBean.getResult().getAddress_id());
                            personalInfoLevel = addressResultBean.getResult().getPersonalinfo_level();
                            id_no = trim;
                            b.etId.setVisibility(View.GONE);
                            b.btnId.setText("修改");
                            b.tvId.setVisibility(View.VISIBLE);
                            b.tvId.setText(id_no.replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1**********$2"));
                            b.etId.setText("");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        JUtils.Toast("地址信息保存失败，请重新点击保存");
                        hideIndeterminateProgressDialog();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PayUtils.REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getExtras().getString("pay_result");
                String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
                String extraMsg = data.getExtras().getString("extra_msg"); // 错误信息
                if (result == null) {
                    return;
                }
                if (result.equals("cancel")) {
                    JUtils.Toast("你已取消支付!");
                    if (order_id != -1) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("order_id", order_id);
                        readyGo(OrderDetailActivity.class, bundle);
                    }
                    finish();
                } else if (result.equals("success")) {
                    JUtils.Toast("支付成功！");
                    successJump();
                } else {
                    showMsgAndFinish(result, errorMsg, extraMsg, true);
                }
            }
        }
        if (requestCode == REQUEST_CODE_COUPON) {
            if (resultCode == Activity.RESULT_OK) {
                coupon_id = data.getStringExtra("coupon_id");
                coupon_price = data.getDoubleExtra("coupon_price", 0);
                b.tvCoupon.setText(coupon_price + "元优惠券");
                if (coupon_id == null || coupon_id.isEmpty() || coupon_price == 0) {
                    loadData(false);
                    isCoupon = false;
                    b.tvCoupon.setText("");
                    coupon_price = 0;
                } else {
                    isCoupon = true;
                }
                calcAllPrice();
            }
        }
        if (requestCode == REQUEST_CODE_ADDRESS) {
            if (resultCode == Activity.RESULT_OK) {
                addressId = data.getStringExtra("addressId");
                name = data.getStringExtra("name");
                mobile = data.getStringExtra("phone");
                id_no = data.getStringExtra("id_no");
                state = data.getStringExtra("state");
                city = data.getStringExtra("city");
                district = data.getStringExtra("district");
                address = data.getStringExtra("address");
                isDefault = data.getBooleanExtra("isDefault", false);
                face = data.getStringExtra("face");
                back = data.getStringExtra("back");
                loadIdImage();
                b.tvName.setText(name);
                b.tvPhone.setText(mobile);
                b.tvAddress.setText(data.getStringExtra("addressDetails"));
                personalInfoLevel = data.getIntExtra("level", 0);
                setIdNo();
            }
        }
        if (requestCode == CameraUtils.SELECT_PICTURE) {
            if (resultCode == RESULT_OK) {
                file = null;
                Uri uri = data.getData();
                String path = Utils.getPath(this, uri);
                if (path != null) {
                    file = new File(path);
                    if ("face".equals(side)) {
                        Glide.with(this).load(file).into(b.imageIdFace);
                    } else {
                        Glide.with(this).load(file).into(b.imageIdBack);
                    }
                } else {
                    JUtils.Toast("获取照片失败");
                }
            } else {
                JUtils.Toast("请重新上传");
            }
        }
    }

    private void payWithDialog() {
        if (isHaveAddress || needLevel == 0) {
            if (!isWx && !isAlipay && reallyPayment > 0) {
                new MyDialog(this).show();
            } else {
                if (isAlipay) {
                    channel = ALIPAY;
                } else if (isWx) {
                    channel = WX;
                } else {
                    channel = BUDGET;
                }
                StringBuilder sb = new StringBuilder(APP_PAY + appCut + ";");
                if (isCoupon) {
                    sb.append(COUPON_PAY).append(coupon_id).append(":value:").append(coupon_price).append(";");
                }
                if (isBudget) {
                    sb.append(BUDGET_PAY).append(realUseWallet).append(";");
                } else if (isCoin) {
                    sb.append(COIN_PAY).append(realUseCoin).append(";");
                }
                pay_extras = sb.toString();
                payV2();
            }
        } else {
            JUtils.Toast("你还未设置地址");
        }
    }

    private void payV2() {
        if (!payFlag) {
            payFlag = true;
            showIndeterminateProgressDialog(false);
            b.confirm.setClickable(false);
            BaseApp.getTradeInteractor(this)
                .shoppingCartCreateV2(mCartIds, addressId, channel,
                    (reallyPayment + realUseCoin + realUseWallet) + "", post_fee + "",
                    totalDiscount + "", total_fee + "", uuid, pay_extras,
                    new ServiceResponse<PayInfoBean>(mBaseActivity) {

                        @Override
                        public void onError(Throwable e) {
                            JUtils.Toast("支付失败!");
                            hideIndeterminateProgressDialog();
                        }

                        @Override
                        public void onNext(PayInfoBean payInfoBean) {
                            if (null != payInfoBean && payInfoBean.getTrade() != null) {
                                order_id = payInfoBean.getTrade().getId();
                                Gson gson = new Gson();
                                if ((payInfoBean.getChannel() != null) && (!payInfoBean.getChannel()
                                    .equals("budget"))) {
                                    if (payInfoBean.getCode() > 0) {
                                        JUtils.Toast(payInfoBean.getInfo());
                                    } else {
                                        PayUtils.createPayment(PayInfoActivity.this, gson.toJson(payInfoBean.getCharge()));
                                    }
                                } else {
                                    if (payInfoBean.getCode() == 0) {
                                        JUtils.Toast("支付成功");
                                        hideIndeterminateProgressDialog();
                                        successJump();
                                    } else {
                                        JUtils.Toast(payInfoBean.getInfo());
                                    }
                                }
                            } else if (null != payInfoBean) {
                                JUtils.Toast(payInfoBean.getInfo());
                            }
                            hideIndeterminateProgressDialog();
                        }
                    });
        }
    }

    private void successJump() {
        Bundle bundle = new Bundle();
        bundle.putInt("fragment", 3);
        readyGoThenKill(AllOrderActivity.class, bundle);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_wallet:
                isBudget = isChecked;
                if (isBudget && isCoin) {
                    isCoin = false;
                    b.cbCoin.setChecked(false);
                }
                calcAllPrice();
                break;
            case R.id.cb_coin:
                isCoin = isChecked;
                if (isCoin && isBudget) {
                    isBudget = false;
                    b.cbWallet.setChecked(false);
                }
                calcAllPrice();
                break;
            case R.id.cb_rule:
                if (isChecked) {
                    b.confirm.setClickable(true);
                    b.confirm.setEnabled(true);
                } else {
                    b.confirm.setClickable(false);
                    b.confirm.setEnabled(false);
                }
                break;
        }
    }

    private void checkIdNo() {
        String contentStr;
        if (needLevel >= 2) {
            contentStr = "订单中包含进口保税区发货商品，根据海关监管要求，需要提供收货人身份证信息。此信息加密保存，只用于此订单海关通关，请点击地址进行修改。";
        } else {
            contentStr = "地址信息未完善，请填写完整。";
        }
        new AlertDialog.Builder(this)
            .setTitle("提示")
            .setMessage(contentStr)
            .setPositiveButton("确认", (dialog1, which) -> {
                dialog1.dismiss();
                Bundle addressBundle = new Bundle();
                addressBundle.putString("addressId", addressId);
                addressBundle.putInt("needLevel", needLevel);
                if (isHaveAddress) {
                    if (needLevel >= 2) {
                        JUtils.Toast("请填写身份证号或上传身份证照片");
                    } else {
                        JUtils.Toast("请完善收货地址信息");
                    }
                } else {
                    readyGo(AddAddressActivity.class, addressBundle);
                }
            })
            .setCancelable(false)
            .show();
    }

    private class MyDialog extends Dialog {
        MyDialog(Context context) {
            super(context, R.style.MyDialog);
            setDialog();
        }

        private void setDialog() {
            View mView = LayoutInflater.from(getContext()).inflate(R.layout.pop_wxoralipay, null);
            LinearLayout wx_layout = (LinearLayout) mView.findViewById(R.id.wx_layout);
            LinearLayout alipay_layout = (LinearLayout) mView.findViewById(R.id.alipay_layout);
            TextView textView = (TextView) mView.findViewById(R.id.total_price);
            mView.findViewById(R.id.finish_iv).setOnClickListener(v -> MyDialog.this.dismiss());
            textView.setText("¥" + (double) (Math.round(reallyPayment * 100)) / 100);
            alipay_layout.setOnClickListener(v -> {
                alipay_layout.setClickable(false);
                isAlipay = true;
                isWx = false;
                payWithDialog();
                MyDialog.this.dismiss();
            });
            wx_layout.setOnClickListener(v -> {
                wx_layout.setClickable(false);
                isAlipay = false;
                isWx = true;
                payWithDialog();
                MyDialog.this.dismiss();
            });
            MyDialog.this.setCanceledOnTouchOutside(true);
            Window win = this.getWindow();
            win.setGravity(Gravity.BOTTOM);
            win.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams lp = win.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            win.setAttributes(lp);
            super.setContentView(mView);
        }
    }

    private class CompressTask extends AsyncTask<String, Integer, String> {
        private Bitmap bitmap;
        private Bitmap compressImage;

        @Override
        protected String doInBackground(String... strings) {
            try {
                bitmap = BitmapFactory.decodeFile(strings[0]);
                compressImage = CameraUtils.imageZoom(bitmap, 50);
                file = null;
                return CameraUtils.getBitmapStrBase64(compressImage);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if ("".equals(s) || s == null) {
                hideIndeterminateProgressDialog();
            } else {
                BaseApp.getAddressInteractor(PayInfoActivity.this)
                    .idCardIndentify(side, s, new ServiceResponse<IdCardBean>(mBaseActivity) {
                        @Override
                        public void onNext(IdCardBean idCardBean) {
                            if (idCardBean.getCode() == 0) {
                                JUtils.Toast("上传成功");
                                if ("face".equals(side)) {
                                    card_facepath = idCardBean.getCard_infos().getCard_imgpath();
                                } else {
                                    card_backpath = idCardBean.getCard_infos().getCard_imgpath();
                                }
                                if (card_facepath != null && !"".equals(card_facepath) &&
                                    card_backpath != null && !"".equals(card_backpath)) {
                                    saveIdIfo(id_no);
                                }
                            } else {
                                JUtils.Toast(idCardBean.getInfo());
                            }
                            hideIndeterminateProgressDialog();
                        }

                        @Override
                        public void onError(Throwable e) {
                            JUtils.Toast("上传失败!");
                            hideIndeterminateProgressDialog();
                        }
                    });
            }
            bitmap.recycle();
            compressImage.recycle();
        }
    }
}
