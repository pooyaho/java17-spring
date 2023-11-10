package ir.mapsa.java17.test;

import ir.mapsa.java.java17spring.launcher.Java17SpringApplication;
import ir.mapsa.java.java17spring.models.entities.StudentEntity;
import ir.mapsa.java.java17spring.respositories.StudentRepository;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = Java17SpringApplication.class)
@ComponentScan("ir.mapsa.java17spring")
public class StudentRepositoryTest {

    @Mock
    private StudentRepository studentRepository;

    private List<StudentEntity> studentEntities = new ArrayList<>();

    @BeforeEach
    public void init() {
        doAnswer(invocation -> {
            StudentEntity student = invocation.getArgument(0);
            studentEntities.add(student);
            return student;
        }).when(this.studentRepository)
                .save(any());
        when(this.studentRepository.findAll()).thenReturn(studentEntities);
    }


    @Test
    public void testInsertion() {
        StudentEntity entity = StudentEntity.builder()
                .age(18)
                .fatherName("Mohammad")
                .nationalCode("1234567890")
                .name("John")
                .family("Doe")
                .build();
        this.studentRepository.save(entity);
        this.studentRepository.save(entity);
        assertThat(this.studentRepository.findAll(), hasSize(2));
    }

    @Test
    public void testDuplicateInsert() {
        StudentEntity entity = StudentEntity.builder()
                .age(18)
                .fatherName("Mohammad")
                .nationalCode("1234567890")
                .name("John")
                .family("Doe")
                .build();

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            this.studentRepository.save(entity);
        });
    }

    @Test
    public void testInsertionOfRepository() {
        List<StudentEntity> all = this.studentRepository.findAll();
        assertThat(all, IsCollectionWithSize.hasSize(1));
    }

    @Test
    public void whenAddCalled_thenVerified() {
        List myList = mock(List.class);
//        doNothing().when(myList).add(isA(Integer.class), isA(Integer.class));
        myList.add(0, "");

        verify(myList, times(1)).add(0, "");
    }
}
