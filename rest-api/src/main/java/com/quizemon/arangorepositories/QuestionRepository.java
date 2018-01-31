package com.quizemon.arangorepositories;

import com.arangodb.springframework.repository.ArangoRepository;
import com.quizemon.arangoentities.Question;

public interface QuestionRepository extends ArangoRepository<Question> {

}
