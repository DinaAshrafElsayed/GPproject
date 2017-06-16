CREATE TABLE T_BORROW_STATE 
(
  ID NUMBER NOT NULL 
, ACTIVITY NUMBER NOT NULL 
, IS_BACK NUMBER NOT NULL 
, CONSTRAINT T_BORROW_STATE_PK PRIMARY KEY 
  (
    ID 
  )
  ENABLE 
);

ALTER TABLE T_BORROW_STATE
ADD CONSTRAINT T_BORROW_STATE_FK1 FOREIGN KEY
(
  ACTIVITY 
)
REFERENCES T_ACTIVITY
(
  ID 
)
ON DELETE CASCADE ENABLE;

CREATE SEQUENCE T_BORROW_STATE_SEQ;

CREATE TRIGGER T_BORROW_STATE_TRG 
BEFORE INSERT ON T_BORROW_STATE 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT T_BORROW_STATE_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/