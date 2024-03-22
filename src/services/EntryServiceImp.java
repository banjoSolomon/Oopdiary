package services;

import exceptions.EmptyEntryListException;
import exceptions.EntryNotFoundException;
import model.Entry;
import repositories.EntryRepository;
import repositories.EntryRepositoryImpl;

import java.util.List;

public class EntryServiceImp implements EntryService {
    private static final EntryRepository repository = new EntryRepositoryImpl();

    @Override
    public void save(Entry entry) {
        repository.save(entry);


    }

    @Override
    public void deleteEntry(int id) {
        Entry entry = repository.findById(id);
        if (entry == null) throw new EntryNotFoundException("Entry not found");

        repository.delete(id);

    }

    @Override
    public Entry getEntry(int id) {
        Entry entry = repository.findById(id);
        if (entry == null) throw new EntryNotFoundException("Entry not found");
        return entry;
    }

    @Override
    public List<Entry> getEntries(String username) {
        List<Entry> entries = repository.findByAuthor(username.toLowerCase());
        if (entries.isEmpty()) throw new EmptyEntryListException("No entry found");
        return entries;
    }
}
