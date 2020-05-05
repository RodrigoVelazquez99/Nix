CREATE DATABASE  IF NOT EXISTS nix;
USE nix;

CREATE TABLE Usuario (
 	correo VARCHAR(50) NOT NULL UNIQUE, 	
    nombre VARCHAR(50) NOT NULL, 
    contraseña VARCHAR(8) NOT NULL,
    CONSTRAINT PK_Usuario PRIMARY KEY (correo)
);

CREATE TABLE Cliente (
	correo VARCHAR(50) NOT NULL UNIQUE, 	
	telefono VARCHAR(10) NOT NULL,
    calificacion INT(8) NULL,
    CONSTRAINT PK_Cliente PRIMARY KEY (correo),
    CONSTRAINT FK_Cliente FOREIGN KEY (correo) REFERENCES Usuario (correo)
);

CREATE TABLE Cliente_Direccion (
	direccion VARCHAR(50) NOT NULL,
	correo VARCHAR(50) NOT NULL, 	
    CONSTRAINT PK_Cliente_Direccion PRIMARY KEY (correo, direccion),
    CONSTRAINT FK_Cliente_Direccion FOREIGN KEY (correo) REFERENCES Cliente(correo)
);

CREATE TABLE Repartidor (
	correo VARCHAR(50) NOT NULL UNIQUE, 	
    CONSTRAINT PK_Repartidor PRIMARY KEY (correo), 
    CONSTRAINT FK_Repartidor FOREIGN KEY (correo) REFERENCES Usuario(correo)
);

CREATE TABLE Administrador (
	correo VARCHAR(50) NOT NULL UNIQUE, 	
    CONSTRAINT PK_Administrador PRIMARY KEY (correo), 
    CONSTRAINT FK_Administrador FOREIGN KEY (correo) REFERENCES Usuario(correo)	
);

CREATE TABLE Carrito (
	correo VARCHAR(50) NOT NULL UNIQUE, 	
	id_carrito INT NOT NULL AUTO_INCREMENT UNIQUE,
	CONSTRAINT PK_Carrito PRIMARY KEY (correo, id_carrito),	 
    CONSTRAINT FK_Carrito FOREIGN KEY (correo) REFERENCES Cliente(correo)
);

CREATE TABLE Categoria (
	nombre VARCHAR(15) NOT NULL UNIQUE, 
    CONSTRAINT PK_Categoria PRIMARY KEY (nombre)
);

CREATE TABLE Platillo (
	id_platillo INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(30) NOT NULL, 
    descripcion TEXT NOT NULL, 
    precio INT NOT NULL, 
    foto BLOB NULL,
    categoria VARCHAR(15) NOT NULL, 
    CONSTRAINT PK_Platillo PRIMARY KEY (id_platillo),
    CONSTRAINT FK_Platillo FOREIGN KEY (categoria) REFERENCES Categoria(nombre)
);

CREATE TABLE Agregar (
	correo VARCHAR(50) NOT NULL, 	
	id_carrito INT NOT NULL,
    id_platillo INT NOT NULL,
    CONSTRAINT PK_Agregar PRIMARY KEY (correo, id_carrito, id_platillo),
    CONSTRAINT FK_Agregar_Cliente FOREIGN KEY (correo) REFERENCES Carrito(correo),
    CONSTRAINT FK_Agregar_Carrito FOREIGN KEY (id_carrito) REFERENCES Carrito(id_carrito),
    CONSTRAINT FK_Agregar_Platillo FOREIGN KEY (id_platillo) REFERENCES Platillo(id_platillo)    
);

CREATE TABLE Orden (
	id_orden INT NOT NULL AUTO_INCREMENT, 
    fecha_entrega DATE NOT NULL, 
    estado VARCHAR(10) NOT NULL, 
    correo_repartidor VARCHAR(50) NOT NULL,  
    CONSTRAINT PK_Orden PRIMARY KEY (id_orden),
    CONSTRAINT FK_Orden_Repartidor FOREIGN KEY (correo_repartidor) REFERENCES Repartidor(correo)
);

CREATE TABLE Contener (
	id_platillo INT NOT NULL, 
    id_orden INT NOT NULL,
	CONSTRAINT PK_Contener PRIMARY KEY (id_platillo, id_orden),
    CONSTRAINT FK_Contener_Platillo FOREIGN KEY (id_platillo) REFERENCES Platillo(id_platillo),
    CONSTRAINT FK_Contener_Orden FOREIGN KEY (id_orden) REFERENCES Orden(id_orden)    
);
