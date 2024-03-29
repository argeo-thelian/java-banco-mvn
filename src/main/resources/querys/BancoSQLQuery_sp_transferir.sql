USE [Banco]
GO
/****** Object:  StoredProcedure [dbo].[sp_transferir]    Script Date: 13/02/2023 05:50:41 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER   PROCEDURE [dbo].[sp_transferir]
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
		END
		COMMIT;
	END TRY
	BEGIN CATCH
		ROLLBACK;
	END CATCH
END
