/* Categorías.
*/
insert into category (name) values ('Aperitivos');
insert into category (name) values ('Ensaladas');
insert into category (name) values ('Entradas');
insert into category (name) values ('Guarniciones');
insert into category (name) values ('Bebidas');

/*
* Comida
*/
insert into food (id, name, description, imagepath, price, category_name) values (1, 'Zontrax', 'Robust multi-state pricing structure', 'tellus_in_sagittis.png', 66, 'Entradas');
insert into food (id, name, description, imagepath, price, category_name) values (2, 'Job', 'Reactive 3rd generation standardization', 'cursus_vestibulum.tiff', 64, 'Guarniciones');
insert into food (id, name, description, imagepath, price, category_name) values (3, 'Sub-Ex', 'Optional leading edge analyzer', 'tincidunt_eget_tempus.tiff', 39, 'Aperitivos');
insert into food (id, name, description, imagepath, price, category_name) values (4, 'Kanlam', 'Front-line eco-centric forecast', 'ligula_in.jpeg', 5, 'Aperitivos');
insert into food (id, name, description, imagepath, price, category_name) values (5, 'Bamity', 'Distributed well-modulated budgetary management', 'nonummy_maecenas.jpeg', 56, 'Entradas');
insert into food (id, name, description, imagepath, price, category_name) values (6, 'Holdlamis', 'Secured motivating project', 'porttitor.gif', 76, 'Guarniciones');
insert into food (id, name, description, imagepath, price, category_name) values (7, 'Stringtough', 'Secured solution-oriented methodology', 'sapien_urna.tiff', 56, 'Aperitivos');
insert into food (id, name, description, imagepath, price, category_name) values (8, 'Hatity', 'Open-source context-sensitive matrices', 'purus.tiff', 45, 'Bebidas');
insert into food (id, name, description, imagepath, price, category_name) values (9, 'Otcom', 'Distributed directional access', 'condimentum.jpeg', 55, 'Bebidas');
insert into food (id, name, description, imagepath, price, category_name) values (10, 'Wrapsafe', 'Digitized content-based challenge', 'ac.gif', 18, 'Bebidas');
insert into food (id, name, description, imagepath, price, category_name) values (11, 'Tampflex', 'Re-contextualized responsive model', 'quam_suspendisse_potenti.jpeg', 79, 'Guarniciones');
insert into food (id, name, description, imagepath, price, category_name) values (12, 'Greenlam', 'Devolved value-added ability', 'nulla.gif', 51, 'Ensaladas');
insert into food (id, name, description, imagepath, price, category_name) values (13, 'Zathin', 'Up-sized next generation algorithm', 'aenean.png', 54, 'Aperitivos');
insert into food (id, name, description, imagepath, price, category_name) values (14, 'Job', 'Operative responsive support', 'platea.jpeg', 16, 'Bebidas');
insert into food (id, name, description, imagepath, price, category_name) values (15, 'Solarbreeze', 'Self-enabling cohesive synergy', 'nulla_pede_ullamcorper.tiff', 59, 'Bebidas');
insert into food (id, name, description, imagepath, price, category_name) values (16, 'Asoka', 'Cloned system-worthy infrastructure', 'lectus.jpeg', 19, 'Guarniciones');
insert into food (id, name, description, imagepath, price, category_name) values (17, 'Job', 'Balanced full-range frame', 'consectetuer.jpeg', 60, 'Guarniciones');
insert into food (id, name, description, imagepath, price, category_name) values (18, 'Zontrax', 'Switchable encompassing initiative', 'erat.tiff', 20, 'Ensaladas');
insert into food (id, name, description, imagepath, price, category_name) values (19, 'Holdlamis', 'Streamlined uniform approach', 'eu_pede.png', 88, 'Bebidas');
insert into food (id, name, description, imagepath, price, category_name) values (20, 'Bitchip', 'Synergized composite hardware', 'neque_aenean_auctor.jpeg', 52, 'Aperitivos');


/*
Órdenes
*/
insert into takeout (id, status, client_id, delivery_man_id) values (1, 'READY', 'client@naat.io', NULL);
insert into takeout (id, status, client_id, delivery_man_id) values (2, 'PREPARING', 'client@naat.io', NULL);
insert into takeout (id, status, client_id, delivery_man_id) values (3, 'DELIVERED', 'client@naat.io', 'delivery@naat.io');
insert into takeout (id, status, client_id, delivery_man_id) values (4, 'READY', 'client@naat.io', NULL);
insert into takeout (id, status, client_id, delivery_man_id) values (5, 'PREPARING', 'client@naat.io', NULL);
insert into takeout (id, status, client_id, delivery_man_id) values (6, 'PREPARING', 'client@naat.io', NULL);
insert into takeout (id, status, client_id, delivery_man_id) values (7, 'DELIVERING', 'client@naat.io', 'delivery@naat.io');
insert into takeout (id, status, client_id, delivery_man_id) values (8, 'DELIVERING', 'client@naat.io', 'delivery@naat.io');
insert into takeout (id, status, client_id, delivery_man_id) values (9, 'READY', 'client@naat.io', NULL);
insert into takeout (id, status, client_id, delivery_man_id) values (10, 'DELIVERED', 'client@naat.io', 'delivery@naat.io');

/*
* Agragando algunos platillos a las órdenes
*/
insert into takeout_contains_food (takeout_id, food_id) values (9, 3);
insert into takeout_contains_food (takeout_id, food_id) values (7, 16);
insert into takeout_contains_food (takeout_id, food_id) values (9, 1);
insert into takeout_contains_food (takeout_id, food_id) values (1, 16);
insert into takeout_contains_food (takeout_id, food_id) values (7, 10);
insert into takeout_contains_food (takeout_id, food_id) values (3, 10);
insert into takeout_contains_food (takeout_id, food_id) values (9, 15);
insert into takeout_contains_food (takeout_id, food_id) values (10, 11);
insert into takeout_contains_food (takeout_id, food_id) values (2, 10);
insert into takeout_contains_food (takeout_id, food_id) values (10, 9);
insert into takeout_contains_food (takeout_id, food_id) values (4, 7);
insert into takeout_contains_food (takeout_id, food_id) values (2, 7);
insert into takeout_contains_food (takeout_id, food_id) values (7, 1);
insert into takeout_contains_food (takeout_id, food_id) values (1, 8);
insert into takeout_contains_food (takeout_id, food_id) values (2, 13);
insert into takeout_contains_food (takeout_id, food_id) values (9, 16);
insert into takeout_contains_food (takeout_id, food_id) values (6, 6);
insert into takeout_contains_food (takeout_id, food_id) values (9, 10);
insert into takeout_contains_food (takeout_id, food_id) values (7, 6);
insert into takeout_contains_food (takeout_id, food_id) values (9, 19);
insert into takeout_contains_food (takeout_id, food_id) values (7, 17);
insert into takeout_contains_food (takeout_id, food_id) values (10, 11);
insert into takeout_contains_food (takeout_id, food_id) values (3, 19);
insert into takeout_contains_food (takeout_id, food_id) values (4, 14);
insert into takeout_contains_food (takeout_id, food_id) values (8, 7);
insert into takeout_contains_food (takeout_id, food_id) values (1, 17);
insert into takeout_contains_food (takeout_id, food_id) values (8, 1);
insert into takeout_contains_food (takeout_id, food_id) values (3, 10);
insert into takeout_contains_food (takeout_id, food_id) values (7, 20);
insert into takeout_contains_food (takeout_id, food_id) values (7, 6);