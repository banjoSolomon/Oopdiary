package repositories;

import model.Diary;

import java.util.ArrayList;
import java.util.List;

public class DiaryRepositoryImpl implements DiaryRepository {

    private List<Diary> diaryList;
    private int count;
    private String username;

    public DiaryRepositoryImpl() {
        this.diaryList = new ArrayList<>();

    }

    @Override
    public Diary save(Diary diary) {
        diaryList.add(diary);
        count++;
        return diary;

    }

    @Override
    public List<Diary> findAll() {
        return new ArrayList<>(diaryList);
    }

    @Override
    public Diary findById(String username) {
        for (Diary diary : diaryList) {
            if (diary.getUsername().equals(username)) {
                return diary;
            }
        }
        return null;
    }

    @Override
    public long count() {
        return count;
    }

    @Override
    public void delete(String username) {
        Diary diaryToRemove = null;
        for (Diary diary : diaryList) {
            if (diary.getUsername().equals(username)) {
                diaryToRemove = diary;
                break;
            }
        }
        if (diaryToRemove != null) diaryList.remove(diaryToRemove);
        count--;

    }

    @Override
    public void delete(Diary diary) {
        diaryList.clear();
        count = 0;

    }

    public String getUsername() {
        return username;
    }


}
