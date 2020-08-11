package com.grow.demo.record;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @author: create by liuxw
 * @date: 2018/11/7
 */


public class TestZIP {

    private static final Logger logger = LoggerFactory.getLogger(TestZIP.class);
    /**
     *    * 功能:压缩多个文件成一个zip文件
     *    * @param srcfile：源文件列表
     *    * @param zipfile：压缩后的文件
     *    
     */
    public static void zipFiles(File[] srcfile, File zipfile) {
        byte[] buf = new byte[1024];
        try {
            //ZipOutputStream类：完成文件或文件夹的压缩
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
            for (int i = 0; i < srcfile.length; i++) {
                FileInputStream in = new FileInputStream(srcfile[i]);
                out.putNextEntry(new ZipEntry(srcfile[i].getName()));
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.closeEntry();
                in.close();
            }
            out.close();
            System.out.println("压缩完成.");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static InputStream getUrlInputStream(String uri){

        try{
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置请求方式为"GET"
            conn.setRequestMethod("GET");
            //超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            //通过输入流获取图片数据
            return conn.getInputStream();
        }catch (Exception e){
            e.getMessage();
            return null;
        }
    }

    public static void zipFiles(final List<String> list, final File zipfile) {
        byte[] buf = new byte[1024];
        try {

            final PipedOutputStream pos = new PipedOutputStream();
            final PipedInputStream pis = new PipedInputStream();
            //将管道输出流连接到管道输入流来创建通信管道
            pos.connect(pis);

            final ZipOutputStream out = new ZipOutputStream(pos);

            //ZipOutputStream类：完成文件或文件夹的压缩
            /*ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
            for (int i = 0; i < list.size(); i++) {

                InputStream in = getUrlInputStream(list.get(i)); //FileInputStream in = new FileInputStream(srcfile[i]);
                out.putNextEntry(new ZipEntry("222"+ new Random().nextInt()+".jpg"));
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.closeEntry();
                in.close();
            }
            out.close();*/


            //创建生产者线程来为管道输出流写入数据.
            new Thread(new Runnable() {

                @Override
                public void run() {
                    byte[] buf = new byte[1024];
                    try{
                        for (int i = 0; i < list.size(); i++) {

                            InputStream in = getUrlInputStream(list.get(i)); //FileInputStream in = new FileInputStream(srcfile[i]);
                            out.putNextEntry(new ZipEntry("222"+ new Random().nextInt()+".jpg"));
                            int len;
                            while ((len = in.read(buf)) > 0) {
                                out.write(buf, 0, len);
                            }
                            out.closeEntry();
                            in.close();
                        }
                        out.close();
                    }catch (Exception e){

                    }
                }
            }).start();

            //创建消费者线程来从 PipedInputStream对象读取数据
            Thread customer = new Thread(new Runnable() {

                @Override
                public void run() {
                    try{

                        String newPath = "d://t1.zip";
                        File file = new File(newPath);
                        OutputStream outputStream = new FileOutputStream(file);

                        byte[] buf1 = new byte[1024];
                        int len;
                        while ((len = pis.read(buf1)) > 0) {
                            outputStream.write(buf1, 0, len);
                        }
                        outputStream.close();
                        pis.close();
                    }catch (Exception e){
                        logger.error(e.getMessage());
                        System.out.println("-----------"+e);
                    }

                }
            });
            customer.start();

            try {
                //勿必等所有数据读完才继续后面的操作,否则可能造成数据仍未读完便GameOver了.
                customer.join();
            } catch (InterruptedException e) {
            }

            System.out.println("压缩完成.");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     *    * 功能:解压缩
     *    * @param zipfile：需要解压缩的文件
     *    * @param descDir：解压后的目标目录
     *    
     */
    public static void unZipFiles(File zipfile, String descDir) {
        try {
            ZipFile zf = new ZipFile(zipfile);
            for (Enumeration entries = zf.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryName = entry.getName();
                InputStream in = zf.getInputStream(entry);
                OutputStream out = new FileOutputStream(descDir + zipEntryName);
                byte[] buf1 = new byte[1024];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                in.close();
                out.close();
                System.out.println("解压缩完成.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能:
     *    * @param args
     *    
     */
    public static void main(String[] args) {

        List<String> imgs = new ArrayList<>();
        imgs.add("http://t2.hddhhn.com/uploads/tu/201801/9999/04fd84a337.jpg");
        imgs.add("http://t2.hddhhn.com/uploads/tu/201801/9999/9038254043.jpg");
        File zipfile = new File("D:\\biao1.zip");
        TestZIP.zipFiles(imgs,zipfile);
        /*//2个源文件
        File f1 = new File("D:\\20171227125439.jpg");
        File f2 = new File("D:\\20171227125550.jpg");
        File[] srcfile = {f1, f2};
        //压缩后的文件
        File zipfile = new File("D:\\biao.zip");
        TestZIP.zipFiles(srcfile, zipfile);
        //需要解压缩的文件
        File file = new File("D:\\biao.zip");
        //解压后的目标目录
        String dir = "D:\\test\\";
        TestZIP.unZipFiles(file, dir);*/
    }
}