SHOW TABLES;

SELECT * FROM 수강신청;
SELECT * FROM 학생;

SELECT * FROM 수강학생;
SELECT * FROM 수강과목;
SELECT * FROM 성적;
SELECT * FROM 개인성적표;
SELECT * FROM 개인성적계산;


SELECT a.학생번호, a.학생이름, a.소속학과, b.총점, b.평균
	FROM 학생 a
	INNER JOIN 개인성적계산 b
	ON a.학생번호 = b.학생번호
	WHERE a.학생번호 = '201223004';


DELIMITER $$
CREATE PROCEDURE 학점구하기 (IN 평균 DOUBLE, OUT 알파벳 VARCHAR(10))
	BEGIN
		SELECT 학점 INTO 알파벳 FROM 성적기준표 WHERE 평균 >= 최저점수 AND 평균 < 최고점수;
    END $$
DELIMITER ;

DROP PROCEDURE 학점구하기;
	
CALL 학점구하기(90, @alpha);
SELECT @alpha;

SHOW TABLES;
SHOW DATABASES;