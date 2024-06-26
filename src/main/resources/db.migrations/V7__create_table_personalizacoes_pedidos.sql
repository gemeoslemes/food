CREATE TABLE personalizacoes_pedidos(
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    id_itens_pedidos bigint not null,
    id_personalizacoes bigint not null,
    foreign key(id_itens_pedidos) references item_pedidos(id),
    foreign key(id_personalizacoes) references personalizacoes(id)
);