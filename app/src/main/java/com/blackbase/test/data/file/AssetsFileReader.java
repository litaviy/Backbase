package com.blackbase.test.data.file;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.blackbase.test.common.Destroyable;

import org.json.JSONObject;

/**
 * Created by klitaviy on 1/23/19-9:41 PM.
 */
public interface AssetsFileReader extends Destroyable {
    @Nullable
    JSONObject getJson(@NonNull final String fileName);
}
