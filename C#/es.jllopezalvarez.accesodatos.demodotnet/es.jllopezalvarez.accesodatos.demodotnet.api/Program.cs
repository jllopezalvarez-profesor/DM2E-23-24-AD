
using es.jllopezalvarez.accesodatos.demodotnet.api.Db;
using es.jllopezalvarez.accesodatos.demodotnet.api.Repositories;
using es.jllopezalvarez.accesodatos.demodotnet.api.Services;
using Microsoft.EntityFrameworkCore;

namespace es.jllopezalvarez.accesodatos.demodotnet.api
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var builder = WebApplication.CreateBuilder(args);

            // Configurar los servicios en el contenedor de inyecci�n de dependencias
            // Explicaci�n de Transient / Scoped / Singleton:
            // https://blog.almarag.com/scoped-vs-singleton-vs-transient-cual-usar/ 
            // https://medium.com/@susithapb/understanding-singleton-scoped-and-transient-in-net-core-b7efede6c513
            // Art�culo m�s general sobre inyecci�n de dependencias en ASP.Net Core:
            // https://learn.microsoft.com/en-us/aspnet/core/fundamentals/dependency-injection?view=aspnetcore-8.0

            // Servicio de base de datos. Tiene su propio m�todo para configurarse (AddDbContext).
            // Este m�todo tiene una sobrecarga que permite usar una acci�n (lambda) para configurar la BD.
            // Si usamos esta sobrecarga, el DBContext que configuremos tiene que tener un constructor con un
            // formato espec�fico, que recibe un objeto DbContextOptions<tipo-de-contexto-registrado>
            builder.Services.AddDbContext<DemoDotNetDbContext>(options => options.UseInMemoryDatabase(databaseName: "EstudiantesDb"));

            // En el servicio "EstudianteService" estamos inyectando no una clase sino un objeto de la interfaz IEstudianteRepository
            // La sobrecarga del m�todo AddScoped que usamos en este caso est� indicando que cuando se tenga que inyectar un objeto
            // de la interfaz IEstudianteRepository queremos que se cree un objeto de la clase EstudianteDbRepository
            builder.Services.AddScoped<IEstudianteRepository, EstudianteDbRepository>();
            // Si cambiamos en la l�nea anterior EstudianteDbRepository por EstudianteCollectionRepository, 
            // cambiar�amos la implementaci�n de la interfaz utilizada. Esto permite cambiar unas "versiones"
            // de los servicios por otra para pruebas, seg�n el entorno (desarrollo, pro). etc. En este caso
            // la instrucci�n para registrar el servicio ser�a:
            // builder.Services.AddScoped<IEstudianteRepository, EstudianteCollectionRepository>();

            // Como el servicio EstudianteService no tiene una interfaz definida, se usa la sobrecarga que recibe
            // directamente la clase que act�a como servicio
            builder.Services.AddScoped<EstudianteService>();

            // Esto recorre todos los controladores para inyectarlos como servicios
            builder.Services.AddControllers();

            // Configuraci�n de servicios relacionados con swagger / OpenAPI
            // Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
            builder.Services.AddEndpointsApiExplorer();
            builder.Services.AddSwaggerGen();

            // Construimos la aplicaci�n. Aqu� es donde saltan errores si tenemos algo mal.
            // Por ejemplo, si inyectamos un servicio transient o scoped en un singleton.
            var app = builder.Build();

            // Aqu� se activan car�ter�sticas del servicio.
            // Configure the HTTP request pipeline.
            if (app.Environment.IsDevelopment())
            {
                // Esto hace que se tenga una URL en la que se genere el json con los servicios (endpoints) disponibles
                app.UseSwagger();
                // Esto habilita la interfaz web de swagger para pruebas.
                app.UseSwaggerUI();
            }

            // Esto permite que las peticiones HTTP se redirija autom�ticamente a HTTPS.
            // Est� porque al crear el proyecto de API activamos el uso de HTTPS.
            app.UseHttpsRedirection();

            // Aunque no hemos pedido que se autorice ninguna petici�n, por defecto se activa esta caracter�stica. 
            // M�s informaci�n sobre la seguridad en .Net Core: https://learn.microsoft.com/en-us/aspnet/core/security/?view=aspnetcore-8.0
            app.UseAuthorization();

            // Esto es lo que realmente genera los endpoints, lo que deja la app lista
            // para atender las peticiones en los distintos mappings (Java) / rutas (.net)
            app.MapControllers();

            // Arrancar la aplicaci�n
            app.Run();
        }
    }
}
