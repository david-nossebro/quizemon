package com.quizemon.arangorepositories;

import com.arangodb.springframework.repository.ArangoRepository;
import com.quizemon.arangoentities.QuestionPointDao;

public interface QuestionPointRepo extends ArangoRepository<QuestionPointDao> {
}
