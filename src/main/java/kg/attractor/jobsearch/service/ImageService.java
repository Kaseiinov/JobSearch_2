package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.ImageDto;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public interface ImageService {

    @SneakyThrows
    default String saveUploadedFile(MultipartFile file, String subDir) {
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + "_" + file.getOriginalFilename();

        Path pathDir = Paths.get("data/" + subDir);
        Files.createDirectories(pathDir);

        Path filePath = Paths.get(pathDir  + "/" + fileName);
        if(!Files.exists(filePath)) Files.createFile(filePath);

        try(OutputStream os = Files.newOutputStream(filePath)){
            os.write(file.getBytes());
        } catch (IOException e){
            e.printStackTrace();
        }

        return fileName;
    };

    ImageDto getImageById(long id);

    void create(ImageDto imageDto);
}
