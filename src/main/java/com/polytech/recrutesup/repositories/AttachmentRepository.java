package com.polytech.recrutesup.repositories;

import com.polytech.recrutesup.entities.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.NamedNativeQuery;
import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    @Query(
            value = "SELECT * FROM attachment a \n" +
                    "    INNER JOIN offer_attachment oa on a.id = oa.id_attachment\n" +
                    "    WHERE oa.id_offer = :idOffer",
            nativeQuery = true)
    List<Attachment> findAllAttachmentByOffer(@Param("idOffer") Long idOffer);

}
