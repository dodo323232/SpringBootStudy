CREATE database board_db;

USE board_db;

CREATE TABLE member(
	member_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(50) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

SELECT * FROM member;
SELECT * FROM board;

CREATE TABLE board (
    board_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    member_id BIGINT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (member_id) REFERENCES member(member_id)
);

CREATE TABLE comment(
    comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    board_id BIGINT NOT NULL,
    member_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (board_id) REFERENCES board(board_id),
    FOREIGN KEY (member_id) REFERENCES member(member_id)
);

DESC comment;

SELECT * FROM comment;

 SELECT b.title AS boardTitle, b.content AS boardContent, c.content AS commentContent
        FROM comment c
        JOIN board b ON b.board_id = c.board_id
        WHERE c.member_id = 2