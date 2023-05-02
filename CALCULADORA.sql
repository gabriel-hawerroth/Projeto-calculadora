INSERT INTO CALCULADORA (ID, NUM1, OPERACAO, NUM2, RESULTADO, DATA_HORA)
VALUES (idcalculo.nextval, 5.29183982, 1, 5, 10.29184, TRUNC(SYSDATE, 'MI'));

SELECT 
    A.NUM1, 
    B.DS_OPERACAO OPERACAO, 
    A.NUM2, 
    A.RESULTADO, 
    TO_CHAR(A.DATA_HORA, 'DD/MM/YYYY HH24:MI') DATA_HORA_REGISTRO
FROM 
    CALCULADORA A INNER JOIN OPERACOES B ON A.OPERACAO = B.ID_OPERACAO;d
    

SELECT * FROM OPERACOES;
SELECT * FROM CALCULADORA;
    

ALTER SEQUENCE IDCALCULO RESTART;

DELETE FROM CALCULADORA WHERE ID > 0;

SELECT IDCALCULO.NEXTVAL FROM DUAL;

COMMIT;

