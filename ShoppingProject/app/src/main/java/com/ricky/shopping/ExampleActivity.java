package com.ricky.shopping;

import com.ricky.latte.activities.ProxyActivity;
import com.ricky.latte.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
