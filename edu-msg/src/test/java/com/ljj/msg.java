package com.ljj;


import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.AddSmsTemplateRequest;
import com.aliyun.dysmsapi20170525.models.AddSmsTemplateResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import org.junit.Test;

public class msg {

    @Test
    public void test(){
        try {
            Client client = new Client(new Config().setAccessKeyId("LTAI5tSbsPh1SXsYxN1gRkcX")
                    .setAccessKeySecret("YDFhLYL0LHFAKrI12peVVCZE0pnWJ5"));
            AddSmsTemplateRequest templateRequest = new AddSmsTemplateRequest()
                    .setTemplateType(0)
                    .setTemplateName("content3")
                    .setTemplateContent("您正在申请手机注册，验证码为：${code}，5分钟内有效")
                    .setRemark("test");
            client.addSmsTemplate(templateRequest);
            AddSmsTemplateResponse response=client.addSmsTemplate(templateRequest);
            System.out.println(JSONObject.toJSONString(response));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
