/**
 * Copyright 2014 Zhenguo Jin
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.danlai.library.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.os.Build;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.danlai.library.R;
import com.danlai.library.widget.glidemoudle.CropCircleTransformation;
import com.danlai.library.widget.glidemoudle.GlideRoundTransform;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * @author wisdom
 * @date 2016年05月12日 下午2:12
 */
public final class ViewUtils {

    private static final String CLASS_NAME_GRID_VIEW = "android.widget.GridView";
    private static final String FIELD_NAME_VERTICAL_SPACING = "mVerticalSpacing";

    /**
     * Don't let anyone instantiate this class.
     */
    private ViewUtils() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * get ListView height according to every children
     */
    public static int getListViewHeightBasedOnChildren(ListView view) {
        int height = getAbsListViewHeightBasedOnChildren(view);
        ListAdapter adapter;
        int adapterCount;
        if (view != null
            && (adapter = view.getAdapter()) != null
            && (adapterCount = adapter.getCount()) > 0) {
            height += view.getDividerHeight() * (adapterCount - 1);
        }
        return height;
    }

    /**
     * get GridView vertical spacing
     */
    public static int getGridViewVerticalSpacing(GridView view) {
        // get mVerticalSpacing by android.widget.GridView
        Class<?> demo = null;
        int verticalSpacing = 0;
        try {
            demo = Class.forName(CLASS_NAME_GRID_VIEW);
            Field field = demo.getDeclaredField(FIELD_NAME_VERTICAL_SPACING);
            field.setAccessible(true);
            verticalSpacing = (Integer) field.get(view);
            return verticalSpacing;
        } catch (Exception e) {
            /**
             * accept all exception, include ClassNotFoundException,
             * NoSuchFieldException, InstantiationException,
             * IllegalArgumentException, IllegalAccessException,
             * NullPointException
             */
            e.printStackTrace();
        }
        return verticalSpacing;
    }

    /**
     * get AbsListView height according to every children
     */
    public static int getAbsListViewHeightBasedOnChildren(AbsListView view) {
        ListAdapter adapter;
        if (view == null || (adapter = view.getAdapter()) == null) {
            return 0;
        }

        int height = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View item = adapter.getView(i, null, view);
            if (item instanceof ViewGroup) {
                item.setLayoutParams(
                    new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            }
            item.measure(0, 0);
            height += item.getMeasuredHeight();
        }
        height += view.getPaddingTop() + view.getPaddingBottom();
        return height;
    }

    /**
     * get Font height
     */
    public static int getFontHeight(TextView view) {
        Paint paint = new Paint();
        paint.setTextSize(view.getTextSize());
        FontMetrics fm = paint.getFontMetrics();
        return (int) (Math.ceil(fm.descent - fm.ascent));
    }

    /**
     * set view height
     */
    public static void setViewHeight(View view, int height) {
        if (view == null) {
            return;
        }

        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = height;
    }

    /**
     * set ListView height which is calculated by
     * {@link # getListViewHeightBasedOnChildren(ListView)}
     */
    public static void setListViewHeightBasedOnChildren(ListView view) {
        setViewHeight(view, getListViewHeightBasedOnChildren(view));
    }

    /**
     * set AbsListView height which is calculated by
     * {@link # getAbsListViewHeightBasedOnChildren(AbsListView)}
     */
    public static void setAbsListViewHeightBasedOnChildren(AbsListView view) {
        setViewHeight(view, getAbsListViewHeightBasedOnChildren(view));
    }

    /**
     * set SearchView OnClickListener
     */
    public static void setSearchViewOnClickListener(View v, OnClickListener listener) {
        if (v instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) v;
            int count = group.getChildCount();
            for (int i = 0; i < count; i++) {
                View child = group.getChildAt(i);
                if (child instanceof LinearLayout || child instanceof RelativeLayout) {
                    setSearchViewOnClickListener(child, listener);
                }

                if (child instanceof TextView) {
                    TextView text = (TextView) child;
                    text.setFocusable(false);
                }
                child.setOnClickListener(listener);
            }
        }
    }

    public static <T extends View> List<T> getDescendants(ViewGroup parent, Class<T> filter,
                                                          boolean includeSubClass) {
        List<T> descendedViewList = new ArrayList<T>();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            Class<? extends View> childsClass = child.getClass();
            if ((includeSubClass && filter.isAssignableFrom(childsClass)) || (!includeSubClass
                && childsClass == filter)) {
                descendedViewList.add(filter.cast(child));
            }
            if (child instanceof ViewGroup) {
                descendedViewList.addAll(getDescendants((ViewGroup) child, filter, includeSubClass));
            }
        }
        return descendedViewList;
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
            & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static void loadImgToImgView(Context context, ImageView img, String picPath) {
        if (null == picPath || "".equals(picPath)) return;
        Glide.with(context)
            .load(picPath)
            .diskCacheStrategy(DiskCacheStrategy.RESULT)
            .centerCrop()
            .into(img);

    }

    public static void loadImgToImgView(Context context, ImageView img, String picPath, int radius) {
        if (null == picPath || "".equals(picPath)) return;
        Glide.with(context)
            .load(picPath)
            .diskCacheStrategy(DiskCacheStrategy.RESULT)
            .centerCrop()
            .transform(new GlideRoundTransform(context, radius))
            .into(img);
    }

    public static void loadImgToImgViewWithTransformCircle(Context context, ImageView img,
                                                           String picPath) {
        if (null == picPath || "".equals(picPath)) return;
        Glide.with(context)
            .load(picPath)
            .diskCacheStrategy(DiskCacheStrategy.RESULT)
            .bitmapTransform(new CropCircleTransformation(context))
            .into(img);
    }

    public static void loadImgToImgViewWithPlaceholder(Context context, ImageView img, String picPath) {
        if (null == picPath || "".equals(picPath)) {
            Glide.with(context)
                .load(R.drawable.place_holder)
                .centerCrop()
                .into(img);
            return;
        }
        Glide.with(context)
            .load(picPath + "?imageMogr2/format/jpg/size-limit/30k/thumbnail/289/quality/80")
            .diskCacheStrategy(DiskCacheStrategy.RESULT)
            .centerCrop()
            .placeholder(R.drawable.place_holder)
            .into(img);
    }

    public static LayoutParams getLayoutParams(Bitmap bitmap, int screenWidth) {
        float rawWidth = bitmap.getWidth();
        float rawHeight = bitmap.getHeight();

        float width;
        float height;

        if (rawWidth > screenWidth) {
            height = (rawHeight / rawWidth) * screenWidth;
            width = screenWidth;
        } else {
            width = rawWidth;
            height = rawHeight;
        }

        LayoutParams layoutParams =
            new LayoutParams((int) width, (int) height);

        return layoutParams;
    }

    public static void setWindowStatus(Activity activity) {
        Window window = activity.getWindow();
        //4.4版本及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //5.0版本及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView()
                .setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
