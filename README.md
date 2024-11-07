<h1 align="center">📚 LiterAlura - Catálogo de Libros 📚</h1>

<p >
    Bienvenidos a <strong>LiterAlura</strong>, un proyecto del challenge de back-end de Oracle Next Education. Esta aplicación, desarrollada en <strong>Java Spring Boot</strong>, permite gestionar un catálogo de libros en una base de datos, consultando información directamente desde la API de Gutendex.
</p>

<p >
    <a href="#-descripción-del-proyecto">Descripción</a> •
    <a href="#-funcionalidades">Funcionalidades</a> •
    <a href="#-tecnologías-utilizadas">Tecnologías</a> •
    <a href="#-instalación-y-configuración">Instalación</a> •
    <a href="#-uso-de-la-aplicación">Uso</a> •
    <a href="#-contacto">Contacto</a>
</p>

---

## 📋 Descripción del Proyecto

**LiterAlura** es una aplicación de consola que permite a los usuarios buscar libros, registrarlos en una base de datos y consultar información relacionada. Los datos de los libros son obtenidos de la API de Gutendex (basada en el Proyecto Gutenberg), una biblioteca digital gratuita. Esta aplicación se enfoca en el back-end y en la gestión de datos, sin necesidad de un front-end.

## ⚙️ Funcionalidades

- **🔍 Buscar y registrar libro por título**: Busca un libro en la API y, si no está registrado, lo almacena en la base de datos con información como título, autor, idioma y número de descargas.
- **📑 Listar todos los libros registrados**: Muestra todos los libros almacenados en la base de datos.
- **👥 Listar todos los autores registrados**: Permite ver la lista de autores, mostrando cada uno solo una vez, incluso si tienen varios libros.
- **📅 Listar autores vivos en un año específico**: Muestra los autores que estaban vivos durante un año dado.
- **🌐 Listar libros por idioma**: Permite al usuario filtrar los libros por idioma usando códigos como `ES`, `EN`, `FR` y `PT`.

**Validaciones adicionales**:
- ✅ Notifica cuando no se encuentra un libro en la API.
- 🔄 Evita insertar el mismo libro dos veces en la base de datos.

## 💻 Tecnologías Utilizadas

- **Java** - versión 17
- **Spring Boot** - versión 3.2.4
- **Spring Data JPA** - para operaciones de persistencia
- **PostgreSQL** - base de datos relacional
- **Gutendex API** - para buscar información sobre libros

## 🔧 Instalación y Configuración

1. **Clona el repositorio**:

   ```bash
   git clone https://github.com/elimes29/litelaluray.git
   cd litelalura

## 🖥️ Uso de la Aplicación
Este proyecto es una aplicación de consola. Al iniciar, verás opciones para interactuar con el catálogo de libros:

**🔍Buscar y Registrar Libro**
Ingresa el título del libro. La aplicación buscará en la API de Gutendex y lo registrará en la base de datos si no existe.

**📑Listar Todos los Libros Registrados**
Muestra la lista de todos los libros en la base de datos.

**👥Listar Todos los Autores Registrados**
Muestra la lista de autores, evitando duplicados para aquellos con varios libros.

**📅Listar Autores Vivos en un Año Específico**
Escribe un año, y la aplicación mostrará autores vivos durante ese año.

**🌐Listar Libros por Idioma**
Filtra los libros según el idioma (EN, ES, FR, PT).

## 📬 Contacto
Para dudas o sugerencias, puedes contactar a Elimes Rodríguez:

**GitHub:** elimes29

**LinkedIn:** Elimes Rodríguez
