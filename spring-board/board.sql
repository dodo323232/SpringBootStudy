CREATE TABLE board (
    board_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    member_id BIGINT NOT NULL,
    view_count INT DEFAULT 0,          
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (member_id) REFERENCES member(member_id)
);

SELECT title, content, created_at FROM board;

SELECT * FROM member;
SELECT * FROM board;

SELECT b.board_id, b.title, b.content, m.nickname
FROM board b
JOIN member m ON b.member_id = m.member_id;

desc member;

SELECT b.board_id, b.title, b.content, m.nickname
FROM board b
JOIN member m ON b.member_id = m.member_id
WHERE b.member_id = 1;