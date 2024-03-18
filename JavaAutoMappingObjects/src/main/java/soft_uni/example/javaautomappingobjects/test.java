package soft_uni.example.javaautomappingobjects;

import DTOs.EmployeeDTO;
import entities.Employee;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Scanner;

@Component
public class test implements CommandLineRunner {
    private Employee employee;
    private EmployeeDTO employeeDTO;

    private ModelMapper modelMapper;
@Autowired
    public test(Employee employee, EmployeeDTO employeeDTO) {
        this.employee = employee;
        this.employeeDTO = employeeDTO;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
Scanner scanner = new Scanner(System.in );
        employee = new Employee("Ivan", "Petrov", 2399, LocalDate.now(),"Lulin");
        TypeMap<Employee,EmployeeDTO> typeMap = this.modelMapper.createTypeMap(Employee.class, EmployeeDTO.class);

        typeMap.addMappings(m ->
                m.map(Employee::getLastName,EmployeeDTO::setFirstName));
        typeMap.addMappings(m ->
                m.map(Employee::getLastName,EmployeeDTO::setLastName));
        typeMap.addMappings(m ->
                m.map(Employee::getSalary,EmployeeDTO::setSalary));

        System.out.println(employeeDTO.getLastName());
        System.out.println();
    }
}
