package com.quizemon.arangorepositories;

import com.arangodb.springframework.annotation.Param;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.quizemon.arangoentities.QuestionPointDao;
import com.quizemon.types.QuestionPoint;

import java.util.stream.Stream;

public interface QuestionPointRepo extends ArangoRepository<QuestionPointDao> {

  @Query(
    "FOR q IN WITHIN(questionpoints, @lat, @lng, @distance)" +
      " RETURN q"
  )
  Iterable<QuestionPointDao> findWithinDistance(@Param("lat") Double lat,
                                         @Param("lng") Double lng,
                                         @Param("distance") int distance);
}
