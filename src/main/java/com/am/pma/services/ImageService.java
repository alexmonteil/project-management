package com.am.pma.services;

import org.springframework.stereotype.Service;
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

    public String extractImageType(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
