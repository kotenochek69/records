package com.example.recordsdb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "records")
public class RecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "record", fetch = FetchType.EAGER)
    private List<RecordsCopy> recordsCopy = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="records_genres", joinColumns = @JoinColumn(name = "records_id"), inverseJoinColumns = @JoinColumn(name = "genres_id"))
    private List<Genre> genres = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name="records_songs",
            joinColumns=
            @JoinColumn(name="records_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="song_id", referencedColumnName="id")
    )
    private List<Song> songs = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="records_musicians",
            joinColumns=
            @JoinColumn(name="musicians_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="records_id", referencedColumnName="id")
    )
    private Set<Musician> musicians = new HashSet<>();

}