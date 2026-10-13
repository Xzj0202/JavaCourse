USE library;

CREATE TABLE book (
    id INT AUTO_INCREMENT PRIMARY KEY, -- 主键：每个字段的解释见下
    isbn VARCHAR(20) NOT NULL UNIQUE, -- 书号：不能空、不能重复
    title VARCHAR(100) NOT NULL, -- 书名：不能空
    author VARCHAR(50), -- 作者：可为空
    total_count INT NOT NULL DEFAULT 1, -- 总册数，默认 1
    available_count INT NOT NULL DEFAULT 1 -- 可借册数，默认 1
);

CREATE TABLE reader (
    id INT AUTO_INCREMENT PRIMARY KEY, -- 主键、自增
    name VARCHAR(50) NOT NULL, -- 姓名，不能为空
    student_no VARCHAR(20) NOT NULL UNIQUE, -- 学号，不能为空、不能重复
    phone VARCHAR(20) -- 手机号，可以为空
);

CREATE TABLE borrow_record (
    id INT AUTO_INCREMENT PRIMARY KEY, -- 主键、自增
    book_id INT NOT NULL, -- 外键、指向book表中id
    reader_id INT NOT NULL, -- 外键、指向reader表中id
    loan_time DATETIME NOT NULL, -- 借出时间
    return_time DATETIME, -- 归还时间
    FOREIGN KEY (book_id) REFERENCES book (id), -- 外键、指向book表中id
    FOREIGN KEY (reader_id) REFERENCES reader (id) -- 外键、指向reader表中id
);

SHOW CREATE TABLE borrow_record;

SELECT COUNT(*) AS fk_count
FROM information_schema.TABLE_CONSTRAINTS
WHERE
    CONSTRAINT_SCHEMA = 'library'
    AND TABLE_NAME = 'borrow_record'
    AND CONSTRAINT_TYPE = 'FOREIGN KEY';