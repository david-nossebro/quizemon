package com.quizemon.types;

import com.quizemon.arangoentities.QuestionPointDAO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionPoint {

    private String id;

    @NotNull(message = "You need to provide latitude")
    private Double latitude;

    @NotNull(message = "You need to provide longitude")
    private Double longitude;

    public QuestionPointDAO toDao() {
        return new QuestionPointDAO(id, latitude, longitude);
    }
}
