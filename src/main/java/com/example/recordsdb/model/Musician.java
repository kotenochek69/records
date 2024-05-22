package com.example.recordsdb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "musicians")
@Accessors(chain = true)
public class Musician {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "country")
    private String country;
    @OneToMany(mappedBy = "musician", orphanRemoval = true)
    private Set<Song> songs = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name="musicians_genres",
            joinColumns=
            @JoinColumn(name="musician_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="genres_id", referencedColumnName="id")
    )
    private List<Genre> genres = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="records_musicians",
            joinColumns=
            @JoinColumn(name="records_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="musicians_id", referencedColumnName="id")
    )
    private Set<RecordEntity> records = new HashSet<>();

}