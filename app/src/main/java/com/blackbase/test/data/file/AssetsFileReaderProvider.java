package com.blackbase.test.data.file;

import android.content.res.AssetManager;
import android.support.annotation.NonNull;

import com.blackbase.test.logging.Logger;
import com.blackbase.test.logging.LoggerProvider;

/**
 * Created by klitaviy on 1/23/19-10:00 PM.
 */
public class AssetsFileReaderProvider {
    @NonNull
    public static AssetsFileReader provide(@NonNull final AssetManager assetManager) {
        final AssetsFileStreamProvider streamProvider = new AssetsFileStreamProviderImpl(assetManager);
        final Logger logger = LoggerProvider.provide();
        return new AssetsFileReaderImpl(streamProvider, logger);
    }
}
