package com.jencys.apicreateuser.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column
    private Date created;
    @Column
    private Date modified;
    @Column
    private Date lastLogin;
    @Column
    private String token;
    @Column
    private Boolean isActive;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String password;

    @PrePersist
    private void firstCreated(){
        this.created = new Date();
        this.isActive = true;
    }


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Phone> phones;
}
