package com.huguo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huguo.entity.User;
import com.huguo.service.UserService;
import com.huguo.utils.httprest.HttpRestClient;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class WXLoginController {

    private HttpRestClient httpRestClient;
    @Autowired
    private UserService userservice;
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String test(@RequestBody JSONObject jsonobj) throws Exception{
        System.out.println("wxdata : " + jsonobj.toString());

        /*GET https://api.weixin.qq.com/sns/jscode2session?appid=APPID
                                                           &secret=SECRET
                                                           &js_code=JSCODE
                                                           &grant_type=authorization_code*/
        /*Map<String,String> urlparam = new HashMap<String,String>();
        urlparam.put("appid","wxd6ec84e1027cfa8b");
        urlparam.put("secret","f23d5d80485fbd24f355b17cc9f2235e");
        urlparam.put("js_code",code);
        urlparam.put("grant_type","authorization_code");*/

        String result = null;
        try {
            //api url地址
            String url = "https://api.weixin.qq.com/sns/jscode2session";
            //post请求
            HttpMethod method =HttpMethod.GET;
            // 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
            MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
            params.add("appid","wxd6ec84e1027cfa8b");
            params.add("secret","f23d5d80485fbd24f355b17cc9f2235e");
            String code = String.valueOf(jsonobj.get("code"));
            params.add("js_code", code);
            params.add("grant_type","authorization_code");
            //发送http请求并返回结果
            result = httpRestClient.doHttpRest(url,method,params);
            JSONObject res = JSONObject.parseObject(result);
            if(res.get("openid")!=null){
                User user = new User();
                String openid = String.valueOf(res.get("openid"));
                //user.setCode(1);
                user.setWxopenid(openid);
                String nickname = String.valueOf(jsonobj.get("nickName"));
                user.setWxnickname(nickname);
                user.setProtraiturl(String.valueOf(jsonobj.get("avatarUrl")));
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date data = new Date();
                user.setCreatetime(String.valueOf(sf.format(data)));
                user.setTs(String.valueOf(sf.format(data)));
                user.setDr(0);
                int nisertres = userservice.insertUser(user);
                System.out.println("openid : " + res.get("openid"));
            }
            System.out.println("result : " + result);
        } catch (Exception e) {
            result = e.getMessage();
            throw new Exception(e);
        }

        return result;
    }

    @RequestMapping(value="/search",method = RequestMethod.GET)
    public String serchAll(){
        try {
            List<User> users= userservice.searchAllUser(10,10);
            String result = MAPPER.writeValueAsString(users);
            System.out.println("result : " + result);
            return result;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "ok";
    }

}
