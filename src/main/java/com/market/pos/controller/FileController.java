package com.market.pos.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
public class FileController {
    @Autowired
//    private imgService service;

    @Value("${com.qiuchengxin}")
    //获取主机端口
    private String post;
    //获取本机ip
    private String host;
    //图片存放根路径post
    private String rootPath = "C:";
    //图片存放根目录下的子目录
    private String sonPath = "/img/";
    //获取图片链接
    private String imgPath;

//    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "upload")
    @ResponseBody
    public String upload(@RequestParam("test") MultipartFile file) {
        System.out.println("进入后台");
        //返回上传的文件是否为空，即没有选择任何文件，或者所选文件没有内容。
        //防止上传空文件导致奔溃
        if (file.isEmpty()) {
            return "文件为空";
        }

        //获取本机IP
        try {
            host = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            System.out.println("get server host Exception e:");
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 设置文件上传后的路径
        String filePath = rootPath + sonPath;
//        System.out.println("上传的文件路径" + filePath);
        System.out.println("上传图片路径：" + host + ":" + post + sonPath + fileName);
        //创建文件路径
        File dest = new File(filePath + fileName);

        String imgPath = (host + ":" + post + sonPath + fileName).toString();

        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;

        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            //假如文件不存在即重新创建新的文件已防止异常发生
            dest.getParentFile().mkdirs();
        }
        try {
            //transferTo（dest）方法将上传文件写到服务器上指定的文件
            file.transferTo(dest);
            //将链接保存到URL中
//            imgTest imgTest = service.add(new imgTest(), imgPath);
//            JSONObject suc = new JSONObject();
//            JSONObject src = new JSONObject();
//            suc.put("code", 0);
//            suc.put("msg", "上传成功");
//            src.put("src",host + ":" + post + sonPath + fileName);
//            suc.put("data", src);
//            System.out.println(suc);
//                String html = "<!DOCTYPE html>\n" +
//                        "<html lang=\"en\">\n" +
//                        "<head>\n" +
//                        "<title></title>\n" +
//                        "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n" +
//                        "</head>\n" +
//                        "<body>\n" +
//                        "<img src=" + host + ":" + post + sonPath + fileName + ">\n" +
//                        "</body>\n" +
//                        "</html>";
                return "上传成功";
        } catch (Exception e) {
            return "上传失败";
        }
    }
}