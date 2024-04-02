package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AstronomerDto;
import softuni.exam.models.dto.AstronomerRootDto;
import softuni.exam.models.entity.Astronomer;
import softuni.exam.models.entity.Star;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.AstronomerService;
import softuni.exam.util.ValidationUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.Optional;

@Service
public class AstronomerServiceImpl implements AstronomerService {
    private final static String FILE_PATH = "src/main/resources/files/xml/astronomers.xml";
    private final StarRepository starRepository;
    private final AstronomerRepository astronomerRepository;
    private final ConstellationRepository constellationRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public AstronomerServiceImpl(StarRepository starRepository, AstronomerRepository astronomerRepository, ConstellationRepository constellationRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.starRepository = starRepository;
        this.astronomerRepository = astronomerRepository;
        this.constellationRepository = constellationRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.astronomerRepository.count()>0;
    }

    @Override
    public String readAstronomersFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_PATH)));
    }

    @Override
    public String importAstronomers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        JAXBContext context = JAXBContext.newInstance( AstronomerRootDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        AstronomerRootDto dtos = (AstronomerRootDto) unmarshaller.unmarshal(new FileReader(FILE_PATH));

        for (AstronomerDto dto : dtos.getAstronomers()) {
            Optional<Astronomer> optionalAstronomer = this.astronomerRepository
                    .findByFirstNameAndLastName(dto.getFirstName(),dto.getLastName());

            Optional<Star> optionalStar = this.starRepository.findById(dto.getObservingStarId());

            if (!validationUtil.isValid(dto) || optionalAstronomer.isPresent() || optionalStar.isEmpty()){
                sb.append("Invalid astronomer\n");
            }else {
                Astronomer astronomer = this.modelMapper.map(dto, Astronomer.class);
                astronomer.setObservingStar(optionalStar.get());
                this.astronomerRepository.saveAndFlush(astronomer);
                DecimalFormat df = new DecimalFormat("####0.00");
                sb.append(String.format("Successfully imported astronomer %s %s - %s\n",
                        astronomer.getFirstName(),
                        astronomer.getLastName(),
                        df.format(dto.getAverageObservationHours())));
            }
        }
        return sb.toString();
    }
}
