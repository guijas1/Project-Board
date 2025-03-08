--Liquibase formatted SQL
--changeset junior:202503081927
--comment: cards table create

CREATE TABLE CARDS(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    `order` INT NOT NULL,
    board_column_id BIGINT NOT NULL,
    CONSTRAINT boards_columns__card_fk FOREIGN KEY (board_column_id) REFERENCES BOARDS_COLUMNS(id) ON DELETE CASCADE
)ENGINE=InnoDB;