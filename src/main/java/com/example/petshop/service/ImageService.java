package com.example.petshop.service;

import com.example.petshop.model.Exceptions.InvalidImageException;
import com.example.petshop.model.Image;
import com.example.petshop.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public Image getByName(String name){
        return this.imageRepository.findByName(name).orElseThrow(()->new InvalidImageException(name));
    }
    private String getFileName(MultipartFile multipartFile) {
        return StringUtils.cleanPath(multipartFile.getOriginalFilename().replaceAll("\\s+", "_"));
    }

    public Image create(MultipartFile multipartFile) throws RuntimeException{
        //We must use RunTimeException because otherwise we will get error of unhandled IO Exception for get Bytes
        try {
            return this.imageRepository.save(new Image(this.getFileName(multipartFile), multipartFile.getBytes(), multipartFile.getContentType()));
        }
        catch (IOException ioException){
            throw new RuntimeException("Could not process image");
        }
    }

    public Image update(Image image, MultipartFile multipartFile) throws RuntimeException{
        try{
            image.setName(this.getFileName(multipartFile));
            image.setData(multipartFile.getBytes());
            image.setType(multipartFile.getContentType());

            return image;
        }
        catch (IOException ioException){
            throw new RuntimeException("Could not process image");
        }
    }

}
