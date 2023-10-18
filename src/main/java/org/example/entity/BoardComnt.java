package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @author 임승범
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "board_comnt")
@EntityListeners(AuditingEntityListener.class)
@Getter
@ToString
@Entity
public class BoardComnt {

    // 댓글 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comnt_id" , updatable = false)
    private Long Id;

    //  게시글(article) id (외래키)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id" , updatable = false , nullable = false)
    private BoardArticle boardArticle;

    // 회원ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Member member;

    // 댓글 작성일시
    @CreatedDate
    @Column(name = "comnt_at" , nullable = false)
    private LocalDateTime comntAt;

    // 댓글 작성자
    @Column(name = "comnt_writer" , nullable = false)
    private String comntWriter;

    // 댓글 내용
    @Column(name = "comnt_cont" , nullable = false)
    private String comntCont;

    @Builder
    public BoardComnt (BoardArticle boardArticle , String comntWriter , String comntCont){
        this.boardArticle = boardArticle;
        this.comntWriter = comntWriter;
        this.comntCont = comntCont;
    }

}