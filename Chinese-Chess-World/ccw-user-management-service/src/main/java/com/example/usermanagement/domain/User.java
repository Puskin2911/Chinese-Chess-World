package com.example.usermanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = User.TABLE_NAME)
@Entity
public class User {

    public static final String TABLE_NAME = "user";

    public static class ColumnName {

        private ColumnName() {
        }

        public static final String ID = "id";

        public static final String USERNAME = "username";

        public static final String EMAIL = "email";

        public static final String PASS_HASHED = "pass_hashed";

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ColumnName.ID)
    private Long id;

    @NotNull
    @Column(name = ColumnName.USERNAME, unique = true)
    private String username;

    @NotNull
    @Column(name = ColumnName.EMAIL, unique = true)
    private String email;

    @NotNull
    @Column(name = ColumnName.PASS_HASHED)
    private String passHashed;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserData userData;

}
