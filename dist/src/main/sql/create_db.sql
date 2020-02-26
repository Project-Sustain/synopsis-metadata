# noinspection SqlNoDataSourceInspectionForFile

# DATASET SERVICE
DROP TABLE IF EXISTS dataset;
CREATE TABLE dataset (
    id                          VARCHAR(64)     NOT NULL,
    created_at                  TIMESTAMP       NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS strand_schema;
CREATE TABLE strand_schema (
   id                          VARCHAR(64)     NOT NULL,
   geohash_length              INT             NOT NULL,
   temporal_bracket_length     LONG            NOT NULL,
   bin_config                  TEXT            NOT NULL,
   created_at                  TIMESTAMP       NOT NULL
);

DROP TABLE IF EXISTS session;
CREATE TABLE session (
    session_id                  BIGINT          NOT NULL AUTO_INCREMENT,
    dataset_id                  VARCHAR(64)     NOT NULL,
    strand_schema_id            VARCHAR(64)     NOT NULL,
    created_at                  TIMESTAMP       NOT NULL,
    PRIMARY KEY (session_id),
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
