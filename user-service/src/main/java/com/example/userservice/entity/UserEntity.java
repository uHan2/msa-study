package com.example.userservice.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "tbl_user")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String userId;

    private String password;

}
