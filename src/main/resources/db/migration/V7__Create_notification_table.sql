CREATE TABLE notification
(
    id int AUTO_INCREMENT PRIMARY KEY,
    natifier bigint NOT NULL,
    receiver bigint NOT NULL,
    outerId bigint NOT NULL,
    type int NOT NULL,
    gmt_create bigint NOT NULL,
    status int DEFAULT 0 NOT NULL
);