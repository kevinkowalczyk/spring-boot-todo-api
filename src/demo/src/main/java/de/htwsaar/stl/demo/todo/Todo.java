package de.htwsaar.stl.demo.todo;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
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

    public Todo() {}

    public Todo(Long id, String title, String description, boolean done, LocalDateTime startedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.done = done;
        this.startedAt = startedAt;
    }
}