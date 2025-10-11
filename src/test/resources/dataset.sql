INSERT INTO usuario (id, nombre, email, telefono, password, foto_url, fecha_nacimiento, fecha_creacion, rol)
VALUES
    (1, 'Amyi Lopez', 'amyi@example.com', '3001234567', '$2a$10$W89d1M9Ubntp8DOs/D8yoeghxj.jl/gfIPCG5XehNyISO6JvOtMsq', 'https://img.com/amy.jpg', '1990-01-01', NOW(), 'HUESPED'),
    (2, 'Juan Pérez', 'juan@example.com', '3017654321', '$2a$10$iNgtMHF3ddXmoqlbv2wuU.Y9Z4Tog35LjV1iAmh14EryXh6woA61K', 'https://img.com/juan.jpg', '1985-02-15', NOW(), 'ANFITRION'),
    (3, 'Laura Mendoza', 'laura@example.com', NULL, '$2a$10$9Bw8ULxiCzX7BUzjvDAz3.vCNShoZrwH2SUypd8J4Gn0sFJ/cJkRK', NULL, '1998-07-10', NOW(), 'HUESPED');

INSERT INTO perfil_anfitrion (id, descripcion, usuario_id)
VALUES (2, 'Anfitrion experimentado en cabanias rurales', 2);

-- Crear alojamiento con ubicación y anfitrión
INSERT INTO alojamientos (
    id,
    titulo,
    descripcion,
    precio_noche,
    capacidad_max,
    fecha_creacion,
    calificacion_promedio,
    estado,
    anfitrion_id,
    direccion,
    ciudad,
    pais,
    latitud,
    longitud
) VALUES (
             1,
             'Cabania en las montanias',
             'Hermosa cabania rodeada de naturaleza, ideal para desconectarse del ruido.',
             250000,
             4,
             NOW(),
             0.0,
             'ACTIVO',
             2, -- ID del anfitrión (Juan Pérez)
             'Vereda El Retiro, km 5',
             'Manizales',
             'Colombia',
             5.048,
             -75.517
         );
INSERT INTO reservas (
    id,
    fecha_check_in,
    fecha_check_out,
    cantidad_huespedes,
    total,
    fecha_creacion,
    estado,
    huesped_id,
    alojamiento_id
) VALUES (
             1,
             '2025-10-15 15:00:00',
             '2025-10-20 12:00:00',
             2,
             500.0,
             NOW(),
             'PENDIENTE',
             1, -- ID del huésped (Amyi Lopez)
             1 -- ID del alojamiento (Cabania en las montanias)
         );

-- Reserva finalizada
INSERT INTO reservas (
    id, fecha_check_in, fecha_check_out, cantidad_huespedes, total, fecha_creacion, estado, huesped_id, alojamiento_id
) VALUES
    (2, '2025-09-01 15:00:00', '2025-09-05 12:00:00', 2, 1000000.0, NOW(), 'COMPLETADA', 1, 1);

INSERT INTO reservas (
    id, fecha_check_in, fecha_check_out, cantidad_huespedes, total, fecha_creacion, estado, huesped_id, alojamiento_id
) VALUES
      (5, '2025-07-01 15:00:00', '2025-07-05 12:00:00', 1, 600000.0, NOW(), 'COMPLETADA', 1, 1),
      (6, '2025-06-10 15:00:00', '2025-06-15 12:00:00', 2, 700000.0, NOW(), 'COMPLETADA', 3, 1);

-- Reserva aún activa
INSERT INTO reservas (
    id, fecha_check_in, fecha_check_out, cantidad_huespedes, total, fecha_creacion, estado, huesped_id, alojamiento_id
) VALUES
    (3, '2025-10-15 15:00:00', '2025-10-20 12:00:00', 2, 900000.0, NOW(), 'PENDIENTE', 1, 1);

-- Reserva finalizada y ya comentada
INSERT INTO reservas (
    id, fecha_check_in, fecha_check_out, cantidad_huespedes, total, fecha_creacion, estado, huesped_id, alojamiento_id
) VALUES
    (4, '2025-08-10 15:00:00', '2025-08-15 12:00:00', 2, 800000.0, NOW(), 'COMPLETADA', 3, 1);

-- Comentario asociado a la reserva 3 (ya comentada)
INSERT INTO comentarios (id, texto, calificacion, fecha, alojamiento_id, reserva_id)
VALUES
    (1, 'Excelente lugar para descansar', 5, '2025-08-16 10:00:00', 1, 4);-- Comentario asociado a la reserva 4 (ya comentada)

INSERT INTO comentarios (id, texto, calificacion, fecha, alojamiento_id, reserva_id)
VALUES
    (3, 'Lugar acogedor, volvería de nuevo', 5, '2025-07-06 11:00:00', 1, 5),
    (4, 'Todo perfecto, recomiendo', 5, '2025-06-16 12:00:00', 1, 6);