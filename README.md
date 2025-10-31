# TallerMasterChef

## **📖 Descripción**
API REST desarrollada en Spring Boot para la gestión de recetas del programa Máster Chef Celebrity. Permite a televidentes, participantes y chefs publicar y consultar recetas de cocina.

## **🚀 Características**
- ✅ 12 endpoints completos para gestión de recetas
- ✅ MongoDB Atlas como base de datos en la nube
- ✅ Documentación interactiva con Swagger/OpenAPI
- ✅ Validación de datos integrada
- ✅ CI/CD automático con GitHub Actions
- ✅ Despliegue en Azure Web Apps

## **🛠 Tecnologías**
- **Backend:** Java 17 + Spring Boot 3.5.7
- **Base de datos:** MongoDB Atlas
- **Documentación:** Swagger/OpenAPI 3
- **CI/CD:** GitHub Actions
- **Cloud:** Azure Web Apps
- **Build:** Maven

---

## **📚 Documentación de la API**

### **🔗 URLs de Acceso**
- **🔧 Local:** `http://localhost:8080/swagger-ui.html`
- **🌐 Producción:** `https://master-chef-api.azurewebsites.net/swagger-ui.html`

### **📋 Endpoints Disponibles**

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/recipes/viewer` | Registrar receta de televidente |
| POST | `/api/recipes/participant` | Registrar receta de participante |
| POST | `/api/recipes/chef` | Registrar receta de chef |
| GET | `/api/recipes` | Obtener todas las recetas |
| GET | `/api/recipes/{id}` | Obtener receta por ID |
| GET | `/api/recipes/type/viewer` | Recetas de televidentes |
| GET | `/api/recipes/type/participant` | Recetas de participantes |
| GET | `/api/recipes/type/chef` | Recetas de chefs |
| GET | `/api/recipes/season/{season}` | Recetas por temporada |
| GET | `/api/recipes/search/ingredient` | Buscar por ingrediente |
| PUT | `/api/recipes/{id}` | Actualizar receta |
| DELETE | `/api/recipes/{id}` | Eliminar receta |

---

## **🍽 Ejemplos de Uso en Swagger**

### **1. 📝 Registrar Receta de Televidente**
```json
{
  "title": "Ensalada César Casera",
  "ingredients": [
    "Lechuga romana",
    "Pollo a la parrilla", 
    "Queso parmesano rallado",
    "Crutones caseros",
    "Salsa César"
  ],
  "preparationSteps": [
    "Lavar y cortar la lechuga en trozos",
    "Cocinar el pollo y cortar en tiras",
    "Mezclar todos los ingredientes en un bowl",
    "Agregar la salsa César y mezclar suavemente",
    "Servir inmediatamente"
  ],
  "chefName": "María González"
}
```

### **2. 🏆 Registrar Receta de Participante**
```json
{
  "title": "Risotto de Champiñones Silvestres",
  "ingredients": [
    "Arroz arbóreo",
    "Champiñones silvestres",
    "Caldo de verduras",
    "Vino blanco",
    "Cebolla picada",
    "Queso parmesano",
    "Mantequilla",
    "Aceite de oliva"
  ],
  "preparationSteps": [
    "Sofreír la cebolla en aceite de oliva",
    "Agregar los champiñones y saltear",
    "Incorporar el arroz y tostar por 2 minutos",
    "Añadir el vino blanco y dejar evaporar",
    "Agregar el caldo caliente poco a poco, moviendo constantemente",
    "Cocinar por 18-20 minutos hasta que esté cremoso",
    "Incorporar mantequilla y queso parmesano al final"
  ],
  "chefName": "Carlos Rodríguez",
  "season": "Temporada 5"
}
```

### **3. 👨‍🍳 Registrar Receta de Chef**
```json
{
  "title": "Salmón a la Mantequilla de Limón y Eneldo",
  "ingredients": [
    "Filetes de salmón",
    "Limón",
    "Mantequilla sin sal",
    "Eneldo fresco",
    "Ajo",
    "Vino blanco",
    "Aceite de oliva",
    "Sal y pimienta"
  ],
  "preparationSteps": [
    "Sazonar los filetes de salmón con sal y pimienta",
    "Sellar el salmón en sartén con aceite de oliva",
    "Preparar la salsa derritiendo mantequilla con ajo",
    "Añadir jugo de limón, vino blanco y eneldo picado",
    "Cocinar hasta que la salsa espese ligeramente",
    "Bañar el salmón con la salsa y servir caliente"
  ],
  "chefName": "Chef Antonio"
}
```

### **4. 🔍 Buscar Recetas por Ingrediente**
```
GET /api/recipes/search/ingredient?ingredient=pollo
```

### **5. 📅 Obtener Recetas por Temporada**
```
GET /api/recipes/season/Temporada%205
```

---

## **🚀 Instalación y Ejecución Local**

### **Prerrequisitos**
- Java 17 o superior
- Maven 3.6+
- Cuenta en MongoDB Atlas
- Git

### **Pasos para Ejecutar Localmente**

1. **Clonar el repositorio:**
```bash
git clone https://github.com/tu-usuario/master-chef-api.git
cd master-chef-api
```

2. **Configurar base de datos:**
   - Crear cuenta en [MongoDB Atlas](https://www.mongodb.com/atlas)
   - Crear cluster gratuito
   - Obtener cadena de conexión

3. **Configurar aplicación:**
Editar `src/main/resources/application.properties`:
```properties
spring.data.mongodb.uri=mongodb+srv://usuario:contraseña@cluster.mongodb.net/master-chef-recipes?retryWrites=true&w=majority
```

4. **Ejecutar la aplicación:**
```bash
# Compilar y ejecutar
mvn spring-boot:run

# O compilar y ejecutar el JAR
mvn clean package -DskipTests
java -jar target/master-chef-api-0.0.1-SNAPSHOT.jar
```

5. **Acceder a la aplicación:**
   - API: `http://localhost:8080`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`

---

## **☁️ Despliegue en Azure**

### **Opción 1: Despliegue Automático (Recomendado)**

1. **El pipeline CI/CD se ejecuta automáticamente** al hacer push a `main`
2. **La aplicación se despliega en:** `https://master-chef-api.azurewebsites.net`

### **Opción 2: Despliegue Manual**

```bash
# Compilar para producción
mvn clean package -DskipTests

# Desplegar usando Azure CLI
az webapp deploy --name master-chef-api --resource-group tu-grupo --src-path target/master-chef-api-0.0.1-SNAPSHOT.jar
```

---

## **🔧 Configuración de Entorno**

### **Variables de Entorno para Producción**
```properties
MONGODB_URI=mongodb+srv://usuario:contraseña@cluster.mongodb.net/master-chef-recipes
SPRING_PROFILES_ACTIVE=prod
WEBSITES_PORT=8080
```

---

## **🧪 Pruebas**

```bash
# Ejecutar pruebas unitarias
mvn test

# Ejecutar pruebas con cobertura
mvn jacoco:report

# Compilar sin ejecutar pruebas
mvn clean compile -DskipTests
```

---

## **📊 CI/CD Pipeline**

El pipeline incluye:
- ✅ **Validación de código** en push/PR a develop
- ✅ **Compilación y empaquetado** automático
- ✅ **Despliegue automático** a Azure en push a main
- ✅ **Verificación de calidad** del código

### **Estado del Pipeline:**
![CI/CD Pipeline](https://github.com/tu-usuario/master-chef-api/workflows/Master%20Chef%20CI/CD%20Pipeline/badge.svg)
