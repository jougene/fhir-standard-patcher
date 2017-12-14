CREATE TABLE resource_type (
      id          SERIAL PRIMARY KEY,
      name        varchar(32) NOT NULL
);

CREATE TABLE fhir_version (
      id          SERIAL PRIMARY KEY,
      version     varchar(16) NOT NULL
);

CREATE TABLE schema (
      id                SERIAL PRIMARY KEY,
      resource_type_id  varchar(32) NOT NULL,
      fhir_version_id   integer REFERENCES fhir_version (id) ON DELETE SET NULL,
      data              text
);

CREATE TABLE resource (
      id                SERIAL PRIMARY KEY,
      resource_type_id  varchar(32) NOT NULL,
      schema_id         integer REFERENCES schema (id) ON DELETE SET NULL,
      data              text
);
