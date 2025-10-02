-- DDL para o sistema de biblioteca
DROP TABLE IF EXISTS emprestimo;
DROP TABLE IF EXISTS livro;
DROP TABLE IF EXISTS cliente;

CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE livro (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    quantidade INTEGER NOT NULL DEFAULT 1
);

CREATE TABLE emprestimo (
    id SERIAL PRIMARY KEY,
    id_livro INTEGER NOT NULL REFERENCES livro(id) ON DELETE CASCADE,
    id_cliente INTEGER NOT NULL REFERENCES cliente(id) ON DELETE CASCADE,
    data_emprestimo TIMESTAMP NOT NULL,
    data_devolucao TIMESTAMP
);

