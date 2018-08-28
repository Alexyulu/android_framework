package com.example.mytest.app;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2018/5/29
 * Desc : Glide的注入类
 */
@GlideModule
public class MyAppGlideModule extends AppGlideModule {
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
