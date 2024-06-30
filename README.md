<div align="center">
  <h1 align="center">
    Challenge Literalura
  </h1>
  <h3> 
    Un Challenge de Alura - ONE
  </h3>
    <br />
  <img src="https://github.com/Facundo177/Challenge-Literalura__Spring-Boot/assets/99448005/193ae586-8d49-468a-8f3d-c8756fce28d4">
   <br />
   <br />
</div>

## Introducción

**Challenge realizado en [Java](https://docs.oracle.com/en/java/javase/17/docs/api/index.html)**

En Challenge Literatura tomamos el papel de un desarrollador back-end, creando una aplicación con conexión a una base de datos relacional.
Se hacen consultas a una [API](https://gutendex.com/) para obtener datos de los libros y autores consultados.
El usuario puede elegir entre las siete opciones disponibles en el menú o cerrar la aplicación. De dichas opciones, las primeras tres consultan a la API y guardan los datos en nuestra base de datos, mientras que las cuatro restantes traen los datos registrados mediante querys.  

## ¿Cómo se usa?
Se le presenta un menú al usuario que ejecuta el programa:

![image](https://github.com/Facundo177/Challenge-Literalura__Spring-Boot/assets/99448005/f34ea555-8841-4aa8-9fb9-9fe95215a889)

Las opciones y sus resultados son:

### Opción 1
![image](https://github.com/Facundo177/Challenge-Literalura__Spring-Boot/assets/99448005/883d0976-d444-4651-808d-aead13e21477)
### Opción 2
![image](https://github.com/Facundo177/Challenge-Literalura__Spring-Boot/assets/99448005/0f26488f-259c-420e-b91a-6b5ce811df44)
### Opción 3
![image](https://github.com/Facundo177/Challenge-Literalura__Spring-Boot/assets/99448005/504dd2e4-0f3f-41fd-9967-107e92dd5e46)
### Opción 4
![image](https://github.com/Facundo177/Challenge-Literalura__Spring-Boot/assets/99448005/83926336-070f-48c2-b5a2-905482e45cf5) <br />
![image](https://github.com/Facundo177/Challenge-Literalura__Spring-Boot/assets/99448005/a356ac4d-2021-443b-b211-81b1975ed03a)
### Opción 5
![image](https://github.com/Facundo177/Challenge-Literalura__Spring-Boot/assets/99448005/cdd074f6-20df-4057-9ad1-4442b19fe1c9) <br />
![image](https://github.com/Facundo177/Challenge-Literalura__Spring-Boot/assets/99448005/a265db53-2fca-4ca2-ab48-969064c281bb)
### Opción 6
![image](https://github.com/Facundo177/Challenge-Literalura__Spring-Boot/assets/99448005/6c570b58-87e8-44fc-bd94-81253f4afe15) <br />
![image](https://github.com/Facundo177/Challenge-Literalura__Spring-Boot/assets/99448005/c8538aff-c1cc-493f-8d5e-3b06e0966731)
### Opción 7
![image](https://github.com/Facundo177/Challenge-Literalura__Spring-Boot/assets/99448005/b0625ba3-aeed-4ab0-8822-1042732f8dbd) <br />
![image](https://github.com/Facundo177/Challenge-Literalura__Spring-Boot/assets/99448005/808f06ca-71d9-4e1c-ab33-61127e484443)

![image](https://github.com/Facundo177/Challenge-Literalura__Spring-Boot/assets/99448005/cee1a369-0a0d-4ae6-b9de-6fc74acb9668) <br />
![image](https://github.com/Facundo177/Challenge-Literalura__Spring-Boot/assets/99448005/6b8669f2-32d5-4cb4-a2a1-455ef1665579)

![image](https://github.com/Facundo177/Challenge-Literalura__Spring-Boot/assets/99448005/0afea30b-cfb0-49e1-8cca-9c69281ef3a6) <br />
![image](https://github.com/Facundo177/Challenge-Literalura__Spring-Boot/assets/99448005/31bf4286-8b09-4f90-9d41-6063091f5199)

![image](https://github.com/Facundo177/Challenge-Literalura__Spring-Boot/assets/99448005/d65ddb6e-3f40-4379-a208-49f5f19e7ec3) <br />
![image](https://github.com/Facundo177/Challenge-Literalura__Spring-Boot/assets/99448005/220a12c2-c234-45c5-ad7d-3af210504874)


## Este proyecto incluye:
- Uso de la API de libros Gutendex (https://gutendex.com/)
- Base de datos relacional con PostgreSQL
- Sping Boot con dependencias como Jackson, Spring Data JPA y PostgreSQL Driver para el manejo de los JSON y la persistencia de datos 
- Solicitudes y respuestas a la API con HttpClient, HttpRequest y HttpResponse
- Manipulación de un JSON
- Interacción con el usuario mediante Scanner
- Tablas de libros y autores, con una relación ManyToOne
- Aplicación de los conceptos de abstracción, encapsulamiento y polimorfismo

## Sobre el código
### La persistencia de datos fue implementada de la siguiente manera
Las entidades insertadas en la base de datos son:

![image](https://github.com/Facundo177/Challenge-Literalura__Spring-Boot/assets/99448005/06c2046d-d58e-4e4a-acb7-8adc65442003)

![image](https://github.com/Facundo177/Challenge-Literalura__Spring-Boot/assets/99448005/a767d5f6-ed22-445c-90b0-a91c3c44a385)

Con los siguientes repositorios y consultas:

![image](https://github.com/Facundo177/Challenge-Literalura__Spring-Boot/assets/99448005/d668c95d-22c8-415c-8061-6097b71c92fa)

![image](https://github.com/Facundo177/Challenge-Literalura__Spring-Boot/assets/99448005/012edf93-0a57-46dd-b494-9a11c47e995c)




