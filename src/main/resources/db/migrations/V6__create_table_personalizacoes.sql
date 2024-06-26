CREATE TABLE personalizacoes(
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    nome varchar(80) not null,
    descricao text not null,
    preco_adicional decimal(10,2) not null default 0,
    id_itens bigint not null,
    quantidade int not null,
    foreign key(id_itens) references itens(id)
);