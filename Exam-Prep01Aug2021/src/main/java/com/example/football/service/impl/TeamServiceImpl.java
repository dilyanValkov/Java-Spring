package com.example.football.service.impl;

import com.example.football.models.dto.TeamImportDto;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.TeamService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    private final TownRepository townRepository;
    private final TeamRepository teamRepository;
    private final Validator validator;
    private final Gson gson;
    private final ModelMapper modelMapper;

    public TeamServiceImpl(TownRepository townRepository, TeamRepository teamRepository, Validator validator, Gson gson, ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.teamRepository = teamRepository;
        this.validator = validator;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count()>0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        Path path = Path.of("src","main","resources","files","json","teams.json");
        return Files.readString(path);
    }

    @Override
    public String importTeams() throws IOException {
        String json = this.readTeamsFileContent();
        TeamImportDto[] teamImportDtos = this.gson.fromJson(json, TeamImportDto[].class);
        return Arrays.stream(teamImportDtos)
                .map(this::importTeam)
                .collect(Collectors.joining("\n"));
    }

    private String importTeam(TeamImportDto dto) {

        Set<ConstraintViolation<TeamImportDto>> validationErrors = this.validator.validate(dto);
        if (validationErrors.isEmpty()){
            Optional<Team> optionalTeam = this.teamRepository.findByName(dto.getName());
            if (optionalTeam.isEmpty()){
                Team team = this.modelMapper.map(dto, Team.class);
                Optional<Town> town = this.townRepository.findByName(dto.getTownName());

                team.setTown(town.get());

                this.teamRepository.save(team);
                return "Successfully imported Team " + team.getName();
            }else {
                return "Invalid Team";
            }
        }else {
            return "Invalid Team";
        }
    }
}
