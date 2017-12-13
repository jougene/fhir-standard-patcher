CREATE TABLE resources (
    id          integer PRIMARY KEY,
    type        varchar(32) NOT NULL,
    data        text,
    version_id  integer REFERENCES fhir_versions (id) ON DELETE CASCADE
);
