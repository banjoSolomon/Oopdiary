package diaryFIle.services;

import diaryFIle.exceptions.EmptyEntryListException;
import diaryFIle.exceptions.EntryNotFoundException;
import diaryFIle.model.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import diaryFIle.repositories.EntryRepository;


import java.util.List;

@Service
public class EntryServiceImp implements EntryService {
    @Autowired
    private EntryRepository repository;

    @Override
    public void save(Entry entry) {
        repository.save(entry);
    }

    @Override
    public void deleteEntry(int id) {
        repository.deleteById(String.valueOf(id));
    }

    @Override
    public Entry getEntry(int id) {
        return repository.findById(String.valueOf(id))
                .orElseThrow(() -> new EntryNotFoundException("Entry not found"));
    }

    @Override
    public List<Entry> getEntries(String username) {
        List<Entry> entries = repository.findByAuthor(username.toLowerCase());
        if (entries.isEmpty()) {
            throw new EmptyEntryListException("No entry found");
        }
        return entries;
    }
}