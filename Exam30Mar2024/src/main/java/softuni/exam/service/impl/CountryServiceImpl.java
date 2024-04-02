package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountryImportDto;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final String FILE_PATH = "src/main/resources/files/json/countries.json";
    private final ModelMapper modelMapper;

    private final Gson gson;

    private final ValidationUtil validationUtil;

    private final CountryRepository countryRepository;

    public CountryServiceImpl(ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, CountryRepository countryRepository) {
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.countryRepository = countryRepository;
    }

    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_PATH)));
    }

    @Override
    public String importCountries() throws IOException {
        StringBuilder sb = new StringBuilder();
        CountryImportDto[] dtos =
                gson.fromJson(readCountriesFromFile(), CountryImportDto[].class);
        for (CountryImportDto dto : dtos) {
            Optional<Country> optionalCountry = this.countryRepository.findByName(dto.getName());
            if (!validationUtil.isValid(dto) || optionalCountry.isPresent() ){
                sb.append("Invalid country\n");
            }else {
                Country country = this.modelMapper.map(dto, Country.class);
                this.countryRepository.saveAndFlush(country);
                sb.append(String.format("Successfully imported country %s - %s\n",
                        country.getName(),
                        country.getCapital()));
            }
        }
        return sb.toString().trim();
    }
}
