package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsons.PartsImportDto;
import softuni.exam.models.entity.Part;
import softuni.exam.repository.PartsRepository;
import softuni.exam.service.PartsService;
import softuni.exam.util.ValidationUtil;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class PartsServiceImpl implements PartsService {

    private static String PARTS_FILE_PATH = "src/main/resources/files/json/parts.json";
    private final PartsRepository partsRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public PartsServiceImpl(PartsRepository partsRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.partsRepository = partsRepository;

        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.partsRepository.count()>0;
    }

    @Override
    public String readPartsFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PARTS_FILE_PATH)));
    }

    @Override
    public String importParts() throws IOException {
        StringBuilder sb = new StringBuilder();
        PartsImportDto[] dtos =
                gson.fromJson(this.readPartsFileContent(), PartsImportDto[].class);

        for (PartsImportDto dto : dtos) {
            Optional<Part> optionalPart = this.partsRepository.findByPartName(dto.getPartName());
            if (!validationUtil.isValid(dto) || optionalPart.isPresent()){
                sb.append("Invalid part\n");
            }else {
                Part part = this.modelMapper.map(dto, Part.class);
                this.partsRepository.save(part);
                sb.append(String.format("Successfully imported part %s - ",part.getPartName()));
                sb.append(part.getPrice());
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }
}
