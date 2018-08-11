INSERT INTO usuario(email, nome, senha)	VALUES ('admin', 'Eduardo Pereira', '$2a$10$xpzY/XWFIvalyiQdDp87B.lV6A/W147YQrgSeJueUfWFgKUwGJNO2');
INSERT INTO usuario(email, nome, senha)	VALUES ('geral', 'Geral User', '$2a$10$kfqXbiZSMXC2Vqt5BEj9AeEqYwZUKQFvqzz0WZA.KYyUXA1yd87XS');


INSERT INTO permissao(id, descricao) VALUES (1, 'ROLE_CADASTRAR_USUARIO');
INSERT INTO permissao(id, descricao) VALUES (2, 'ROLE_PESQUISAR_USUARIO');
INSERT INTO permissao(id, descricao) VALUES (3, 'ROLE_LISTAR_USUARIO');

INSERT INTO permissao(id, descricao) VALUES (4, 'ROLE_CADASTRAR_PRODUTO');
INSERT INTO permissao(id, descricao) VALUES (5, 'ROLE_LISTAR_PRODUTO');
INSERT INTO permissao(id, descricao) VALUES (6, 'ROLE_PESQUISAR_PRODUTO');

INSERT INTO permissao(id, descricao) VALUES (7, 'ROLE_CRIAR_COMANDA');
INSERT INTO permissao(id, descricao) VALUES (8, 'ROLE_ADD_PRODUTO_COMANDA');
INSERT INTO permissao(id, descricao) VALUES (9, 'ROLE_PESQUISAR_COMANDA');


INSERT INTO usuario_permissao(usuario_id, permissao_id)	VALUES (1, 1);
INSERT INTO usuario_permissao(usuario_id, permissao_id)	VALUES (1, 2);
INSERT INTO usuario_permissao(usuario_id, permissao_id)	VALUES (1, 3);
INSERT INTO usuario_permissao(usuario_id, permissao_id)	VALUES (1, 4);
INSERT INTO usuario_permissao(usuario_id, permissao_id)	VALUES (1, 5);
INSERT INTO usuario_permissao(usuario_id, permissao_id)	VALUES (1, 6);
INSERT INTO usuario_permissao(usuario_id, permissao_id)	VALUES (1, 7);
INSERT INTO usuario_permissao(usuario_id, permissao_id)	VALUES (1, 8);
INSERT INTO usuario_permissao(usuario_id, permissao_id)	VALUES (1, 9);

INSERT INTO usuario_permissao(usuario_id, permissao_id)	VALUES (2, 7);
INSERT INTO usuario_permissao(usuario_id, permissao_id)	VALUES (2, 8);
INSERT INTO usuario_permissao(usuario_id, permissao_id)	VALUES (2, 9);