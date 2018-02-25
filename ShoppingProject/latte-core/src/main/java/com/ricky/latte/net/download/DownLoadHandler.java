package com.ricky.latte.net.download;

import android.os.AsyncTask;

import com.ricky.latte.net.RestCreator;
import com.ricky.latte.net.callback.IError;
import com.ricky.latte.net.callback.IFailure;
import com.ricky.latte.net.callback.IRequest;
import com.ricky.latte.net.callback.ISuccess;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hepengcheng on 2018/2/25.
 */

public class DownLoadHandler {
    private final String URL;

    private static final Map<String, Object> PARAMS = RestCreator.getParams();

    private final IRequest REQUEST;

    private final ISuccess SUCCESS;

    private final IFailure FAILURE;

    private final IError ERROR;

    private final String DOWNLOAD_DIR;

    private final String EXTENSION;

    private final String NAME;

    public DownLoadHandler(String url, IRequest request, ISuccess success, IFailure failure, IError error, String downloadDir, String extension, String name) {
        this.URL = url;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
    }

    public final void handleDownload() {
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        RestCreator.getRestService().download(URL, PARAMS).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    final ResponseBody requestBody = response.body();
                    final SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS);
                    task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR, EXTENSION, requestBody, NAME);

                    //这里一定要主要判断,否则文件下载不全
                    if (task.isCancelled()) {
                        if (REQUEST != null) {
                            REQUEST.onRequestEnd();
                        }
                    }
                } else {
                    if (ERROR != null) {
                        ERROR.onError(response.code(), response.message());
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (FAILURE != null) {
                    FAILURE.onFailure();
                }
            }
        });
    }

}
