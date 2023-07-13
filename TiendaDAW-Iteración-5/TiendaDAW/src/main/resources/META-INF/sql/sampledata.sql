insert into Usuario (email,contraseña,nombre,direccion,tarjeta,admin) values ('admin@admin', 'admin', 'Pepe', 'Calle Encina 27', '111111111111', true);
insert into Usuario (email,contraseña,nombre,direccion,tarjeta,admin) values ('user@user', 'user', 'Pepe', 'Calle Encina 27', '111111111111', false);
insert into Usuario (email,contraseña,nombre,direccion,tarjeta,admin) values ('admin@admin1', 'admin', 'Pepe', 'Calle Encina 27', '111111111111', true);

insert into Producto (id,nombre,descripcion,precio,ruta,stock) values (1,'Camiseta Manchester United Temporada 2022/2024','Su diseño de alto rendimiento con tecnología de ventilación HEAT.RDY te permite emplearte a fondo en el terreno de juego.',90,'./resources/images/1.jpg',1);
insert into Producto (id,nombre,descripcion,precio,ruta,stock) values (2,'Camiseta Real Madrid Temporada 2022/2023','Su diseño de alto rendimiento con tecnología de ventilación HEAT.RDY te permite emplearte a fondo en el terreno de juego.',60,'./resources/images/2.jpg',1);
insert into Producto (id,nombre,descripcion,precio,ruta,stock) values (3,'Camiseta FC Barcelona Temporada 2022/2023','Su diseño de alto rendimiento con tecnología de ventilación HEAT.RDY te permite emplearte a fondo en el terreno de juego.',55,'./resources/images/3.jpg',3);
insert into Producto (id,nombre,descripcion,precio,ruta,stock) values (4,'Manchester City Temporada 2022/2023','Su diseño de alto rendimiento con tecnología de ventilación HEAT.RDY te permite emplearte a fondo en el terreno de juego.',60,'./resources/images/4.jpg',1);
insert into Producto (id,nombre,descripcion,precio,ruta,stock) values (5,'Atlético de Madrid Temporada 2022/2023','Su diseño de alto rendimiento con tecnología de ventilación HEAT.RDY te permite emplearte a fondo en el terreno de juego.',55,'./resources/images/5.jpg',2);
insert into Producto (id,nombre,descripcion,precio,ruta,stock) values (6,'Selección Argentina Temporada 2022/2023','Su diseño de alto rendimiento con tecnología de ventilación HEAT.RDY te permite emplearte a fondo en el terreno de juego.',40,'./resources/images/6.jpg',1);
insert into Producto (id,nombre,descripcion,precio,ruta,stock) values (7,'AC Milan Temporada 2022/2023','Su diseño de alto rendimiento con tecnología de ventilación HEAT.RDY te permite emplearte a fondo en el terreno de juego.',68,'./resources/images/7.jpg',2);
insert into Producto (id,nombre,descripcion,precio,ruta,stock) values (8,'Liverpool FC Temporada 2022/2023','Su diseño de alto rendimiento con tecnología de ventilación HEAT.RDY te permite emplearte a fondo en el terreno de juego.',80,'./resources/images/8.jpg',1);


insert into Compra (id,email,idProducto) values (1,'user@user',1);
insert into Compra (id,email,idProducto) values (2,'admin@admin',2);
