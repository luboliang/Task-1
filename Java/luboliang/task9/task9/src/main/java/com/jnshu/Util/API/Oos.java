package com.jnshu.Util.API;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.common.utils.IOUtils;
import com.aliyun.oss.model.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

public class Oos {
    static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    static String accessKeySecret = "f6HQXy3uf9RIsbT40b91uoDOldO9ke";
    static String accessKeyId = "LTAInaGl38lwb5JO";
    static String bucketName = "luboliang";
    static OSSClient ossClient = null;

    static {
        // 创建OSSClient实例。
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 上传
     *
     * @param key
     * @param file
     * @return
     */
    public static String geturl(String key, File file) {
        // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
        ossClient.putObject(bucketName, key, file);
        System.out.println("上传成功");
// 关闭OSSClient。
        URL url = null;
        url = ossClient.generatePresignedUrl(bucketName, key, new Date(new Date().getTime() + 5 * 60 * 10000));
        return String.valueOf(url);
    }

    /**
     * 下载
     * @param key
     * @param path
     * @return
     */
    public static boolean download(String key, String path) {
        try {
// 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
            ossClient.getObject(new GetObjectRequest(bucketName, key), new File(path));
// 关闭OSSClient。
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 列举文件
     * @return
     */
    public static List getobjects(){
// 列举文件。
        ObjectListing objectListing = ossClient.listObjects(new ListObjectsRequest(bucketName));
        List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
// 关闭OSSClient。

        return sums;
    }

    /**
     * 图片处理
     */
    public static void x(){
        String sourceImage = "qqq.JPEG";
        try {
            // 创建OSSClient实例。
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

            // 图片处理持久化 : 缩放
            StringBuilder sbStyle = new StringBuilder();
            Formatter styleFormatter = new Formatter(sbStyle);
            String styleType = "image/resize,m_fixed,w_100,h_100";
            String targetImage = "clipboard.png";
            styleFormatter.format("%s|sys/saveas,o_%s,b_%s", styleType,
                    BinaryUtil.toBase64String(targetImage.getBytes()),
                    BinaryUtil.toBase64String(bucketName.getBytes()));
            System.out.println(sbStyle.toString());
            ProcessObjectRequest request = new ProcessObjectRequest(bucketName, sourceImage, sbStyle.toString());
            GenericResult processResult = ossClient.processObject(request);
            String json = IOUtils.readStreamAsString(processResult.getResponse().getContent(), "UTF-8");
            processResult.getResponse().getContent().close();
            System.out.println(json);

            // 图片处理持久化 : 裁剪
            sbStyle.delete(0, sbStyle.length());
            styleType = "image/crop,w_100,h_100,x_100,y_100,r_1";
            targetImage = "example-crop.png";
            styleFormatter.format("%s|sys/saveas,o_%s,b_%s", styleType,
                    BinaryUtil.toBase64String(targetImage.getBytes()),
                    BinaryUtil.toBase64String(bucketName.getBytes()));
            System.out.println(sbStyle.toString());
            request = new ProcessObjectRequest(bucketName, sourceImage, sbStyle.toString());
            processResult = ossClient.processObject(request);
            json = IOUtils.readStreamAsString(processResult.getResponse().getContent(), "UTF-8");
            processResult.getResponse().getContent().close();
            System.out.println(json);

            // 图片处理持久化 : 旋转
            sbStyle.delete(0, sbStyle.length());
            styleType = "image/rotate,90";
            targetImage = "example-rotate.png";
            styleFormatter.format("%s|sys/saveas,o_%s,b_%s", styleType,
                    BinaryUtil.toBase64String(targetImage.getBytes()),
                    BinaryUtil.toBase64String(bucketName.getBytes()));
            request = new ProcessObjectRequest(bucketName, sourceImage, sbStyle.toString());
            processResult = ossClient.processObject(request);
            json = IOUtils.readStreamAsString(processResult.getResponse().getContent(), "UTF-8");
            processResult.getResponse().getContent().close();
            System.out.println(json);

            // 图片处理持久化 : 锐化
            sbStyle.delete(0, sbStyle.length());
            styleType = "image/sharpen,100";
            targetImage = "example-sharpen.png";
            styleFormatter.format("%s|sys/saveas,o_%s,b_%s", styleType,
                    BinaryUtil.toBase64String(targetImage.getBytes()),
                    BinaryUtil.toBase64String(bucketName.getBytes()));
            request = new ProcessObjectRequest(bucketName, sourceImage, sbStyle.toString());
            processResult = ossClient.processObject(request);
            json = IOUtils.readStreamAsString(processResult.getResponse().getContent(), "UTF-8");
            processResult.getResponse().getContent().close();
            System.out.println(json);

            // 图片处理持久化 : 水印
            sbStyle.delete(0, sbStyle.length());
            styleType = "image/watermark,text_SGVsbG8g5Zu-54mH5pyN5YqhIQ";
            targetImage = "example-watermark.png";
            styleFormatter.format("%s|sys/saveas,o_%s,b_%s", styleType,
                    BinaryUtil.toBase64String(targetImage.getBytes()),
                    BinaryUtil.toBase64String(bucketName.getBytes()));
            request = new ProcessObjectRequest(bucketName, sourceImage, sbStyle.toString());
            processResult = ossClient.processObject(request);
            json = IOUtils.readStreamAsString(processResult.getResponse().getContent(), "UTF-8");
            processResult.getResponse().getContent().close();
            System.out.println(json);

            // 图片处理持久化 : 格式转换
            sbStyle.delete(0, sbStyle.length());
            styleType = "image/format,jpg";
            targetImage = "example-formatconvert.jpg";
            styleFormatter.format("%s|sys/saveas,o_%s,b_%s", styleType,
                    BinaryUtil.toBase64String(targetImage.getBytes()),
                    BinaryUtil.toBase64String(bucketName.getBytes()));
            request = new ProcessObjectRequest(bucketName, sourceImage, sbStyle.toString());
            processResult = ossClient.processObject(request);
            json = IOUtils.readStreamAsString(processResult.getResponse().getContent(), "UTF-8");
            processResult.getResponse().getContent().close();
            System.out.println(json);

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
    public  static void  hotlinkProtection(){
        List<String> refererList = new ArrayList<String>();
// 添加Referer白名单。Referer参数支持通配符星号（*）和问号（？）。
        refererList.add("127.0.0.1");
        refererList.add("118.190.199.167");
// 设置存储空间Referer列表。设为true表示Referer字段允许为空。
        BucketReferer br = new BucketReferer(true, refererList);
        ossClient.setBucketReferer(bucketName, br);
// 关闭OSSClient。
//        ossClient.shutdown();

    }

    public  static void getHotlinkProtection(){
// 获取存储空间Referer白名单列表。
        BucketReferer br = ossClient.getBucketReferer(bucketName);
        List<String> refererList = br.getRefererList();
        for (String referer : refererList) {
            System.out.println(referer);
        }
// 关闭OSSClient。
//        ossClient.shutdown();
    }

    public static void deleteHotlinkProtection(){
        // 防盗链不能直接清空，需要新建一个允许空Referer的规则来覆盖之前的规则。
        BucketReferer br = new BucketReferer();
        ossClient.setBucketReferer(bucketName, br);
// 关闭OSSClient。
//    ossClient.shutdown();
    }

}

