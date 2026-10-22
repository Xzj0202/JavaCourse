USE library;

INSERT INTO
    book (
        isbn,
        title,
        author,
        total_count,
        available_count
    )
VALUES ('1', '三体', '刘慈欣', '5', '5');

SELECT * FROM book;

INSERT INTO
    book (
        isbn,
        title,
        author,
        total_count,
        available_count
    )
VALUES (
        9787111636663,
        'Java核心技术卷I',
        '凯·霍斯特曼',
        3,
        3
    );

INSERT INTO
    book (
        isbn,
        title,
        author,
        total_count,
        available_count
    )
VALUES (
        9787111407010,
        '算法导论',
        'Thomas H.Cormen',
        2,
        2
    );

INSERT INTO
    reader (name, student_no, phone)
VALUES (
        '张小三',
        '2024001',
        '13800000001'
    );

INSERT INTO
    reader (name, student_no, phone)
VALUES ('李四', '2024002', NULL);

SELECT * FROM reader;

SELECT id, title FROM book;

SELECT id, name FROM reader;

INSERT INTO
    borrow_record (book_id, reader_id, loan_time)
VALUES (1, 1, '2026-10-22 10:00:00');

SELECT * FROM borrow_record;

SELECT * FROM book WHERE title = '三体';

SELECT * FROM book WHERE total_count >= 3;

SELECT * FROM borrow_record WHERE return_time IS NULL;

SELECT * FROM book ORDER BY total_count DESC LIMIT 2;

SELECT * FROM book WHERE id = 1;

UPDATE book SET isbn = '9787536692930' WHERE id = 1;

SELECT * FROM book WHERE id = 1;

INSERT INTO
    book (
        isbn,
        title,
        author,
        total_count,
        available_count
    )
VALUES (
        '0000000000000',
        '待删测试书',
        '无',
        1,
        1
    );

SELECT * FROM book WHERE isbn = '0000000000000';

DELETE FROM book WHERE id = 4;

SELECT * FROM book WHERE isbn = '0000000000000';