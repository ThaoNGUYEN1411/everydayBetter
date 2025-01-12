package co.simplon.everydaybetterbusiness.repositories;

import co.simplon.everydaybetterbusiness.entities.LabelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<LabelEntity, Long> {
    LabelEntity findByLabelNameIgnoreCase(String labelName);
}
//todo: return optional