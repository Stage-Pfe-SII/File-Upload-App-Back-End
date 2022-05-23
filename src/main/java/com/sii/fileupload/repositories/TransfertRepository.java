package com.sii.fileupload.repositories;

import com.sii.fileupload.entities.Transfert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransfertRepository extends JpaRepository<Transfert,Long> {
}
