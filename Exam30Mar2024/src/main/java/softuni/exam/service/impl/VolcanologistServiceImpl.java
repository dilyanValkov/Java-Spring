package softuni.exam.service.impl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.VolcanologistImportDto;
import softuni.exam.models.dto.VolcanologistImportRootDto;
import softuni.exam.models.entity.Volcano;
import softuni.exam.models.entity.Volcanologist;
import softuni.exam.repository.VolcanoRepository;
import softuni.exam.repository.VolcanologistRepository;
import softuni.exam.service.VolcanologistService;
import softuni.exam.util.ValidationUtil;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class VolcanologistServiceImpl implements VolcanologistService {

    private final String FILE_PATH = "src/main/resources/files/xml/volcanologists.xml";
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final VolcanologistRepository volcanologistRepository;
    private final VolcanoRepository volcanoRepository;


    public VolcanologistServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, VolcanologistRepository volcanologistRepository, VolcanoRepository volcanoRepository) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.volcanologistRepository = volcanologistRepository;
        this.volcanoRepository = volcanoRepository;
    }

    @Override
    public boolean areImported() {
        return this.volcanologistRepository.count() > 0;
    }

    @Override
    public String readVolcanologistsFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_PATH)));
    }

    @Override
    public String importVolcanologists() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        JAXBContext context = JAXBContext.newInstance(VolcanologistImportRootDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        VolcanologistImportRootDto dtos
                = (VolcanologistImportRootDto) unmarshaller
                .unmarshal(new FileReader(FILE_PATH));

        for (VolcanologistImportDto dto : dtos.getVolcanologistImportDtoList()) {

            Optional<Volcanologist> optionalVolcanologist =
                    this.volcanologistRepository.findByFirstNameAndLastName(
                            dto.getFirstName(), dto.getLastName());

            Optional<Volcano> optionalVolcano =
                    this.volcanoRepository.findById(dto.getExploringVolcanoId());

            if (!validationUtil.isValid(dto)
                    || optionalVolcanologist.isPresent()
                    || optionalVolcano.isEmpty()){
                sb.append("Invalid volcanologist\n");
            }else {
                Volcanologist volcanologist = this.modelMapper.map(dto, Volcanologist.class);
                volcanologist.setVolcano(optionalVolcano.get());
                this.volcanologistRepository.saveAndFlush(volcanologist);

                sb.append(String.format("Successfully imported volcanologist %s %s\n",
                        volcanologist.getFirstName(),
                        volcanologist.getLastName()));
            }

        }
        return sb.toString().trim();
    }
}