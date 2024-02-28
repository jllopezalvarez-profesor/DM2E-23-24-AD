using System.ComponentModel.DataAnnotations;

namespace es.jllopezalvarez.accesodatos.demodotnet.api.Entities
{
    public class Estudiante
    {
        [Key]
        public String Dni { get; set; }
        public String Nombre { get; set; }
        public String Apellidos { get; set; }
        public DateOnly FechaNacimiento { get; set; }

        public List<Modulo>  Modulos { get; set; }

        public Estudiante() { 
        }

        public Estudiante(string dni, string nombre, string apellidos, string fechaNacimiento)
        {
            this.Dni = dni;
            this.Nombre = nombre;
            this.Apellidos = apellidos;
            this.FechaNacimiento = DateOnly.Parse(fechaNacimiento);
        }

        public int Edad
        {
            get
            {
                var periodo = DateTime.Now - new DateTime(this.FechaNacimiento.Year, this.FechaNacimiento.Month, this.FechaNacimiento.Day);

                return (new DateTime(1, 1, 1) + periodo).Year - 1;
            }
        }

    }
}
