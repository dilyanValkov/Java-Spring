package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.StarsImportDto;
import softuni.exam.models.entity.Star;
import softuni.exam.models.entity.StarType;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.StarService;
import softuni.exam.util.ValidationUtil;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StarServiceImpl implements StarService {
    private final static String FILE_PATH = "src/main/resources/files/json/stars.json";
    private final StarRepository starRepository;
    private final ConstellationRepository constellationRepository;

    private final ModelMapper modelMapper;

    private final Gson gson;
    private final ValidationUtil validationUtil;

    public StarServiceImpl(StarRepository starRepository, ConstellationRepository constellationRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.starRepository = starRepository;
        this.constellationRepository = constellationRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.starRepository.count()>0;
    }

    @Override
    public String readStarsFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_PATH)));
    }

    @Override
    public String importStars() throws IOException {

        StringBuilder sb = new StringBuilder();
        StarsImportDto[] dtos = gson.fromJson(new FileReader(FILE_PATH), StarsImportDto[].class);
        for (StarsImportDto dto : dtos) {
            Optional<Star> optionalStar = this.starRepository.findByName(dto.getName());
            if (!validationUtil.isValid(dto) || optionalStar.isPresent()){
                sb.append("Invalid star\n");
            }else {
                Star star = this.modelMapper.map(dto, Star.class);
                star.setConstellation(this.constellationRepository.getById(dto.getConstellation()));
                this.starRepository.save(star);
                sb.append(String.format("Successfully imported star %s - %.2f light years\n",star.getName(),
                        star.getLightYears()));
            }
        }
        return sb.toString();
    }

    @Override
    public String exportStars() {
        StringBuilder sb = new StringBuilder();
        Set<Star> records =
                this.starRepository.findAllByStarTypeAndObserversIsEmptyOrderByLightYearsAsc(StarType.RED_GIANT);
        records.forEach(r ->
                      sb.append(String.format("Star: %s\n" +
                                      "   *Distance: %.2f light years\n" +
                                      "   **Description: %s\n" +
                                      "   ***Constellation: %s\n",
                              r.getName(),
                              r.getLightYears(),
                              r.getDescription(),
                              r.getConstellation().getName())));
                return sb.toString().trim();
    }
}
