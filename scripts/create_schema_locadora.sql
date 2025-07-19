CREATE DATABASE IF NOT EXISTS locadora_filme DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE locadora_filme;

-- Tabela: cliente
CREATE TABLE cliente (
  cpf VARCHAR(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  nome_completo VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  num_telefone VARCHAR(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  PRIMARY KEY (cpf)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci;

-- Tabela: genero
CREATE TABLE genero (
  id INT NOT NULL AUTO_INCREMENT,
  tipo_genero VARCHAR(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci;

-- Tabela: filme
CREATE TABLE filme (
  idFilme INT NOT NULL AUTO_INCREMENT,
  nome_filme VARCHAR(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  data_lancamento DATE NOT NULL,
  genero_filme INT NOT NULL,
  valor_filme DECIMAL(4, 2) NOT NULL,
  PRIMARY KEY (idFilme),
  KEY fk_Filme_Secao_idx (genero_filme),
  CONSTRAINT fk_Filme_Secao FOREIGN KEY (genero_filme) REFERENCES genero (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci;

-- Tabela: locacao
CREATE TABLE locacao (
  id INT NOT NULL AUTO_INCREMENT,
  Filme_idFilme INT NOT NULL,
  data_alugado DATE NOT NULL,
  data_devolvido DATE NOT NULL,
  valor_pagar DECIMAL(5, 2) NOT NULL,
  pendente TINYINT NOT NULL,
  cliente_cpf VARCHAR(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  locacaocol VARCHAR(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  PRIMARY KEY (id),
  KEY fk_FilmesXcliente_Filme1_idx (Filme_idFilme),
  CONSTRAINT fk_FilmesXcliente_Filme1 FOREIGN KEY (Filme_idFilme) REFERENCES filme (idFilme)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci;

-- Tabela: itemlocacao
CREATE TABLE itemlocacao (
  id INT NOT NULL AUTO_INCREMENT,
  LocacaoId INT NOT NULL,
  FilmeId INT NOT NULL,
  PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci;