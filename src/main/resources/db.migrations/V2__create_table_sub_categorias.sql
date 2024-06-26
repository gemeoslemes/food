CREATE TABLE sub_categorias(
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    nome varchar(50) NOT NULL,
    descricao text NOT NULL,
    id_categoria BIGINT NOT NULL,
    foreign key(id_categoria) references categorias(id)
);