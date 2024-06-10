ALTER TABLE post ADD subTitle VARCHAR(250) NOT NULL;

UPDATE post SET subTitle = 'id luctus nec molestie sed justo...' WHERE id = 1;
UPDATE post SET subTitle = 'mauris enim leo rhoncus sed...' WHERE id = 2;
UPDATE post SET subTitle = 'libero nam dui proin leo...' WHERE id = 3;
UPDATE post SET subTitle = 'amet lobortis sapien...' WHERE id = 4;
UPDATE post SET subTitle = 'sapien iaculis congue vivamus metus...' WHERE id = 5;
UPDATE post SET subTitle = 'ante nulla justo aliquam...' WHERE id = 6;
UPDATE post SET subTitle = 'eu tincidunt in leo maecenas...' WHERE id = 7;
UPDATE post SET subTitle = 'quisque ut erat curabitur...' WHERE id = 8;
UPDATE post SET subTitle = 'luctus nec molestie sed justo...' WHERE id = 9;
UPDATE post SET subTitle = 'sapien iaculis congue vivamus metus...' WHERE id = 10;

CREATE TABLE comment
(
    id INT NOT NULL AUTO_INCREMENT,
    postId INT NOT NULL,
    fullName VARCHAR(250) NOT NULL,
    content VARCHAR(2000) NOT NULL,
    createdAt DATETIME,
    updatedAt DATETIME,
    PRIMARY KEY (id),
    CONSTRAINT FK_postId FOREIGN KEY (postId) REFERENCES category(id)
);

INSERT INTO comment (id,postId,fullName,content) VALUES (NULL, 1, "Tran Tam", "Good job!");
INSERT INTO comment (id,postId,fullName,content) VALUES (NULL, 1, "Nguyen Tuan", "Greate !");
INSERT INTO comment (id,postId,fullName,content) VALUES (NULL, 2, "Tran Tam", "Greate !");
INSERT INTO comment (id,postId,fullName,content) VALUES (NULL, 3, "Tran Kha", "Good job !");
INSERT INTO comment (id,postId,fullName,content) VALUES (NULL, 3, "Vu Tao", "Like post !");
INSERT INTO comment (id,postId,fullName,content) VALUES (NULL, 3, "Vo Tong", "Good job !");
INSERT INTO comment (id,postId,fullName,content) VALUES (NULL, 4, "Vinh Xuan", "Good job !");
INSERT INTO comment (id,postId,fullName,content) VALUES (NULL, 4, "Vu Tu", "I'm like it !");