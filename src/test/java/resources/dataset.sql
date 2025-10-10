INSERT INTO usuario (id, nombre, email, telefono, password, foto_url, fecha_nacimiento, fecha_creacion, rol)
VALUES
    (1, 'Amyi Lopez', 'amyi@example.com', '3001234567', 'pass123', 'https://img.com/amy.jpg', '1990-01-01', NOW(), 'HUESPED'),
    (2, 'Juan Pérez', 'juan@example.com', '3017654321', 'pass456', 'https://img.com/juan.jpg', '1985-02-15', NOW(), 'ANFITRION'),
    (3, 'Laura Mendoza', 'laura@example.com', NULL, 'pass789', NULL, '1998-07-10', NOW(), 'HUESPED');
