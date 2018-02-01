package com.quizemon.arangorepositories;

import com.arangodb.springframework.repository.ArangoRepository;
import com.quizemon.arangoentities.QuestionDAO;

public interface QuestionRepository extends ArangoRepository<QuestionDAO> {
}
