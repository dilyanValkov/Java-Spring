package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.VolcanoImportDto;
import softuni.exam.models.entity.Country;
import softuni.exam.models.entity.Volcano;
import softuni.exam.repository.CountryRepository;
import softuni.exam.repository.VolcanoRepository;
import softuni.exam.service.VolcanoService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class VolcanoServiceImpl implements VolcanoService {
    private final String FILE_PATH = "src/main/resources/files/json/volcanoes.json";
    private final ModelMapper modelMapper;

    private final Gson gson;

    private final ValidationUtil validationUtil;

    private final VolcanoRepository volcanoRepository;

    private final CountryRepository countryRepository;

    public VolcanoServiceImpl(ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, VolcanoRepository volcanoRepository, CountryRepository countryRepository) {
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.volcanoRepository = volcanoRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public boolean areImported() {
        return this.volcanoRepository.count() > 0;
    }

    @Override
    public String readVolcanoesFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_PATH)));
    }

    @Override
    public String importVolcanoes() throws IOException {
        StringBuilder sb = new StringBuilder();
        VolcanoImportDto[] dtos =
                this.gson.fromJson(readVolcanoesFileContent(), VolcanoImportDto[].class);
        for (VolcanoImportDto dto : dtos) {
            Optional<Volcano> optionalVolcano = this.volcanoRepository.findByName(dto.getName());
            if (!validationUtil.isValid(dto) || optionalVolcano.isPresent()){
                sb.append("Invalid volcano\n");
            }else {
                Volcano volcano = this.modelMapper.map(dto, Volcano.class);
                Country country = this.countryRepository.getById(dto.getCountry());
                volcano.setCountry(country);

                this.volcanoRepository.saveAndFlush(volcano);
                sb.append(String.format("Successfully imported volcano %s of type %s\n",
                        volcano.getName(),
                        volcano.getVolcanoType().toString()));
            }
        }
        return sb.toString().trim();
    }
    @Override
    public String exportVolcanoes() {
        StringBuilder sb = new StringBuilder();

        List<Volcano> vulcanos = this.volcanoRepository
                .findAllByIsActiveAndElevationIsGreaterThanAndLastEruptionIsNotNullOrderByElevationDesc(true, 3000);

        vulcanos.forEach(v->
                sb.append(String.format("Volcano: %s\n" +
                        "   *Located in: %s\n" +
                        "   **Elevation: %d\n" +
                        "   ***Last eruption on: %s\n",
                        v.getName(),
                        v.getCountry().getName(),
                        v.getElevation(),
                        v.getLastEruption())));
        return sb.toString().trim();
    }
}