package com.ljj.msg.service;


import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.aliyuncs.DefaultAcsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MsgServiceImpl implements MsgService{

    @Value("${sping.alibaba.msg.acaccessKeyId}")
    private String accessKeyId;
    @Value("${sping.alibaba.msg.accessKeySecret}")
    private String accessKeySecret;

    @Override
    public boolean sendMsg(String code, String phone) {


        String param = "{\"code\":" + "\"" + code + "\"}";
        try {
            Client client = new Client(new Config().setAccessKeyId(accessKeyId).setAccessKeySecret(accessKeySecret));
            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setPhoneNumbers(phone)
                    .setSignName("卷芯菜")
                    .setTemplateCode("SMS_274525897")
                    .setTemplateParam(param);


            SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
            if (sendSmsResponse.getStatusCode() == 200){
                System.out.println("发送成功");
                return true;
            }
            else{
                System.out.println(sendSmsResponse.body.getMessage());
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }
}
