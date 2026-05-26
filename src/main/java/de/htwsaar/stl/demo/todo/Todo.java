package de.htwsaar.stl.demo.todo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor // Required for the Builder. Create Constructor with all Arguments.
@NoArgsConstructor // Required for JPA Specification. Create Constructor with no Arguments.
@Entity
@Table(name = "todo")
public class Todo {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "title", nullable = false)
    private String title;

    @Getter
    @Column(name = "description", nullable = false)
    private String description;

    @Getter
    @Column(name = "done", nullable = false)
    private boolean done;

    @Getter
    @Column(name = "started_at", nullable = false)
    private LocalDateTime startedAt;

}