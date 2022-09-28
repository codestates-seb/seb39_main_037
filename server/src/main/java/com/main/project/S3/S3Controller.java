package com.main.project.S3;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/images")
@CrossOrigin("*")
public class S3Controller {


    S3Service s3Service;


    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/post")
    public String uploadImage(@RequestPart MultipartFile multipartFile) throws IOException{

      String storedS3ImgUrl =   s3Service.uploadMutipartFile(multipartFile,"reviewimg" );

      return storedS3ImgUrl;
    }

    @GetMapping("/get/{filename}")
    public String getImageUrl(@PathVariable String filename){

       return s3Service.findeImgUrl(filename);
    }




    @DeleteMapping("/delete/{filname}")
    public String deleteFile(@PathVariable("filename") String fileName){
        return s3Service.deleteFile(fileName);

    }

//    public static byte[] compressBytes(byte[] data){
//        Deflater deflater = new Deflater();
//        deflater.setInput(data);
//        deflater.finish();
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//        byte[] buffer = new byte[1024];
//        while (!deflater.finished()){
//            int count = deflater.deflate(buffer);
//            outputStream.write(buffer,0,count);
//        }
//
//        try{
//            outputStream.close();
//        }catch (IOException e){
//
//        }
//        return outputStream.toByteArray();
//
//    }




}
