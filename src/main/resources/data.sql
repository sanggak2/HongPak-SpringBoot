-- 게시글 더미데이터
INSERT INTO article(id, title, content) VALUES (1, '가', '1');
INSERT INTO article(id, title, content) VALUES (2, '나', '2');
INSERT INTO article(id, title, content) VALUES (3, '다', '3');
INSERT INTO article(id, title, content) VALUES (4, '응애', 'baby');
INSERT INTO article(id, title, content) VALUES (5, '나 애기', '구구가가');
INSERT INTO article(id, title, content) VALUES (6, '안아줘요', '으악');

-- 댓글 더미데이터
INSERT INTO comment(id, article_id, nickname, body) VALUES (1, 4, '응애맨', '나 아기');
INSERT INTO comment(id, article_id, nickname, body) VALUES (2, 4, 'kim', '김부각');
INSERT INTO comment(id, article_id, nickname, body) VALUES (3, 4, 'jang', '장독대');
INSERT INTO comment(id, article_id, nickname, body) VALUES (4, 5, '애기맨', '응애응애');
INSERT INTO comment(id, article_id, nickname, body) VALUES (5, 5, 'kim', '부기부기');
INSERT INTO comment(id, article_id, nickname, body) VALUES (6, 5, '번개맨', '번개번개');
INSERT INTO comment(id, article_id, nickname, body) VALUES (7, 6, '허깅워기', '나 강림');
INSERT INTO comment(id, article_id, nickname, body) VALUES (8, 6, '모델워깅', '또각또각');
INSERT INTO comment(id, article_id, nickname, body) VALUES (9, 6, 'kim', '렛미쉐익더부디');