using es.jllopezalvarez.accesodatos.demodotnet.api.Entities;

namespace es.jllopezalvarez.accesodatos.demodotnet.api.Repositories
{
    public interface IEstudianteRepository
    {
        void Add(Estudiante estudiante);
        List<Estudiante> find(string? nombre, string? apellido, int? edad);
        List<Estudiante> findAll();
        Estudiante? findById(string id);
        void removeById(string id);
    }
}