package diaryFIle.services;

import diaryFIle.model.Entry;

import java.util.List;

public interface EntryService {
    void save(Entry entry);

    void deleteEntry(int id);

    Entry getEntry(int id);

    List<Entry> getEntries(String username);

}
