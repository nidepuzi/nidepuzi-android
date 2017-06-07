package com.danlai.nidepuzi.ui.activity.product;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieSyncManager;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.danlai.library.utils.DateUtils;
import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.ViewUtils;
import com.danlai.library.widget.AttrView;
import com.danlai.library.widget.SpaceItemDecoration;
import com.danlai.library.widget.TagTextView;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.SkuColorAdapter;
import com.danlai.nidepuzi.adapter.SkuSizeAdapter;
import com.danlai.nidepuzi.base.BaseApi;
import com.danlai.nidepuzi.base.BaseImageLoader;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.base.BaseWebViewActivity;
import com.danlai.nidepuzi.databinding.ActivityProductDetailBinding;
import com.danlai.nidepuzi.entity.CartsInfoBean;
import com.danlai.nidepuzi.entity.CartsNumResultBean;
import com.danlai.nidepuzi.entity.ProductDetailBean;
import com.danlai.nidepuzi.entity.ResultEntity;
import com.danlai.nidepuzi.entity.ShareModelBean;
import com.danlai.nidepuzi.htmlJsBridge.AndroidJsBridge;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.main.TabActivity;
import com.danlai.nidepuzi.ui.activity.shop.NinePicActivity;
import com.danlai.nidepuzi.ui.activity.trade.CartActivity;
import com.danlai.nidepuzi.ui.activity.trade.PayInfoActivity;
import com.danlai.nidepuzi.ui.activity.user.LoginActivity;
import com.danlai.nidepuzi.util.JumpUtils;
import com.danlai.nidepuzi.util.LoginUtils;
import com.danlai.nidepuzi.util.ShareUtils;
import com.youth.banner.BannerConfig;

import java.util.List;

/**
 * @author wisdom
 * @date 17/4/7
 */
public class ProductDetailActivity extends BaseMVVMActivity<ActivityProductDetailBinding>
    implements View.OnClickListener {
    private static final String POST_URL = "?imageMogr2/format/jpg/thumbnail/640/quality/70";
    private ProductDetailBean productDetail;
    private int cart_num = 0;
    private Dialog dialog;
    private ImageView img, plusIv, minusIv;
    private TextView nameTv, skuTv, agentTv, saleTv, numTv, addCartTv, buyNowTv;
    private RecyclerView colorRv, sizeRv;
    private int model_id, sku_id, item_id, num;
    private SkuSizeAdapter skuSizeAdapter;
    private LinearLayout colorLayout, sizeLayout;
    private ShareModelBean mShareModelBean;
    private CountDownTimer mCountDownTimer;

    @Override
    public void getIntentUrl(Uri uri) {
        model_id = Integer.valueOf(uri.getQueryParameter("model_id"));
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        model_id = extras.getInt("model_id");
    }

    @Override
    protected void initViews() {
        setStatusBar();
        num = 1;
        try {
            WebSettings settings = b.webView.getSettings();
            if (Build.VERSION.SDK_INT >= 19) {
                settings.setLoadsImagesAutomatically(true);
            } else {
                settings.setLoadsImagesAutomatically(false);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            }
            settings.setJavaScriptEnabled(true);
            b.webView.addJavascriptInterface(new AndroidJsBridge(this), "AndroidBridge");
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            b.webView.setWebChromeClient(new WebChromeClient());
            b.webView.setWebViewClient(new WebViewClient() {

                @Override
                public void onPageFinished(WebView view, String url) {
                    CookieSyncManager.getInstance().sync();
                    if (b.webView != null && !b.webView.getSettings().getLoadsImagesAutomatically()) {
                        b.webView.getSettings().setLoadsImagesAutomatically(true);
                    }
                }

                @Override
                public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host,
                                                      String realm) {
                    view.reload();
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return super.shouldOverrideUrlLoading(view, url);
                }

                public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                    handler.proceed();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        View view = getLayoutInflater().inflate(R.layout.pop_product_detail_layout, null);
        findById(view);
        dialog = new Dialog(this, R.style.CustomDialog);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        window.setWindowAnimations(R.style.BottomDialogAnim);
        colorRv.setLayoutManager(new GridLayoutManager(this, 3));
        colorRv.setOverScrollMode(View.OVER_SCROLL_NEVER);
        colorRv.addItemDecoration(new SpaceItemDecoration(12, 12, 8, 8));
        sizeRv.setLayoutManager(new GridLayoutManager(this, 3));
        sizeRv.setOverScrollMode(View.OVER_SCROLL_NEVER);
        sizeRv.addItemDecoration(new SpaceItemDecoration(12, 12, 8, 8));
    }

    private void findById(View view) {
        img = ((ImageView) view.findViewById(R.id.img));
        nameTv = ((TextView) view.findViewById(R.id.name));
        skuTv = ((TextView) view.findViewById(R.id.sku));
        agentTv = ((TextView) view.findViewById(R.id.agent_price));
        saleTv = ((TextView) view.findViewById(R.id.sale_price));
        saleTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        plusIv = ((ImageView) view.findViewById(R.id.plus));
        minusIv = ((ImageView) view.findViewById(R.id.minus));
        numTv = ((TextView) view.findViewById(R.id.num));
        addCartTv = ((TextView) view.findViewById(R.id.tv_add_cart));
        buyNowTv = ((TextView) view.findViewById(R.id.tv_buy_now));
        colorRv = ((RecyclerView) view.findViewById(R.id.rv_color));
        sizeRv = ((RecyclerView) view.findViewById(R.id.rv_size));
        colorLayout = ((LinearLayout) view.findViewById(R.id.ll_color));
        sizeLayout = ((LinearLayout) view.findViewById(R.id.ll_size));
    }

    @Override
    protected void setListener() {
        b.finish.setOnClickListener(this);
        b.share.setOnClickListener(this);
        b.rlCart.setOnClickListener(this);
        b.tvBuy.setOnClickListener(this);
        b.tvSale.setOnClickListener(this);
        b.textLayout.setOnClickListener(this);
        plusIv.setOnClickListener(this);
        minusIv.setOnClickListener(this);
        addCartTv.setOnClickListener(this);
        buyNowTv.setOnClickListener(this);
        b.layoutRefund.setOnClickListener(this);
        b.pullToLoad.setScrollListener(
            (scrollView, x, y, oldx, oldy) -> {
                float v = ((float) y) / (b.banner.getHeight() - b.tvTitle.getHeight());
                if (v >= 0.25) {
                    b.tvTitle.setAlpha(1);
                    b.finish.getBackground().setAlpha(0);
                    b.share.getBackground().setAlpha(0);
                } else {
                    b.tvTitle.setAlpha(4 * v);
                    b.finish.getBackground().setAlpha((int) ((1 - 4 * v) * 255));
                    b.share.getBackground().setAlpha((int) ((1 - 4 * v) * 255));
                }
            }
        );
    }

    @Override
    protected void initData() {
        showIndeterminateProgressDialog(false);
        BaseApp.getProductInteractor(this)
            .getProductDetail(model_id, new ServiceResponse<ProductDetailBean>(mBaseActivity) {
                @Override
                public void onNext(ProductDetailBean productDetailBean) {
                    productDetail = productDetailBean;
                    fillDataToView(productDetailBean);
                    hideIndeterminateProgressDialog();
                    showCartNum();
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    hideIndeterminateProgressDialog();
                }
            });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (productDetail != null) {
            showCartNum();
        }
    }

    private void showCartNum() {
        BaseApp.getMainInteractor(this)
            .getCartsNum(0, new ServiceResponse<CartsNumResultBean>(mBaseActivity) {
                @Override
                public void onNext(CartsNumResultBean cartsNumResultBean) {
                    cart_num = cartsNumResultBean.getResult();
                    b.tvCart.setText(cart_num + "");
                    if (cart_num > 0) {
                        b.tvCart.setVisibility(View.VISIBLE);
                    }
                }
            });
    }

    private void fillDataToView(ProductDetailBean productDetailBean) {
        if (productDetailBean.getSource_type() >= 2) {
            b.levelLayout.setVisibility(View.VISIBLE);
        }
        b.webView.loadUrl(BaseApi.getAppUrl() + "/mall/product/details/app/" + model_id);
        ProductDetailBean.DetailContentBean detailContent = productDetailBean.getDetail_content();
        List<ProductDetailBean.SkuInfoBean> skuInfo = productDetailBean.getSku_info();
        if ("will".equals(detailContent.getSale_state())) {
            b.tvBuy.setClickable(false);
            b.tvSale.setClickable(false);
            b.tvBuy.setText("即将开售");
            b.tvSale.setText("即将开售");
        } else if ("off".equals(detailContent.getSale_state())) {
            b.tvBuy.setClickable(false);
            b.tvSale.setClickable(false);
            b.tvBuy.setText("已下架");
            b.tvSale.setText("已下架");
        } else if ("on".equals(detailContent.getSale_state()) && detailContent.isIs_sale_out()) {
            b.tvBuy.setClickable(false);
            b.tvSale.setClickable(false);
            b.tvBuy.setText("已抢光");
            b.tvSale.setText("已抢光");
        } else {
            try {
                if (skuInfo.size() > 0) {
                    ViewUtils.loadImgToImgView(this, img, skuInfo.get(0).getProduct_img());
                    nameTv.setText(detailContent.getName() + "/" + skuInfo.get(0).getName());
                    skuTv.setText(skuInfo.get(0).getSku_items().get(0).getName());
                    agentTv.setText("¥" + skuInfo.get(0).getSku_items().get(0).getAgent_price() + "");
                    saleTv.setText("" + skuInfo.get(0).getSku_items().get(0).getStd_sale_price());
                }
            } catch (Exception ignored) {
            }
            SkuColorAdapter colorAdapter = new SkuColorAdapter(skuInfo, this);
            colorRv.setAdapter(colorAdapter);
            skuSizeAdapter = new SkuSizeAdapter(this);
            sizeRv.setAdapter(skuSizeAdapter);
            skuSizeAdapter.update(skuInfo.get(0).getSku_items());
            setSkuId(skuInfo);
            if (skuInfo.size() == 1 && skuInfo.get(0).getSku_items().size() == 1) {
                nameTv.setText(detailContent.getName());
                skuTv.setText(skuInfo.get(0).getSku_items().get(0).getName());
                colorLayout.setVisibility(View.GONE);
                sizeLayout.setVisibility(View.GONE);
            }
        }
        b.name.setText(detailContent.getName());
        b.agentPrice.setText(JUtils.formatDouble(detailContent.getLowest_agent_price()));
        b.tvProfit.setText("赚" + JUtils.formatDouble(productDetailBean.getProfit().getMin()));
        b.timePrice.setText(JUtils.formatDouble(detailContent.getLowest_agent_price()));
        b.timeProfit.setText("赚" + JUtils.formatDouble(productDetailBean.getProfit().getMin()));
        b.tvSellNum.setText("在售人数" + productDetailBean.getSelling_num());
        b.tvStock.setText("库存" + productDetailBean.getStock());
        if (productDetailBean.is_flashsale()) {
            b.timeLayout.setVisibility(View.VISIBLE);
            b.priceLayout.setVisibility(View.GONE);
        } else {
            b.timeLayout.setVisibility(View.GONE);
            b.priceLayout.setVisibility(View.VISIBLE);
        }
        if (detailContent.getOffshelf_time() != null) {
            String offshelf_time = detailContent.getOffshelf_time().replace("T", " ");
            long left = DateUtils.calcLeftTime(offshelf_time);
            mCountDownTimer = new CountDownTimer(left, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    update(millisUntilFinished);
                }

                @Override
                public void onFinish() {
                    b.tvHour.setText("00");
                    b.tvMinute.setText("00");
                    b.tvSecond.setText("00");
                }
            };
            mCountDownTimer.start();
        }
        List<String> item_marks = detailContent.getItem_marks();
        for (int i = 0; i < item_marks.size(); i++) {
            TagTextView tagTextView = new TagTextView(this);
            tagTextView.setTagName(item_marks.get(i));
            b.llTag.addView(tagTextView);
        }
        initAttr(productDetailBean.getComparison().getAttributes());
        initHeadImg(detailContent);
        if (detailContent.is_onsale()) {
            addCartTv.setVisibility(View.GONE);
        }
    }

    private void update(long ms) {
        int mHour = (int) ((ms % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        if (mHour < 10) {
            b.tvHour.setText("0" + mHour);
        } else {
            b.tvHour.setText("" + mHour);
        }
        int mMinute = (int) ((ms % (1000 * 60 * 60)) / (1000 * 60));
        if (mMinute < 10) {
            b.tvMinute.setText("0" + mMinute);
        } else {
            b.tvMinute.setText("" + mMinute);
        }
        int mSecond = (int) ((ms % (1000 * 60)) / 1000);
        if (mSecond < 10) {
            b.tvSecond.setText("0" + mSecond);
        } else {
            b.tvSecond.setText("" + mSecond);
        }
    }

    @Override
    protected void onDestroy() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        super.onDestroy();
    }

    private void setSkuId(List<ProductDetailBean.SkuInfoBean> sku_info) {
        for (int i = 0; i < sku_info.size(); i++) {
            for (int j = 0; j < sku_info.get(0).getSku_items().size(); j++) {
                if (!sku_info.get(i).getSku_items().get(j).isIs_saleout()) {
                    item_id = sku_info.get(i).getProduct_id();
                    sku_id = sku_info.get(i).getSku_items().get(j).getSku_id();
                    return;
                }
            }
        }
    }

    private void initAttr(List<ProductDetailBean.ComparisonBean.AttributesBean> attributes) {
        for (int i = 0; i < attributes.size(); i++) {
            AttrView attrView = new AttrView(this);
            attrView.setAttrName(attributes.get(i).getName());
            attrView.setAttrValue(attributes.get(i).getValue());
            b.llAttr.addView(attrView);
        }
    }

    private void initHeadImg(ProductDetailBean.DetailContentBean detail_content) {
        List<String> head_imgs = detail_content.getHead_imgs();
        String watermark_op = detail_content.getWatermark_op();
        for (int i = 0; i < head_imgs.size(); i++) {
            String str;
            if (TextUtils.isEmpty(watermark_op)) {
                str = head_imgs.get(i) + POST_URL;
            } else {
                str = head_imgs.get(i) + POST_URL + "|" + watermark_op;
            }
            head_imgs.set(i, str);
        }
        b.banner.setImageLoader(new BaseImageLoader());
        b.banner.setImages(head_imgs);
        b.banner.setIndicatorGravity(BannerConfig.CIRCLE_INDICATOR);
        b.banner.start();
        b.banner.setDelayTime(3000);
    }

    private void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_product_detail;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.finish:
                this.finish();
                break;
            case R.id.layout_refund:
                JumpUtils.jumpToWebViewWithCookies(this, productDetail.getDetail_content().getRefund_tips_url()
                    , -1, BaseWebViewActivity.class, "七天退换货规则");
                break;
            case R.id.share:
                shareProduct();
                break;
            case R.id.text_layout:
                if (!LoginUtils.checkLoginState(getApplicationContext())) {
                    jumpToLogin();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putInt("model_id", model_id);
                    readyGo(NinePicActivity.class, bundle);
                }
                break;
            case R.id.rl_cart:
                if (!LoginUtils.checkLoginState(getApplicationContext())) {
                    jumpToLogin();
                } else {
                    Bundle bundle = new Bundle();
                    if (productDetail == null || productDetail.getDetail_content().is_boutique()) {
                        bundle.putString("flag", "car");
                        readyGoThenKill(TabActivity.class, bundle);
                    } else {
                        bundle.putBoolean("isNormal", true);
                        readyGoThenKill(CartActivity.class, bundle);
                    }
                }
                break;
            case R.id.tv_buy:
                if (!LoginUtils.checkLoginState(getApplicationContext())) {
                    jumpToLogin();
                } else {
                    dialog.show();
                }
                break;
            case R.id.tv_sale:
                shareProduct();
                break;
            case R.id.plus:
                num++;
                numTv.setText(num + "");
                break;
            case R.id.minus:
                if (num > 1) {
                    num--;
                    numTv.setText(num + "");
                }
                break;
            case R.id.tv_add_cart:
                addToCart(true);
                break;
            case R.id.tv_buy_now:
                BaseApp.getCartsInteractor(this)
                    .addToCart(item_id, sku_id, num, 0, new ServiceResponse<ResultEntity>(mBaseActivity) {
                        @Override
                        public void onNext(ResultEntity resultEntity) {
                            if (resultEntity.getCode() == 0) {
                                BaseApp.getCartsInteractor(ProductDetailActivity.this)
                                    .getCartsList(0, new ServiceResponse<List<CartsInfoBean>>(mBaseActivity) {
                                        @Override
                                        public void onNext(List<CartsInfoBean> cartsInfoBeen) {
                                            if (cartsInfoBeen != null && cartsInfoBeen.size() > 0) {
                                                String ids = cartsInfoBeen.get(0).getId() + "";
                                                Bundle bundle = new Bundle();
                                                bundle.putString("ids", ids);
                                                bundle.putBoolean("couponFlag", true);
                                                Intent intent = new Intent(ProductDetailActivity.this, PayInfoActivity.class);
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                                dialog.dismiss();
                                            } else {
                                                JUtils.Toast("购买失败!");
                                            }
                                        }
                                    });
                            } else {
                                JUtils.Toast(resultEntity.getInfo());
                            }
                        }
                    });
                break;
            default:
                break;
        }

    }

    private void shareProduct() {
        if (mShareModelBean == null) {
            showIndeterminateProgressDialog(false);
            BaseApp.getProductInteractor(this)
                .getShareModel(model_id, new ServiceResponse<ShareModelBean>(mBaseActivity) {
                    @Override
                    public void onNext(ShareModelBean shareModel) {
                        mShareModelBean = shareModel;
                        ShareUtils.shareWithModel(shareModel, ProductDetailActivity.this);
                        hideIndeterminateProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        JUtils.Toast("分享失败,请点击重试!");
                        hideIndeterminateProgressDialog();
                    }
                });
        } else {
            ShareUtils.shareWithModel(mShareModelBean, ProductDetailActivity.this);
        }
    }

    private void jumpToLogin() {
        JUtils.Toast("没有登录哦!");
        readyGoThenKill(LoginActivity.class);
    }

    private void addToCart(boolean dismiss) {
        BaseApp.getCartsInteractor(this)
            .addToCart(item_id, sku_id, num, 0, new ServiceResponse<ResultEntity>(mBaseActivity) {
                @Override
                public void onNext(ResultEntity resultEntity) {
                    JUtils.Toast(resultEntity.getInfo());
                    if (resultEntity.getCode() == 0) {
                        cart_num += num;
                        if (dismiss)
                            dialog.dismiss();
                        b.tvCart.setText(cart_num + "");
                        if (cart_num > 0) {
                            b.tvCart.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });
    }

    public void refreshSku(int position) {
        ProductDetailBean.SkuInfoBean skuInfoBean = productDetail.getSku_info().get(position);
        item_id = skuInfoBean.getProduct_id();
        ViewUtils.loadImgToImgView(this, img, skuInfoBean.getProduct_img());
        nameTv.setText(productDetail.getDetail_content().getName() + "/" + skuInfoBean.getName());
        List<ProductDetailBean.SkuInfoBean.SkuItemsBean> sku_items = skuInfoBean.getSku_items();
        skuSizeAdapter.updateWithClear(sku_items);
    }

    public void refreshSkuId(ProductDetailBean.SkuInfoBean.SkuItemsBean skuItemsBean) {
        agentTv.setText("¥" + skuItemsBean.getAgent_price());
        saleTv.setText("" + skuItemsBean.getStd_sale_price());
        skuTv.setText(skuItemsBean.getName());
        sku_id = skuItemsBean.getSku_id();
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }
}
