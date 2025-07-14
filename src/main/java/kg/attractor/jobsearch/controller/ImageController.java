package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.ImageDto;
import kg.attractor.jobsearch.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @GetMapping("{id}")
    public ImageDto findById(@PathVariable long id){
        return imageService.getImageById(id);
    }

    @PostMapping("save")
    public HttpStatus save(ImageDto imageDto){
        imageService.create(imageDto);
        return HttpStatus.CREATED;
    }
}
