# 🛠️ Sistema de Gestión de Productos con Hibernate

Aplicación en Java basada en consola para gestionar productos usando **Hibernate**, **MySQL** y una **arquitectura en capas** (Controller, Service, DAO, DTO).
La cual es una migracion del proyecto[ ProductManagement con JDBC](https://github.com/kritzanyeraldin/ProductManagement )
---

## 📚 Funcionalidades

- Agregar nuevos productos
- Listar todos los productos
- Actualizar productos (actualización parcial soportada)
- Eliminar productos (con validación de existencia)
- Buscar producto por ID
- Manejo de errores de entrada y conexión a base de datos

---

## 🧱 Arquitectura

El proyecto sigue una estructura por capas:

```plaintext
ProductManagement/
├── src/
│   └── main/
│       └── java/
│           ├── controller/
│           │   └── ProductController.java
│           ├── dao/
│           │   ├── ProductDao.java
│           │   └── ProductDaoImpl.java
│           ├── dto/
│           │   ├── ProductCreateDto.java
│           │   ├── ProductResponseDto.java
│           │   └── ProductUpdateDto.java
│           ├── model/
│           │   └── Product.java
│           ├── service/
│           │   └── ProductService.java
│           ├── util/
│           │   ├── ErrorCode.java
│           │   └── HibernateSessionManager.java
│           └── main/
├── .gitignore
├── docker-compose.yml
├── init.sql
├── pom.xml
└── README.md
```
---

## 🛠️ Tecnologías Utilizadas

- Java 17
- Hibernate ORM
- MySQL (puede adaptarse a cualquier RDBMS compatible con Hibernate)
- Maven
- Arquitectura en capas (Controller, Service, DAO, DTO, Model)

---


## 🚀 Cómo ejecutar

### 1. Clona el repositorio

Puedes clonar el repositorio con HTTPS o SSH:

#### 🔐 Vía HTTPS

```bash
git clone https://github.com/kritzanyeraldin/ProductManagementHibernate.git
cd ProductManagementHibernate
```

#### 🔐 Vía SSH

```bash
git clone git@github.com:kritzanyeraldin/ProductManagementHibernate.git
cd ProductManagementHibernate
```

### 2. Inicia la base de datos con Docker Compose (opcional)
Asegúrate de tener Docker y Docker Compose instalados.
Desde la misma carpeta donde está el archivo docker-compose.yml, ejecuta:
```bash
docker-compose up -d
```
Esto levantará un contenedor de MySQL con la base de datos productdb en el puerto 3306.

Para detener o eliminar el contenedor, se ejecuta
```bash
docker-down
```

Finalmente, ejecuta el proyecto




