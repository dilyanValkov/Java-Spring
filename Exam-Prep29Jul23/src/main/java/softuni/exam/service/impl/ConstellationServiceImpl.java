package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ConstellationImportDto;
import softuni.exam.models.entity.Constellation;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.service.ConstellationService;
import softuni.exam.util.ValidationUtil;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class ConstellationServiceImpl implements ConstellationService {
    private final static String FILE_PATH = "src/main/resources/files/json/constellations.json";
    private final ConstellationRepository constellationRepository;

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    @Autowired
    public ConstellationServiceImpl(ConstellationRepository constellationRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.constellationRepository = constellationRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.constellationRepository.count() > 0;
    }

    @Override
    public String readConstellationsFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_PATH)));
    }

    @Override
    public String importConstellations() throws IOException {
        StringBuilder sb = new StringBuilder();
        ConstellationImportDto[] dtos =
                gson.fromJson(new FileReader(FILE_PATH), ConstellationImportDto[].class);
        for (ConstellationImportDto dto : dtos) {
            Optional<Constellation> optConstellation = this.constellationRepository.findByName(dto.getName());
            if (!validationUtil.isValid(dto)||optConstellation.isPresent()){
                sb.append("Invalid constellation\n");
            }else {
                Constellation constellation = this.modelMapper.map(dto, Constellation.class);
                this.constellationRepository.save(constellation);
                sb.append(String.format("Successfully imported constellation %s - %s\n",dto.getName(),dto.getDescription()));
            }
        }
        return sb.toString();
    }
}
