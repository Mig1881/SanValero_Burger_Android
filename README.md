# San Valero Burger Contest 🍔

### ¡Bienvenidos al concurso de San Valero Burger Contest!

Esta es una aplicación Android nativa desarrollada para gestionar y votar en el certamen de hamburguesas más importante de la ciudad. La app permite a los usuarios explorar Food Trucks, descubrir nuevas hamburguesas, gestionar las inscripciones del concurso y visualizar los detalles de cada participante.

El proyecto está diseñado siguiendo buenas prácticas de desarrollo móvil y está completamente internacionalizado.

---

## 📱 Funcionalidades Principales

La aplicación cuenta con una arquitectura **MVP (Model-View-Presenter)** y ofrece las siguientes características:

### 🚚 Gestión de Food Trucks
* **Listado de Food Trucks:** Visualiza todos los camiones participantes en una lista optimizada.
* **Detalle completo:** Consulta la descripción, valoración ⭐, teléfono 📞 (con enlace directo), email 📧 y si tienen reparto a domicilio.
* **Administración:** Crea, edita y elimina Food Trucks fácilmente con validaciones de entrada.

### 🍔 Gestión de Hamburguesas
* **Catálogo por Camión:** Explora las hamburguesas asociadas a cada Food Truck específico.
* **Ficha detallada:** Foto de alta calidad, lista de ingredientes, precio y fecha de alta.
* **Opciones dietéticas:** Indicador visual automático para hamburguesas **Veganas 🌱**.
* **CRUD completo:** Añade nuevas propuestas (con subida de imagen desde galería), actualiza precios/ingredientes o borra hamburguesas del concurso.

### 🌍 Internacionalización y UI/UX
* **Multi-idioma:** Soporte nativo completo para **Español 🇪🇸** e **Inglés 🇬🇧**. La app detecta automáticamente el idioma del dispositivo y adapta textos, formatos de moneda (€) y fechas.
* **Material Design:** Interfaz visual moderna utilizando componentes oficiales de Google (Cards, Floating Action Buttons, Ripples).
* **Feedback al usuario:** Mensajes de carga, diálogos de confirmación y alertas de error/éxito (Toast/Snackbars).

---

## 🛠️ Stack Tecnológico

Este proyecto ha sido construido utilizando las siguientes tecnologías y librerías:

* **Lenguaje:** Java (Android SDK).
* **Arquitectura:** MVP (Model - View - Presenter) para separar la lógica de negocio de la interfaz.
* **Red (API REST):** Retrofit 2 + Gson para la comunicación con el backend.
* **Imágenes:** Glide (carga y cacheo eficiente de imágenes).
* **Diseño:** Material Design Components, ConstraintLayout, CardView, RecyclerView.
* **Gestión de Dependencias:** Gradle (Kotlin DSL).

---

## 🚀 Guía de Instalación y Ejecución

Sigue estos pasos para ejecutar el proyecto en tu entorno local.

### 1. Requisitos Previos
* **Android Studio:** (Versión Ladybug o superior recomendada).
* **JDK:** Versión 11 o superior.
* **Backend:** Debes tener el servidor API REST (Java/Spring Boot) ejecutándose en tu máquina local en el puerto `8080`.

### 2. Clonar el Repositorio
Abre tu terminal y ejecuta el siguiente comando para descargar el código:
# [San Valero Burger Contest 🍔](https://github.com/Mig1881/SanValero_Burger_Android)





