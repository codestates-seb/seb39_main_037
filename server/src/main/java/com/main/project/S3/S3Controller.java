package com.main.project.S3;


import com.amazonaws.services.ec2.model.Image;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;

@RestController

@CrossOrigin("*")
public class S3Controller {


    S3UploadService2 s3UploadService2;


    public S3Controller(S3UploadService2 s3UploadService2) {
        this.s3UploadService2 = s3UploadService2;
    }

    @PostMapping("/images")
    public String uploadImage(@RequestPart MultipartFile multipartFile) throws IOException{

      String storedS3ImgUrl =   s3UploadService2.uploadMutipartFile(multipartFile,"reviewimg" );

      return storedS3ImgUrl;
    }


    public static byte[] compressBytes(byte[] data){
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()){
            int count = deflater.deflate(buffer);
            outputStream.write(buffer,0,count);
        }

        try{
            outputStream.close();
        }catch (IOException e){

        }
        return outputStream.toByteArray();

    }

}
