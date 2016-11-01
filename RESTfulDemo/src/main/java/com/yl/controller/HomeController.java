package com.yl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <pre>
 *   首页
 *
 * </pre>
 * <p>
 * Created by luxiaohu at 16/10/20 11:46
 */

@RestController
public class HomeController {

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value = "/home")
    public String home() {


        logger.info("test");

        return "Welcome RESTfulDeme";
    }

    /**
     * <p>
     *     上传图片;多张
     *     上传文件
     *
     * </p>
     *
     * @param name
     * @param age
     * @param images
     * @param file1
     * @return
     */
    @RequestMapping(value = "/addFile", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String addFile(@RequestParam(value ="name",required = false) String name,
                          @RequestParam(value ="age",required = false) String age,
                          @RequestParam(value ="images",required = true) MultipartFile[] images,
                          @RequestParam(value ="file1",required = true) MultipartFile file1) throws IOException {
        logger.info(name +" -- "+ age);
        logger.info("上传多张图片:"+images.length);
        if(images.length >0 ) {
            for(int i=0;i<images.length;i++) {
                String imageName = images[i].getOriginalFilename();
                long size = images[i].getSize();
                logger.info("图片"+i+"+:" + imageName + " ,大小:" + size);
                //获得文件流,可以直接服务端上传s3了
                //这里key,我使用的文件名,这个做法是错误的,我是为了方便测试才这么做.key应该是唯一的.
                //S3.putObject("test/"+imageName,images[i].getInputStream(),size,"image/jpeg");
            }
        }
        String imageName2 = file1.getOriginalFilename();
        long size2  = file1.getSize();
        logger.info("HTML:"+imageName2 + " 大小:"+size2);
        //获得文件流,可以直接服务端上传s3了
        //这里key,我使用的文件名,这个做法是错误的,我是为了方便测试才这么做.key应该是唯一的.
        //S3.putObject("test/"+imageName2,file1.getInputStream(),size2,"text/html;charset='utf-8'");
        return "success";
    }


}
