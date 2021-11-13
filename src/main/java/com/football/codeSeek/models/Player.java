package com.football.codeSeek.models;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "firstName", length = 32)
    private String firstName;

    @Column(nullable = false, name = "lastName", length = 32)
    private String lastName;

    @Min(1)
    @Column(nullable = false, name = "experience")
    private Integer experience;

    @Min(16)
    @Max(46)
    @Column(nullable = false, name = "age")
    private Integer age;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;
}
