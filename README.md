# Taller 1 – Refactorización del Proyecto Inventario con CDI y Managed Beans

## Objetivo
Modernizar el proyecto de inventario aplicando CDI (Contexts and Dependency Injection) y Managed Beans para mejorar el desacoplamiento, la claridad de responsabilidades y la preparación para migrar a JSF.

## Investigación Previa

## ¿Qué es un Managed Bean y en qué se diferencia de un JavaBean?

| Característica               | JavaBean                                                  | Managed Bean (CDI)                                              |
|-----------------------------|------------------------------------------------------------|------------------------------------------------------------------|
| **Definición**              | Clase POJO con atributos privados y métodos públicos.      | Objeto gestionado por el contenedor CDI.                        |
| **Gestión del ciclo de vida** | Manual (instanciación directa por el desarrollador).       | Automática (el contenedor crea, inyecta y destruye el bean).    |
| **Acceso desde la vista**   | No accesible directamente desde EL (`#{}`) en JSP/JSF.     | Accesible mediante `@Named` y EL (`#{bean}`) en JSP/JSF.        |
| **Inyección de dependencias** | No soporta inyección automática.                          | Soporta `@Inject`, `@Produces`, y otros mecanismos CDI.         |
| **Scopes disponibles**      | No tiene scopes definidos por el contenedor.               | Usa `@RequestScoped`, `@SessionScoped`, `@ApplicationScoped`, etc. |
| **Uso típico**              | Modelos simples, DTOs, entidades.                          | Controladores, servicios, validadores, beans de presentación.   |

**Resumen**:  
Un **JavaBean** es una clase simple que encapsula datos sin gestión automática.  
Un **Managed Bean** es una clase que el contenedor CDI administra, permitiendo inyección de dependencias, control de ciclo de vida y acceso desde la vista mediante EL.

- **Scopes CDI**:
  - `@ApplicationScoped`: Una instancia compartida por toda la aplicación. Ideal para servicios y fachada.
  - `@RequestScoped`: Vive durante una petición HTTP. Útil para mensajes y errores.
  - `@SessionScoped`: Persiste durante la sesión del usuario. Ideal para preferencias.
  - `@Dependent`: Ciclo de vida atado al consumidor. Útil para validadores y utilidades.
  - `@Named`: Permite acceder al bean desde la vista con EL.
    

 <img width="400" height="400" alt="image" src="https://github.com/user-attachments/assets/974bf139-6027-4f0a-baad-2d58f4905f50" /> 

 - Un Managed Bean es gestionado por el contenedor CDI y accesible desde la vista con EL.
- A diferencia de un JavaBean, permite inyección de dependencias y control de ciclo de vida.
- `@ApplicationScoped` se usa en la fachada para compartir una instancia en toda la aplicación.
- `@RequestScoped` se aplica en `MensajeBean` para manejar mensajes por petición.
- `@SessionScoped` se usa en `PreferenciasBean` para guardar idioma y filtros del usuario.
- `@Dependent` se aplica en `ValidadorProducto` para validar datos de forma reutilizable.
- Se usa `@Inject` para desacoplar la creación de DAO y otros beans.
- La vista JSP accede a los beans mediante EL (`#{mensajeBean.textoError}`).


##  Diagrama de Secuencia – Flujo “Crear producto”

<img width="600" height="600" alt="image" src="https://github.com/user-attachments/assets/0e56fd94-458d-489a-9a4e-17f2ffdbf891" />


Este diagrama representa el flujo de interacción entre los componentes CDI en el proceso de creación de un producto dentro del sistema de inventario. Cada elemento está asociado a un scope específico, lo que permite una arquitectura desacoplada y clara:

- **Usuario** inicia la acción desde la interfaz.
- El **Servlet/Controlador** (`@ApplicationScoped`) recibe la solicitud y coordina el proceso.
- Llama a la **fachada `ProductoFacade`**, también `@ApplicationScoped`, que:
  - Utiliza **`ValidadorProducto`** (`@Dependent`) para aplicar reglas de negocio.
  - Delega la persistencia a **`ProductoDAO`** (inyectado), que accede a la **base de datos**.
- Una vez procesado el resultado, se actualiza el estado del bean **`MensajeBean`** (`@RequestScoped`) con mensajes informativos o de error.
- Finalmente, la vista **JSP** muestra el contenido de `mensajeBean.textoError` o `mensajeBean.textoInfo`.

Este flujo demuestra cómo los distintos scopes CDI se integran para manejar validaciones, persistencia, mensajes y presentación de forma modular y eficiente.








 



