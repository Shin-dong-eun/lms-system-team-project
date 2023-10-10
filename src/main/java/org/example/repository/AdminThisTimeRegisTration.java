package org.example.repository;

import org.example.dto.LectNthDto;
import org.example.entity.LectNth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/* 온라인 강의수강현황 LectNth*/
public interface AdminThisTimeRegisTration extends JpaRepository<LectNth, Long> {

    @Query("select new org.example.dto.LectNthDto(lif.lectName, lif.lectSem, lif.enrollStart, lif.enrollEnd,"
            + "lif.lectStart, lif.lectEnd, lif.isActive, nth.nthName, nth.nthSequence)"
            + "from LectNth nth join nth.lectInfo lif ")
    List<LectNthDto> findLectNthDtos();

    @Query("select new org.example.dto.LectNthDto(lif.lectName, lif.lectSem, lif.enrollStart, lif.enrollEnd,"
            + "lif.lectStart, lif.lectEnd, lif.isActive, nth.nthName, nth.nthSequence)"
            + "from LectNth nth join nth.lectInfo lif " +
            "WHERE lif.lectName LIKE CONCAT('%', :lectName, '%')")
    List<LectNthDto> findLectNthSearch(@Param("lectName") String lectName);

    /* 검색 기능 */
    List<LectNth> findByNthIdContaining(String keyword);

}