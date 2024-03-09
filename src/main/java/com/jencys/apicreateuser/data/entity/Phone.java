package com.jencys.apicreateuser.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "phones")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private String number;
    @Column
    private String cityCode;
    @Column
    private String countryCode;
}
