CREATE TABLE categoria(
	cod_categoria INT NOT NULL AUTO_INCREMENT,
	descricao VARCHAR(50) NULL,
	PRIMARY KEY (cod_categoria)
);

CREATE TABLE produto(
	cod_produto INT NOT NULL AUTO_INCREMENT,
	descricao VARCHAR(50) NULL,
	preco DECIMAL(8,2) NULL,
	PRIMARY KEY (cod_produto)
);

CREATE TABLE categoria_produto(
	cod_categoria INT NOT NULL,
	cod_produto INT NOT NULL,
	PRIMARY KEY (cod_categoria),
	CONSTRAINT fk_categoria_produto_categoria
		FOREIGN KEY (cod_categoria)
		REFERENCES categoria (cod_categoria)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION,
	CONSTRAINT fk_categoria_produto_produto
		FOREIGN KEY (cod_produto)
		REFERENCES produto (cod_produto)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
);