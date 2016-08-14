package com.gitSample.common.io;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by xyz on 8/14/2016.
 */
public class ConfigManager {

    private static ConfigManager sConfigManager;

    private ConfigManager (){

    }

    public interface OnCongifDownloadListner {

        void onConfigDownloaded(com.gitSample.common.dto.Configuration configuration);

        void onConfigFailed(VolleyError error);
    }

    public static ConfigManager getInstance() {
        if(sConfigManager == null) {
            sConfigManager = new ConfigManager();
        }
        return sConfigManager;
    }

    public void downLoadConfig(Context context, String url, OnCongifDownloadListner listner){
        GsonObjectRequest<com.gitSample.common.dto.Configuration> gsonObjectRequest = new GsonObjectRequest<com.gitSample.common.dto.Configuration>(
                Request.Method.GET, url , com.gitSample.common.dto.Configuration.class, successListner(listner), errorListener(listner));
        gsonObjectRequest.setTag(getClass().getName());
        VolleyRequestQueue.getInstance(context).addToRequestQueue(gsonObjectRequest);
    }


        private Response.Listener<com.gitSample.common.dto.Configuration> successListner(final OnCongifDownloadListner listner) {
          return new Response.Listener<com.gitSample.common.dto.Configuration>() {
              @Override
              public void onResponse(com.gitSample.common.dto.Configuration configuration) {
                  if(listner != null)
                      listner.onConfigDownloaded(configuration);
              }
          };
        }

    private Response.ErrorListener errorListener(final OnCongifDownloadListner listner) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(listner != null)
                    listner.onConfigFailed(error);
            }
        };
    }
}
