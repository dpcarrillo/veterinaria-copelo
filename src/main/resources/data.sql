-- Dueños de ejemplo
INSERT INTO duenos (id, nombre, apellido, email, telefono, direccion) VALUES
(1, 'María', 'Gonzalez', 'maria.gonzalez@email.com', '555-0101', 'Calle Principal 123'),
(2, 'Carlos', 'Lopez', 'carlos.lopez@email.com', '555-0102', 'Avenida Central 456'),
(3, 'Ana', 'Martinez', 'ana.martinez@email.com', '555-0103', 'Plaza Mayor 789');

-- Mascotas de ejemplo
INSERT INTO mascotas (id, nombre, especie, raza, fecha_nacimiento, sexo, color, peso, dueno_id) VALUES
(1, 'Max', 'PERRO', 'Labrador', '2020-05-15', 'MACHO', 'Dorado', 25.5, 1),
(2, 'Luna', 'GATO', 'Siamés', '2021-02-20', 'HEMBRA', 'Blanco', 4.2, 1),
(3, 'Rocky', 'PERRO', 'Bulldog', '2019-11-10', 'MACHO', 'Blanco', 18.0, 2),
(4, 'Mimi', 'GATO', 'Persa', '2022-03-08', 'HEMBRA', 'Gris', 3.8, 3);

-- Citas de ejemplo
INSERT INTO citas (id, fecha_hora, tipo, motivo, estado, mascota_id) VALUES
(1, '2024-12-20 10:00:00', 'CONSULTA', 'Chequeo anual', 'PROGRAMADA', 1),
(2, '2024-12-21 11:30:00', 'VACUNACIÓN', 'Vacuna antirrábica', 'PROGRAMADA', 2),
(3, '2024-12-22 09:00:00', 'ESTÉTICA', 'Baño y corte de pelo', 'PROGRAMADA', 3);