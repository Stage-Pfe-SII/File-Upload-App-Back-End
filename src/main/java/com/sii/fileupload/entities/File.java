package com.sii.fileupload.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @JoinColumn(name = "transfert_id")
    private Transfert transfert;
}
