package com.grow.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * 文件操作controller
 * @author liuxw
 * @date 2020/6/29
 * @since 1.0
 */
@Controller
@RequestMapping("/file")
public class FileController {

    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Value("${server.download.path}")
    private String downPath;

    @Value("${server.upload.path}")
    private String uploadPath;

    @RequestMapping("/test")
    @ResponseBody
    public String upload(HttpServletRequest request){
        return "test";
    }

    /**
     * 上传单个文件
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
        String filePath = "E:/upload/";

        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            logger.info("上传成功");
            return "上传成功";
        } catch (IOException e) {
            logger.error(e.toString(), e);
        }
        return "上传失败！";
    }


    /**
     * 多文件上传
     * @param request
     * @return
     */
    @PostMapping("/multiUpload")
    @ResponseBody
    public String multiUpload(@RequestParam("file")MultipartFile[] files,HttpServletRequest request) {

        String filePath = "E:/upload/";

        for (int i = 0; i < files.length; i++) {

            MultipartFile file = files[i];

            if (file.isEmpty()) {
                return "上传第" + (i++) + "个文件失败";
            }

            String fileName = file.getOriginalFilename();

            File dest = new File(filePath + fileName);

            try {
                file.transferTo(dest);

                logger.info("第" + (i + 1) + "个文件上传成功" + fileName);

            } catch (IOException e) {
                logger.error(e.toString(), e);
                return "上传第" + (i++) + "个文件失败";
            }
        }

        return "上传成功";

    }


    /**
     * 文件下载
     */
    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response){

        //待下载文件名
        String fileName = "zk.png";

        String type = new MimetypesFileTypeMap().getContentType(fileName);

        //设置为png格式的文件
        response.setHeader("content-type", type);
        /*response.setContentType("application/octet-stream");*/
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        byte[] buff = new byte[1024];

        //创建缓冲输入流
        BufferedInputStream bis = null;
        OutputStream outputStream = null;

        try {
            outputStream = response.getOutputStream();

            //这个路径为待下载文件的路径
            bis = new BufferedInputStream(new FileInputStream(new File("D:/SourceCode/" + fileName )));
            int read = bis.read(buff);

            //通过while循环写入到指定了的文件夹中
            while (read != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                read = bis.read(buff);
            }
        } catch ( IOException e ) {
            e.printStackTrace();
            //出现异常返回给页面失败的信息
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //成功后返回成功信息
    }

}
