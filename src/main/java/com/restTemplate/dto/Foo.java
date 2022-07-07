package com.restTemplate.dto;

import com.restTemplate.Entity.StudentRest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Foo implements Serializable {
@Id
    private long id;

    private String name;

//   List<StudentRest> list;
    // standard getters and setters
}
