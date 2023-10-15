package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 수강생차시진도 엔티티
 * @author 신민기
 */
@Entity
@Setter @Getter
@Table(name = "stud_lect_prog")
@NoArgsConstructor
public class StudLectProg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mag_id")
    private Long magId;

    /* 수강생 번호 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stud_id")
    private Student student;

    /* 차시ID */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nth_id")
    private LectNth lectNth;

    /* 강좌ID */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lect_id")
    private LectInfo lectInfo;

    /* 최종재생위치 */
    @Column(name = "fnl_posi")
    private double fnlPosi;

    /* 최대재생위치 */
    @Column(name = "max_posi")
    private double maxPosi;

    /* 현재재생위치 */
//    @Column(name = "curr_posi")
//    private double currPosi;

    /* 출석 상태 */
    @Column(name = "is_checked")
    private Boolean isChecked = false;

    /* 출석 날짜 */
    @Column(name = "check_date")
    private LocalDate checkDate;

    @Builder
    public StudLectProg(double fnlPosi, double maxPosi, Boolean isChecked, LocalDate checkDate) {
        this.fnlPosi = fnlPosi;
        this.maxPosi = maxPosi;
        this.isChecked = isChecked;
        this.checkDate = checkDate;
    }
}
