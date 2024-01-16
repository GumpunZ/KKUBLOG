package com.cp.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cp.Model.Image;
import com.cp.Repository.ImageRepository;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    public List<Image> findAllImage(){
        List<Image> imagelist = new ArrayList<>();
        imageRepository.findAll().forEach(imagelist::add);
        return imagelist;
    }

    public void saveImage(Image I){
        this.imageRepository.save(I);
    }

    public Image getImageId(Integer imageid){
        return this.imageRepository.findById(imageid).get();
    }
    
    public void deleteImage(Image i){
        this.imageRepository.delete(i);
    }

}
