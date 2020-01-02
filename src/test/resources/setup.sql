DROP TABLE IF EXISTS card;
CREATE TABLE IF NOT EXISTS card (
    id bigint(20) NOT NULL,
    user_id bigint(20) NOT NULL,
    pairent_id bigint(20) DEFAULT NULL,
    title varchar(255) NOT NULL,
    description varchar(255) NOT NULL,
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
    PRIMARY KEY (id)
);