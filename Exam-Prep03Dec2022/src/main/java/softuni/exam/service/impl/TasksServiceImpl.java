package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xml.TaskImportDto;
import softuni.exam.models.dto.xml.TaskImportRootDto;
import softuni.exam.models.entity.*;
import softuni.exam.repository.CarsRepository;
import softuni.exam.repository.MechanicsRepository;
import softuni.exam.repository.PartsRepository;
import softuni.exam.repository.TasksRepository;
import softuni.exam.service.TasksService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class TasksServiceImpl implements TasksService {
    private static String TASKS_FILE_PATH = "src/main/resources/files/xml/tasks.xml";
    private final TasksRepository tasksRepository;
    private final MechanicsRepository mechanicsRepository;
    private final CarsRepository carsRepository;
    private final PartsRepository partsRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    private final ValidationUtil validationUtil;

    public TasksServiceImpl(TasksRepository tasksRepository, MechanicsRepository mechanicsRepository, CarsRepository carsRepository, PartsRepository partsRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.tasksRepository = tasksRepository;
        this.mechanicsRepository = mechanicsRepository;
        this.carsRepository = carsRepository;
        this.partsRepository = partsRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.tasksRepository.count()>0;
    }

    @Override
    public String readTasksFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(TASKS_FILE_PATH)));
    }

    @Override
    public String importTasks() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        TaskImportRootDto dtos =
                xmlParser.fromFile(Path.of(TASKS_FILE_PATH).toFile(), TaskImportRootDto.class);

        for (TaskImportDto dto : dtos.getTasks()) {
            Optional<Mechanic> optionalMechanic =
                    this.mechanicsRepository.findByFirstName(dto.getMechanic().getFirstName());
            if(!validationUtil.isValid(dto) || optionalMechanic.isEmpty()){
                sb.append("Invalid task\n");
            }else {
                Task task = this.modelMapper.map(dto, Task.class);
                Car car = this.carsRepository.getById(dto.getCar().getId());
                Part part = this.partsRepository.getById(dto.getPart().getId());

                task.setMechanic(optionalMechanic.get());
                task.setCar(car);
                task.setPart(part);

                this.tasksRepository.saveAndFlush(task);
                sb.append(String.format("Successfully imported task %.2f\n",dto.getPrice()));
            }
        }

        return sb.toString();
    }

    @Override
    public String getCoupeCarTasksOrderByPrice() {
        StringBuilder sb = new StringBuilder();
        List<Task> tasks
                = this.tasksRepository.findAllByCar_CarTypeOrderByPriceDesc(CarType.coupe);

        tasks.forEach(t->
                sb.append(String.format("Car %s %s with %dkm\n" +
                        "-Mechanic: %s %s - task №%d:\n" +
                        " --Engine: %.1f\n" +
                        "---Price: %.2f$\n",
                        t.getCar().getCarMake(),
                        t.getCar().getCarModel(),
                        t.getCar().getKilometers(),
                        t.getMechanic().getFirstName(),
                        t.getMechanic().getLastName(),
                        t.getId(),
                        t.getCar().getEngine(),
                        t.getPrice())));
        return sb.toString().trim();
    }
}
