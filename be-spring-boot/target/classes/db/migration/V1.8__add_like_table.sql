CREATE TABLE like_of_post
(
    id INT NOT NULL AUTO_INCREMENT,
    authorId   INT NOT NULL,
    postId INT NOT NULL,
    createdAt DATETIME,
    PRIMARY KEY (id),
    CONSTRAINT FK_author_id FOREIGN KEY (authorId) REFERENCES author(id),
    CONSTRAINT FK_post_id FOREIGN KEY (postId) REFERENCES post(id)
);