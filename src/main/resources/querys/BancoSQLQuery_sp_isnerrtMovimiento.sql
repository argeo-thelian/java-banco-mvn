USE [Banco]
GO
/****** Object:  StoredProcedure [dbo].[sp_insetaMovimiento]    Script Date: 13/02/2023 06:53:36 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER   PROCEDURE [dbo].[sp_insetaMovimiento]
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
		COMMIT;
	END TRY
	BEGIN CATCH 
		ROLLBACK;
	END CATCH
END
