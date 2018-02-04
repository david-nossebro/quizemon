package com.quizemon.arangorepositories;

import com.arangodb.springframework.repository.ArangoRepository;
import com.quizemon.arangoentities.QuestionPointDAO;

public interface QuestionPointRepository extends ArangoRepository<QuestionPointDAO> {
}
