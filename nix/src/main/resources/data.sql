/*
* Categorías
**/
insert into categoria (nombre) values ('Guarniciones');
insert into categoria (nombre) values ('Bebidas');
insert into categoria (nombre) values ('Aperitivos');
insert into categoria (nombre) values ('Entradas');
insert into categoria (nombre) values ('Ensaladas');

/*
* Comida
**/
insert into platillo (id, nombre, descripcion, foto, precio, categoria) values (1, 'Keylex', 'Synergized scalable solution', 'semper.tiff', 85, 'Bebidas');
insert into platillo (id, nombre, descripcion, foto, precio, categoria) values (2, 'Bamity', 'Fully-configurable empowering knowledge base', 'hac_habitasse.png', 59, 'Guarniciones');
insert into platillo (id, nombre, descripcion, foto, precio, categoria) values (3, 'Hatity', 'Virtual zero tolerance benchmark', 'volutpat_in.png', 52, 'Entradas');
insert into platillo (id, nombre, descripcion, foto, precio, categoria) values (4, 'Fix San', 'Multi-channelled well-modulated matrix', 'eget_semper.tiff', 79, 'Aperitivos');
insert into platillo (id, nombre, descripcion, foto, precio, categoria) values (5, 'Zoolab', 'Extended secondary groupware', 'odio_cras_mi.tiff', 52, 'Ensaladas');
insert into platillo (id, nombre, descripcion, foto, precio, categoria) values (6, 'Lotlux', 'Reduced optimizing open system', 'donec_odio.gif', 90, 'Guarniciones');
insert into platillo (id, nombre, descripcion, foto, precio, categoria) values (7, 'Opela', 'Synchronised upward-trending orchestration', 'eget_semper.jpeg', 90, 'Aperitivos');
insert into platillo (id, nombre, descripcion, foto, precio, categoria) values (8, 'Quo Lux', 'Configurable didactic hardware', 'augue_a.png', 17, 'Entradas');
insert into platillo (id, nombre, descripcion, foto, precio, categoria) values (9, 'Stringtough', 'Persevering encompassing service-desk', 'nibh_fusce_lacus.jpeg', 13, 'Entradas');
insert into platillo (id, nombre, descripcion, foto, precio, categoria) values (10, 'Konklux', 'Networked high-level Graphic Interface', 'justo_aliquam.gif', 100, 'Bebidas');
insert into platillo (id, nombre, descripcion, foto, precio, categoria) values (11, 'Span', 'Advanced non-volatile complexity', 'at_turpis.jpeg', 79, 'Ensaladas');
insert into platillo (id, nombre, descripcion, foto, precio, categoria) values (12, 'Transcof', 'Business-focused logistical array', 'pellentesque_volutpat_dui.png', 91, 'Bebidas');
insert into platillo (id, nombre, descripcion, foto, precio, categoria) values (13, 'Stronghold', 'Upgradable local help-desk', 'justo_sollicitudin.tiff', 92, 'Aperitivos');
insert into platillo (id, nombre, descripcion, foto, precio, categoria) values (14, 'Aerified', 'Reverse-engineered cohesive open system', 'erat_curabitur_gravida.tiff', 51, 'Guarniciones');
insert into platillo (id, nombre, descripcion, foto, precio, categoria) values (15, 'Sub-Ex', 'Monitored explicit capability', 'lacinia_aenean_sit.jpeg', 5, 'Entradas');
insert into platillo (id, nombre, descripcion, foto, precio, categoria) values (16, 'Otcom', 'Open-architected discrete standardization', 'malesuada_in_imperdiet.jpeg', 72, 'Ensaladas');
insert into platillo (id, nombre, descripcion, foto, precio, categoria) values (17, 'Tresom', 'Robust national access', 'ornare_consequat_lectus.png', 2, 'Entradas');
insert into platillo (id, nombre, descripcion, foto, precio, categoria) values (18, 'Mat Lam Tam', 'Enhanced secondary encryption', 'fusce.tiff', 24, 'Bebidas');
insert into platillo (id, nombre, descripcion, foto, precio, categoria) values (19, 'Veribet', 'User-friendly client-driven application', 'rhoncus_mauris_enim.tiff', 38, 'Bebidas');
insert into platillo (id, nombre, descripcion, foto, precio, categoria) values (20, 'Trippledex', 'Exclusive transitional paradigm', 'rhoncus.tiff', 96, 'Ensaladas');

/*
* Órdenes
**/
insert into takeout (id, status, client_id, delivery_man_id) values (1, 'READY', 'client@naat.io', 'delivery@naat.io');
insert into takeout (id, status, client_id, delivery_man_id) values (2, 'DELIVERYING', 'client@naat.io', 'delivery@naat.io');
insert into takeout (id, status, client_id, delivery_man_id) values (3, 'DELIVERED', 'client@naat.io', 'delivery@naat.io');
insert into takeout (id, status, client_id, delivery_man_id) values (4, 'PREPARING', 'client@naat.io', 'delivery@naat.io');
insert into takeout (id, status, client_id, delivery_man_id) values (5, 'PREPARING', 'client@naat.io', 'delivery@naat.io');
insert into takeout (id, status, client_id, delivery_man_id) values (6, 'READY', 'client@naat.io', 'delivery@naat.io');
insert into takeout (id, status, client_id, delivery_man_id) values (7, 'DELIVERYING', 'client@naat.io', 'delivery@naat.io');
insert into takeout (id, status, client_id, delivery_man_id) values (8, 'PREPARING', 'client@naat.io', 'delivery@naat.io');
insert into takeout (id, status, client_id, delivery_man_id) values (9, 'DELIVERED', 'client@naat.io', 'delivery@naat.io');
insert into takeout (id, status, client_id, delivery_man_id) values (10, 'DELIVERED', 'client@naat.io', 'delivery@naat.io');

/*
* Platillos en las órdenes
**/
insert into takeout_contains_food (takeout_id, food_id) values (3, 3);
insert into takeout_contains_food (takeout_id, food_id) values (7, 19);
insert into takeout_contains_food (takeout_id, food_id) values (9, 9);
insert into takeout_contains_food (takeout_id, food_id) values (7, 3);
insert into takeout_contains_food (takeout_id, food_id) values (2, 18);
insert into takeout_contains_food (takeout_id, food_id) values (10, 18);
insert into takeout_contains_food (takeout_id, food_id) values (2, 11);
insert into takeout_contains_food (takeout_id, food_id) values (9, 15);
insert into takeout_contains_food (takeout_id, food_id) values (9, 12);
insert into takeout_contains_food (takeout_id, food_id) values (10, 2);
insert into takeout_contains_food (takeout_id, food_id) values (2, 8);
insert into takeout_contains_food (takeout_id, food_id) values (9, 11);
insert into takeout_contains_food (takeout_id, food_id) values (9, 15);
insert into takeout_contains_food (takeout_id, food_id) values (3, 5);
insert into takeout_contains_food (takeout_id, food_id) values (7, 17);
insert into takeout_contains_food (takeout_id, food_id) values (3, 7);
insert into takeout_contains_food (takeout_id, food_id) values (10, 1);
insert into takeout_contains_food (takeout_id, food_id) values (9, 10);
insert into takeout_contains_food (takeout_id, food_id) values (10, 19);
insert into takeout_contains_food (takeout_id, food_id) values (3, 2);
insert into takeout_contains_food (takeout_id, food_id) values (3, 13);
insert into takeout_contains_food (takeout_id, food_id) values (10, 1);
insert into takeout_contains_food (takeout_id, food_id) values (7, 9);
insert into takeout_contains_food (takeout_id, food_id) values (3, 5);