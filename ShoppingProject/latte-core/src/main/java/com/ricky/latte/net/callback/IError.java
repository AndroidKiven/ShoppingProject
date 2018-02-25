package com.ricky.latte.net.callback;

/**
 * @author hepengcheng
 * @package_name com.ricky.latte.net.callback
 * @date 2018/1/10
 */

public interface IError {
    void onError(int code, String msg);
}
