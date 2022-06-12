-- CREACIÓN DEL CENTRO
INSERT IGNORE INTO `hospital`.`centro` (`nombre`, `correo`, `cp`, `direccion`, `horario`, `movil`, `provincia`, `tel_fijo`)
	VALUES ( "Amikeha", "prueba.tfg.emailsender@gmail.com", 28007, "C/ Guadamolinos, 1", "9:00-18:00", "+34691882776", "Madrid", "917203377");

-- CREACIÓN DE LOS DEPARTAMENTOS
INSERT IGNORE INTO `hospital`.`departamento` (`nombre`, `centro_nombre`) VALUES ('Medicina', 'Amikeha');
INSERT IGNORE INTO `hospital`.`departamento` (`nombre`, `centro_nombre`) VALUES ('Comunicaciones', 'Amikeha');
INSERT IGNORE INTO `hospital`.`departamento` (`nombre`, `centro_nombre`) VALUES ('Administradores de inventario', 'Amikeha');
INSERT IGNORE INTO `hospital`.`departamento` (`nombre`, `centro_nombre`) VALUES ('Administradores de la base de datos', 'Amikeha');
INSERT IGNORE INTO `hospital`.`departamento` (`nombre`, `centro_nombre`) VALUES ('Administradores y directores de la empresa', 'Amikeha');


-- CREACIÓN DE LOS DISTINTOS ROLES
INSERT IGNORE INTO `hospital`.`perfil_usuario` (`rol`) VALUES ('doctor');
INSERT IGNORE INTO `hospital`.`perfil_usuario` (`rol`) VALUES ('operador');
INSERT IGNORE INTO `hospital`.`perfil_usuario` (`rol`) VALUES ('adminInventario');
INSERT IGNORE INTO `hospital`.`perfil_usuario` (`rol`) VALUES ('encargadoBasesDeDatos');
INSERT IGNORE INTO `hospital`.`perfil_usuario` (`rol`) VALUES ('director');
INSERT IGNORE INTO `hospital`.`perfil_usuario` (`rol`) VALUES ('estandar');
INSERT IGNORE INTO `hospital`.`perfil_usuario` (`rol`) VALUES ('creadorWeb');

-- CREACIÓN DE LAS ESPECIALIDADES DE LOS DOCTORES
INSERT IGNORE INTO `hospital`.`especialidad` (`nombre_especialidad`) VALUES ('Cardiología');
INSERT IGNORE INTO `hospital`.`especialidad` (`nombre_especialidad`) VALUES ('Oftalmología');
INSERT IGNORE INTO `hospital`.`especialidad` (`nombre_especialidad`) VALUES ('Radiodiagnóstico');
INSERT IGNORE INTO `hospital`.`especialidad` (`nombre_especialidad`) VALUES ('Neurofisiología');

-- CREACIÓN DE LAS DISTINTAS PRESTACIONES DE CADA ESPECIALIDAD
INSERT IGNORE INTO `hospital`.`prestaciones` (`prestacion`, `especialidad`) VALUES ('Electrocardiograma', 'Cardiología');
INSERT IGNORE INTO `hospital`.`prestaciones` (`prestacion`, `especialidad`) VALUES ('sala de curas', 'Cardiología');
INSERT IGNORE INTO `hospital`.`prestaciones` (`prestacion`, `especialidad`) VALUES ('consulta', 'Oftalmología');
INSERT IGNORE INTO `hospital`.`prestaciones` (`prestacion`, `especialidad`) VALUES ('Paquimetría corneal', 'Oftalmología');
INSERT IGNORE INTO `hospital`.`prestaciones` (`prestacion`, `especialidad`) VALUES ('RX Simple genérica', 'Radiodiagnóstico');
INSERT IGNORE INTO `hospital`.`prestaciones` (`prestacion`, `especialidad`) VALUES ('Potenciales evocados auditivos', 'Neurofisiología');
INSERT IGNORE INTO `hospital`.`prestaciones` (`prestacion`, `especialidad`) VALUES ('Electrocardiograma cuantificado', 'Neurofisiología');


-- CREACION DE USUARIOS PARA REALIZAR DIVERSAS OPERACIONES DE PRUEBA EN LA WEB

-- Usuarios clientes
INSERT IGNORE INTO `hospital`.`usuarios` (`correo_usuario`, `password`, `rol`) VALUES ('liam@gmail.com', "$2a$10$unBBFjLlxImffICgO4Gt4uYy8JQ7ZdR3SRNOIU8wmzvE0bI5fupHG", 'estandar');
INSERT IGNORE INTO `hospital`.`usuarios` (`correo_usuario`, `password`, `rol`) VALUES ('anoia@gmail.com', "$2a$10$unBBFjLlxImffICgO4Gt4uYy8JQ7ZdR3SRNOIU8wmzvE0bI5fupHG", 'estandar');
INSERT IGNORE INTO `hospital`.`usuarios` (`correo_usuario`, `password`, `rol`) VALUES ('aron@gmail.com', "$2a$10$unBBFjLlxImffICgO4Gt4uYy8JQ7ZdR3SRNOIU8wmzvE0bI5fupHG", 'estandar');
INSERT IGNORE INTO `hospital`.`usuarios` (`correo_usuario`, `password`, `rol`) VALUES ('martin@gmail.com', "$2a$10$unBBFjLlxImffICgO4Gt4uYy8JQ7ZdR3SRNOIU8wmzvE0bI5fupHG", 'estandar');
INSERT IGNORE INTO `hospital`.`usuarios` (`correo_usuario`, `password`, `rol`) VALUES ('pablo@gmail.com', "$2a$10$unBBFjLlxImffICgO4Gt4uYy8JQ7ZdR3SRNOIU8wmzvE0bI5fupHG", 'estandar');
INSERT IGNORE INTO `hospital`.`usuarios` (`correo_usuario`, `password`, `rol`) VALUES ('arsacio@gmail.com', "$2a$10$unBBFjLlxImffICgO4Gt4uYy8JQ7ZdR3SRNOIU8wmzvE0bI5fupHG", 'estandar');

-- Personal
INSERT IGNORE INTO `hospital`.`usuarios` (`correo_usuario`, `password`, `rol`) VALUES ('sergiotfg@gmail.com', '$2a$10$unBBFjLlxImffICgO4Gt4uYy8JQ7ZdR3SRNOIU8wmzvE0bI5fupHG', 'creadorWeb');
INSERT IGNORE INTO `hospital`.`usuarios` (`correo_usuario`, `password`, `rol`) VALUES ('juan@gmail.com', '$2a$10$unBBFjLlxImffICgO4Gt4uYy8JQ7ZdR3SRNOIU8wmzvE0bI5fupHG', 'doctor');
INSERT IGNORE INTO `hospital`.`usuarios` (`correo_usuario`, `password`, `rol`) VALUES ('valentina@gmail.com', '$2a$10$unBBFjLlxImffICgO4Gt4uYy8JQ7ZdR3SRNOIU8wmzvE0bI5fupHG', 'doctor');
INSERT IGNORE INTO `hospital`.`usuarios` (`correo_usuario`, `password`, `rol`) VALUES ('marco@gmail.com', '$2a$10$unBBFjLlxImffICgO4Gt4uYy8JQ7ZdR3SRNOIU8wmzvE0bI5fupHG', 'doctor');
INSERT IGNORE INTO `hospital`.`usuarios` (`correo_usuario`, `password`, `rol`) VALUES ('oscar@gmail.com', '$2a$10$unBBFjLlxImffICgO4Gt4uYy8JQ7ZdR3SRNOIU8wmzvE0bI5fupHG', 'doctor');
INSERT IGNORE INTO `hospital`.`usuarios` (`correo_usuario`, `password`, `rol`) VALUES ('sara@gmail.com', '$2a$10$unBBFjLlxImffICgO4Gt4uYy8JQ7ZdR3SRNOIU8wmzvE0bI5fupHG', 'operador');
INSERT IGNORE INTO `hospital`.`usuarios` (`correo_usuario`, `password`, `rol`) VALUES ('felipe@gmail.com', '$2a$10$unBBFjLlxImffICgO4Gt4uYy8JQ7ZdR3SRNOIU8wmzvE0bI5fupHG', 'encargadoBasesDeDatos');
INSERT IGNORE INTO `hospital`.`usuarios` (`correo_usuario`, `password`, `rol`) VALUES ('manuela@gmail.com', '$2a$10$unBBFjLlxImffICgO4Gt4uYy8JQ7ZdR3SRNOIU8wmzvE0bI5fupHG', 'adminInventario');
INSERT IGNORE INTO `hospital`.`usuarios` (`correo_usuario`, `password`, `rol`) VALUES ('antonio@gmail.com', '$2a$10$unBBFjLlxImffICgO4Gt4uYy8JQ7ZdR3SRNOIU8wmzvE0bI5fupHG', 'director');
INSERT IGNORE INTO `hospital`.`usuarios` (`correo_usuario`, `password`, `rol`) VALUES ('oscar@gmail.com', '$2a$10$unBBFjLlxImffICgO4Gt4uYy8JQ7ZdR3SRNOIU8wmzvE0bI5fupHG', 'doctor');
INSERT IGNORE INTO `hospital`.`usuarios` (`correo_usuario`, `password`, `rol`) VALUES ('amador@gmail.com', '$2a$10$unBBFjLlxImffICgO4Gt4uYy8JQ7ZdR3SRNOIU8wmzvE0bI5fupHG', 'doctor');
INSERT IGNORE INTO `hospital`.`usuarios` (`correo_usuario`, `password`, `rol`) VALUES ('judith@gmail.com', '$2a$10$unBBFjLlxImffICgO4Gt4uYy8JQ7ZdR3SRNOIU8wmzvE0bI5fupHG', 'doctor');
INSERT IGNORE INTO `hospital`.`usuarios` (`correo_usuario`, `password`, `rol`) VALUES ('rebeca@gmail.com', '$2a$10$unBBFjLlxImffICgO4Gt4uYy8JQ7ZdR3SRNOIU8wmzvE0bI5fupHG', 'doctor');




-- =============================================== CREACIÓN DEL PERSONAL QUE TENDRÁ EL HOSPITAL ===============================================

-- *************** CREADOR DE LA WEB ***************

INSERT IGNORE INTO `hospital`.`personal`
  (`dnincolegiado`, `apellidos`, `correo`, `cp`, `dia_disponible`, `direccion`, `disponible_desde`, `disponible_hasta`,  `localidad`, `movil`, `nombre`,
    `provincia`, `tel_fijo`, `departamento_nombre`, `especialidad`, `rol`, `correo_usuario`)

    VALUES ('02751827S', 'Gonzalo Fuentes', 'sergiotfg@gmail.com', 28007, 1, "C/ Felipe II", "09:00:00", "18:00:00", "Madrid", "+34671993772", "Sergio",  "Madrid", "914069273",

    "Administradores de inventario", null, "creadorWeb", 'sergiotfg@gmail.com');

-- *************** DIRECTOR DE LA EMPRESA ***************
INSERT IGNORE INTO `hospital`.`personal`
  (`dnincolegiado`, `apellidos`, `correo`, `cp`, `dia_disponible`, `direccion`, `disponible_desde`, `disponible_hasta`, `localidad`, `movil`, `nombre`,
    `provincia`, `tel_fijo`, `departamento_nombre`, `especialidad`, `rol`, `correo_usuario`)

    VALUES ('23765813M', 'Recio', 'antonio@gmail.com', '28007', 1, 'C/ Felipe II', '09:00:00', '18:00:00', 'Madrid', '+34610992881', 'Antonio', 'Madrid', '914069273',

    'Administradores y directores de la empresa', null, 'director','antonio@gmail.com');


-- *************** DOCTORES ***************

-- ESPECIALIDAD CARDIOLOGIA

INSERT IGNORE INTO `hospital`.`personal`
	(`dnincolegiado`, `apellidos`, `correo`, `cp`, `dia_disponible`, `direccion`, `disponible_desde`, `disponible_hasta`,  `localidad`, `movil`, `nombre`,
    `provincia`, `tel_fijo`, `departamento_nombre`, `especialidad`, `rol`, `correo_usuario`)

    VALUES ('282814947', 'Fernando Pérez del Corral', 'juan@gmail.com', 28007, 1, "C/ Felipe II", "09:00:00", "18:00:00", "Madrid", "+34691882991", "Juan",  "Madrid", "914069273",
    "Medicina", "Cardiología", "doctor", 'juan@gmail.com');

INSERT IGNORE INTO `hospital`.`personal`
	(`dnincolegiado`, `apellidos`, `correo`, `cp`, `dia_disponible`, `direccion`, `disponible_desde`, `disponible_hasta`,   `localidad`, `movil`, `nombre`,
	`provincia`, `tel_fijo`, `departamento_nombre`, `especialidad`, `rol`, `correo_usuario`)

	VALUES ('281108563', 'Ruíz Ortega', 'amancio@gmail.com', 28007, 1, "C/ Felipe II", "09:00:00", "18:00:00", "Madrid", "+34610281726", "Amancio",  "Madrid", "914069273",
	"Medicina", "Cardiología", "doctor", 'amancio@gmail.com');

-- ESPECIALIDAD OFTALMOLOGÍA
INSERT IGNORE INTO `hospital`.`personal`
	(`dnincolegiado`, `apellidos`, `correo`, `cp`, `dia_disponible`, `direccion`, `disponible_desde`, `disponible_hasta`,   `localidad`, `movil`, `nombre`,
    `provincia`, `tel_fijo`, `departamento_nombre`, `especialidad`, `rol`, `correo_usuario`)

    VALUES ('282820687', 'Laverde de la Rosa', 'valentina@gmail.com', 28007, 2, "C/ Felipe II", "09:00:00", "18:00:00", "Madrid", "+34601992881", "Valentina",  "Madrid", "914069273",
    "Medicina", "Oftalmología", "doctor", 'valentina@gmail.com');

-- ESPECIALIDAD NEUROFISIOLOGÍA
INSERT IGNORE INTO `hospital`.`personal`
	(`dnincolegiado`, `apellidos`, `correo`, `cp`, `dia_disponible`, `direccion`, `disponible_desde`, `disponible_hasta`,   `localidad`, `movil`, `nombre`,
    `provincia`, `tel_fijo`, `departamento_nombre`, `especialidad`, `rol`, `correo_usuario`)

    VALUES ('281903254', 'de todos los Santos', 'marco@gmail.com', 28007, 3, "C/ Felipe II", "09:00:00", "18:00:00", "Madrid", "+34691882776", "Marco Antonio",  "Madrid", "914069273",
    "Medicina", "Neurofisiología", "doctor", 'marco@gmail.com');

-- ESPECIALIDAD RADIODIAGNÓSTICO
INSERT IGNORE INTO `hospital`.`personal`
(`dnincolegiado`, `apellidos`, `correo`, `cp`, `dia_disponible`, `direccion`, `disponible_desde`, `disponible_hasta`,   `localidad`, `movil`, `nombre`,
`provincia`, `tel_fijo`, `departamento_nombre`, `especialidad`, `rol`, `correo_usuario`)

VALUES ('282810764', 'de la Renta', 'oscar@gmail.com', 28007, 4, "C/ Felipe II", "09:00:00", "18:00:00", "Madrid", "+34610229881", "Óscar",  "Madrid", "914069273",
"Medicina", "Radiodiagnóstico", "doctor", 'oscar@gmail.com');

INSERT IGNORE INTO `hospital`.`personal`
(`dnincolegiado`, `apellidos`, `correo`, `cp`, `dia_disponible`, `direccion`, `disponible_desde`, `disponible_hasta`,   `localidad`, `movil`, `nombre`,
`provincia`, `tel_fijo`, `departamento_nombre`, `especialidad`, `rol`, `correo_usuario`)

VALUES ('281305140', 'Rivas', 'amador@gmail.com', 28007, 5, "C/ Felipe II", "09:00:00", "18:00:00", "Madrid", "+34610229881", "Amador",  "Madrid", "914069273",
"Medicina", "Radiodiagnóstico", "doctor", 'amador@gmail.com');

INSERT IGNORE INTO `hospital`.`personal`
(`dnincolegiado`, `apellidos`, `correo`, `cp`, `dia_disponible`, `direccion`, `disponible_desde`, `disponible_hasta`,   `localidad`, `movil`, `nombre`,
`provincia`, `tel_fijo`, `departamento_nombre`, `especialidad`, `rol`, `correo_usuario`)

VALUES ('282862143', 'Becker', 'judith@gmail.com', 28007, 6, "C/ Felipe II", "09:00:00", "18:00:00", "Madrid", "+34610229881", "Judith",  "Madrid", "914069273",
"Medicina", "Radiodiagnóstico", "doctor", 'judith@gmail.com');

INSERT IGNORE INTO `hospital`.`personal`
(`dnincolegiado`, `apellidos`, `correo`, `cp`, `dia_disponible`, `direccion`, `disponible_desde`, `disponible_hasta`,   `localidad`, `movil`, `nombre`,
`provincia`, `tel_fijo`, `departamento_nombre`, `especialidad`, `rol`, `correo_usuario`)

VALUES ('282839613', 'Ortiz', 'rebeca@gmail.com', 28007, 7, "C/ Felipe II", "09:00:00", "18:00:00", "Madrid", "+34610229881", "Rebeca",  "Madrid", "914069273",
"Medicina", "Radiodiagnóstico", "doctor", 'rebeca@gmail.com');

-- *************** OPERADORES ***************
INSERT IGNORE INTO `hospital`.`personal`
	(`dnincolegiado`, `apellidos`, `correo`, `cp`, `dia_disponible`, `direccion`, `disponible_desde`, `disponible_hasta`,   `localidad`, `movil`, `nombre`,
    `provincia`, `tel_fijo`, `departamento_nombre`, `especialidad`, `rol`, `correo_usuario`)

    VALUES ('54476197F', 'Sánchez del Pinar', 'sara@gmail.com', 28007, 7, "C/ Felipe II", "09:00:00", "18:00:00", "Madrid", "+34600998118", "Sara Teresa",  "Madrid", "914069273",
    "Comunicaciones", null, "operador", 'sara@gmail.com');

-- *************** BASE DE DATOS ***************
INSERT IGNORE INTO `hospital`.`personal`
	(`dnincolegiado`, `apellidos`, `correo`, `cp`, `dia_disponible`, `direccion`, `disponible_desde`, `disponible_hasta`,   `localidad`, `movil`, `nombre`,
    `provincia`, `tel_fijo`, `departamento_nombre`, `especialidad`, `rol`, `correo_usuario`)

    VALUES ('42938213L', 'de los Ríos Acosta', 'prueba.tfg.emailsender@gmail.com', 28007, 7, "C/ Felipe II", "09:00:00", "18:00:00", "Madrid", "+34691882771", "Felipe Elías",  "Madrid", "914069273",
    "Administradores de la base de datos", null, "encargadoBasesDeDatos", 'felipe@gmail.com');

-- *************** INVENTARIO ***************
INSERT IGNORE INTO `hospital`.`personal`
	(`dnincolegiado`, `apellidos`, `correo`, `cp`, `dia_disponible`, `direccion`, `disponible_desde`, `disponible_hasta`,   `localidad`, `movil`, `nombre`,
    `provincia`, `tel_fijo`, `departamento_nombre`, `especialidad`, `rol`, `correo_usuario`)


    VALUES ('53838172W', 'Matamoros', 'manuela@gmail.com', 28007, 7, "C/ Felipe II", "2022-01-01 09:00:00", "", "Madrid", "+34619228771", "Manuela",  "Madrid", "914069273",
    "Medicina", null, "adminInventario", 'manuela@gmail.com');


-- CREACIÓN DE PACIENTES PARA SIMULAR COMO SERÍAN LAS INTERVENCIONES

INSERT IGNORE INTO `hospital`.`cliente`
(`dni`, `apellidos`, `correo`, `cp`, `direccion`, `localidad`, `movil`, `nombre`,  `provincia`, `tel_fijo`, `rol`)
VALUES ('58437209M', 'Malfred', 'liam@gmail.com', 28007, 'Avenida Reyes Católicos', 'Madrid', '+34819220192', 'Líam',  'Madrid', '9182918291', 'estandar');

INSERT IGNORE INTO `hospital`.`cliente`
(`dni`, `apellidos`, `correo`, `cp`, `direccion`, `localidad`, `movil`, `nombre`,  `provincia`, `tel_fijo`, `rol`)
VALUES ('99882456B', 'López', 'anoia@gmail.com', 28007, 'CALLE BERENISA 29', 'Madrid', '+346001001001', 'Anoia',  'Madrid', '915102981', 'estandar');

INSERT IGNORE INTO `hospital`.`cliente`
(`dni`, `apellidos`, `correo`, `cp`, `direccion`, `localidad`, `movil`, `nombre`,  `provincia`, `tel_fijo`, `rol`)
VALUES ('29053612N', 'Sánchez', 'aron@gmail.com', 28009, 'CALLE MAIQUEZ 21', 'Madrid', '+34619228776', 'Aron',  'Madrid', '918827722', 'estandar');

INSERT IGNORE INTO `hospital`.`cliente`
(`dni`, `apellidos`, `correo`, `cp`, `direccion`, `localidad`, `movil`, `nombre`,  `provincia`, `tel_fijo`, `rol`)
VALUES ('69883430T', 'López', 'martin@gmail.com', 28017, 'CALLE POETA BLAS DE OTERO 12', 'Madrid', '+34617226552', 'Martín',  'Madrid', '918827719', 'estandar');

INSERT IGNORE INTO `hospital`.`cliente`
(`dni`, `apellidos`, `correo`, `cp`, `direccion`, `localidad`, `movil`, `nombre`,  `provincia`, `tel_fijo`, `rol`)
VALUES ('21201768T', 'Cerdanya', 'pablo@gmail.com', 28009, 'PASEO FERNAN NUÑEZ 10', 'Madrid', '+34610998765', 'Pablo',  'Madrid', '918029988', 'estandar');

INSERT IGNORE INTO `hospital`.`cliente`
(`dni`, `apellidos`, `correo`, `cp`, `direccion`, `localidad`, `movil`, `nombre`,  `provincia`, `tel_fijo`, `rol`)
VALUES ('46043677T', 'Romero', 'arsacio@gmail.com', 28011, 'CALLE HEXAGONOS 1', 'Madrid', '+34671992881','Arsacio',  'Madrid', '917281111', 'estandar');


-- DATOS DE LAS INTERVENCIONES
-- -> el tipo de intervencion esta relacionado con la especialidad del doctor
INSERT IGNORE INTO `hospital`.`intervenciones` (`fecha`,`descripcion`,`precio`) VALUES ('2022-02-18 10:00:00','Intervencón a corazón abierto',7278.10);
INSERT IGNORE INTO `hospital`.`intervenciones` (`fecha`,`descripcion`,`precio`) VALUES ('2022-02-19 10:00:00','Revisión de las pupilas', 31.37);
INSERT IGNORE INTO `hospital`.`intervenciones` (`fecha`,`descripcion`,`precio`) VALUES ('2022-02-20 10:00:00','Evaluación del sistema nervioso central',178.99);
INSERT IGNORE INTO `hospital`.`intervenciones` (`fecha`,`descripcion`,`precio`) VALUES ('2022-02-21 10:00:00','Radiografía de Tórax', 20.87);

-- RELACION DEL DOCTOR CON LA INTERVENCIÓN

INSERT IGNORE INTO `hospital`.`cliente_interv_doctor` (`intervenciones_fecha`, `personal_dnincolegiado`, `cliente_dni`) VALUES ('2022-02-18 10:00:00', '282814947', '58437209M');
INSERT IGNORE INTO `hospital`.`cliente_interv_doctor` (`intervenciones_fecha`, `personal_dnincolegiado`, `cliente_dni`) VALUES ('2022-02-19 10:00:00', '282820687', '99882456B');
INSERT IGNORE INTO `hospital`.`cliente_interv_doctor` (`intervenciones_fecha`, `personal_dnincolegiado`, `cliente_dni`) VALUES ('2022-02-20 10:00:00', '281903254', '29053612N');
INSERT IGNORE INTO `hospital`.`cliente_interv_doctor` (`intervenciones_fecha`, `personal_dnincolegiado`, `cliente_dni`) VALUES ('2022-02-20 10:00:00', '281903254', '69883430T');
INSERT IGNORE INTO `hospital`.`cliente_interv_doctor` (`intervenciones_fecha`, `personal_dnincolegiado`, `cliente_dni`) VALUES ('2022-02-21 10:00:00', '282810764', '21201768T');

-- SIMULACIÓN DE FACTURAS PENDIENTES DE PAGO Y OTRAS QUE NO
-- -> puede ocurrir que el mismo cliente tenga dos facturas en dos dias distintos

-- * Mismo paciente en distintos dias
INSERT IGNORE INTO `hospital`.`facturas` (`fecha`, `pagada`, `dni`, `fecha_intervencion`, `dnincolegiado`) VALUES ('2022-02-18 17:00:00', 0, '58437209M', '2022-02-18 10:00:00', '282814947');
INSERT IGNORE INTO `hospital`.`facturas` (`fecha`, `pagada`, `dni`, `fecha_intervencion`, `dnincolegiado`) VALUES ('2022-02-19 17:00:00', 1, '58437209M', '2022-02-19 10:00:00', '282814947');

-- CREACION DE LAS HORAS DISPONIBLES
INSERT IGNORE INTO `hospital`.`horario` (`hora`) VALUES ("09:00:00");
INSERT IGNORE INTO `hospital`.`horario` (`hora`) VALUES ("10:00:00");
INSERT IGNORE INTO `hospital`.`horario` (`hora`) VALUES ("11:00:00");
INSERT IGNORE INTO `hospital`.`horario` (`hora`) VALUES ("12:00:00");
INSERT IGNORE INTO `hospital`.`horario` (`hora`) VALUES ("13:00:00");
INSERT IGNORE INTO `hospital`.`horario` (`hora`) VALUES ("15:00:00");
INSERT IGNORE INTO `hospital`.`horario` (`hora`) VALUES ("16:00:00");
INSERT IGNORE INTO `hospital`.`horario` (`hora`) VALUES ("17:00:00");
INSERT IGNORE INTO `hospital`.`horario` (`hora`) VALUES ("18:00:00");


-- RELACION DE LOS DOCTORES CON LAS CITAS
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("09:00:00", "282810764");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("10:00:00", "282810764");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("11:00:00", "282810764");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("12:00:00", "282810764");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("13:00:00", "282810764");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("15:00:00", "282810764");
-- (1 h para comer)
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("16:00:00", "282810764");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("17:00:00", "282810764");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("18:00:00", "282810764");

-- ---------------------------------------------------------------------------------"--------"--"---------"--
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("09:00:00", "282820687");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("10:00:00", "282820687");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("11:00:00", "282820687");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("12:00:00", "282820687");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("13:00:00", "282820687");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("15:00:00", "282820687");
-- (1 h para comer)
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("16:00:00", "282820687");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("17:00:00", "282820687");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("18:00:00", "282820687");

-- ---------------------------------------------------------------------------------"--------"--"---------"--
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("09:00:00", "281903254");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("10:00:00", "281903254");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("11:00:00", "281903254");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("12:00:00", "281903254");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("13:00:00", "281903254");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("15:00:00", "281903254");
-- (1 h para comer)
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("16:00:00", "281903254");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("17:00:00", "281903254");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("18:00:00", "281903254");

-- ---------------------------------------------------------------------------------"--------"--"---------"--
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("09:00:00", "282814947");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("10:00:00", "282814947");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("11:00:00", "282814947");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("12:00:00", "282814947");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("13:00:00", "282814947");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("15:00:00", "282814947");
-- (1 h para comer)
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("16:00:00", "282814947");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("17:00:00", "282814947");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("18:00:00", "282814947");

-- ---------------------------------------------------------------------------------"--------"--"---------"--
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("09:00:00", "282820687");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("10:00:00", "282820687");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("11:00:00", "282820687");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("12:00:00", "282820687");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("13:00:00", "282820687");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("15:00:00", "282820687");
-- (1 h para comer)
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("16:00:00", "282820687");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("17:00:00", "282820687");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("18:00:00", "282820687");

-- ---------------------------------------------------------------------------------"--------"--"---------"--
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("09:00:00", "281903254");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("10:00:00", "281903254");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("11:00:00", "281903254");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("12:00:00", "281903254");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("13:00:00", "281903254");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("15:00:00", "281903254");
-- (1 h para comer)
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("16:00:00", "281903254");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("17:00:00", "281903254");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("18:00:00", "281903254");

-- ---------------------------------------------------------------------------------"--------"--"---------"--
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("09:00:00", "281305140");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("10:00:00", "281305140");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("11:00:00", "281305140");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("12:00:00", "281305140");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("13:00:00", "281305140");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("15:00:00", "281305140");
-- (1 h para comer)
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("16:00:00", "281305140");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("17:00:00", "281305140");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("18:00:00", "281305140");

-- ---------------------------------------------------------------------------------"--------"--"---------"--
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("09:00:00", "281108563");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("10:00:00", "281108563");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("11:00:00", "281108563");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("12:00:00", "281108563");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("13:00:00", "281108563");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("15:00:00", "281108563");
-- (1 h para comer)
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("16:00:00", "281108563");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("17:00:00", "281108563");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("18:00:00", "281108563");

-- ---------------------------------------------------------------------------------"--------"--"---------"--
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("09:00:00", "282862143");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("10:00:00", "282862143");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("11:00:00", "282862143");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("12:00:00", "282862143");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("13:00:00", "282862143");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("15:00:00", "282862143");
-- (1 h para comer)
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("16:00:00", "282862143");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("17:00:00", "282862143");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("18:00:00", "282862143");

-- ---------------------------------------------------------------------------------"--------"--"---------"--
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("09:00:00", "282839613");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("10:00:00", "282839613");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("11:00:00", "282839613");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("12:00:00", "282839613");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("13:00:00", "282839613");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("15:00:00", "282839613");
-- (1 h para comer)
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("16:00:00", "282839613");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("17:00:00", "282839613");
INSERT IGNORE INTO `hospital`.`citas_disponibles` (`hora`, `dnincolegiado`) VALUES ("18:00:00", "282839613");

-- CREAR LOS ARTÍCULOS QUE SE UTILIZARÁN

INSERT IGNORE INTO `hospital`.`inventario` (`descripcion`, `fabricante`, `fecha_compra`, `modelo`, `precio_articulo`, `tipo_articulo`, `unidades`, `nombre_especialidad`)
  VALUES ('con las funciones de autoanálisis y autodiagnóstico para parámetros de ECG de rutina, proporciona resultados de medición y conclusión de autodiagnóstico',
  'Mobiclinic', '2017-07-23', 'ECG1200G ', 33.80, 'Electrocardiógrafo', 70, 'Cardiología');

INSERT IGNORE INTO `hospital`.`inventario` (`descripcion`, `fabricante`, `fecha_compra`, `modelo`, `precio_articulo`, `tipo_articulo`, `unidades`, `nombre_especialidad`)
VALUES ('Solución oftalmológica liposomada para los ojos secos. Restaura el micro-ambiente de la superficie ocular alterado por una elevada tendencia a la evaporación.',
'Navilipo', '2019-11-23', '71829-RSOX ', 9.67, 'Gotas', 37,'Oftalmología');

INSERT IGNORE INTO `hospital`.`inventario` (`descripcion`, `fabricante`, `fecha_compra`, `modelo`, `precio_articulo`, `tipo_articulo`, `unidades`, `nombre_especialidad`)
VALUES ('Monitor de signos vitales para la monitorización continuada de la Presión Arterial No Invasiva (PANI–NIBP), Frecuencia de Pulso (PR) y Oximetría (SpO2).',
'PC-900 Plus', '2010-02-11', 'PPM ', 765.67, 'Monitor Multiparárametros', 15,'Cardiología');

INSERT IGNORE INTO `hospital`.`inventario` (`descripcion`, `fabricante`, `fecha_compra`, `modelo`, `precio_articulo`, `tipo_articulo`, `unidades`, `nombre_especialidad`)
VALUES ('Vibracell de VITAE es un complemento nutricional natural a base de frutas y verduras que complementa la dieta, proporciona energía y mejora el rendimiento físico y mental.',
'Vibracell ', '2014-11-11', 'Vitae ', 39.59, 'Complemento nutricional', 67,'Neurofisiología');





