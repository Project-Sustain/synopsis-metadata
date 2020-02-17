# noinspection SqlNoDataSourceInspectionForFile

# DATASET SERVICE
DROP TABLE IF EXISTS dataset;
CREATE TABLE dataset (
    id                          VARCHAR(64)     NOT NULL,
    temporal_bracket_length     LONG            NOT NULL,
    geohash_length              INT             NOT NULL,
    bin_config                  TEXT            NOT NULL,
    created_at                  TIMESTAMP       NOT NULL,
    updated_at                  TIMESTAMP       NOT NULL DEFAULT NOW() ON UPDATE NOW(),
    PRIMARY KEY (id)
);

# INGEST SERVICE
DROP TABLE IF EXISTS ingest_session;
CREATE TABLE ingest_session (
   dataset_id               VARCHAR(64)     NOT NULL,
   session_id               INT             NOT NULL,
   creator_client_id        VARCHAR(64)     NOT NULL,
   created_at               TIMESTAMP       NOT NULL,
   PRIMARY KEY (dataset_id, session_id),
   INDEX(dataset_id)
);

# USER SERVICE
DROP TABLE IF EXISTS user;
CREATE TABLE user (
    email                   VARCHAR(64)     NOT NULL,
    secret                  BINARY(32)      NOT NULL,
    has_confirmed_email     BOOL            NOT NULL,
    is_admin                BOOL            NOT NULL,
    created_at              TIMESTAMP       NOT NULL,
    updated_at              TIMESTAMP       NOT NULL DEFAULT NOW() ON UPDATE NOW(),
    PRIMARY KEY (email)
);
