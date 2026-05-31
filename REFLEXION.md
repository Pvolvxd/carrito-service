Reflexión - Laboratorio TDD

¿Qué hubiera pasado si hubieras escrito el código de CartService completo antes de escribir las pruebas? ¿Qué habrías perdido?
Si hubiera escrito todo el código primero, habría perdido varias cosas fundamentales como:

Retroalimentación inmediata: Con TDD, cada línea de código que escribo tiene un propósito inmediato validado por una prueba que falla (Red) y luego pasa (Green). Si escribo todo junto, no tengo forma de saber si cada regla individual funciona hasta el final y si algo falla, es más difícil encontrar el error.
Prevención del sobre-diseño: Al escribir solo el código mínimo para que la prueba pase, evito escribir funcionalidades que "creo" que voy a necesitar pero que nadie pidió.
Seguridad al refactorizar: Al tener la suite de pruebas corriendo después de cada cambio (como cuando extraemos los números mágicos a constantes), tuve la confianza de que mi refactor no rompió nada. Sin pruebas previas, refactorizar es aterrador porque puedes introducir bugs sin darte cuenta.
Diseño orientado al uso: Las pruebas me obligaron a pensar primero en cómo quería usar la clase tipo los métodos y sus nombres antes de pensar en cómo implementarla internamente.

En resumen, habría perdido confianza en mi código, agilidad para encontrar errores y la seguridad de que cada regla de negocio está realmente cubierta.