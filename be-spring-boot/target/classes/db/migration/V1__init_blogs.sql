CREATE TABLE author (
    id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE category (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    subCategory INT,
    PRIMARY KEY (id),
    CONSTRAINT FK_sub_category FOREIGN KEY (subCategory) REFERENCES category(id)
);

CREATE TABLE tag (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE post
(
    id INT NOT NULL AUTO_INCREMENT,
    authorId   INT NOT NULL,
    categoryId INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    content VARCHAR(2000) NOT NULL,
    thumbnail VARCHAR(255) NOT NULL,
    createdAt DATETIME,
    updatedAt DATETIME,
    published BOOLEAN NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    CONSTRAINT FK_author_id FOREIGN KEY (authorId) REFERENCES author(id),
    CONSTRAINT FK_category_id FOREIGN KEY (categoryId) REFERENCES category(id)
);

CREATE TABLE post_tag (
    postId INT NOT NULL,
    tagId INT NOT NULL,
    PRIMARY KEY (postId, tagId),
    CONSTRAINT FK_post  FOREIGN KEY (postId) REFERENCES post(id),
    CONSTRAINT FK_tag  FOREIGN KEY (tagId) REFERENCES tag(id)
);