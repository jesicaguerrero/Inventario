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
