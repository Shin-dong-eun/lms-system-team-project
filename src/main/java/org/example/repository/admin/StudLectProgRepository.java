package org.example.repository.admin;

import org.example.dto.admin.StudLectProgDto;
import org.example.entity.StudLectProg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudLectProgRepository extends JpaRepository<StudLectProg, Long> {

    @Query("SELECT NEW org.example.dto.admin.StudLectProgDto " +
            "(sp.magId) " +
            "FROM StudLectProg sp")
    StudLectProgDto findMagId();

    @Query("SELECT NEW org.example.dto.admin.StudLectProgDto " +
            "(sp.magId, sp.fnlPosi, sp.maxPosi, sp.isChecked, sp.checkDate) " +
            "FROM StudLectProg sp")
    List<StudLectProgDto> findStudLectProg();

    @Query("SELECT NEW org.example.dto.admin.StudLectProgDto " +
            "(sp.magId, sp.fnlPosi, sp.maxPosi, sp.isChecked, sp.checkDate) " +
            "FROM StudLectProg sp " +
            "WHERE sp.magId = :magId")
    StudLectProgDto findProg(@Param("magId") Long magId);
}
