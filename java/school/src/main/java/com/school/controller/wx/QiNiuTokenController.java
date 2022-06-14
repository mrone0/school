package com.school.controller.wx;


import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.school.commons.Result;
import com.school.dto.QiNiu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@ResponseBody
@Controller
@RequestMapping("/wx")
public class QiNiuTokenController {


    QiNiu qiNiu = new QiNiu();


    // 获取 七牛云的 token
    @RequestMapping(value = "/getToken", method = RequestMethod.GET)
    public String getToken() {
        String accessKey = "";
        String secretKey = "";
        String bucket = "mronei";
        long expireSeconds = 600;   //过期时间
        StringMap putPolicy = new StringMap();
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
        qiNiu.setKey(UUID.randomUUID().toString().replaceAll("\\-", ""));
        qiNiu.setToken(upToken);
        return qiNiu.getToken();
    }

    @GetMapping(value = "/qiniu/upload")
    public Result UpLoad() {
        Result result = new Result();
        Configuration cfg = new Configuration(Region.huanan());//Region.huanan()对应自己的存储区域
        UploadManager uploadManager = new UploadManager(cfg);
        String token = qiNiu.getToken();
        String key = qiNiu.getKey();
        String url = "/";//这里是你的域名地址
        String filePath = "C:/Users/一字先生/Desktop/index.jpg";
        try {
            Response response = uploadManager.put(filePath, key, token);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            String imageUrl = url + putRet.key;
            result.setData(imageUrl);
            return result;
        } catch (QiniuException e) {
            e.printStackTrace();
            result.setMessage("err");
            return result;
        }
    }
}
