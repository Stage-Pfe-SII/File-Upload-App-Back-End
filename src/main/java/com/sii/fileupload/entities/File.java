package com.sii.fileupload.entities;

import lombok.Data;

import javax.persistence.*;


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

    @Lob
    private byte[] content;

    @ManyToOne
    @JoinColumn(name = "transfert_id", nullable = false)
    private Transfert transfert;
}
