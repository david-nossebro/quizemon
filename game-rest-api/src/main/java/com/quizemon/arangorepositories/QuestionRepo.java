package com.quizemon.arangorepositories;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.quizemon.arangoentities.QuestionDao;

public interface QuestionRepo extends ArangoRepository<QuestionDao> {

  @Query(
    "FOR q IN questions" +
    " SORT RAND()" +
    " LIMIT 1" +
    " RETURN q"
  )
  QuestionDao getRandom();

}
