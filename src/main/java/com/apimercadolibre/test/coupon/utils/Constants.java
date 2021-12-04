package com.apimercadolibre.test.coupon.utils;

public final class Constants {

    public enum Messages {
        NOT_ITEM("No se encontró ningun item, por lo tanto no se puede calcular el monto del cupón"),
        MIN_AMOUNT("El valor de 'amount' es menor al precio de los items,por lo tanto no se puede calcular el monto del cupón"),
        NOT_FOUND("Los siguientes items no fuerón econtrados:"),
        ERROR_ITEMS("Error al consultar los items y el monto total"),
        ERROR_ALG("Error al ejecutar el algoritmo de encontrar items y monto tota");
        private final String message;
        private Messages(String message) {
            this.message = message;
        }
        public String getMessages() {
            return this.message;
        }
    }
}
