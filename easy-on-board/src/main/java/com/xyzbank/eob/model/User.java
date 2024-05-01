package com.xyzbank.eob.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_master")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends ParentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;
    private LocalDate dateOfBirth;

    @ManyToMany
    @JoinTable(
            name = "user_role_mapping",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @ManyToMany
    @JoinTable(
            name = "user_address_mapping",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private Set<Address> addresses;

    @ManyToMany
    @JoinTable(
            name = "user_account_mapping",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private Set<Account> accounts;


    @OneToMany(mappedBy = "user")
    private List<UserLoginHistory> loginHistories;

}
