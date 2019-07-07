CREATE EXTENSION postgis;

CREATE TABLE reservation (
    id SERIAL PRIMARY KEY,
    date_demande timestamp NOT NULL,
    date_entree_vigueur timestamp NOT NULL,
    statut character varying(200),
    id_jardin integer NOT NULL,
    id_lot integer NOT NULL
);

