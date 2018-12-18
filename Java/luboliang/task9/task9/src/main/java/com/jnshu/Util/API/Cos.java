package com.jnshu.Util.API;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.Download;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;

public class Cos {

    static String bucketName = "812497683"; //桶的名称
    //上传到云上路径
    static String region = "ap-beijing";//区域北京则  beijing
    static String appId = "1258109975"; //APPID
    static COSCredentials cred = null;
//    static TransferManager transferManager = null;
    static COSClient cosClient = null;
    static String SecretId = "AKIDVo7B1clECvS4TgaMuDH580d2FEySkcNw";
    static String SecretKey = "VnsiMsAF5g4C5jGZcR4SbVAgz96oIZbQ";
    static String bucket = bucketName + "-" + appId;

    static {
        cred = new BasicCOSCredentials(SecretId, SecretKey);
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        cosClient = new COSClient(cred, clientConfig);


    }

    public static String getURL(String key, File file) throws CosClientException, CosServiceException, InterruptedException {

        try {
            String bucket = bucketName + "-" + appId;
            System.out.println("file===" + file);
            PutObjectRequest putObjectRequest1 = new PutObjectRequest(bucket, key, file);
            putObjectRequest1.setStorageClass(StorageClass.Standard_IA);
// 设置自定义属性(如 content-type, content-disposition 等)
            ObjectMetadata objectMetadata = new ObjectMetadata();
// 设置 Content type, 默认是 application/octet-stream
            objectMetadata.setContentType("image/jpeg");
            putObjectRequest1.setMetadata(objectMetadata);

            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest1);
            String etag = putObjectResult.getETag();  // 获取文件的 etag

            URL url1 = cosClient.generatePresignedUrl(bucket, key, new Date(new Date().getTime() + 5 * 60 * 10000));
            String url = String.valueOf(url1);
            System.out.println(url);
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 下载
     */
    public static boolean download(String key, String path) {
        try {
            //下载到本地指定路径
            File localDownFile = new File(path);
            GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, key);

            // 下载文件
            // 等待传输结束（如果想同步的等待上传结束，则调用 waitForCompletion）
            ObjectMetadata downObjectMeta = cosClient.getObject(getObjectRequest, localDownFile);

            System.out.println("下载成功");
            return true;
        } catch (Throwable tb) {
            System.out.println("下载失败");

            tb.printStackTrace();
            return false;
        } finally {
            // 关闭 TransferManger
        }
    }

    /**
     * 列举
     * @return
     */
    public static List<COSObjectSummary> listObjects() {
// 获取 bucket 下成员（设置 delimiter）
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        listObjectsRequest.setBucketName(bucket);
// 设置 list 的 prefix, 表示 list 出来的文件 key 都是以这个 prefix 开始
        listObjectsRequest.setPrefix("");
// 设置 delimiter 为/, 即获取的是直接成员，不包含目录下的递归子成员
        listObjectsRequest.setDelimiter("/");
// 设置 marker, (marker 由上一次 list 获取到, 或者第一次 list marker 为空)
        listObjectsRequest.setMarker("");
// 设置最多 list 100个成员,（如果不设置, 默认为1000个，最大允许一次 list 1000个 key）
//    listObjectsRequest.setMaxKeys(1000);

        ObjectListing objectListing = cosClient.listObjects(listObjectsRequest);

// 获取下次 list 的 marker
        String nextMarker = objectListing.getNextMarker();
// 判断是否已经 list 完, 如果 list 结束, 则 isTruncated 为 false, 否则为 true
        boolean isTruncated = objectListing.isTruncated();
        List<COSObjectSummary> objectSummaries = objectListing.getObjectSummaries();

        return objectSummaries;
    }
}




