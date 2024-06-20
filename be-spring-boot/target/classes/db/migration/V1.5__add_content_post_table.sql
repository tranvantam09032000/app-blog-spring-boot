CREATE TABLE post_content
(
    id INT NOT NULL AUTO_INCREMENT,
    postId INT NOT NULL,
    type VARCHAR(250) NOT NULL,
    value VARCHAR(10000) NOT NULL,
    position INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_postContentId FOREIGN KEY (postId) REFERENCES post(id)
);