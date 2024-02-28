using es.jllopezalvarez.accesodatos.demodotnet.api.Entities;

namespace es.jllopezalvarez.accesodatos.demodotnet.api.Repositories
{


    public class EstudianteCollectionRepository : IEstudianteRepository
    {

        private List<Estudiante> data = new List<Estudiante>();

        public EstudianteCollectionRepository()
        {
            this.data.Add(new Estudiante("12345678A", "José Luis", "López Álvarez", "1972-08-13"));
            this.data.Add(new Estudiante("23456789B", "María", "García Martínez", "2000-03-25"));
            this.data.Add(new Estudiante("34567890C", "Juan", "Pérez Rodríguez", "1998-11-10"));
            this.data.Add(new Estudiante("45678901D", "Laura", "González Sánchez", "1995-09-18"));
            this.data.Add(new Estudiante("56789012E", "Carlos", "Fernández López", "1989-07-03"));
            this.data.Add(new Estudiante("67890123F", "Ana", "Martínez García", "1985-05-20"));
            this.data.Add(new Estudiante("78901234G", "David", "Sánchez Martín", "1983-12-15"));
            this.data.Add(new Estudiante("89012345H", "Sofía", "López Hernández", "1980-10-08"));
            this.data.Add(new Estudiante("90123456I", "Pedro", "Gómez Pérez", "1978-04-29"));
            this.data.Add(new Estudiante("01234567J", "Elena", "Hernández Rodríguez", "1975-02-14"));
            this.data.Add(new Estudiante("12345678K", "Miguel", "Rodríguez Gómez", "1970-06-30"));
        }


        public Estudiante? findById(string id)
        {
            return data.FirstOrDefault(e => e.Dni == id);
        }

        public void Add(Estudiante estudiante)
        {

            if (!data.Any(e => e.Dni == estudiante.Dni))
            {
                data.Add(estudiante);
            }
        }

        public void removeById(string id)
        {
            data.RemoveAll(e => e.Dni == id);
        }

        public List<Estudiante> findAll()
        {
            return data;
        }

        public List<Estudiante> find(string? nombre, string? apellido, int? edad)
        {
            IQueryable<Estudiante> query = data.AsQueryable();

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
    }


}
