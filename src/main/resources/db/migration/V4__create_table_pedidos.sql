CREATE TABLE pedidos(
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    data_hora datetime not null,
    valor_total decimal(10,2) not null default 0,
    status ENUM('AGUARDANDO_CONFIRMACAO', 'EM_PREPARO', 'PRONTO_PARA_ENTREGA', 'EM_ENTREGA', 'ENTREGUE', 'CANCELADO') NOT NULL DEFAULT 'AGUARDANDO_CONFIRMACAO'
);