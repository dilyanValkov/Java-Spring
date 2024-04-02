package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BorrowingRecordsDto;
import softuni.exam.models.dto.BorrowingRecordsRootDto;
import softuni.exam.models.entity.Book;
import softuni.exam.models.entity.BorrowingRecord;
import softuni.exam.models.entity.Genre;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.repository.BookRepository;
import softuni.exam.repository.BorrowingRecordRepository;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.BorrowingRecordsService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BorrowingRecordsServiceImpl implements BorrowingRecordsService {
    private final static String FILE_PATH = "src/main/resources/files/xml/borrowing-records.xml";
    private final BorrowingRecordRepository borrowingRecordRepository;
    private final BookRepository bookRepository;
    private final LibraryMemberRepository libraryMemberRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

@Autowired
    public BorrowingRecordsServiceImpl(BorrowingRecordRepository borrowingRecordRepository, BookRepository bookRepository, LibraryMemberRepository libraryMemberRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) throws JAXBException {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
    this.libraryMemberRepository = libraryMemberRepository;
    this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    this.xmlParser = xmlParser;
}

    @Override
    public boolean areImported() {
        return this.borrowingRecordRepository.count() > 0;
    }

    @Override
    public String readBorrowingRecordsFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_PATH)));
    }

    @Override
    public String importBorrowingRecords() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        JAXBContext context = JAXBContext.newInstance(BorrowingRecordsRootDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();BorrowingRecordsRootDto borrowingRecordsRootDto = (BorrowingRecordsRootDto) unmarshaller
               .unmarshal(new FileReader(FILE_PATH));
       // BorrowingRecordsRootDto borrowingRecordsRootDto = xmlParser.fromFile(new File(FILE_PATH), BorrowingRecordsRootDto.class);

        for (BorrowingRecordsDto dto : borrowingRecordsRootDto.getBorrowingRecordsDtoSet()) {
            Optional<Book> book = this.bookRepository.findBookByTitle(dto.getBook().getTitle());
            Optional<LibraryMember> member = this.libraryMemberRepository.findById(dto.getMember().getId());

            if (!validationUtil.isValid(dto)||book.isEmpty()||member.isEmpty()){
                sb.append("Invalid borrowing record\n");
            }else {
                BorrowingRecord borrowingRecord = this.modelMapper.map(dto,
                        BorrowingRecord.class);

                borrowingRecord.setBook(book.get());
                borrowingRecord.setLibraryMember(member.get());
                this.borrowingRecordRepository.saveAndFlush(borrowingRecord);
                sb.append(String.format("Successfully imported borrowing record %s - %s\n",
                        borrowingRecord.getBook().getTitle(),borrowingRecord.getBorrowDate()));
            }
        }
        return sb.toString();
    }
    @Override
    public String exportBorrowingRecords() {
    StringBuilder sb = new StringBuilder();
        Set<BorrowingRecord> records =
                this.borrowingRecordRepository
                        .findAllByBorrowDateBeforeAndBook_GenreOrderByBorrowDateDesc
                                (LocalDate.parse("2021-09-10"), Genre.SCIENCE_FICTION);
        records.forEach(r ->
              sb.append(String.format("Star: %s\n" +
                              "   *Distance: %.2f light years\n" +
                              "   **Description: %s\n" +
                              "   ***Constellation: %s",
                      r.getBook().getTitle(),
                      r.getBook().getAuthor(),
                      r.getBorrowDate(),
                      r.getLibraryMember().getFirstName(),
                      r.getLibraryMember().getLastName())));
        return sb.toString().trim();
    }
}
