-- 멤버 초기화
INSERT INTO member (email, password, nickname, role, created_at, updated_at)
VALUES ('user1@example.com', 'password1', 'nickname1', 'USER', NOW(), NOW());
INSERT INTO member (email, password, nickname, role, created_at, updated_at)
VALUES ('user2@example.com', 'password2', 'nickname2', 'USER', NOW(), NOW());
INSERT INTO member (email, password, nickname, role, created_at, updated_at)
VALUES ('user3@example.com', 'password3', 'nickname3', 'USER', NOW(), NOW());

INSERT INTO posts (title, content, member_id, created_at, updated_at) VALUES ('제목1', '내용1', 1, NOW(), NOW());
INSERT INTO posts (title, content, member_id, created_at, updated_at) VALUES ('제목2', '내용2', 2, NOW(), NOW());
INSERT INTO posts (title, content, member_id, created_at, updated_at) VALUES ('제목3', '내용3', 3, NOW(), NOW());