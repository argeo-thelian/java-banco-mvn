use Banco

CREATE TABLE cliente(
	rfc varchar(13) primary key,
	nombre varchar(50),
	ciudad varchar(30)
)

GO
CREATE TABLE cuenta(
	no_cuenta int primary key identity(1,1),
	tipo varchar(12) check(tipo = 'Ahorro' or tipo = 'Cheques' or tipo = 'Inversiones' or tipo = 'Crédito' ),
	saldo money, 
	rfc varchar(13),
	foreign key(rfc) references cliente(rfc)
)
GO
CREATE TABLE movimient(
	id_movimiento int primary key identity(1,1),
	fecha datetime,
	monto money,
	no_cuenta int,
	foreign key(no_cuenta) references cuenta(no_cuenta)
)
GO

INSERT INTO cliente
VALUES
	('ABC12345','Miguel Angel Mendez','Tampico'),
	('CDF12345','Beatriz Montes','CDMX'),
	('CBV12345','Magdalena Martinez','Veracruz'),
	('DFR12321','Claudia Hernandez','Tulticlan'),
	('ASC12311','Rubén Suarez','Tlauac'),
	('AQW11215','Morelos Morales','Tampico'),
	('ASD22125','Jorge Robles','CDMX'),
	('BBF12215','Abner Robinson','Queretaro'),
	('MNB12675','Mariana Rosales','Durango')
GO

SELECT * FROM cliente
GO

INSERT INTO cuenta(saldo, tipo, rfc) VALUES
(0, 'Ahorro', 'ABC12345')
INSERT INTO cuenta(saldo, tipo, rfc) VALUES
(0, 'Cheques', 'ABC12345')
INSERT INTO cuenta(saldo, tipo, rfc) VALUES
(0, 'Inversiones', 'ABC12345')
INSERT INTO cuenta(saldo, tipo, rfc) VALUES
(0, 'Crédito', 'ABC12345')
INSERT INTO cuenta(saldo, tipo, rfc) VALUES
(0, 'Inversiones', 'CDF12345')
INSERT INTO cuenta(saldo, tipo, rfc) VALUES
(0, 'Crédito', 'CBV12345')
INSERT INTO cuenta(saldo, tipo, rfc) VALUES
(0, 'Ahorro', 'DFR12321')
INSERT INTO cuenta(saldo, tipo, rfc) VALUES
(0, 'Inversiones', 'ASC12311')
INSERT INTO cuenta(saldo, tipo, rfc) VALUES
(0, 'Ahorro', 'ASC12311')
INSERT INTO cuenta(saldo, tipo, rfc) VALUES
(0, 'Crédito', 'AQW11215')
INSERT INTO cuenta(saldo, tipo, rfc) VALUES
(0, 'Ahorro', 'ASD22125')
INSERT INTO cuenta(saldo, tipo, rfc) VALUES
(0, 'Inversiones', 'BBF12215')
INSERT INTO cuenta(saldo, tipo, rfc) VALUES
(0, 'Inversiones', 'MNB12675')
go 


SELECT * FROM cliente
SELECT * FROM cuenta
SELECT * FROM movimient
GO

INSERT INTO movimient
	(fecha, monto, no_cuenta)
VALUES
	(GETDATE(), 2000,2)

UPDATE Cuenta SET saldo = (SELECT SUM(monto) FROM movimient WHERE no_cuenta = 2)
WHERE no_cuenta = 2
GO 

CREATE or ALTER TRIGGER tr_actualizarSaldo
ON movimient AFTER INSERT
AS
BEGIN
	BEGIN TRANSACTION
	BEGIN  TRY

		DECLARE @cuenta int
		SELECT @cuenta= no_cuenta FROM INSERTED 
		UPDATE cuenta SET saldo = (SELECT SUM(monto) FROM movimient WHERE no_cuenta = @cuenta)
		WHERE no_cuenta = @cuenta
		COMMIT;
	END TRY
	BEGIN CATCH
		ROLLBACK
	END CATCH
END
GO

INSERT INTO movimient
	(fecha, monto, no_cuenta)
VALUES
	(GETDATE(), 100,1)
	
SELECT * FROM cuenta
SELECT * FROM movimient

GO

CREATE or ALTER PROCEDURE sp_insetaMovimiento
	@monto MONEY,
	@cuenta INT
AS
BEGIN
	BEGIN TRANSACTION
	BEGIN TRY
		DECLARE @saldo MONEY, @montoAbs MONEY 
		IF @monto < 0 
		BEGIN
			SELECT @saldo = saldo FROM cuenta WHERE no_cuenta = @cuenta
			SET @montoAbs = @monto * -1
			if @montoAbs <= @saldo
			BEGIN
				INSERT INTO movimient (fecha, monto, no_cuenta)
				VALUES (GETDATE(), @monto, @cuenta)
			END
		END
		ELSE
		BEGIN
			INSERT INTO movimient (fecha, monto, no_cuenta)
			VALUES (GETDATE(), @monto, @cuenta)
		END 
		COMMIT
	END TRY
	BEGIN CATCH 
		ROLLBACK
	END CATCH
END
GO 


SELECT * FROM cliente
SELECT * FROM cuenta
SELECT * FROM movimient
EXEC sp_insetaMovimiento -20,  1
SELECT * FROM cuenta
SELECT * FROM movimient
GO

--- Transferecias de una cuentas a otra

CREATE OR ALTER PROCEDURE sp_transferir
	@origen INT,
	@destino INT,
	@monto MONEY
AS
BEGIN
	BEGIN TRANSACTION
	BEGIN TRY
		DECLARE @saldo MONEY, @monto_retiro MONEY
		SELECT @saldo = saldo FROM cuenta WHERE no_cuenta = @origen
		if @monto <= @saldo
		BEGIN
			SET @monto_retiro = @monto * -1
			EXEC sp_insetaMovimiento @monto_retiro, @origen
			EXEC sp_insetaMovimiento @monto, @destino
			COMMIT
		END
		
	END TRY
	BEGIN CATCH
		ROLLBACK
	END CATCH
END

SELECT * FROM cliente
SELECT * FROM cuenta
SELECT * FROM movimient
EXEC sp_insetaMovimiento -200,  1
EXEC sp_transferir 2, 1, 300
SELECT * FROM cuenta
SELECT * FROM movimient
GO


SELECT * FROM cliente
SELECT * FROM cuenta
SELECT * FROM movimient

CREATE VIEW cuenta_cliente
AS 
SELECT no_cuenta, tipo, saldo, c.rfc, nombre, ciudad
FROM cuenta c, cliente ct
WHERE c.rfc = ct.rfc

SELECT * FROM cuenta_cliente

SELECT c.no_cuenta, c.saldo, cl.nombre
FROM cuenta as c INNER JOIN cliente as cl
ON c.rfc = cl.rfc 
Where c.saldo > 0
GO

SELECT COUNT(c.rfc), c.saldo
FROM cuenta c
GROUP BY c.saldo
GO

SELECT c.saldo
FROM cuenta c

SELECT COUNT(*)
FROM cuenta c

SELECT c.saldo, COUNT(*)
FROM cuenta c
GROUP BY c.saldo
HAVING COUNT(*) >=1
GO

SELECT COUNT(c.rfc)as 'Cuentas con Saldo Cero', c.saldo
FROM cuenta c
GROUP BY c.saldo
HAVING COUNT(c.rfc) > 30
GO


SELECT COUNT(c.rfc)
FROM cuenta c
GO

SELECT MAX(c.saldo)
FROM cuenta c


USE Banco
GO
xp_readerrorlog 0, 1, N'Server is listening on' 
GO