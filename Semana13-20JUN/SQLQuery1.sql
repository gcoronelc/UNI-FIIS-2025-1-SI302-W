

select count(1) cont from MATRICULA
where cur_id=1 and alu_id=4
go

select *, cur_vacantes - cur_matriculados cont from curso;
go

select * from MATRICULA order by mat_fecha desc;
go

select cur_precio from CURSO where cur_id=1
go

insert into MATRICULA(cur_id,alu_id,emp_id,
mat_tipo,mat_fecha,mat_precio,mat_cuotas)
values(?,?,?,?,GETDATE(),?,?)
go


select * from CURSO
go

update curso 
set cur_matriculados = cur_matriculados + 1
where cur_id = ?
go


-- Consulta de los pagos

select * from MATRICULA 
where cur_id = 3;
go


declare @idCurso int, @idAlumno int;
set @idCurso = 3;
set @idAlumno = 7;
with
-- paso 1
MAT AS (
	select mat_tipo, mat_precio, mat_cuotas 
	from MATRICULA 
	where cur_id = @idCurso and alu_id = @idAlumno
),
-- Paso 2
PAG AS (
	select max(pag_cuota) ucuota, sum(pag_importe) pagado 
	from PAGO
	where cur_id = @idCurso and alu_id = @idAlumno
)
-- Consulta final
select 
	MAT.mat_tipo tipo, MAT.mat_precio precio,
	MAT.mat_cuotas cuotas, 
	ISNULL(PAG.ucuota,0) ultcuota,
	ISNULL(PAG.pagado,0.0) pagado
from MAT cross join PAG
go


select * from PAGO;
go

insert into PAGO(cur_id,alu_id,pag_cuota,emp_id,pag_fecha,pag_importe)
values(?,?,?,?,GETDATE(),?)
go