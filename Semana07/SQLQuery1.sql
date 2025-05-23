-- Convert

declare @fecha1 varchar(20), @fecha2 varchar(20);
set @fecha1 = '15/03/2025';
set @fecha2 = '18/03/2025';
select datediff(dd,convert(date,@fecha1,103), convert(date,@fecha2,103)) dias;
go

