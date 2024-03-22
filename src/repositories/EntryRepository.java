package repositories;

import model.Entry;

import java.util.List;

public interface EntryRepository {
    Entry save(Entry entry);

    List<Entry> findAll();

    Entry findById(int id);

    List<Entry> findByAuthor(String author);

    void delete(int id);

}
