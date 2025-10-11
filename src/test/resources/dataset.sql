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
             2, -- id del anfitrión existente en tu tabla perfil_anfitrion
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
             '2025-10-15 15:00:00', -- fechaCheckIn
             '2025-10-20 12:00:00', -- fechaCheckOut
             2,                     -- cantidadHuespedes
             500.0,                 -- total
             NOW(),                 -- fechaCreacion
             'PENDIENTE',            -- estado (según tu enum)
             1,                     -- huesped_id (debe existir en la tabla usuarios)
             1                      -- alojamiento_id (debe existir en la tabla alojamientos)
         );


