# Goodjob
Proyecto con el fin de promover un mejor cuidado del medio ambiente

## Contenido
* [Integrantes](#integrantes)
* [Historias de Usuario](#historias-de-usuario)
* [Prerequisitos](#prerequisitos)
* [Como colaborar](#instrucciones-para-colaborar)

## Integrantes
* [Cachi Ayala](https://github.com/Anthonyca18m/)
* [Castillo Aycachi](https://github.com/carlosjordi/)
* [Lizarzaburu Mercado](https://github.com/glizarzaburu/)
* [Pacheco Bada](https://github.com/pbada1996/)

## Historias de Usuario
#### Cachi Ayala	
- [x] Perfil de usuario	
- [ ] Mantenimiento de usuarios
#### Castillo Aycachi
- [x] Solicitud de registro como empresa
- [x] Aceptar/Rechazar Solicitudes de Empresas 	
- [x] Aceptación/Rechazo de actividad 
- [x] Publicar actividad	
- [x] Registro de productos canjeados
- [x] Canje de puntos
- [x] Aceptación/Rechazo de producto
- [ ] Reparto de recompensa	
#### Lizarzaburu Mercado
- [x] Inicio de sesión	
- [x] Aplicar a actividad
- [x] Estado de postulación a actividades	
- [x] Detalle de actividad aceptada
- [ ] Puntuación de compañeros 
#### Pacheco Bada
- [x] Registro como usuario 
- [x] Listado de actividades
- [x] Aceptación/Rechazo de aplicantes
- [ ] Mantenimiento de actividades	
- [ ] Insignias y medallas de perfil

## Prerequisitos

Para este proyecto solo necesitas:
* Android studio - descargalo de su [página oficial](https://developer.android.com/studio)


## Instrucciones para colaborar

1. Entran a la url https://github.com/carlosjordi/goodjob y le dan a la opción de la esquina superior derecha que dice 'Fork'

[fork]:https://i.gyazo.com/576cccd2d9c45ada41b72f7f1f9cef05.png "Fork"

>![alt text][fork]

2. Esto les dará una copia exacta del proyecto en su propio repositorio por lo cual ahora deben clonarlo en su Pc local, para ello le dan click al portapapeles que sale a la derecha de 'https://github.com/...'

[cloning]:https://i.gyazo.com/eb472523db3a22b4a793c67e0e7f1ab5.png "cloning"
>![alt text][cloning]

3. Abren la consola de git(de usar windows) o la terminal de linux ubicados en el lugar donde vayan a almacenar el repositorio del proyecto y escriben el siguiente comando:

```bash
git clone https://github.com/carlosjordi/goodjob.git GoodJobProject
```

Cabe recalcar que donde dice 'carlosjordi' irá su propio nombre de usuario de git, pero no es necesario que lo escriban a mano, ya que lo tienen copiado en el portapapeles, así que basta con click derecho -> pegar

4. El siguiente paso es ubicar la consola en la carpeta que contiene el proyecto:

```bash
cd GoodJobProject
```

5. Ya ubicados en esa carpeta el siguiente comando es:

```bash
git remote add main https://github.com/carlosjordi/goodjob
```

**OJO: este comando es tal cual, no deben cambiar el nombre de carlosjordi por ningún otro.**

6. Deben crear una rama donde harán sus propios cambios al proyecto, esta rama pueden llamarla como uds. Dentro de esta son libres de ramificar como prefieran, siempre y cuando nunca toquen la rama 'master' todo estará bien.

```bash
git checkout -b castillo
```

Como fue mencionado, donde dice 'castillo' pueden y deberían colocar su nombre/apellido

Una vez llegado a este paso ya hemos terminado todo para que empiecen a desarrollar de manera local sin tener que preocuparse, pero aún nos queda la parte de colaborar.

7. Cada vez que se considere necesario y sus avances estén :+1: uds harán uso del siguiente comando:

```bash
git push origin castillo
```

Nuevamente recalco, donde dice 'castillo' va el nombre de SUS ramas

8. Toca ir a sus repositorios de github, ya que una vez hayan ejecutado ese comando les aparecerá algo como esto:

[pull_request]:https://i.gyazo.com/e24937b233aa49d1a6d1abc1a70305ee.png "pull request"

>![alt text][pull_request]

9. Solo queda ubicarnos en la rama con nuestro nombre/apellido y darle click a alguno de los 'pull request'. Nos toparemos con la siguiente pantalla.

[open_pull_request]:https://i.gyazo.com/4e4944774a20d33f6e3a6e4f412a94a9.png "opening"

>![alt text][open_pull_request]

10. Solo debemos asegurarnos que diga **base repository:carlosjordi/goodjob base: master** y al lado derecho debe estar su repositorio junto al nombre de su rama. Terminan con el título y una descripción de lo hecho para que proceda a revisarlo y aceptarlo/rechazarlo.

11. Una vez sea aprobado, uds harán uso de este comando

```bash
git pull main master
```

Con esto se actualizarán a los cambios que tenga el repositorio principal, y este comando les será útil cada vez que cada uno de nosotros mande nuevos cambios, para estar al día con lo que ocurre.

Si notaron algún error hacermelo saber para corregirlo o en el mejor de los casos, corregirlo uds mismos y hacer el pull request para que se actualize en el repositorio principal. :+1:
