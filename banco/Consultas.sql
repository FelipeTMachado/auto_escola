-- 
SELECT * FROM PESSOA;
-- 
-- SELECT * FROM PESSOA WHERE CPF = "09297363970";
-- 
-- SELECT * FROM PESSOA WHERE USUARIO = 'feh' AND SENHA = '123';
-- 
-- DELETE FROM PESSOA WHERE CPF = "555555";

SELECT * FROM TURMA;

SELECT * FROM PARCELAS;

SELECT T.*, P.CPF CPF FROM TURMA T JOIN PESSOA P ON P.HANDLE = T.INSTRUTOR

DELETE FROM PESSOA WHERE HANDLE > 3

INSERT INTO PARCELAS
	(NUMEROPARCELA, DATAPARCELA, PESSOA, PAGO)
VALUES 
	(1, "2023-01-0", 2, 1);
	
UPDATE TURMA 
SET CATEGORIA = "A"
WHERE HANDLE = 1