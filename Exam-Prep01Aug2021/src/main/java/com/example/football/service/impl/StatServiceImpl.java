package com.example.football.service.impl;

import com.example.football.models.dto.StatImportDto;
import com.example.football.models.dto.StatImportRootDto;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.StatService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StatServiceImpl implements StatService {

    private final StatRepository statRepository;
    private final Validator validator;
    private final ModelMapper modelMapper;
    private final Unmarshaller unmarshaller;


    @Autowired
    public StatServiceImpl(StatRepository statRepository, Validator validator, ModelMapper modelMapper) throws JAXBException {
        this.statRepository = statRepository;
        this.validator = validator;
        this.modelMapper = modelMapper;

        JAXBContext context = JAXBContext.newInstance(StatImportRootDto.class);
        this.unmarshaller = context.createUnmarshaller();
    }


    @Override
    public boolean areImported() {
        return this.statRepository.count()>0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        Path path = Path.of("src","main","resources","files","xml","stats.xml");
        return Files.readString(path);
    }

    @Override
    public String importStats() throws JAXBException, IOException {
    Path path = Path.of(this.readStatsFileContent());
        StatImportRootDto dto = (StatImportRootDto) unmarshaller.unmarshal(new FileReader(path.toAbsolutePath().toFile()));

        return dto.getStats()
                .stream()
                .map(this::importStat)
                .collect(Collectors.joining("\n"));
    }

    private String importStat(StatImportDto dto) {

        Set<ConstraintViolation<StatImportDto>> validationErrors = validator.validate(dto);
        if (validationErrors.isEmpty()){
            Optional<Stat> optionalStat = this.statRepository.findByShootingAndPassingAndEndurance(dto.getShooting(),
                    dto.getPassing(), dto.getEndurance());
            if (optionalStat.isPresent()){
                return "Invalid Stat";
            }else {
                Stat stat = this.modelMapper.map(dto, Stat.class);
                this.statRepository.save(stat);
                return "Successfully imported Stat " + stat;
            }
        }else {
            return "Invalid Stat";
        }
    }
}
