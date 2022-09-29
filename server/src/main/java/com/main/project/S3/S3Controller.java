
package com.main.project.S3;


import com.main.project.exception.BusinessLogicException;
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
    public String uploadImage(@RequestPart MultipartFile multipartFile) throws Exception {


        return s3Service.uploadMutipartFile(multipartFile, "reviewimg");
    }

    @GetMapping("/get/{filename}")
    public String getImageUrl(@PathVariable("filename") String filename) {

        return s3Service.findeImgUrl(filename);
    }


    @DeleteMapping("/delete/{filename}")
    public String deleteFile(@PathVariable("filename") String fileName) throws BusinessLogicException {
        return s3Service.deleteFile(fileName);

    }
}
