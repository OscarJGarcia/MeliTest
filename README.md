# MeliTest - Coupon

Este es el repositorio para la solucipon del test de MercadoLibre de cupones que pueden redimir los usuarios.

### Api de la solución

En el enlace siguiente se encuentra alojado la solucion del test:

---

### Ejecución de la api

Para la ejecucion el api de la solución puede consultar el swagger con la documentación necesaria en el siguiente enlace:

---

Se realizo la prueba con el siguiente json

```sh
{
    "item_ids": [
        "MCO501668687",
        "MCO515420765",
        "MCO500099080",
        "MCO487719207"
    ],
    "amount": 500000
}
```

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
