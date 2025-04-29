-- Requerimiento
-- ===========================
/*
Se requiere obtener el side ingresos por curso:

CODIGO  NOMBRE             MATRICULADOS     ING.PROYECTADO     ING.REAL
-------------------------------------------------------------------------
C001    Fisica Espacial         10           10,000.00         2,000.00
C003    Aaaaa                   15           17,000.00         6,200.00
C005    BBBBbb                  18           25,000.00         9,300.00
-------------------------------------------------------------------------
*/

-- Parte 1

select cur_id CODIGO,  cur_nombre NOMBRE, cur_matriculados MATRICULADOS
from CURSO where cur_matriculados>0;
go

-- Parte 2

select cur_id CODIGO, sum(mat_precio) ING_PROYECTADO
from MATRICULA
group by cur_id;
go

-- Parte 3

select cur_id CODIGO, sum(pag_importe) ING_REAL
from PAGO
group by cur_id;
go


-- Consulta final

WITH
PA1 as (
	select cur_id CODIGO,  cur_nombre NOMBRE, cur_matriculados MATRICULADOS
	from CURSO where cur_matriculados>0
),
PA2 as (
	select cur_id CODIGO, sum(mat_precio) ING_PROYECTADO
	from MATRICULA group by cur_id
),
PA3 as (
	select cur_id CODIGO, sum(pag_importe) ING_REAL
	from PAGO group by cur_id
)
select 
	PA1.CODIGO, PA1.NOMBRE, PA1.MATRICULADOS,
	PA2.ING_PROYECTADO, ISNULL(PA3.ING_REAL,0.0) ING_REAL
from PA1
join PA2 on PA1.CODIGO = PA2.CODIGO
left join PA3 on PA1.CODIGO = PA3.CODIGO;
GO


-- Requerimiento
-- ===========================
/*
Se requiere obtener un reporte por curso.
LA estructura del reporte es:

CODIGO  NOMBRE               TIPO         PRECIO     CUOTAS   ABONO      SALDO
-----------------------------------------------------------------------------------
  1     Pedro Vargas         REGULAR      4,000.00     2      2,000.00   2,000.00
  4     Mariela Rosas        MEDIABECA    1,500.00     1      0.00       1,500.00
  6     Juana la Cubana      BECA         80.00        1      80.00      0.00
------------------------------------------------------------------------------------
*/

-- Parte 1

select 
	a.alu_id CODIGO, a.alu_nombre NOMBRE, m.mat_tipo TIPO,
	m.mat_precio PRECIO, m.mat_cuotas CUOTAS
from MATRICULA m
join ALUMNO a on m.alu_id = a.alu_id
where m.cur_id = 1;
go

-- Parte 2

select alu_id CODIGO, sum(pag_importe) ABONO
from PAGO
where cur_id = 1
group by alu_id;
go


-- Consulta final

WITH
PA1 as (
	select 
		a.alu_id CODIGO, a.alu_nombre NOMBRE, m.mat_tipo TIPO,
		m.mat_precio PRECIO, m.mat_cuotas CUOTAS
	from MATRICULA m
	join ALUMNO a on m.alu_id = a.alu_id
	where m.cur_id = 1
	),
PA2 as (
	select alu_id CODIGO, sum(pag_importe) ABONO
	from PAGO
	where cur_id = 1
	group by alu_id
)
select 
	PA1.CODIGO, PA1.NOMBRE, PA1.TIPO, 
	PA1.PRECIO, PA1.CUOTAS, 
	ISNULL(PA2.ABONO,0) ABONO,
	PA1.PRECIO - ISNULL(PA2.ABONO,0) SALDO
from PA1 left join PA2 
on PA1.CODIGO = PA2.CODIGO





