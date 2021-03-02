package com.example.usermanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = UserData.TABLE_NAME)
public class UserData {

    public static final String TABLE_NAME = "user_data";

    public static class ColumnName {

        private ColumnName() {
        }

        public static final String ID = "id";

        public static final String USER_ID = "user_id";

    }

    @Id
    @Column(name = ColumnName.ID)
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = ColumnName.USER_ID)
    private User user;

}
