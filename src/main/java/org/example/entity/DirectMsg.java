package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "direct_msg")
@Getter
@Setter
@Entity
@ToString
public class DirectMsg {

    // 쪽지 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "msg_id", updatable = false)
    private Long id;

    //이전쪽지 ID
    @ManyToOne
    @JoinColumn(name = "msg_org_id", nullable = true)
    private DirectMsg orgMsg;

    // 발신자 id
    @OneToOne
    @JoinColumn(name = "send_id" , nullable = false)
    private Member sendId;

    // 수신자 id
    @OneToOne
    @JoinColumn(name = "recv_id" , nullable = true)
    private Member recvId;

    // 작성일시
    @CreatedDate
    @Column(name = "send_at" , nullable = false)
    private LocalDateTime sendAt;

    // 수신자 읽은날짜
    @Column(name = "recv_at" , nullable = true)
    private LocalDateTime recvAt;

    // 쪽지 제목
    @Column(name = "msg_title" , nullable = false)
    private String msgTitle;

    // 쪽지 내용
    @Column(name = "msg_cont" , nullable = false)
    private String msgCont;

    // 발신자 쪽지 삭제여부
    @Column(name = "send_del_yn" , nullable = false)
    @ColumnDefault("false")
    private Boolean sendDelYn;

    // 수신자 쪽지 삭제여부
    @Column(name = "recv_del_yn" , nullable = false)
    @ColumnDefault("false")
    private Boolean recvDelYn;

}
