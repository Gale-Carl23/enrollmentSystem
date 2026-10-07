CREATE TABLE schema_info (
    id BIGSERIAL PRIMARY KEY,
    application_name VARCHAR(150) NOT NULL,
    application_version VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);