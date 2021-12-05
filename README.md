# MeliTest - Coupon

Este es el repositorio para la solución del test de MercadoLibre de cupones que pueden redimir los usuarios.

### Api de la solución

En el enlace siguiente se encuentra alojada la solución del test:


```sh
http://couponmelitest.uc.r.appspot.com/api/coupon
```
### Ejecución de la api

Para la ejecución del api de la solución puede consultar el swagger con la documentación necesaria en el siguiente enlace:

```sh
http://couponmelitest.uc.r.appspot.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
```
Se realizó la prueba con el siguiente json:

```sh
{
    "itemIds": [
        "MCO501668687",
        "MCO515420765",
        "MCO500099080",
        "MCO487719207"
    ],
    "amount": 500000
}
```

![image](https://user-images.githubusercontent.com/95514404/144734567-79f50f11-1d73-4b1e-b0b6-5e8f05edb9cc.png)


la respuesta del servicio fue: 

```sh
{
    "itemIds": [
        "MCO515420765",
        "MCO500099080"
    ],
    "total": 304800.0,
    "notFoundItems": null
}
```
### Pruebas del api

Para Coupon Api ser realizarón pruebas unitarias que cubrieron el 80% de la capa de servicios
![image](https://user-images.githubusercontent.com/95514404/144734602-1306f1d9-aed6-409f-828b-9aec27b6d8de.png)

### Despliegue

Para realizar el despliegue de la aplicación es necesario clonar el siguiente repositorio 

```sh
https://github.com/OscarJGarcia/MeliTest.git
```
Para la ejecución del proyecto ubicarse dentro del directorio y realizar la ejecución de los siguientes comandos:
```sh
$ mvn clean package
$ cd target
$ java -jar coupon-meli-test.jar

Nota: Se debe tener configurada las variables de entorno y tener Java 11
```

