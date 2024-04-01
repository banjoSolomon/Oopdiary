package diaryFIle.repositories;

import diaryFIle.model.Entry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends MongoRepository<Entry, String> {
    List<Entry> findByAuthor(String author);
}