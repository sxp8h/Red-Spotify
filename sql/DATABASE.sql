--                   /\\  ,,    
--      ,           || || ||    
-- _-_, \\ /` -_-_   \ /  ||/\\ 
--||_.   \\   || \\  /\\  || || 
-- ~ ||  /\\  || || // \\ || || 
--,-_-  /  \; ||-'  || || \\ |/ 
--            |/     \\/    _/  
--            '
--
-- Database extructure -- 
-- 2 TABLES RED_USERS & RED_ARTISTS
CREATE TABLE RED_USERS 
(
  ID_USER NUMBER NOT NULL 
, NAME VARCHAR2(40) NOT NULL 
, SURNAME VARCHAR2(40) NOT NULL 
, EMAIL VARCHAR2(255) NOT NULL 
, PASSWORD VARCHAR2(18) NOT NULL 
, CONSTRAINT RED_USERS_PK PRIMARY KEY 
  (
    ID_USER 
  )
  ENABLE 
);

CREATE SEQUENCE users_seq START WITH 1;

CREATE TABLE RED_ARTISTS 
(
  ID_ARTIST VARCHAR2(20) NOT NULL 
, NAME VARCHAR2(255) NOT NULL 
, URL VARCHAR2(255) NOT NULL 
, CONSTRAINT RED_ARTISTS_PK PRIMARY KEY 
  (
    ID_ARTIST 
  )
  ENABLE 
);

CREATE SEQUENCE artist_seq START WITH 1;

-- Insert a couple triggers for auto increment

CREATE OR REPLACE TRIGGER users_bir 
BEFORE INSERT ON red_users
FOR EACH ROW

BEGIN
  SELECT users_seq.NEXTVAL
  INTO   :new.id_user
  FROM   dual;
END;
/

CREATE OR REPLACE TRIGGER artist_bir                                                                                  
BEFORE INSERT ON red_artists                                                                                          
FOR EACH ROW                                                                                                          

BEGIN                                                                                                                 
  SELECT artist_seq.NEXTVAL
  INTO   :new.id_artist
  FROM dual;                                                                                                        
END;                                                                                                                  
/  

-- Insert a example user and example artist

INSERT INTO RED_USERS (NAME, SURNAME, EMAIL, PASSWORD) VALUES ('sxp8h', 'RCabrera', 'admin@admin.com', '1234')
INSERT INTO RED_ARTISTS (NAME, URL) VALUES ('Abel K Kaña', '....')
