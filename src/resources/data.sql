-- Competitions
INSERT INTO competitions (id, name, sport, start_date, end_date, tracks_per_day) VALUES 
(1, 'Torneo de Verano 2024', 'Fútbol', '2024-07-01', '2024-07-10', 4),
(2, 'Liga Invierno 2024', 'Baloncesto', '2024-08-01', '2024-08-15', 3);

-- Teams para Competition 1
INSERT INTO teams (id, name, competition_id) VALUES 
(1, 'Los Leones', 1),
(2, 'Águilas FC', 1),
(3, 'Tigres del Norte', 1),
(4, 'Dragones Rojo', 1),
(5, 'Halcones Azules', 1),
(6, 'Osos Polares', 1);

-- Teams para Competition 2
INSERT INTO teams (id, name, competition_id) VALUES 
(7, 'Celtas Basket', 2),
(8, 'Lakers del Sur', 2),
(9, 'Titans Ball', 2),
(10, 'Warriors Norte', 2);

-- Matches para Competition 1
INSERT INTO matches (id, competition_id, team_a_id, team_b_id, date, track_number) VALUES 
(1, 1, 1, 2, '2024-07-01 10:00:00', 1),
(2, 1, 3, 4, '2024-07-01 11:30:00', 2),
(3, 1, 5, 6, '2024-07-01 10:00:00', 3);

-- Matches para Competition 2
INSERT INTO matches (id, competition_id, team_a_id, team_b_id, date, track_number) VALUES 
(4, 2, 7, 8, '2024-08-01 09:00:00', 1),
(5, 2, 9, 10, '2024-08-01 10:30:00', 2);