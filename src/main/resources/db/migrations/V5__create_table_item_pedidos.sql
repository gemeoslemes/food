CREATE TABLE item_pedidos(
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    id_pedidos BIGINT NOT NULL,
    id_itens BIGINT NOT NULL,
    quantidade int NOT NULL,
    preco_unitario decimal(10,2) NOT null,
    foreign key(id_pedidos) references pedidos(id),
    foreign key(id_itens) references itens(id)
);