ALTER TABLE user_tbl
    ADD COLUMN user_password VARCHAR(50) AFTER user_name;
ALTER TABLE user_tbl
    ADD COLUMN user_email VARCHAR(50) AFTER user_name;

ALTER TABLE review_tbl
    CHANGE review_id review_id INT NOT NULL AUTO_INCREMENT;

DELETE FROM review_tbl
    WHERE review_id = 1

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


SELECT user_id, user_name, user_history_browse
                FROM user_tbl
                WHERE user_password is NULL
                  and user_history_browse is not null and user_name="Sheldon";
/*
 * Select Null and more
 */
SELECT * FROM runoob_test_tbl WHERE runoob_count = NULL;

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
Group the news by author/field
*/

SELECT name, COUNT(*) FROM   employee_tbl GROUP BY name;
SELECT news_author, COUNT(*) FROM news_tbl GROUP BY news_author;

SELECT user_name, user_history_browse, COUNT(*)
FROM user_tbl GROUP BY user_name, user_history_browse;

SELECT DISTINCT user_history_browse
FROM user_tbl WHERE user_name = "Sheldon" AND user_history_browse IS NOT NULL;

SELECT user_history_browser from( SELECT user_name, user_history_browse, COUNT(*)
FROM user_tbl GROUP BY user_name, user_history_browse as Counter ) where user_name= "Sheldon";

SELECT news_author, COUNT(*) FROM news_tbl WHERE user_name = "Sheldon"

select user_History_browse,
       (SELECT news_author, COUNT(*) FROM news_tbl WHERE user_name = "Sheldon") as entry_count
from user_tbl
where user_name = "Sheldon"
/*
 * Insert with output updated
 *
 */
INSERT INTO table (name)
    OUTPUT Inserted.ID
VALUES('bob');

INSERT into review_tbl ( user_id, news_id, review_content,
                review_datetime, review_upvote_vol,
                review_downvote_vol) values (1, 1, "www", now(),0,0);
SELECT LAST_INSERT_ID();

/*
* Select Top Occurance
 */

SELECT *, count(*)
FROM news_tbl
GROUP BY news_author
ORDER BY count(*) DESC LIMIT 10;


/*
 *  一些积累下来的问题
 *     1。 用多个表的情况下应该怎么处理
 *     比如在展示评论的时候，增添了链接和新闻ID， 评论表里面有user_id
 *     再吧user_id 单独拿出来用来在user_tbl 里面搜索可以吗？
 *     还是有更便捷的方式
 */