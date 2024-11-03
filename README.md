<h1 align="center">Best-Effort</h1>

### Sistema de Logística y Distribución para Optimización de Traslados

<p align="center">
    <img src="https://visionindustrial.com.mx/wp-content/uploads/2020/08/LOGISTICA-Y-DISTRIBUCION-ch.jpg" alt="Logística y Distribución">
</p>

**BestEffort S.A.** es una empresa de logística y distribución que se especializa en entregar pedidos de manera eficiente en diversas ciudades de la provincia de Buenos Aires. Cada pedido implica el traslado de productos de una ciudad a otra.

### Descripción del Sistema

La red de ciudades en las que opera BestEffort se modela como un **grafo completo** (ver imagen inferior), es decir, existe un camino directo entre cada par de ciudades. Además, estos caminos son de menor costo, convirtiéndolos en la opción ideal para los traslados.

<p align="center">
    <img src="https://a0.anyrgb.com/pngimg/422/1690/grafo-semplice-simplex-graph-dodecagram-ordered-pair-regular-graph-aresta-directed-graph-complete-graph-graph-theory-regular-polygon.png" alt="Grafo Completo de Ciudades" width=350 height=320>
</p>

### Funcionalidades

Este sistema proporciona una serie de herramientas para facilitar la logística de BestEffort S.A. y optimizar el despacho de traslados. Sus principales funcionalidades incluyen:

- **Registro de traslados**: Mantiene un registro actualizado de todos los traslados de productos entre ciudades.
  
- **Despacho de traslados**: Implementa una política que prioriza dinámicamente los traslados:
  - **Más rentables**: Despacha primero aquellos traslados que generen mayores beneficios.
  - **Pendientes de entrega más antiguos**: Asegura la entrega de pedidos antiguos para evitar inanición, garantizando que todos los traslados se despachen en tiempo y forma.

- **Recolección de estadísticas por ciudad**: Permite recolectar y analizar estadísticas relevantes de cada ciudad, como ganancias, pérdidas y el balance de traslados realizados.

Este sistema optimiza las operaciones logísticas mediante el uso de estructuras de datos avanzadas, diseñadas específicamente para manejar y priorizar traslados en tiempo real, adaptándose a las necesidades cambiantes de la empresa.

-----------
> [!NOTE] 
> Este proyecto fue diseñado como una simulación académica de un sistema de logística eficiente, aplicando principios de estructuras de datos y algoritmos. No se recomienda su implementación directa en entornos comerciales sin realizar un análisis exhaustivo del mercado y las necesidades específicas de la empresa. Para un desarrollo en el mundo real, sería necesario evaluar los sistemas actuales y adaptar el software a los requerimientos específicos de la organización.
