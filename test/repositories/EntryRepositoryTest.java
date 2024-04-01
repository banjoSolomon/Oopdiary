package repositories;

import diaryFIle.model.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EntryRepositoryTest {
    private EntryRepositoryImpl entryRepo;

    @BeforeEach
    public void setUp() {
        entryRepo = new EntryRepositoryImpl();
    }

    @Test
    public void testToSaveEntry() {
        entryRepo = new EntryRepositoryImpl();
        Entry myEntry = new Entry("Semicolon", "Its not a easy Journey", 1, "Kent Beck");
        entryRepo.save(myEntry);
        assertEquals(1, entryRepo.count());

    }

    @Test
    public void testToSaveTwoEntry() {
        entryRepo = new EntryRepositoryImpl();
        Entry myEntry = new Entry("Semicolon", "Its not a easy Journey", 1, "Kent Beck");
        Entry secondEntry = new Entry("People", "Love Life", 2, "Mile Stamp");
        entryRepo.save(myEntry);
        entryRepo.save(secondEntry);
        assertEquals(2, entryRepo.count());

    }

    @Test
    public void testToFindAllEntry() {
        entryRepo = new EntryRepositoryImpl();
        Entry myEntry = new Entry("Semicolon", "Its not a easy Journey", 1, "Kent Beck");
        Entry secondEntry = new Entry("People", "Love Life", 2, "Mile Stamp");
        Entry thirdEntry = new Entry("People", "Love Life", 3, "Oliver Queen");
        entryRepo.save(myEntry);
        entryRepo.save(secondEntry);
        entryRepo.save(thirdEntry);
        assertEquals(3, entryRepo.count());
        List<Entry> allEntry = entryRepo.findAll();
        assertEquals(3, allEntry.size());

    }

    @Test
    public void testToFindEntryById() {
        entryRepo = new EntryRepositoryImpl();
        Entry myEntry = new Entry("Semicolon", "Its not a easy Journey", 1, "Kent Beck");
        Entry secondEntry = new Entry("People", "Love Life", 2, "Mile Stamp");
        entryRepo.save(myEntry);
        entryRepo.save(secondEntry);
        Entry foundEntry = entryRepo.findById(myEntry.getId());
        Entry foundSecondEntry = entryRepo.findById(secondEntry.getId());
        assertNotNull(foundEntry);
        assertNotNull(foundSecondEntry);
        assertEquals(myEntry, foundEntry);
        assertEquals(foundSecondEntry, secondEntry);

    }

    @Test
    public void testANewEntry() {
        entryRepo = new EntryRepositoryImpl();
        Entry myEntry = new Entry("Semicolon", "Its not a easy Journey", 1, "Kent Beck");
        Entry secondEntry = new Entry("People", "Love Life", 2, "Mile Stamp");
        entryRepo.save(myEntry);
        entryRepo.save(secondEntry);
        assertFalse(entryRepo.isNew(myEntry));
        assertFalse(entryRepo.isNew(secondEntry));

    }

    @Test
    public void testToUpdateEntry() {
        entryRepo = new EntryRepositoryImpl();
        Entry myEntry = new Entry("Semicolon", "Its not a easy Journey", 1, "Kent Beck");
        entryRepo.save(myEntry);
        myEntry.setTitle("SweetHome");
        myEntry.setBody("Love My Home");
        entryRepo.update(myEntry);
        Entry updateEntry = entryRepo.findById(myEntry.getId());

        assertNotNull(updateEntry);
        assertEquals("SweetHome", updateEntry.getTitle());
        assertEquals("Love My Home", updateEntry.getBody());

    }

    @Test
    public void testToAddEntryById() {
        Entry myEntry = new Entry("Semicolon", "Its not a easy Journey", 1, "Kent Beck");
        entryRepo.addIdTo(myEntry);
        assertEquals(1, myEntry.getId());

    }

    @Test
    public void testToDeleteTwoEntryOutOfThree() {
        entryRepo = new EntryRepositoryImpl();
        Entry myEntry = new Entry("Semicolon", "Its not a easy Journey", 1, "Kent Beck");
        Entry secondEntry = new Entry("People", "Love Life", 2, "Mile Stamp");
        Entry thirdEntry = new Entry("People", "Love Life", 3, "Oliver Queen");
        entryRepo.save(myEntry);
        entryRepo.save(secondEntry);
        entryRepo.save(thirdEntry);

        entryRepo.delete(myEntry);
        entryRepo.delete(thirdEntry);
        assertEquals(1, entryRepo.count());
        assertNull(entryRepo.findById(myEntry.getId()));
        // assertNull(entryRepo.findById(secondEntry.getId()));

    }

    @Test
    public void testToFindByAuthor() {
        entryRepo = new EntryRepositoryImpl();
        Entry myEntry = new Entry("Semicolon", "Its not a easy Journey", 1, "Kent Beck");
        Entry secondEntry = new Entry("People", "Love Life", 2, "Mile Stamp");
        Entry thirdEntry = new Entry("People", "Love Life", 3, "Oliver Queen");
        entryRepo.save(myEntry);
        entryRepo.save(secondEntry);
        entryRepo.save(thirdEntry);

        List<Entry> entriesByPeople = entryRepo.findByAuthor("People");
        assertNotNull(entriesByPeople);
        for (Entry entry : entriesByPeople) {
            assertEquals("People", entry.getAuthor());
        }


    }


}
