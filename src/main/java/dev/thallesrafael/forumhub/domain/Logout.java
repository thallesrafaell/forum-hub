package dev.thallesrafael.forumhub.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "logouts")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "id")
public class Logout {

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String token;
        private LocalDateTime revocationTime;
}
