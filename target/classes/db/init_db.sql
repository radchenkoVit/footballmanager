DROP TABLE if EXISTS players;
DROP TABLE if EXISTS teams;

create table teams (
  id              BIGINT,
  name            VARCHAR(255) NOT NULL,
  captan_id       BIGINT,
  CONSTRAINT teams_PK PRIMARY KEY (id)
);

create table players (
  id              BIGINT,
  first_name      VARCHAR(255) NOT NULL,
  last_name       VARCHAR(255) NOT NULL,
  position        VARCHAR(255) NOT NULL,
  team_id         BIGINT,
  CONSTRAINT players_PK PRIMARY KEY (id),
  CONSTRAINT players_FK FOREIGN KEY (team_id) REFERENCES teams
);

INSERT INTO teams values (1, 'first_team', null);
INSERT INTO teams values (2, 'second_team', null);

INSERT INTO players values (1, 'player_one_name', 'player_one_last_name', 'p1', null);
INSERT INTO players values (2, 'player_two_name', 'player_two_last_name', 'p2', null);
INSERT INTO players values (3, 'player_three_name', 'player_three_last_name', 'p3', null);
