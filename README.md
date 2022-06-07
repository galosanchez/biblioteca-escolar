# Biblioteca escolar
Es un **proyecto personal** para gestionar el control de los prestamos de libros en una biblioteca.

Mira el [diseño](https://www.figma.com/file/lVcD1lTxKimRTPKjiYo4hO/Biblioteca-Escolar?node-id=0%3A1) y el [diagrama](https://i.imgur.com/tndbiiu.jpg) entidad-relación para conocer el modelo de datos.

## Construido con 🔨

- [Apache NetBeans ](https://netbeans.apache.org) - IDE
- [MySQL ](https://www.mysql.com/) - Base de datos
- [Dia](http://dia-installer.de/index.html.es) - Creación de diagrama
- [Figma](https://www.figma.com/) - Creación del diseño

## Características

- Gestión de estudiantes, libros, bibliotecarios y préstamos.
- Filtros para encontrar prestamos.
- Usuario admin gestiona a los bibliotecarios.

## Comenzando 🚀
Correr el programa desde NetBeans.
### Pre-requisitos
*Versión de Mysql*
```
version 5.7
```
*Versión mínima JDK*
```
version 8
```
### Instalación 🔧
#### Base de datos
_Importa el archivo [backup_biblioteca_escolar](https://github.com/galosanchez/biblioteca_escolar/blob/master/backup_biblioteca_escolar.sql) en mysql._
#### Configuración de credenciales
_En el archivo [Conexion.java](https://github.com/galosanchez/biblioteca_escolar/blob/master/biblioteca_escolar_code/src/com/galosanchez/model/Conexion.java) edita el usuario y contraseña con tus credenciales de la base de datos._
```
private static final String USER = "tuUserName";
private static final String PASSWORD = "tuPassword";
```
#### Usuario administrador
*Al arrancar el programa las credenciales del adminstrados son:*
```
usuario: admin
contraseña: admin
```

---

👨‍💻 por [Galo Sánchez](https://github.com/galosanchez) 🖤
