package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.ImageDto;
import kg.attractor.jobsearch.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Override
    public ResponseEntity<?> findImageByFileName(String fileName){
        return downloadFile(fileName, "images", MediaType.IMAGE_JPEG);
    }

    @Override
    public void create(ImageDto imageDto){
        String fileName = saveUploadedFile(imageDto.getFile(), "images");
        System.out.println(fileName);
    }
}
