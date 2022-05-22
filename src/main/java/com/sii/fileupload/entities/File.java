package com.sii.fileupload.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
public class File {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    private String path;
    private long size;
    private Date uploadDate;

    @Lob
    private byte[] content;

    @ManyToOne
    @JoinColumn(name = "transfert_id", nullable = false)
    @JsonIgnore
    private Transfert transfert;

}
