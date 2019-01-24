package com.blackbase.test.data.file;

import android.support.annotation.NonNull;

import com.blackbase.test.common.Destroyable;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by klitaviy on 1/23/19-9:39 PM.
 */
interface AssetsFileStreamProvider extends Destroyable {
    @NonNull
    InputStream getInputStream(@NonNull final String fileName) throws IllegalStateException, IOException;
}
