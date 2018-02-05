package com.quizemon.arangorepositories;

import com.arangodb.springframework.repository.ArangoRepository;
import com.quizemon.arangoentities.QuestionDao;

public interface QuestionRepo extends ArangoRepository<QuestionDao> {
}
