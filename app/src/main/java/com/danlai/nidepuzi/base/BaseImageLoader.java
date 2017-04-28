package com.danlai.nidepuzi.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.qiyukf.unicorn.api.ImageLoaderListener;
import com.qiyukf.unicorn.api.UnicornImageLoader;
import com.youth.banner.loader.ImageLoader;

/**
 * @author wisdom
 * @date 2017年04月13日 下午4:54
 */

public class BaseImageLoader extends ImageLoader implements UnicornImageLoader {

    private Context context;

    public BaseImageLoader() {

    }

    public BaseImageLoader(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }

    @Nullable
    @Override
    public Bitmap loadImageSync(String uri, int width, int height) {
        return null;
    }

    @Override
    public void loadImage(String uri, int width, int height, final ImageLoaderListener listener) {
        Glide.with(context).load(uri).asBitmap().into(new SimpleTarget<Bitmap>(width, height) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                if (listener != null) {
                    listener.onLoadComplete(resource);
                }
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                if (listener != null) {
                    listener.onLoadFailed(e);
                }
            }
        });
    }
}
