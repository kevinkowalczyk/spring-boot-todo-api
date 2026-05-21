ALTER TABLE todo
    ALTER COLUMN started_at TYPE TIMESTAMP
          USING started_at::TIMESTAMP;