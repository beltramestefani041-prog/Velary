PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS fase_saude (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL UNIQUE,
    descricao TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS conteudo_educativo (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    fase_id INTEGER NOT NULL,
    titulo TEXT NOT NULL,
    resumo TEXT NOT NULL,
    tipo TEXT NOT NULL,
    link TEXT,
    CONSTRAINT fk_conteudo_fase
        FOREIGN KEY (fase_id) REFERENCES fase_saude(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);
