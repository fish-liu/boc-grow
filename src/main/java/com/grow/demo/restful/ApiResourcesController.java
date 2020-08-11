package com.grow.demo.restful;

import com.grow.demo.common.ConResult;
import com.grow.demo.common.enums.FileTypeEnum;
import com.grow.demo.model.Resources;
import com.grow.demo.service.ResourcesService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 文件操作controller
 * @author liuxw
 * @since 1.0
 */
@Api(tags = "resource 接口API")
@RestController
@RequestMapping("/api/resource")
public class ApiResourcesController {

    private static Logger logger = LoggerFactory.getLogger(ApiResourcesController.class);

    @Value("${file.download.path}")
    private String downPath;

    @Value("${file.upload.path}")
    private String uploadPath;

    @Autowired
    private ResourcesService resourcesService;


    /**
     * 上传单个文件，每天产生一个文件夹
     * @param file
     * @return
     */
    @ApiOperation(value = "单个文件上传",notes = "上传成功，返回文件在数据中的 id ")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "file", value = "上传文件", required = true, dataType = "MultipartFile"),
            @ApiImplicitParam(paramType="query", name = "uid", value = "用户id", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "type", value = "文件类型", required = true, dataType = "Integer")
    })
    @PostMapping("/upload")
    public ConResult upload(@RequestParam("file") MultipartFile file,
                            @RequestParam("uid") Integer uid,
                            @RequestParam("type") Integer type) {

        if (file.isEmpty()) {
            return ConResult.fail("上传失败，请选择文件");
        }

        if(uid == null || uid == 0){
            return ConResult.fail();
        }

        try{
            // 文件类型的验证
            FileTypeEnum.getEnum(type);
        }catch (Exception e){
            return ConResult.fail();
        }

        String fileName = file.getOriginalFilename();

        String filePath = createFIlePath();

        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            logger.info("上传成功");

            // 构建上传的信息
            Resources re = Resources.builder()
                    .uid(uid)
                    .name(fileName)
                    .path(filePath + fileName)
                    .size(1)
                    .type(type)
                    .build();

            resourcesService.save(re);
            return ConResult.success(re.getId());

        } catch (IOException e) {
            logger.error(e.toString(), e);
        }
        return ConResult.fail("上传失败，请选择文件");
    }


    /**
     * 多文件上传
     * @param files
     * @param uid
     * @param type
     * @return
     */
    @ApiOperation(value = "多文件上传",notes = "上传成功，返回文件在数据库中的 ids（数组）")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "file", value = "上传文件", required = true, dataType = "MultipartFile"),
            @ApiImplicitParam(paramType="query", name = "uid", value = "用户id", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "type", value = "文件类型", required = true, dataType = "Integer")
    })
    @PostMapping("/multiUpload")
    public String multiUpload(@RequestParam("file") MultipartFile[] files,
                              @RequestParam("uid") Integer uid,
                              @RequestParam("type") Integer type
                              ) {

        String filePath = "/Users/itinypocket/workspace/temp/";

        for (int i = 0; i < files.length; i++) {

            MultipartFile file = files[i];

            if (file.isEmpty()) {
                return "上传第" + (i++) + "个文件失败";
            }

            String fileName = file.getOriginalFilename();

            File dest = new File(filePath + fileName);

            try {
                file.transferTo(dest);
                logger.info("第" + (i + 1) + "个文件上传成功");

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
    @ApiOperation(value = "文件下载",notes = "下载指定的文件")
    @RequestMapping(value = "/download",method = RequestMethod.GET)
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

    /**
     * 创建上传文件存放地址
     * @return
     */
    private String createFIlePath(){

        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");

        String path = uploadPath + File.pathSeparator + sf.format(new Date()) + File.pathSeparator ;

        File f = new File(path);
        if(!f.exists()){
            f.mkdirs();
        }

        return path;
    }

}
