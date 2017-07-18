package com.bumptech.glide.samples.gallery;

import android.content.Context;
import android.widget.ImageView;

import com.auto.R;

/**
 * Created by Administrator on 2017/7/10 0010.
 */

public class GlideHelp {
    public static void load(Context context, String url, ImageView view){
//        view.setImageResource(R.mipmap.ic_launcher);
        GlideApp.with(context).asBitmap().load(url).centerCrop().dontAnimate().into(view);
    }
}
