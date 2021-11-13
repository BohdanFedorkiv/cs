package com.football.codeSeek.models;

import lombok.*;
import java.util.*;
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
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name = "name", length = 32)
    private String name;

    @Column(nullable = false, name = "country", length = 32)
    private String country;

    @Min(1000)
    @Column(nullable = false, name = "account")
    private Double account;

    @Min(0)
    @Max(10)
    @Column(nullable = false, name = "commission")
    private Integer commission;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "team", fetch = FetchType.LAZY)
    private List<Player> players;
}
