package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.ImageDto;
import kg.attractor.jobsearch.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @GetMapping()
    public ResponseEntity<?> findByFileName(@RequestParam(name = "fileName") String fileName){
        return imageService.findImageByFileName(fileName);
    }

    @PostMapping("save")
    public HttpStatus save(ImageDto imageDto){
        imageService.create(imageDto);
        return HttpStatus.CREATED;
    }
}
