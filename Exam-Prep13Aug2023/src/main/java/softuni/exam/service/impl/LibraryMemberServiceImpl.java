package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.LibraryMembersImportDto;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.LibraryMemberService;
import softuni.exam.util.ValidationUtil;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class LibraryMemberServiceImpl implements LibraryMemberService {
    private final static String FILE_PATH = "src/main/resources/files/json/library-members.json";
    private final LibraryMemberRepository libraryMemberRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public LibraryMemberServiceImpl(LibraryMemberRepository libraryMemberRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.libraryMemberRepository = libraryMemberRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.libraryMemberRepository.count() > 0;
    }

    @Override
    public String readLibraryMembersFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_PATH)));
    }

    @Override
    public String importLibraryMembers() throws IOException {
        StringBuilder sb = new StringBuilder();
        LibraryMembersImportDto[] dtos = this.gson
                .fromJson(new FileReader(FILE_PATH), LibraryMembersImportDto[].class);
        for (LibraryMembersImportDto dto : dtos) {
            Optional<LibraryMember> optionalLibraryMember =
                    this.libraryMemberRepository.findByPhoneNumber(dto.getPhoneNumber());

            if (!validationUtil.isValid(dto)||optionalLibraryMember.isPresent()){
                sb.append("Invalid library member\n");
            }else {
                LibraryMember libraryMember = this.modelMapper.map(dto, LibraryMember.class);
                this.libraryMemberRepository.save(libraryMember);
                sb.append(String.format("Successfully imported library member %s - %s\n",
                        libraryMember.getFirstName(), libraryMember.getLastName()));
            }
        }
        return sb.toString();
    }
}
