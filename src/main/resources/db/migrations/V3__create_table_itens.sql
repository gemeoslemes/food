CREATE TABLE itens(
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    nome varchar(50) NOT NULL,
    descricao text NOT NULL,
    preco decimal(10, 2) NOT NULL default 0,
    foto_item MEDIUMBLOB NOT NULL,
    id_sub_categoria BIGINT NOT NULL,
	foreign key(id_sub_categoria) references sub_categorias(id)
);