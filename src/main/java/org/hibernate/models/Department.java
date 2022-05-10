package org.hibernate.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Department {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NonNull
    @Column(length = 100, nullable = false)
    String name;
    @NonNull
    @Column(length = 100, nullable = false)
    String city;
}
