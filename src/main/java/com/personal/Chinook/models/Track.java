package com.personal.Chinook.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Track {

    @Id
    @Column(
            name = "TrackId",
            nullable = false
    )
    private Integer trackId;

    @Column(
            name = "Name",
            nullable = false,
            length = 200
    )
    private String name;

    @Column(
            name = "Composer",
            length = 220
    )
    private String composer;

    @Column(
            name = "Milliseconds",
            nullable = false
    )
    private Integer milliseconds;

    @Column(
            name = "Bytes"
    )
    private Integer bytes;

    @Column(
            name = "UnitPrice",
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal unitPrice;

    // jsonignore property to not display it as part of request/response body
    // relationship left untouched for jpql benefit for queries
    @JsonIgnore
    @OneToMany(
            mappedBy = "track",
            fetch = FetchType.LAZY
    )
    private List<InvoiceLine> invoiceLineList;

    // jsonignore property to not display it as part of request/response body
    // relationship left untouched for jpql benefit for queries
    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "AlbumId",
            referencedColumnName = "AlbumId",
            foreignKey = @ForeignKey(name = "FK_TrackAlbumId")
    )
    private Album album;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "GenreId",
            referencedColumnName = "GenreId",
            foreignKey = @ForeignKey(name = "FK_TrackGenreId")
    )
    private Genre genre;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "MediaTypeId",
            referencedColumnName = "MediaTypeId",
            foreignKey = @ForeignKey(name = "FK_TrackMediaTypeId")
    )
    private MediaType mediaType;

    @JsonIgnore
    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "trackList"
    )
    private List<Playlist> playlistList;
}
