CREATE TABLE todo (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    done BOOLEAN NOT NULL,
    started_at DATE NOT NULL
);