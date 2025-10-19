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
 
    [Scopes CDI]
     |
     ├── @ApplicationScoped
     │     └── Fachada de productos, servicios compartidos
     ├── @RequestScoped
     │     └── Mensajes por petición (errores, info)
     ├── @SessionScoped
     │     └── Preferencias del usuario (idioma, filtros)
     └── @Dependent
           └── Validadores y utilidades ligadas al consumidor


