INSERT INTO tb_city(id, city, state) VALUES (1, 'Florianópolis', 'SC');
INSERT INTO tb_city(id, city, state) VALUES (2, 'São José', 'SC');
INSERT INTO tb_city(id, city, state) VALUES (3, 'Gramado', 'SC');
INSERT INTO tb_city(id, city, state) VALUES (4, 'Piracicaba', 'SP');
INSERT INTO tb_city(id, city, state) VALUES (5, 'Palhoça', 'SC');
INSERT INTO tb_city(id, city, state) VALUES (6, 'Cascavel', 'PR');
INSERT INTO tb_city(id, city, state) VALUES (7, 'Limeira', 'SP');

INSERT INTO tb_address(id, street, number, complement, district, city_id, zip_code) VALUES (1, 'Rua Hotel 1', '123', 'prédio amarelo', 'Campinas', 2, '88111450');
INSERT INTO tb_address(id, street, number, district, city_id, zip_code) VALUES (2, 'Rua Hotel 2', '456', 'Ingleses', 1, '88222145');
INSERT INTO tb_address(id, street, number, complement, district, city_id, zip_code) VALUES (3, 'Rua Hotel 3', '456', 'prédio azul', 'Centro', 3, '88154545');
INSERT INTO tb_address(id, street, number, complement, district, city_id, zip_code) VALUES (4, 'Rua Hotel 4', '321', 'prédio verde', 'São Jorge', 4, '87458882');
INSERT INTO tb_address(id, street, number, complement, district, city_id, zip_code) VALUES (5, 'Rua Hotel', '456', 'prédio vermelho', 'Morro das Pedras', 1, '88222145');
INSERT INTO tb_address(id, street, number, district, city_id, zip_code) VALUES (6, 'Rua Hotel', '123', 'Ipiranga', 2, '88111450');
INSERT INTO tb_address(id, street, number, complement, district, city_id, zip_code) VALUES (7, 'Rua Hotel', '123', 'prédio belvedere', 'Belvedere', 3, '88111451');
INSERT INTO tb_address(id, street, number, complement, district, city_id, zip_code) VALUES (8, 'Rua Hotel', '54', 'prédio branco', 'São Pedro', 4, '88111451');
INSERT INTO tb_address(id, street, number, complement, district, city_id, zip_code) VALUES (9, 'Rua Hotel', '64', 'frente padaria', 'Coqueiros', 1, '88222142');
INSERT INTO tb_address(id, street, number, complement, district, city_id, zip_code) VALUES (10, 'Rua Hotel', '123', 'casa', 'Kobrasol', 2, '88111450');
INSERT INTO tb_address(id, street, number, complement, district, city_id, zip_code) VALUES (11, 'Rua Hotel 11', '64', 'prédio amarelo', 'Canascieiras', 1, '88222142');
INSERT INTO tb_address(id, street, number, district, city_id, zip_code) VALUES (12, 'Rua Hotel 12', '64', 'Praia do Forte', 1, '88222142');
INSERT INTO tb_address(id, street, number, district, city_id, zip_code) VALUES (13, 'Rua Hotel', '456', 'Centro', 1, '88222145');
INSERT INTO tb_address(id, street, number, district, city_id, zip_code) VALUES (14, 'Rua Hotel', '456', 'Sul da Ilha', 1, '88222145');
INSERT INTO tb_address(id, street, number, district, city_id, zip_code) VALUES (15, 'Rua Hotel', '456', 'Sul da Ilha', 1, '88222145');
INSERT INTO tb_address(id, street, number, district, city_id, zip_code) VALUES (16, 'Rua Hotel', '456', 'Sul da Ilha', 1, '88222145');
INSERT INTO tb_address(id, street, number, district, city_id, zip_code) VALUES (17, 'Rua Hotel', '456', 'Sul da Ilha', 1, '88222145');
INSERT INTO tb_address(id, street, number, district, city_id, zip_code) VALUES (18, 'Rua Hotel', '456', 'Beira Mar', 1, '88222145');


INSERT INTO tb_establishment(id, cnpj, name, description, email, number_phone, status, created_at, address_id, image) VALUES (1, '11.222.333/0004-01', 'Pêlos e Patas Hotel', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry standard dummy text ever since the 1500s', 'hotel@email.com', '(48)99999-9999', 'ACTIVE', '2022-04-06 10:46:44', 1, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQG20tWVvTn7MblXOgY55mZleRcoDC2_08DD_GI8sjnpXSk0MhdcW0uZslAVPvmUh7NOSs&usqp=CAU');
INSERT INTO tb_establishment(id, cnpj, name, description, email, number_phone, status, created_at, address_id, image) VALUES (2, '11.222.333/0004-02', 'Pet Hotel Astrodog', 't is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as asadfdfgdfdfddfdfdfdfddfdfdfddfdfdg', 'hotel2@email.com', '(48)99999-9999', 'ACTIVE', '2022-04-06 10:46:44', 2, 'https://media.istockphoto.com/vectors/pet-hotel-and-pet-house-for-pets-and-animals-doglittle-dogcatparrot-vector-id1221774782?k=20&m=1221774782&s=170667a&w=0&h=1Fm3vBzGJLmv1RbxFKOehj9iBbyRMVJpP7rf3zzMIy8=');
INSERT INTO tb_establishment(id, cnpj, name, description, email, number_phone, status, created_at, address_id, image) VALUES (3, '11.222.333/0004-03', 'My Pets Hotel', 'he standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from ', 'hotel3@email.com', '(48)99999-9999', 'ACTIVE', '2022-04-06 10:46:44', 3, 'https://i.pinimg.com/736x/cb/7a/56/cb7a56dfdbe6c6c12c12a8b0286a659c.jpg');
INSERT INTO tb_establishment(id, cnpj, name, description, email, number_phone, status, created_at, address_id, image) VALUES (4, '11.222.333/0004-04', 'Pet Hotel 101 Cães', 'There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which dont look even slightly believable.', 'hotel4@email.com', '(48)99999-9999', 'ACTIVE', '2022-04-06 10:46:44', 4, 'https://us.123rf.com/450wm/kcng83/kcng831908/kcng83190800266/131023355-cute-dog-holding-a-big-signboard-pet-hotel.jpg?ver=6');
INSERT INTO tb_establishment(id, cnpj, name, description, email, number_phone, status, created_at, address_id, image) VALUES (5, '11.222.333/0004-05', 'Planet Pet', 't is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as asadfdfgdfdfddfdfdfdfddfdfdfddfdfdg', 'hotel5@email.com', '(48)99999-9999', 'ACTIVE', '2022-04-06 10:46:44', 5, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQNJ641zCZ2jAg1A2YDu5U2IzjLUNuaIUBFfg&usqp=CAU');
INSERT INTO tb_establishment(id, cnpj, name, description, email, number_phone, status, created_at, address_id, image) VALUES (6, '11.222.333/0004-06', 'Hotel Férias do Dono', 't is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as asadfdfgdfdfddfdfdfdfddfdfdfddfdfdg', 'hotel6@email.com', '(48)99999-9999', 'ACTIVE', '2022-04-06 10:46:44', 6, 'https://en.pimg.jp/068/806/068/1/68806068.jpg');
INSERT INTO tb_establishment(id, cnpj, name, description, email, number_phone, status, created_at, address_id, image) VALUES (7, '11.222.333/0004-07', 'Hotel Patinhas', 't is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as ', 'hotel7@email.com', '(48)99999-9999', 'ACTIVE', '2022-04-06 10:46:44', 7, 'https://media.istockphoto.com/vectors/pets-care-services-tiny-people-and-pet-hotel-daycare-veterinary-pet-vector-id1205007403?s=612x612');
INSERT INTO tb_establishment(id, cnpj, name, description, email, number_phone, status, created_at, address_id, image) VALUES (8, '11.222.333/0004-08', 'Cãopanheiro e gatos', 't is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem fact that a reader will be distracted by the readable content of a page', 'hotel8@email.com', '(48)99999-9999', 'ACTIVE', '2022-04-06 10:46:44', 8, 'https://media.istockphoto.com/vectors/online-pets-care-services-tiny-people-and-pet-hotel-daycare-service-vector-id1205007321');
INSERT INTO tb_establishment(id, cnpj, name, description, email, number_phone, status, created_at, address_id, image) VALUES (9, '11.222.333/0004-09', 'Hotel Peludinhos', 't is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as asadfdfgdfdfddfdfdfdfddfdfdfddfdfdg', 'hotel9@email.com', '(48)99999-9999', 'ACTIVE', '2022-04-06 10:46:44', 9, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ0mfqLtczTan-_ZOWpSoo6rZoTgCtxFlHDpg&usqp=CAU');
INSERT INTO tb_establishment(id, cnpj, name, description, email, number_phone, status, created_at, address_id, image) VALUES (10, '11.222.333/0004-10', 'Hotel Planeta Animal', 't is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as ', 'hotel10@email.com', '(48)99999-9999', 'ACTIVE', '2022-04-06 10:46:44', 10, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRJtMIiW8vJJUfJwJrn91slp4tIIKmMPOF5Aw&usqp=CAU');
INSERT INTO tb_establishment(id, cnpj, name, description, email, number_phone, status, created_at, address_id, image) VALUES (11, '11.222.333/0004-11', 'Pêlos e Pulgas Hotel', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s', 'hotel11@email.com', '(48)99999-9999', 'ACTIVE', '2022-04-06 10:46:44', 11, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQG20tWVvTn7MblXOgY55mZleRcoDC2_08DD_GI8sjnpXSk0MhdcW0uZslAVPvmUh7NOSs&usqp=CAU');
INSERT INTO tb_establishment(id, cnpj, name, description, email, number_phone, status, created_at, address_id, image) VALUES (12, '11.222.333/0004-12', 'Dog & Cat Hotel', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s', 'hotel@email.com', '(48)99999-9999', 'ACTIVE', '2022-04-06 10:46:44', 12, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQG20tWVvTn7MblXOgY55mZleRcoDC2_08DD_GI8sjnpXSk0MhdcW0uZslAVPvmUh7NOSs&usqp=CAU');
INSERT INTO tb_establishment(id, cnpj, name, description, email, number_phone, status, created_at, address_id, image) VALUES (13, '11.222.333/0004-13', 'Dog & Cat Hotel', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s', 'hotel@email.com', '(48)99999-9999', 'ACTIVE', '2022-04-06 10:46:44', 13, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQG20tWVvTn7MblXOgY55mZleRcoDC2_08DD_GI8sjnpXSk0MhdcW0uZslAVPvmUh7NOSs&usqp=CAU');
INSERT INTO tb_establishment(id, cnpj, name, description, email, number_phone, status, created_at, address_id, image) VALUES (14, '11.222.333/0004-14', 'Dog & Cat Hotel', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s', 'hotel@email.com', '(48)99999-9999', 'ACTIVE', '2022-04-06 10:46:44', 14, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQG20tWVvTn7MblXOgY55mZleRcoDC2_08DD_GI8sjnpXSk0MhdcW0uZslAVPvmUh7NOSs&usqp=CAU');
INSERT INTO tb_establishment(id, cnpj, name, description, email, number_phone, status, created_at, address_id, image) VALUES (15, '11.222.333/0004-15', 'Dog & Cat Hotel', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s', 'hotel@email.com', '(48)99999-9999', 'ACTIVE', '2022-04-06 10:46:44', 15, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQG20tWVvTn7MblXOgY55mZleRcoDC2_08DD_GI8sjnpXSk0MhdcW0uZslAVPvmUh7NOSs&usqp=CAU');
INSERT INTO tb_establishment(id, cnpj, name, description, email, number_phone, status, created_at, address_id, image) VALUES (16, '11.222.333/0004-16', 'Dog & Cat Hotel', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s', 'hotel@email.com', '(48)99999-9999', 'ACTIVE', '2022-04-06 10:46:44', 16, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQG20tWVvTn7MblXOgY55mZleRcoDC2_08DD_GI8sjnpXSk0MhdcW0uZslAVPvmUh7NOSs&usqp=CAU');
INSERT INTO tb_establishment(id, cnpj, name, description, email, number_phone, status, created_at, address_id, image) VALUES (17, '11.222.333/0004-17', 'Dog & Cat Hotel', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s', 'hotel@email.com', '(48)99999-9999', 'ACTIVE', '2022-04-06 10:46:44', 17, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQG20tWVvTn7MblXOgY55mZleRcoDC2_08DD_GI8sjnpXSk0MhdcW0uZslAVPvmUh7NOSs&usqp=CAU');
INSERT INTO tb_establishment(id, cnpj, name, description, email, number_phone, status, created_at, address_id, image) VALUES (18, '11.222.333/0004-18', 'Dog & Cat Hotel', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s', 'hotel@email.com', '(48)99999-9999', 'ACTIVE', '2022-04-06 10:46:44', 18, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQG20tWVvTn7MblXOgY55mZleRcoDC2_08DD_GI8sjnpXSk0MhdcW0uZslAVPvmUh7NOSs&usqp=CAU');


INSERT INTO tb_filters(id, type, weight, castrated, gender) VALUES (1, 'dog', '5kg', 'uncastrated', 'male');
INSERT INTO tb_filters(id, type, weight, castrated, gender) VALUES (2, 'cat', '5kg', 'castrated', 'male');
INSERT INTO tb_filters(id, type, weight, gender) VALUES (3, 'dog', '15kg',  'female');
INSERT INTO tb_filters(id, type, weight, gender) VALUES (4, 'dog', '20kg',  'male');
INSERT INTO tb_filters(id, type, weight, castrated, gender) VALUES (5, 'dog', '5kg', 'castrated', 'male');
INSERT INTO tb_filters(id, type, weight, castrated, gender) VALUES (6, 'dog', '10kg', 'uncastrated', 'male');
INSERT INTO tb_filters(id, type, weight, castrated, gender) VALUES (7, 'dog', '10kg', 'uncastrated', 'female');
INSERT INTO tb_filters(id, type, weight, castrated, gender) VALUES (8, 'dog', '5kg', 'castrated', 'male');


INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (1, 1);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (2, 2);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (3, 3);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (4, 4);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (5, 6);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (5, 7);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (5, 5);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (6, 7);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (6, 6);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (7, 1);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (8, 8);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (9, 8);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (9, 5);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (10, 2);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (11, 5);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (12, 5);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (13, 8);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (13, 2);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (14, 8);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (14, 2);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (15, 8);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (15, 2);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (16, 8);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (16, 2);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (17, 8);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (17, 1);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (18, 1);
INSERT INTO tb_establishment_filters(establishment_id, filters_id) VALUES (18, 2);



