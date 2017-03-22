package com.guilherme.miguel.domain;

import com.arangodb.entity.DocumentField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Miguel Guilherme
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @DocumentField(DocumentField.Type.KEY)
    private String key;

    private String title;

    private String director;

    private LocalDateTime createdDate;

}
