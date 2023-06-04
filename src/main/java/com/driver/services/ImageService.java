package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);

        // add list to blog
        List<Image> imageList = blog.getImageList();
        imageList.add(image);

        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        int count = 0;
        String []diarr = screenDimensions.split("X");
        Image image = imageRepository2.findById(id).get();
        String dimensionOfImage = image.getDimensions();
        String []imgarr = dimensionOfImage.split("X");
        int imageX=Integer.parseInt(imgarr[0]);
        int imageY=Integer.parseInt(imgarr[1]);

        int dimensionX=Integer.parseInt(diarr[0]);
        int dimensionY=Integer.parseInt(diarr[1]);

        int countX = dimensionX/imageX;
        int countY = dimensionY/imageY;
        count=countX*countY;

        return count;
    }
}
