package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsons.MechanicImportDto;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.repository.MechanicsRepository;
import softuni.exam.service.MechanicsService;
import softuni.exam.util.ValidationUtil;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class MechanicsServiceImpl implements MechanicsService {
    private static String MECH_FILE_PATH = "src/main/resources/files/json/mechanics.json";
    private final MechanicsRepository mechanicsRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public MechanicsServiceImpl(MechanicsRepository mechanicsRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.mechanicsRepository = mechanicsRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.mechanicsRepository.count()>0;
    }

    @Override
    public String readMechanicsFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(MECH_FILE_PATH)));
    }

    @Override
    public String importMechanics() throws IOException {
        StringBuilder sb = new StringBuilder();
        MechanicImportDto[] Dtos =
                gson.fromJson(this.readMechanicsFromFile(), MechanicImportDto[].class);
        for (MechanicImportDto dto : Dtos) {
            Optional<Mechanic> optionalMechanic = this.mechanicsRepository.findByEmail(dto.getEmail());
            if (!validationUtil.isValid(dto)||optionalMechanic.isPresent()){
                sb.append("Invalid mechanic");
                sb.append(System.lineSeparator());
            }else {
                Mechanic mechanic = this.modelMapper.map(dto, Mechanic.class);
                this.mechanicsRepository.save(mechanic);
                sb.append(String.format("Successfully imported mechanic %s %s",
                        mechanic.getFirstName(),
                        mechanic.getLastName()));
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }
}
