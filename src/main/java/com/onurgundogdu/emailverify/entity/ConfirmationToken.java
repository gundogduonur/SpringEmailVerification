package com.onurgundogdu.emailverify.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {
    @Id
    @SequenceGenerator(sequenceName = "confirmation_token_sequence",name = "confirmation_token_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "confirmation_token_sequence")
    private Long id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createdTime;
    @Column(nullable = false)
    private LocalDateTime expiredTime;
    private LocalDateTime confirmedTime;

    @ManyToOne
    @JoinColumn(nullable = false,name = "app_user_id")
    private User user;

    public ConfirmationToken(String token, LocalDateTime createdTime,
                             LocalDateTime expiredTime,
                             User user) {
        this.token = token;
        this.createdTime = createdTime;
        this.expiredTime = expiredTime;
        this.confirmedTime = confirmedTime;
        this.user=user;
    }
}
