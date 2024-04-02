package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xml.CarImportRootDto;
import softuni.exam.models.dto.xml.ImportCarDto;
import softuni.exam.models.entity.Car;
import softuni.exam.repository.CarsRepository;
import softuni.exam.service.CarsService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class CarsServiceImpl implements CarsService {
    private static String CARS_FILE_PATH = "src/main/resources/files/xml/cars.xml";
    private final CarsRepository carsRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    public CarsServiceImpl(CarsRepository carsRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.carsRepository = carsRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.carsRepository.count()>0;
    }

    @Override
    public String readCarsFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(CARS_FILE_PATH)));
    }

    @Override
    public String importCars() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        CarImportRootDto dtos =
                xmlParser.fromFile(Path.of(CARS_FILE_PATH).toFile(), CarImportRootDto.class);
        for (ImportCarDto dto : dtos.getCars()) {
            Optional<Car> carOptional = this.carsRepository.findByPlateNumber(dto.getPlateNumber());

            if (!validationUtil.isValid(dto) || carOptional.isPresent()){
                sb.append("Invalid car\n");
            }else {
                Car car = this.modelMapper.map(dto, Car.class);
                this.carsRepository.save(car);
                sb.append(String.format("Successfully imported car %s - %s\n",
                        car.getCarMake(),
                        car.getCarModel()));
            }
        }
        return sb.toString();
    }
}
