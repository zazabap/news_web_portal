ALTER TABLE user_tbl
    ADD COLUMN user_password VARCHAR(50) AFTER user_name;
ALTER TABLE user_tbl
    ADD COLUMN user_email VARCHAR(50) AFTER user_name;

SELECT *
FROM news_tbl
WHERE news_hit_vol >100
    LIMIT 5;

SELECT TOP 10|percent news_hit_vol
FROM news_tbl
WHERE news_hit_vol>10;

SELECT news_title FROM news_tbl
    ORDER BY news_hit_vol
    LIMIT 5;

/*
 This create add Uniqueness
 */
CREATE TABLE person_tbl
(
    first_name CHAR(20) NOT NULL,
    last_name CHAR(20) NOT NULL,
    sex CHAR(10),
    UNIQUE (last_name, first_name)
);


/*
 This could be used for
 add favorite
*/

/*
Group the news by author/field
*/

SELECT name, COUNT(*) FROM   employee_tbl GROUP BY name;
SELECT news_author, COUNT(*) FROM news_tbl GROUP BY news_author;
