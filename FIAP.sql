CREATE TABLE T_Usuario_Numero (
    ID_USUARIO NUMBER PRIMARY KEY,
    NOME_USUARIO VARCHAR2(128 BYTE) NOT NULL,
    EMAIL VARCHAR2(100 BYTE) NOT NULL,
    SENHA VARCHAR2(12 BYTE) NOT NULL
);

CREATE TABLE T_Transacao (
    id NUMBER PRIMARY KEY,
    tipoTransacao VARCHAR2(15) CHECK (tipoTransacao IN ('entrada', 'saida', 'investimento')) NOT NULL,
    id_Categoria NUMBER NOT NULL,
    IdUsuario NUMBER NOT NULL,
    valor NUMBER(10, 2) NOT NULL,
    dataTransacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    descricao VARCHAR2(255),
    CONSTRAINT fk_categoria FOREIGN KEY (id_Categoria) REFERENCES T_Categoria(ID_CATEGORIA),
    CONSTRAINT fk_usuario FOREIGN KEY (IdUsuario) REFERENCES T_Usuario_Numero(ID_USUARIO)
);
