package de.htwsaar.stl.demo.todo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor // Required for the Builder. Create Constructor with all Arguments.
@NoArgsConstructor // Required for JPA Specification. Create Constructor with no Arguments.
@Entity
@Table(name = "todo")
public class Todo {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(name = "title", nullable = false)
    private String title;

    @Getter @Setter
    @Column(name = "description", nullable = false)
    private String description;

    @Getter @Setter
    @Column(name = "done", nullable = false)
    private boolean done;

    @Getter @Setter
    @Column(name = "started_at", nullable = false)
    private LocalDateTime startedAt;

}