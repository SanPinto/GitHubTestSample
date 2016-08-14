package com.gitSample.common.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by xyz on 8/13/2016.
 */
public class FileUtils {
    private static final String TAG = FileUtils.class.getSimpleName();

    /**
     * Get a usable cache directory (external if available, internal otherwise).
     *
     * @param context    The context to use
     * @param uniqueName A unique directory name to append to the cache dir
     * @return The cache dir
     */
    public static File getDiskCacheDir(Context context, String uniqueName) {
        // Check if media is mounted or storage is built-in, if so, try and use
        // external cache dir
        // otherwise use internal cache dir

        // TODO: getCacheDir() should be moved to a background thread as it
        // attempts to create the
        // directory if it does not exist (no disk access should happen on the
        // main/UI thread).
        // String path = getExternalCacheDir(context).getAbsolutePath();
        final String cachePath = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable() ? getExternalCacheDir(context) == null ? context
                .getCacheDir().getPath() : getExternalCacheDir(context).getAbsolutePath() : context.getCacheDir()
                .getPath();
        // final File cachePath = context.getCacheDir();
        return new File(cachePath + File.separator + uniqueName);
    }

    /**
     * Get the external app cache directory.
     *
     * @param context The context to use
     * @return The external cache dir
     */
    private static File getExternalCacheDir(Context context) {
        // TODO: This needs to be moved to a background thread to ensure no disk
        // access on the
        // main/UI thread as unfortunately getExternalCacheDir() calls mkdirs()
        // for us (even
        // though the Volley library will later try and call mkdirs() as well
        // from a background
        // thread).
        File externalFilesDir = null;
        try {
            externalFilesDir = context.getExternalFilesDir(null);
        } catch (ArrayIndexOutOfBoundsException e) {
            Log.e(TAG, e.getMessage());
        } finally {
            if (null == externalFilesDir) {
                externalFilesDir = context.getCacheDir();
            }
            return externalFilesDir;
        }

    }

}
