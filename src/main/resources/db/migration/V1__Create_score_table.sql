CREATE TABLE score (
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  game VARCHAR(30) NOT NULL,
  score INT(11) NOT NULL,
  timestamp DATETIME NOT NULL,
  PRIMARY KEY(id),
  UNIQUE (name, game)
);