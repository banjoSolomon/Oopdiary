package repositories;

import diaryFIle.model.Diary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryRepositoryImplTest {
    private DiaryRepositoryImpl diaryRepo;
    private Diary diary;

    @BeforeEach
    public void setUp() {
        diaryRepo = new DiaryRepositoryImpl();
        diary = new Diary("Banjo Solomon", "password");

    }

    @Test
    public void testToSaveDiary() {
        diaryRepo = new DiaryRepositoryImpl();
        Diary mydiary = new Diary("Banjo Solomon", "password");
        diaryRepo.save(mydiary);
        assertEquals(1, diaryRepo.count());
    }

    @Test
    public void testToSaveTwoDiary() {
        diaryRepo = new DiaryRepositoryImpl();
        Diary myDiary = new Diary("Banjo Solomon", "password");
        Diary myDiary2 = new Diary("Sam Tem", "password123");
        diaryRepo.save(myDiary);
        diaryRepo.save(myDiary2);
        assertEquals(2, diaryRepo.count());


    }

    @Test
    public void testToFindAllDiary() {
        diaryRepo = new DiaryRepositoryImpl();
        Diary myDiary = new Diary("Banjo Solomon", "password");
        Diary myDiary2 = new Diary("Sam Tem", "password123");
        Diary myDiary3 = new Diary("kelly Tell", "password123");
        diaryRepo.save(myDiary);
        diaryRepo.save(myDiary2);
        diaryRepo.save(myDiary3);
        assertEquals(3, diaryRepo.count());
        List<Diary> allDiaries = diaryRepo.findAll();
        assertEquals(3, allDiaries.size());
    }

    @Test
    public void testDiaryHasAnId() {
        diaryRepo = new DiaryRepositoryImpl();
        Diary myDiary = new Diary("Banjo Solomon", "password");
        diaryRepo.save(myDiary);
        Diary retrievedDiary = diaryRepo.findById("Banjo Solomon");
        assertEquals(myDiary, retrievedDiary);

    }

    @Test
    public void testToDeleteDiaryByUserName() {
        diaryRepo = new DiaryRepositoryImpl();
        Diary myDiary = new Diary("Banjo Solomon", "password");
        Diary myDiary2 = new Diary("Sam Tem", "password123");
        Diary myDiary3 = new Diary("kelly Tell", "password1");
        diaryRepo.save(myDiary);
        diaryRepo.save(myDiary2);
        diaryRepo.save(myDiary3);
        assertEquals(3, diaryRepo.count());

        diaryRepo.delete("Sam Tem");
        assertEquals(2, diaryRepo.count());

    }

    @Test
    void testHaveFourDiaryDeleteTwoByUserName() {
        diaryRepo = new DiaryRepositoryImpl();
        Diary myDiary = new Diary("Banjo Solomon", "password");
        Diary myDiary2 = new Diary("Sam Tem", "password123");
        Diary myDiary3 = new Diary("kelly Tell", "password1");
        Diary myDiary4 = new Diary("Jenny Den", "password1");
        diaryRepo.save(myDiary);
        diaryRepo.save(myDiary2);
        diaryRepo.save(myDiary3);
        diaryRepo.save(myDiary4);
        assertEquals(4, diaryRepo.count());

        diaryRepo.delete("Kelly Tell");
        diaryRepo.delete("Sam Tell");
        assertEquals(2, diaryRepo.count());


    }

    @Test
    public void testToDeleteAllDiary() {
        diaryRepo = new DiaryRepositoryImpl();
        Diary myDiary = new Diary("Banjo Solomon", "password");
        Diary myDiary2 = new Diary("Sam Tem", "password123");
        Diary myDiary3 = new Diary("kelly Tell", "password1");
        Diary myDiary4 = new Diary("Jenny Den", "password1");
        diaryRepo.save(myDiary);
        diaryRepo.save(myDiary2);
        diaryRepo.save(myDiary3);
        diaryRepo.save(myDiary4);
        assertEquals(4, diaryRepo.count());
        diaryRepo.delete(diary);
        assertEquals(0, diaryRepo.count());
    }


}


