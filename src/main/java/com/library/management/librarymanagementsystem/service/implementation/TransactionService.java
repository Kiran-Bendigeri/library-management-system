package com.library.management.librarymanagementsystem.service.implementation;

import com.library.management.librarymanagementsystem.converter.TransactionConverter;
import com.library.management.librarymanagementsystem.dto_models.BookDto;
import com.library.management.librarymanagementsystem.dto_models.TransactionDto;
import com.library.management.librarymanagementsystem.dto_models.UserDto;
import com.library.management.librarymanagementsystem.entity_models.BookEntity;
import com.library.management.librarymanagementsystem.entity_models.TransactionEntity;
import com.library.management.librarymanagementsystem.entity_models.UserEntity;
import com.library.management.librarymanagementsystem.enums.TransactionStatus;
import com.library.management.librarymanagementsystem.enums.TransactionType;
import com.library.management.librarymanagementsystem.repository.BookRepository;
import com.library.management.librarymanagementsystem.repository.TransactionRepository;
import com.library.management.librarymanagementsystem.repository.UserRepository;
import com.library.management.librarymanagementsystem.service.TransactionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService implements TransactionServiceInterface {

    @Value("${user.book.quota:}")
    private Integer userBookQuota;

    @Value("${user.book.return.days:}")
    private Integer userBookReturnDays;

    @Value("${user.book.fine.perday:}")
    private Integer finePerDay;

    // calling repository of one entity in another entity service (Bad practice)
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    private final BookService bookService;
    private final UserService userService;
    private final TransactionRepository transactionRepository;
    private final TransactionConverter transactionConverter;

    public TransactionService(BookService bookService,
                              UserService userService,
                              TransactionRepository transactionRepository,
                              TransactionConverter transactionConverter
                              ){
        this.bookService=bookService;
        this.userService=userService;
        this.transactionRepository=transactionRepository;
        this.transactionConverter=transactionConverter;
    }


    @Override
    public String issueBook(Integer bookId, Integer userId) {
        /*
        - check the user and book are available
        - Check if the book is already issued to someone
        - check user has reached issue limit quota
        - initialize the transaction with status pending and type issue
        -
         */
//        BookDto bookDto = bookService.getBookById(bookId);
//        UserDto userDto = userService.getUserById(userId);
//        if(bookDto.getUserDto() != null){
//            throw new RuntimeException("Cannot issue book, since this is already issued to someone ");
//        }
////        if(userDto.getListOfBooks().size() >= userBookQuota){
////            throw new RuntimeException("Cannot issue book, because user has reached the quota");
//
//        TransactionDto transactionDto = TransactionDto.builder()
////                                              .userDto(userDto)
////                                              .bookDto(bookDto)
//                                                .transactionId(UUID.randomUUID().toString())
//                                                .transactionType(TransactionType.ISSUE)
//                                                .transactionStatus(TransactionStatus.PENDING)
//                                                .build();
//        transactionRepository.save(transactionConverter.dtoToEntity(transactionDto));
//
//        try{
//            bookDto.setUserDto(userDto);
//            bookService.save(bookDto);
//            transactionDto.setTransactionStatus(TransactionStatus.SUCCESS);
//            transactionDto.setBookDto(bookDto);
//            transactionDto.setUserDto(userDto);
//            transactionRepository.save(transactionConverter.dtoToEntity(transactionDto));
//        }catch(RuntimeException obj){
//            bookDto.setUserDto(null);
//            bookService.save(bookDto);
//            transactionDto.setTransactionStatus(TransactionStatus.FAILED);
//            transactionDto.setBookDto(bookDto);
//            transactionDto.setUserDto(userDto);
//            transactionRepository.save(transactionConverter.dtoToEntity(transactionDto));
//        }
//        return transactionDto.getTransactionId();


        Optional<BookEntity> optionalbookEntity = bookRepository.findById(bookId);
        Optional<UserEntity> optionaluserEntity = userRepository.findById(userId);
        if(optionalbookEntity.isEmpty() || optionaluserEntity.isEmpty())
            throw new RuntimeException("Book or the user doesn't exist");
        if(optionalbookEntity.get().getUser() != null){
            throw new RuntimeException("Cannot issue book, since this is already issued to someone ");
        }
//        if(userDto.getListOfBooks().size() >= userBookQuota){
//            throw new RuntimeException("Cannot issue book, because user has reached the quota");

        BookEntity bookEntity = optionalbookEntity.get();
        UserEntity userEntity = optionaluserEntity.get();

        TransactionEntity transactionEntity = TransactionEntity.builder()
                                                                .user(userEntity)
                                                                .book(bookEntity)
                                                                .transactionId(UUID.randomUUID().toString())
                                                                .transactionType(TransactionType.ISSUE)
                                                                .transactionStatus(TransactionStatus.PENDING)
                                                                .build();
        transactionRepository.save(transactionEntity);

        try{
            bookEntity.setUser(userEntity);
            bookRepository.save(bookEntity);
            transactionEntity.setTransactionStatus(TransactionStatus.SUCCESS);
            transactionEntity.setBook(bookEntity);
            transactionEntity.setUser(userEntity);
            transactionRepository.save(transactionEntity);
        }catch(RuntimeException obj) {
            bookEntity.setUser(null);
            bookRepository.save(bookEntity);
            transactionEntity.setTransactionStatus(TransactionStatus.FAILED);
            transactionEntity.setBook(bookEntity);
            transactionEntity.setUser(userEntity);
            transactionRepository.save(transactionEntity);
        }
        return transactionEntity.getTransactionId();
    }

    @Override
    public String returnBook(Integer bookId, Integer userId) {
        /*
        - check the book whether it is assigned to user or not
        - check the return days, does it exceeds 15days
        - calculate fine for each day
        - Remove the student as assignee and make the book available and user id should be set to null
        - change the txn to success
         */

//        BookDto bookDto = bookService.getBookById(bookId);
//        UserDto userDto = userService.getUserById(userId);
//        if(bookDto.getUserDto()!=null || !bookDto.getUserDto().getUserId().equals(userId) || !userDto.getUserId().equals(userId))
//            throw new RuntimeException("Book is not assigned to the user");
//
//        List<TransactionEntity> listOfTransactionEntity = transactionRepository.findByBookAndUserAndTransactionTypeOrderById(bookId, userId, TransactionType.ISSUE);
//
//        TransactionEntity transactionEntity = listOfTransactionEntity.get(0);
//        TransactionDto transactionDto = TransactionDto.builder()
//                .transactionId(UUID.randomUUID().toString())
//                .transactionType(TransactionType.RETURN)
//                .transactionStatus(TransactionStatus.PENDING)
//                .bookDto(bookDto)
//                .userDto(userDto)
//                .build();
//
//        transactionRepository.save(transactionConverter.dtoToEntity(transactionDto));
//        try{
//            bookDto.setUserDto(null);
//            bookService.save(bookDto);
//            transactionDto.setTransactionStatus(TransactionStatus.SUCCESS);
//            transactionDto.setTransactionType(TransactionType.ISSUE);
//            transactionRepository.save(transactionConverter.dtoToEntity(transactionDto));
//        }catch(RuntimeException obj){
//            bookDto.setUserDto(userDto);
//            bookService.save(bookDto);
//            transactionDto.setTransactionStatus(TransactionStatus.FAILED);
//            transactionRepository.save(transactionConverter.dtoToEntity(transactionDto));
//        }
//
//        return transactionDto.getTransactionId();

        Optional<BookEntity> optionalbookEntity = bookRepository.findById(bookId);
        Optional<UserEntity> optionaluserEntity = userRepository.findById(userId);
        if(optionalbookEntity.isEmpty() || optionaluserEntity.isEmpty())
            throw new RuntimeException("Book or the user doesn't exist");
        if(optionalbookEntity.get().getUser() == null || optionalbookEntity.get().getUser().getUserId() != userId){
            throw new RuntimeException("Book is not assigned to the user");
        }
//        if(userDto.getListOfBooks().size() >= userBookQuota){
//            throw new RuntimeException("Cannot issue book, because user has reached the quota");

        BookEntity bookEntity = optionalbookEntity.get();
        UserEntity userEntity = optionaluserEntity.get();

        List<TransactionEntity> listOfTransactionEntity = transactionRepository.findByBookAndUserAndTransactionTypeOrderByIdDesc(bookEntity, userEntity, TransactionType.ISSUE);

        TransactionEntity transactionEntity = listOfTransactionEntity.get(0);

        long timeIssued = ZonedDateTime.of(transactionEntity.getCreatedDate(), ZoneId.systemDefault()).toInstant().toEpochMilli();
        long timeUpdated = System.currentTimeMillis();
        long difference = timeUpdated - timeIssued;

        long days = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
        int fine = 0;
        if(days > 15)
            fine += (days - userBookReturnDays)*finePerDay;
        transactionEntity.setFine(fine);
        transactionEntity.setTransactionStatus(TransactionStatus.PENDING);
        transactionEntity.setTransactionType(TransactionType.RETURN);
        transactionEntity.setTransactionId(UUID.randomUUID().toString());

        transactionRepository.save(transactionEntity);

        try{
            bookEntity.setUser(null);
            bookRepository.save(bookEntity);
            transactionEntity.setTransactionStatus(TransactionStatus.SUCCESS);
            transactionEntity.setBook(bookEntity);
            transactionEntity.setUser(userEntity);
            transactionRepository.save(transactionEntity);
        }catch(RuntimeException obj) {
            bookEntity.setUser(userEntity);
            bookRepository.save(bookEntity);
            transactionEntity.setTransactionStatus(TransactionStatus.FAILED);
            transactionEntity.setBook(bookEntity);
            transactionEntity.setUser(userEntity);
            transactionRepository.save(transactionEntity);
        }
        return transactionEntity.getTransactionId();
    }

}
