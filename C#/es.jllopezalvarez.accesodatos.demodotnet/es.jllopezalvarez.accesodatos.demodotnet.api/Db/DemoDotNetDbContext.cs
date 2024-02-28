using es.jllopezalvarez.accesodatos.demodotnet.api.Entities;
using Microsoft.EntityFrameworkCore;

namespace es.jllopezalvarez.accesodatos.demodotnet.api.Db
{
    public class DemoDotNetDbContext : DbContext
    {

        public DemoDotNetDbContext(DbContextOptions<DemoDotNetDbContext> options) : base(options)
        {

        }

        public DbSet<Estudiante> Estudiantes { get; set; }
        public DbSet<Modulo> Modulos { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            // Esto no funciona directamente.
            // HasData funciona sólo cuando se están usando migraciones de Entity Framework
            // Buscad información sobre "Entity Framework Core Migrations"
            // para saber de qué va esto de las migraciones.

            modelBuilder.Entity<Estudiante>().HasData(
                new Estudiante("12345678A", "José Luis", "López Álvarez", "1972-08-13"),
                new Estudiante("23456789B", "María", "García Martínez", "2000-03-25"),
                new Estudiante("34567890C", "Juan", "Pérez Rodríguez", "1998-11-10"),
                new Estudiante("45678901D", "Laura", "González Sánchez", "1995-09-18"),
                new Estudiante("56789012E", "Carlos", "Fernández López", "1989-07-03"),
                new Estudiante("67890123F", "Ana", "Martínez García", "1985-05-20"),
                new Estudiante("78901234G", "David", "Sánchez Martín", "1983-12-15"),
                new Estudiante("89012345H", "Sofía", "López Hernández", "1980-10-08"),
                new Estudiante("90123456I", "Pedro", "Gómez Pérez", "1978-04-29"),
                new Estudiante("01234567J", "Elena", "Hernández Rodríguez", "1975-02-14"),
                new Estudiante("12345678K", "Miguel", "Rodríguez Gómez", "1970-06-30"));
        }



    }
}
