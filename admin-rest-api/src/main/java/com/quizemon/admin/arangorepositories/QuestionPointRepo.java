package com.quizemon.admin.arangorepositories;

import com.arangodb.springframework.repository.ArangoRepository;
import com.quizemon.admin.arangoentities.QuestionPointDao;

public interface QuestionPointRepo extends ArangoRepository<QuestionPointDao> {
}
