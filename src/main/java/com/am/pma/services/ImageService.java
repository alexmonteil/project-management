package com.am.pma.services;

import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class ImageService {

    public String convertByteArrayToFile(byte[] fileData, String extension) {
        if (fileData == null) {
            return "";
        }

        String imageBase64Data = Base64.getEncoder().encodeToString(fileData);
        return "data:" + extension + ";base64," + imageBase64Data;
    }

    public String extractImageType(MultipartFile file) throws IOException {
        Tika tika = new Tika();
        return tika.detect(file.getBytes());
    }
}
