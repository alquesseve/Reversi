CREATE TABLE `users` (
	`Id`	INTEGER PRIMARY KEY auto_increment,
	`username`	VARCHAR(255),
	`password`	VARCHAR(255),
	`attenteJeu`	INTEGER
);

CREATE TABLE `games` (
	`Id`	INTEGER PRIMARY KEY auto_increment,
	`IdPlayer1`	INTEGER,
	`IdPlayer2`	INTEGER,
	`IdPlaying`	INTEGER
);

CREATE TABLE `grid` (
	`gameid`	INTEGER,
	`cell_x`	INTEGER,
	`cell_y`	INTEGER,
	`value`	INTEGER
);