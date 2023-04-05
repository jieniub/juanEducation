package com.ljj.admin.client.hystrix;

import com.ljj.admin.client.VodClient;
import com.ljj.common.utils.R;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodClientServiceBlow implements VodClient {
    @Override
    public R remove(String id) {
        System.out.println("熔断");
        return R.error().message("time out");
    }

    @Override
    public boolean removeByIds(List<String> ids) {
        return false;
    }
}
