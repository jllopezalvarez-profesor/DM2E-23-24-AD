using es.jllopezalvarez.accesodatos.demodotnet.api.Dto;
using es.jllopezalvarez.accesodatos.demodotnet.api.Entities;
using es.jllopezalvarez.accesodatos.demodotnet.api.Repositories;
using es.jllopezalvarez.accesodatos.demodotnet.api.Services;
using Microsoft.AspNetCore.Mvc;

namespace es.jllopezalvarez.accesodatos.demodotnet.api.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class EstudianteController : ControllerBase
    {
        private readonly EstudianteService estudianteService;

        public EstudianteController(EstudianteService estudianteService)
        {
            this.estudianteService = estudianteService;
        }

        [HttpGet]
        [Route("")]
        public ActionResult<ICollection<Estudiante>> findAll() {
            return Ok(estudianteService.findAll());
        }


        [HttpGet]
        [Route("{id}")]
        public ActionResult<Estudiante> findById(string id)
        {
            Estudiante? estudiante = estudianteService.findById(id);

            if (estudiante == null)
            {
                return NotFound();
            }


            return Ok(estudiante);
        }

        [HttpPost]
        public ActionResult Add(CreateEstudianteDto estudiante)
        {
            Estudiante nuevoEstudiante = new Estudiante(estudiante.Dni, estudiante.Nombre, estudiante.Apellidos, estudiante.FechaNacimiento);
            estudianteService.Add(nuevoEstudiante);
            return Created();
        }



        [HttpPost]
        [Route("find")]
        public ActionResult<ICollection<Estudiante>> Find(FindEstudianteRequestDto request)
        {
            List<Estudiante> estudiantes = estudianteService.find(request.Nombre, request.Apellido, request.Edad);
            return Ok(estudiantes);
        }

        [HttpDelete]
        [Route("{id}")]
        public ActionResult removeById(string id)
        {
            estudianteService.removeById(id);
            return Ok();
        }


    }

}
