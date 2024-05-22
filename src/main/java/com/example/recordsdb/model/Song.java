package com.example.recordsdb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "year")
    private Integer year;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "musician_id")
    private Musician musician;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;


    @JoinTable(
            name="records_songs",
            joinColumns=
            @JoinColumn(name="song_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="records_id", referencedColumnName="id")
    )
    @ManyToMany
    private List<RecordEntity> records = new ArrayList<>();

}