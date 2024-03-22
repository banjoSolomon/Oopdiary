package repositories;


import model.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntryRepositoryImpl implements EntryRepository {
    private List<Entry> entryList;
    int count;

    public EntryRepositoryImpl() {
        this.entryList = new ArrayList<>();
    }

    @Override
    public Entry save(Entry entry) {
        entryList.add(entry);
        count++;
        return entry;
    }

    @Override
    public List<Entry> findAll() {
        return new ArrayList<>(entryList);

    }

    @Override
    public Entry findById(int id) {
        for (Entry entry : entryList) {
            if (entry.getId() == id) {
                return entry;
            }
        }
        return null;
    }

    @Override
    public List<Entry> findByAuthor(String author) {
        List<Entry> entriesByAuthor = new ArrayList<>();
        for (Entry entry : entryList) {
            if (entry.getAuthor().equalsIgnoreCase(author)) {
                entriesByAuthor.add(entry);
            }
        }
        return entriesByAuthor;
    }

    @Override
    public void delete(int id) {
        Entry foundEntry = findById(id);

        entryList.remove(foundEntry);
    }

    public int count() {
        return count;
    }

    public boolean isNew(Entry entry) {
        return entry.getId() == 0;
    }

    public void update(Entry entry) {
        for (Entry findEntry : entryList) {
            if (findEntry.getId() == entry.getId()) entryList.remove(findEntry);
            entryList.add(entry);
        }
    }

    public void addIdTo(Entry entry) {
        entry.setId(generateId());
    }

    private int generateId() {
        return ++count;
    }

    public void delete(Entry entry) {
        entryList.remove(entry);
        count--;

    }
}
