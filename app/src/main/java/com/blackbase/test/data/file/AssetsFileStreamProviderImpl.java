package com.blackbase.test.data.file;

import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.blackbase.test.common.Condition;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by klitaviy on 1/23/19-10:00 PM.
 */
class AssetsFileStreamProviderImpl implements AssetsFileStreamProvider {


    @Nullable
    private AssetManager mAssetManager;

    AssetsFileStreamProviderImpl(@Nullable AssetManager assetManager) {
        mAssetManager = assetManager;
    }

    @NonNull
    @Override
    public InputStream getInputStream(@NonNull String fileName) throws IllegalStateException, IOException {
        if (Condition.isNull(mAssetManager)) {
            throw new IllegalStateException("mAssetManager is null!");
        } else {
            return mAssetManager.open(fileName);
        }
    }

    @Override
    public void destroy() {

        mAssetManager = null;
    }
}