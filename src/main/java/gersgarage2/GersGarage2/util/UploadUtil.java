package gersgarage2.GersGarage2.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class UploadUtil {

    public static boolean uploadImg(MultipartFile img) {

        boolean successUpload = false;

        if(!img.isEmpty()) {
            String fileName = img.getOriginalFilename();
            try{
                String directoryUploadImg = "C:\\Development\\Workspace\\Gers-Garage2\\src\\main\\resources\\static\\img\\imgUploads";
                File dir = new File(directoryUploadImg);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

                stream.write(img.getBytes());
                stream.close();

                System.out.println("stored in: " + serverFile.getAbsolutePath());
                System.out.println("you made the upload of " + fileName + "with success");

            } catch (Exception e){
                System.out.println("upload of " + fileName + "failed. => " + e.getMessage());

            }
            } else {
            System.out.println("upload failed because the file is empty");
        } return successUpload;
    }
}
