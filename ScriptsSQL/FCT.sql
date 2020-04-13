REM *********************************************************
REM ************ EMPRESAS *******************************
REM *********************************************************
DROP TABLE FCTEmpresas cascade constraints;
CREATE TABLE FCTEmpresas 
   (    N_CONV VARCHAR2(50) PRIMARY KEY, 
    REPRESENTANTE VARCHAR2(100), 
    NIF_REP VARCHAR2(9), 
   CIF VARCHAR2(9), 
    NOMBRE VARCHAR2(50), 
    DIRECCION VARCHAR2(150), 
    CP VARCHAR2(5), 
    CIUDAD VARCHAR2(50), 
    PROVINCIA VARCHAR2(50), 
    PAIS VARCHAR2(50), 
    TELEFONO VARCHAR2(9), 
    FAX VARCHAR2(9), 
    FECHA_CONV VARCHAR2(12)
   ) ;


REM *********************************************************
REM ************ TUTOR EMPRESA *******************************
REM *********************************************************
DROP TABLE FCTTUTORES_EMPRESA cascade constraints;
CREATE TABLE  FCTTUTORES_EMPRESA 
   (    NIF_TE VARCHAR2(9) PRIMARY KEY, 
    NOMBRE VARCHAR2(50), 
    TELEFONO VARCHAR2(9), 
    EMAIL VARCHAR2(50), 
    N_CONV VARCHAR2(50)
   ) ;



REM *********************************************************
REM ************ CICLOS *******************************
REM *********************************************************
DROP TABLE FCTCiclos cascade constraints;
CREATE TABLE FCTCiclos  
   (    CLAVE VARCHAR2(20) PRIMARY KEY, 
    NOMBRE VARCHAR2(50), 
    FAMILIA_PROF VARCHAR2(50), 
    CLAVE_FAMILIA VARCHAR2(20), 
    CAP_TERM VARCHAR2(1000), 
    ACT_FORM_PROD VARCHAR2(1000), 
    CRIT_EV VARCHAR2(1000)
   ) ;



REM *********************************************************
REM ************ ALUMNOS *******************************
REM *********************************************************
DROP TABLE FCTAlumnos cascade constraints;
CREATE TABLE  FCTALUMNOS
   (    NIF_AL VARCHAR2(9) PRIMARY KEY, 
    NOMBRE VARCHAR2(50), 
    APELLIDOS VARCHAR2(50), 
    DIRECCION VARCHAR2(150), 
    CIUDAD VARCHAR2(50), 
    CP VARCHAR2(5), 
    PROVINCIA VARCHAR2(50), 
    TELEFONO VARCHAR2(9), 
    EMAIL VARCHAR2(50)
   ) ;

REM *********************************************************
REM ************ FCTCURSAN *******************************
REM *********************************************************
DROP TABLE FCTCURSAN cascade constraints;
CREATE TABLE  FCTCURSAN
  (    CURSO VARCHAR2(10), 
    NIF_AL VARCHAR2(9), 
    CLAVE VARCHAR2(20), 
     CONSTRAINT FCTCURSAN_PK PRIMARY KEY (NIF_AL, CLAVE),
     FOREIGN KEY (CLAVE) REFERENCES  FCTCICLOS (CLAVE),
     FOREIGN KEY (NIF_AL) REFERENCES  FCTALUMNOS (NIF_AL) 
   ) ;
     

REM *********************************************************
REM ************ FCT_CENTROS *******************************
REM *********************************************************
DROP TABLE FCTCentros cascade constraints;
CREATE TABLE  FCTCENTROS 
   (    COD_CENTRO VARCHAR2(30) PRIMARY KEY, 
    NOMBRE VARCHAR2(50), 
    DIRECCION VARCHAR2(150), 
    CIUDAD VARCHAR2(50), 
    CP VARCHAR2(5), 
    PROVINCIA VARCHAR2(50), 
    TELEFONO VARCHAR2(9), 
    FAX VARCHAR2(9), 
    CIF VARCHAR2(10), 
    TITULAR VARCHAR2(50), 
    NIF_TITULAR VARCHAR2(10), 
    DAT VARCHAR2(50)
   ) ;

REM *********************************************************
REM ************ TUTOR CENTRO *******************************
REM *********************************************************
DROP TABLE FCTTutores_CENTRO cascade constraints;
CREATE TABLE FCTTutores_CENTRO (
NIF_TC VARCHAR2(255) PRIMARY KEY,
Nombre VARCHAR2(255) NOT NULL,
Telefono VARCHAR2(12) NOT NULL,
Email VARCHAR2(100) NOT NULL,
Codigo_Centro VARCHAR2(50) NOT NULL,
FOREIGN KEY(Codigo_Centro) REFERENCES FCTCENTROS (Cod_Centro) ON DELETE CASCADE    
);




REM *********************************************************
REM ************ FCTSUPERVISAN *******************************
REM *********************************************************
DROP TABLE FCTSUPERVISAN cascade constraints;
CREATE TABLE  FCTSUPERVISAN
   (     
    
    FECHA_INICIO VARCHAR2(12),
    FECHA_FIN VARCHAR2(12),
    HORAS_DIA VARCHAR2(2),
    HORAS_TOTALES VARCHAR2(5),
    HORA_INICIO_M VARCHAR2(10),
    HORA_FIN_M VARCHAR2(10),
    HORA_INICIO_T VARCHAR2(10),
    HORA_FIN_T VARCHAR2(10),
    NIF_AL VARCHAR2(9),
    NIF_TC VARCHAR2(9),
    NIF_TE VARCHAR2(9),
    N_CONV VARCHAR2(50),
     CONSTRAINT FCTSUPERVISAN_PK PRIMARY KEY (NIF_AL, NIF_TC, NIF_TE, N_CONV), 
      FOREIGN KEY (NIF_TE) REFERENCES  FCTTUTORES_EMPRESA (NIF_TE) 
   ) ;

