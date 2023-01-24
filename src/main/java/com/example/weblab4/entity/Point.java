package com.example.weblab4.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    double x, y, r;

    String owner;

    String time, result;
}
