package diaryFIle.repositories;

import diaryFIle.model.Diary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends MongoRepository<Diary, String> {

}
