package com.ricky.shopping;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ricky.latte.delegates.LatteDelegate;
import com.ricky.latte.net.RestClient;
import com.ricky.latte.net.callback.IError;
import com.ricky.latte.net.callback.IFailure;
import com.ricky.latte.net.callback.ISuccess;

/**
 * @author hepengcheng
 * @package_name com.ricky.shopping
 * @date 2018/1/5
 */

public class ExampleDelegate extends LatteDelegate {


    @Override
    public Object setLayout() {
        return R.layout.delete_example;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://blog.csdn.net/hpc19950723")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), "请求成功", Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
                        Log.e("hpc_kiven", "请求失败");
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(), "请求错误", Toast.LENGTH_SHORT).show();
                        Log.e("hpc_kiven", "请求错误");
                    }
                })
                .build()
                .get();
    }
}
