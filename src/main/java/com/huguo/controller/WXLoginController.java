package com.huguo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WXLoginController {
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String test(String code){
        System.out.println("wxcode : " + code);

        /*GET https://api.weixin.qq.com/sns/jscode2session?appid=APPID
                                                           &secret=SECRET
                                                           &js_code=JSCODE
                                                           &grant_type=authorization_code*/
        Map<String,String> urlparam = new HashMap<String,String>();
        urlparam.put("appid","wxd6ec84e1027cfa8b");
        urlparam.put("secret","f23d5d80485fbd24f355b17cc9f2235e");
        urlparam.put("js_code",code);
        urlparam.put("grant_type","authorization_code");

        return "hello";
    }
}
