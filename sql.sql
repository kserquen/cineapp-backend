/**
 * En caso no se quiera eliminar la DB y continuar con los datos existentes
 * Se altera la tabla agregando una columan 'foto' de tipo 'bytea'
 */
ALTER TABLE cliente
ADD COLUMN foto bytea;
