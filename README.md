# TPO3
Practico Disp Moviles

1. Inicio de la aplicación
Al abrir la app, se muestra la pantalla principal (HomeFragment) con el título y descripción del proyecto.
El menú lateral (Navigation Drawer) permite navegar entre las opciones: Home, Cargar Producto, Listar Productos y Salir.
<hr></hr>
2. Navegación y estructura
La navegación entre pantallas se gestiona con el componente Navigation de Android y el menú lateral.
Cada opción del menú está vinculada a un Fragment específico:
HomeFragment: Pantalla principal con información del proyecto.
CargarProductoFragment: Permite ingresar un nuevo producto.
ListarProductoFragment: Muestra la lista de productos cargados.
Salir: Muestra un diálogo para cerrar la aplicación.
<hr></hr>
3. Cargar un producto (CargarProductoFragment)

a. Interfaz
El usuario ingresa el código, descripción y precio del producto en los campos correspondientes.
Al presionar el botón "Agregar Producto", se ejecuta el siguiente flujo:

b. Código ejecutado
Binding y obtención de datos
El fragment usa View Binding para acceder a los campos. Obtiene los valores ingresados por el usuario.
Conversión y validación
El valor del precio se convierte de texto a número. Si la conversión falla, se muestra un error usando LiveData.
Llamada al ViewModel
Se llama al método agregarProducto del CargarProductoViewModel, pasando los datos ingresados.
Validación en el ViewModel
El ViewModel valida:
- Que los campos no estén vacíos.
- Que el código no se repita.
- Que el precio sea mayor a cero.
Si hay error, actualiza el LiveData de error, que el fragmento observa y muestra en pantalla.
Aviso de éxito
Si el producto se agrega correctamente, el ViewModel emite un evento que el fragment observa y muestra un Toast de éxito.

<hr></hr>
4. Mostrar productos (ListarProductoFragment)
El fragmento observa el LiveData de productos del ListarProductoViewModel.
Cuando se agrega un producto, el LiveData se actualiza y el fragmento recibe la nueva lista.
Se usa un RecyclerView para mostrar los productos ordenados alfabéticamente por descripción.
Si la lista está vacía, se muestra un mensaje indicando que no hay productos.

<hr></hr>
5. Salir de la aplicación
Al seleccionar "Salir" en el menú, se muestra un diálogo de confirmación.
Si el usuario confirma, la aplicación se cierra.
