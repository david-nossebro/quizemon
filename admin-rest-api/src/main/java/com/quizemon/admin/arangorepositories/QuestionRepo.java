package com.quizemon.admin.arangorepositories;

import com.arangodb.springframework.repository.ArangoRepository;
import com.quizemon.admin.arangoentities.QuestionDao;

public interface QuestionRepo extends ArangoRepository<QuestionDao> {
}
