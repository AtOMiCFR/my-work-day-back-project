package com.blinnproject.myworkdayback.model;

import com.blinnproject.myworkdayback.model.common.BaseEntityAudit;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users",
  uniqueConstraints = {
    @UniqueConstraint(columnNames = "username"),
    @UniqueConstraint(columnNames = "email")
  })
public class User extends BaseEntityAudit {
  @Column(nullable=false, length = 20)
  private String username;

  @Enumerated(EnumType.ORDINAL)
  private EGender gender;

  @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
    flags = Pattern.Flag.CASE_INSENSITIVE)
  @Column(nullable=false, unique=true)
  private String email;

  @Column(nullable=false)
  private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }
}

