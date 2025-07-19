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
  idLocacao INT NOT NULL AUTO_INCREMENT,
  data_alugado DATE NOT NULL,
  data_devolvido DATE NOT NULL,
  valor_final DECIMAL(5, 2) DEFAULT 0.00 CHECK (valor_final >= 0),
  multa DECIMAL(5, 2) DEFAULT 0.00 CHECK (multa >= 0),
  pendente TINYINT NOT NULL, -- 0 = locação finalizada, 1 = pendente
  cliente_cpf VARCHAR(12) NOT NULL,
  PRIMARY KEY (idLocacao),
  FOREIGN KEY (cliente_cpf) REFERENCES cliente(cpf)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci;

-- Tabela: acervo
CREATE TABLE acervo (
  idAcervo INT NOT NULL AUTO_INCREMENT,
  filme_id INT NOT NULL,
  situacao ENUM('DISPONIVEL', 'ALUGADO', 'DANIFICADO') DEFAULT 'DISPONIVEL',
  PRIMARY KEY (idAcervo),
  FOREIGN KEY (filme_id) REFERENCES filme (idFilme)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci;

-- Tabela: itemlocacao
CREATE TABLE itemlocacao (
  idItemLocacao INT NOT NULL AUTO_INCREMENT,
  idLocacao INT NOT NULL,
  idAcervo INT NOT NULL,
  PRIMARY KEY (idItemLocacao),
  FOREIGN KEY (idLocacao) REFERENCES locacao(idLocacao),
  FOREIGN KEY (idAcervo) REFERENCES acervo(idAcervo)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci;

