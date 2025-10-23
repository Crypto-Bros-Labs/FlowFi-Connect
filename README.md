# FlowFi Connect
CB Flow Connect es un widget que permite a cualquier plataforma ofrecer on-ramp y off-ramp de MXNB integrando un solo enlace, nosotros nos encargamos del resto. Resolviendo la complejidad técnica y de cumplimiento mientras que para el partner es plug & play

---

## 📑 Índice

- [Inicio Rápido](#-inicio-rápido-api)
- [Ejecución de Proyecto Frontend (Vite + React)](#-ejecución-de-proyecto-frontend-vite--react)

---

## 🚀 Inicio Rápido API

### 1. **Clona el Repositorio**

```bash
git clone https://github.com/Crypto-Bros-Labs/bitso_hack.git
cd bitso_hack/backend/cb_flow_connect
```

---

### 2. **Configuración de la Base de Datos MySQL**

Antes de ejecutar el proyecto, asegúrate de tener una base de datos MySQL 8+ en funcionamiento.  
Crea una nueva base de datos y toma nota de los datos de conexión.

```sql
CREATE DATABASE nombre_de_tu_base_de_datos CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

---

### 3. **Configuración Rápida de Variables**

Para un arranque más rápido, reemplaza directamente las variables en el archivo  
`src/main/resources/application.properties` con tus datos reales, así evitarás depender de un archivo `.env`.

Ejemplo de cómo deben quedar las propiedades:

```properties
spring.application.name=cb_flow_connect_api

# FUENTE DE DATOS
spring.datasource.url=jdbc:mysql://localhost:3306/nombre_de_tu_base_de_datos?useSSL=false&serverTimezone=UTC
spring.datasource.username=usuario_de_base_de_datos
spring.datasource.password=contraseña_de_base_de_datos

# EMAIL
spring.mail.username=tu_correo_gmail@gmail.com
spring.mail.password=tu_contraseña_app_gmail

# CONFIGURACIÓN JWT
jwt.secret-key=tu_jwt_secret
jwt.token-prefix=Bearer 
jwt.token-expiration-after-days=7
jwt.refresh-token-expiration-after-days=30

# JUNO (API de Pagos)
juno.api-key=tu_juno_api_key
juno.api-secret=tu_juno_api_secret
```

**Nota:**  
- Reemplaza los valores por tus credenciales reales.
- Para Gmail, es posible que necesites generar una [Contraseña de Aplicación](https://support.google.com/accounts/answer/185833) si tienes 2FA habilitado.

---

### 4. **Ejecutar el Proyecto Localmente**

#### Usando Maven

```bash
./mvnw spring-boot:run
```

O, si tienes Maven instalado globalmente:

```bash
mvn spring-boot:run
```

#### Usando Gradle

Si el proyecto utiliza Gradle, ejecuta:

```bash
./gradlew bootRun
```

---

## 🐳 Ejecutar con Docker

### 1. **Construir la Imagen de Docker**

Luego de editar `application.properties`, construye la imagen de Docker desde la raíz del proyecto o dentro de `/backend/cb_flow_connect`:

```bash
docker build -t cb_flow_connect_api .
```

### 2. **Ejecutar el Contenedor Docker**

```bash
docker run -p 8080:8080 cb_flow_connect_api
```

- La aplicación estará disponible en `http://localhost:8080`
- Asegúrate de que tu base de datos MySQL sea accesible desde dentro del contenedor Docker (usa red de host o una red Docker si es necesario).

---

### 📝 Ejemplo de `Dockerfile`

Coloca esto en `/backend/cb_flow_connect/Dockerfile` si aún no existe:

```dockerfile
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**Nota:**  
- Asegúrate de empaquetar tu app Spring Boot como un JAR antes de construir:
  ```bash
  mvn clean package
  ```
  El JAR generado estará en `target/`.

---

## ⚙️ Referencia de Configuración

Propiedades clave que puedes necesitar cambiar en `application.properties`:

```properties
spring.application.name=cb_flow_connect_api

# FUENTE DE DATOS #
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/nombre_de_tu_base_de_datos?useSSL=false&serverTimezone=UTC
spring.datasource.username=usuario_de_base_de_datos
spring.datasource.password=contraseña_de_base_de_datos
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# EMAIL #
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=tu_correo_gmail@gmail.com
spring.mail.password=tu_contraseña_app_gmail
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# CONFIGURACIÓN JWT #
jwt.secret-key=tu_jwt_secret
jwt.token-prefix=Bearer 
jwt.token-expiration-after-days=7
jwt.refresh-token-expiration-after-days=30

# JUNO #
juno.api-key=tu_juno_api_key
juno.api-secret=tu_juno_api_secret
juno.base-url=https://stage.buildwithjuno.com
```

---

## 📝 Notas Adicionales

- **MySQL debe estar en ejecución** y accesible en red antes de iniciar la API.
- **Edita `application.properties`** con tus datos antes de ejecutar local o con Docker.
- **Puerto**: Por defecto es 8080. Cámbialo agregando `server.port=PUERTO` en el mismo archivo si lo necesitas.

---

## 🌐 Ejecución de Proyecto Frontend (Vite + React)

El frontend del proyecto se encuentra en la carpeta correspondiente y está construido con **Vite + React**.

### 1. Dirígete a la carpeta del frontend dentro del repositorio

```bash
cd ./frontend/cb-flow
```

(Asegúrate de ajustar la ruta si el frontend está en una ubicación diferente)

### 2. Crea el archivo `.env`

Debes crear un archivo llamado `.env` en la raíz del proyecto frontend con el siguiente contenido:

```env
VITE_API_URL=http://localhost:8080/
```

Esto permitirá que el frontend se comunique con el backend local.

### 3. Instala las dependencias

```bash
npm install
# o si usas yarn
yarn install
```

### 4. Ejecuta el proyecto en modo desarrollo

```bash
npm run dev
# o si usas yarn
yarn dev
```

El frontend estará disponible normalmente en [http://localhost:5173](http://localhost:5173) (o el puerto indicado por Vite).

---

**Recuerda:**  
- El frontend SÍ utiliza archivo `.env` (a diferencia del backend).
- Asegúrate de que el backend esté corriendo en `http://localhost:8080/` antes de iniciar el frontend.
- Puedes ver la aplicación web accediendo al puerto mostrado en consola tras ejecutar el comando de desarrollo.

---
