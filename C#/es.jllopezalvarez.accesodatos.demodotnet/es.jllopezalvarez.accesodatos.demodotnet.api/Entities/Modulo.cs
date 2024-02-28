namespace es.jllopezalvarez.accesodatos.demodotnet.api.Entities
{
    public class Modulo
    {
        public int Id { get; set; }
        public string Nombre { get; set; }
        public int Horas { get; set; }
        public  List<Estudiante> Estudiantes { get; set; }
    }
}
