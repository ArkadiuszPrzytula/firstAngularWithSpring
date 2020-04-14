package com.arek.rest.webservices.restfulwebapi.model;


import lombok.*;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Todo {
    private Long id;
    private String username;
    private String description;
    private Date targetDate;
    private boolean isDone;
}
