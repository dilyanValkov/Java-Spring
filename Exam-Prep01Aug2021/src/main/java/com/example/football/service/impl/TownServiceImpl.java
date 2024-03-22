package com.example.football.service.impl;

import com.example.football.models.dto.TownImportDto;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class TownServiceImpl implements TownService {
        private final TownRepository townRepository;
        private final Validator validator;
        private final Gson gson;
        private final ModelMapper modelMapper;

    public TownServiceImpl(TownRepository townRepository, Validator validator, Gson gson, ModelMapper modelMapper) {
        this.townRepository = townRepository;

        this.validator = validator;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {

        Path path = Path.of("src","main","resources","files","json","towns.json");
        return Files.readString(path);
    }

    @Override
    public String importTowns() throws IOException {
        String json = this.readTownsFileContent();

        TownImportDto[] townImportDto = this.gson.fromJson(json, TownImportDto[].class);
        List<String> result = new ArrayList<>();

        for (TownImportDto importDto : townImportDto) {
            Set<ConstraintViolation<TownImportDto>> validationErrors = this.validator.validate(importDto);

            if (validationErrors.isEmpty()){
                Optional<Town> optionalTown = this.townRepository.findByName(importDto.getName());
                if (optionalTown.isEmpty()){
                    Town town = this.modelMapper.map(importDto, Town.class);
                    this.townRepository.save(town);
                    String msg = String.format("Successfully imported Town %s - %d",
                            town.getName(),town.getPopulation());
                    result.add(msg);
                }else {
                    result.add("Invalid Town");
                }
            }else {
                    result.add("Invalid Town");
            }
        }
        return String.join("\n", result);
    }
}
