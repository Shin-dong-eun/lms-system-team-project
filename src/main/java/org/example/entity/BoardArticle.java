package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "board_article")
@Getter
@Entity
@ToString
public class BoardArticle {

    // 게시글 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id" , updatable = false)
    private Long Id;

    //  게시판 id (외래키)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id" , updatable = false , nullable = true)
    private BoardInfo boardInfo;

    // 게시글 이름
    @Column(name = "article_title" , nullable = false)
    private String articleTitle;

    // 게시글 내용
    @Column(name = "article_cont" , nullable = false)
    private String articleContent;

    // 게시글 조회수
    @Column(name = "article_view" , nullable = false)
    private Long articleView;

    // 게시글 작성일시
    @CreatedDate
    @Column(name = "article_at" , nullable = false)
    private LocalDateTime articleAt;

    // 게시글 공개여부
    @Column(name = "is_locked" , nullable = false)
    @ColumnDefault("false")
    private Boolean isLocked;

    // 게시글 삭제여부
    @Column(name = "is_deleted" , nullable = false)
    @ColumnDefault("false")
    private Boolean isDeleted;

    // 게시글 작성자
    @Column(name = "article_writer" , nullable = false)
    private String articleWriter;


    @Builder
    public BoardArticle(
            BoardInfo boardInfo , String articleTitle , String articleContent , Long articleView ,
            Boolean isLocked , Boolean isDeleted , String articleWriter){
        this.boardInfo = boardInfo;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleView = articleView;
        this.isLocked = isLocked;
        this.isDeleted = isDeleted;
        this.articleWriter = articleWriter;
    }



}