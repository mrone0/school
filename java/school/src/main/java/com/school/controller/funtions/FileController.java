package com.school.controller.funtions;

import com.school.commons.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ResponseBody
@Controller
@RequestMapping("/file")
public class FileController {
    //上传到的文件位置---非本地
    private String newFileName = null;
    private String fileType = null;


    @RequestMapping(value = "/image",method = RequestMethod.POST)
    public Result upload(@RequestParam(name = "file", required = false) MultipartFile file) {
        Result res = new Result();
        if (file == null) {
            res.setCode(0);
            res.setMessage("传的图片为空");
            return res;
        }
        if (file.getSize() > 1024 * 1024 * 10) {
            res.setCode(0);
            res.setMessage("文件大小不能大于10M");
            return res;
        }
        //获取文件后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());
        if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
            res.setCode(0);
            res.setMessage("请选择jpg,jpeg,gif,png格式的图片");
            return res;
        }
        String savePath = new String("F:/image");
        File savePathFile = new File(savePath);
        if (!savePathFile.exists()) {
            //若不存在该目录，则创建目录
            savePathFile.mkdir();
        }
        //通过UUID生成唯一文件名
        String filename = UUID.randomUUID().toString().replaceAll("-","") + "." + suffix;
        try {
            //将文件保存指定目录
            file.transferTo(new File(savePath + filename));
        } catch (Exception e) {
            e.printStackTrace();
            res.setCode(0);
            res.setMessage("保存文件异常");
            return res;
        }
        //返回文件名称
        // return ResultUtil.success(ResultEnum.SUCCESS, filename, request);
        res.setCode(1);
        res.setMessage("成功保存文件");
        res.setData(filename);
        return res;
    }


    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Map<String, String> FileUpload(@RequestParam(name = "file", required = false)MultipartFile multipartFile) {
        Map<String, String> map = new HashMap<>();
        //文件不存在
        if (multipartFile == null) {
            map.put("msg", "请上传文件");
            return map;
        }
        //文件存在，获取文件名，文件地址
        String filename = multipartFile.getOriginalFilename();
        //控制文件大小
        Integer size = 1024 * 1024 * 10;
        if (multipartFile.getSize() > size) {
            map.put("msg", "文件不能超过10M");
            return map;
        }
        //重命名文件名
        String uuid = UUID.randomUUID().toString();
        //只支持JPG和PNG格式的文件类型
        String[] exName = {".jpg", ".png"};
        String ExtendName = filename.substring(filename.lastIndexOf("."));
        if(!(exName[0].equals(ExtendName)||exName[1].equals(ExtendName))){
            map.put("mag","不支持的文件类型");
            return map;
        }
        //拼接文件名
        newFileName=uuid.concat(ExtendName);
        String filePath = new File("").getAbsolutePath();
        File fileUpload = new File(filePath);
        if(!fileUpload.exists()){
            fileUpload.mkdirs();
        }
        fileUpload = new File(filePath,newFileName);
        try {
            multipartFile.transferTo(fileUpload);
            map.put("msg","上传成功");
            fileType=multipartFile.getContentType();
            map.put("fileType",fileType);
            return map;
        } catch (IOException e) {
            map.put("上传失败",e.toString());
        }
        return map;
    }



}