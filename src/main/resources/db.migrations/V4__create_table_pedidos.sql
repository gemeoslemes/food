CREATE TABLE pedidos(
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    data_hora datetime not null,
    valor_total decimal(10,2) not null default 0,
    status enum('SAIU_PARA_ENTREGA', 'EM_PROCESSO', 'FINALIZADO', 'CANCELADO', 'DEVOLVIDO')
);