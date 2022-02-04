package com.yuan.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.yuan.oss.service.OssService;
import com.yuan.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 * @author: yyss
 * @create: 2022-02-04 23:46
 **/
@Service
public class OssServiceImpl implements OssService {

    // 上传文件到oss
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // 工具类获取值
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            InputStream inputStream = file.getInputStream();
            // 获取文件名称,并用时间戳拼接
            String fileName = file.getOriginalFilename();
            // 在文件名称添加唯一随机值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            fileName = uuid+fileName;
            // 把文件按照日期进行分类  2022/02/05/01.jpeg
            // 获取当前日期
            String datePath = new DateTime().toString("yyyy/MM/dd");
            fileName = datePath + "/" + fileName;
            // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName, fileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            // 把上传之后的文件路径返回
            // 需要把上传到阿里云oss的路径手动拼接出来
            // https://yuan-online-edu.oss-cn-beijing.aliyuncs.com/img/avatar.jpeg?versionId=CAEQFhiBgMCOr7LK8hciIGMzM2M5N2M1MzlkMDRiYmRiODE4Y2FhNjYyMjMzZTUy
            String url = "https://" + bucketName + "." + endpoint + "/" + fileName;
            return url;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
