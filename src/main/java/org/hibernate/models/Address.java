package org.hibernate.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NamedQuery(name = "getAllAddresses", query = "FROM Address")
@NamedQuery(name = "getAddressById", query = "FROM Address WHERE id = :id")
@Entity
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NonNull
    @Column(nullable = false, length = 150)
    String addressOne;
    @NonNull
    @Column(nullable = true, length = 150)
    String addressTwo;
    @NonNull
    @Column(length = 100, nullable = false)
    String city;
    @NonNull
    @Column(nullable = false)
    int zipCode;
    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    Employee employee;
}
