using es.jllopezalvarez.accesodatos.demodotnet.api.Db;
using es.jllopezalvarez.accesodatos.demodotnet.api.Entities;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace es.jllopezalvarez.accesodatos.demodotnet.api.Repositories
{
    public class EstudianteDbRepository : IEstudianteRepository
    {
        private readonly DemoDotNetDbContext dbContext;

        public EstudianteDbRepository(DemoDotNetDbContext dbContext)
        {
            this.dbContext = dbContext;
        }

        public void Add(Estudiante estudiante)
        {
            dbContext.Estudiantes.Add(estudiante);
            dbContext.SaveChanges();
        }

        public List<Estudiante> find(string? nombre, string? apellido, int? edad)
        {
            IQueryable<Estudiante> query = dbContext.Estudiantes;

            // ¿Tengo que buscar en nombre?
            if (!string.IsNullOrWhiteSpace(nombre))
            {
                query = query.Where(e => e.Nombre.Contains(nombre));
            }

            // ¿Tengo que buscar el apellido?
            if (!string.IsNullOrWhiteSpace(apellido))
            {
                query = query.Where(e => e.Nombre.Contains(apellido));
            }

            // ¿Tengo que buscar por edad?
            if (edad.HasValue)
            {
                query = query.Where(e => e.Edad == edad.Value);

            }

            return query.ToList();
        }

        public List<Estudiante> findAll()
        {
            return dbContext.Estudiantes.ToList();
        }

        public Estudiante? findById(string id)
        {
            return dbContext.Estudiantes.Find(id);
        }

        public void removeById(string id)
        {
            Estudiante? estudiante = this.findById(id);
            if (estudiante != null) {
                dbContext.Remove(estudiante);
                dbContext.SaveChanges();
            }
        }
    }
}
