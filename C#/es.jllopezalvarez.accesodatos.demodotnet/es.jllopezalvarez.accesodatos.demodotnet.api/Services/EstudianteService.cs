using es.jllopezalvarez.accesodatos.demodotnet.api.Entities;
using es.jllopezalvarez.accesodatos.demodotnet.api.Repositories;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace es.jllopezalvarez.accesodatos.demodotnet.api.Services
{
    public class EstudianteService
    {
        private readonly IEstudianteRepository estudianteRepository;

        public EstudianteService(IEstudianteRepository estudianteRepository)
        {
            this.estudianteRepository = estudianteRepository;
        }

        public Estudiante? findById(string id)
        {
            return estudianteRepository.findById(id);
        }

        public void Add(Estudiante estudiante)
        {
            estudianteRepository.Add(estudiante);
        }

        public void removeById(string id)
        {
            estudianteRepository.removeById(id);
        }

        public List<Estudiante> findAll()
        {
            return estudianteRepository.findAll();
        }

        public List<Estudiante> find(string? nombre, string? apellido, int? edad)
        {
            return estudianteRepository.find(nombre, apellido, edad);
        }
    }
}
