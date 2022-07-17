package com.example.assignment167.entiy;

import com.example.assignment167.entiy.enums.ProductSimpleStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User{
    //    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Id
    private String id;
    private String fullName;
    private String phone;
    private String email;
    private ProductSimpleStatus status;
}
