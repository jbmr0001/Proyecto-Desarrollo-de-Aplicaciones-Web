insert into Usuario (email,contraseña,nombre,direccion,tarjeta,tipo) values ('admin@admin', 'admin', 'Pepe', 'Calle Encina 27', '111111111111', 'admin');
insert into Usuario (email,contraseña,nombre,direccion,tarjeta,tipo) values ('user@user', 'user', 'Pepe', 'Calle Encina 27', '111111111111', 'usuario');

insert into Producto (id,nombre,descripcion,precio) values (1,'El Ingenioso Hidalgo Don Quijote de la Mancha','Clásico de la literatura española',32);
insert into Producto (id,nombre,descripcion,precio) values (2,'The definitive guide to JSF in Java EE 8','The definitive guide to JSF in Java EE 8',42);
insert into Producto (id,nombre,descripcion,precio) values (3,'Naruto 1','Season 1',10);
insert into Producto (id,nombre,descripcion,precio) values (4,'Naruto 2','Season 1',10);
insert into Producto (id,nombre,descripcion,precio) values (5,'Naruto 3','Season 1',10);
insert into Producto (id,nombre,descripcion,precio) values (6,'Naruto 4','Season 1',10);
insert into Producto (id,nombre,descripcion,precio) values (7,'Naruto 5','Season 1',10);
insert into Producto (id,nombre,descripcion,precio) values (8,'Naruto 6','Season 1',10);


insert into Compra (id,email,idProducto) values (1,'user@user',30);
insert into Compra (id,email,idProducto) values (2,'user@user',31);
