DROP TABLE IF EXISTS card;
CREATE TABLE IF NOT EXISTS card (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    user_id bigint(20) NOT NULL,
    parent_id bigint(20) DEFAULT NULL,
    title varchar(255) NOT NULL,
    description varchar(255) NOT NULL,
    del_flg tinyint(1) DEFAULT 0,
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
    PRIMARY KEY (id)
);