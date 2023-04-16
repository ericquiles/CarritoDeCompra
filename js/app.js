const carrito = [];

// Selecciono todos los botones "Agregar carrito"
const botonesAgregar = document.querySelectorAll(".agregar-carrito");

// Añad0 un evento "click" a cada botón seleccionado
botonesAgregar.forEach((boton) => {
  boton.addEventListener("click", agregarProductoAlCarrito);
});

// Guardo los datos de los productos en un objeto
function obtenerDatosProducto(curso) {
  const producto = {
    imagen: curso.querySelector("img").src,
    titulo: curso.querySelector("h4").textContent,
    precio: curso.querySelector(".precio span").textContent,
    id: curso.querySelector(".agregar-carrito").getAttribute("data-id"),
    cantidad: 1,
  };
  return producto;
}

// Agrego el objeto del producto al array del carrito
function agregarProductoAlCarrito(e) {
  e.preventDefault();
  const curso = e.target.parentElement.parentElement;
  const producto = obtenerDatosProducto(curso);

  const existe = carrito.some((productoEnCarrito) => {
    return productoEnCarrito.id === producto.id;
  });

  if (existe) {
    const productos = carrito.map((productoEnCarrito) => {
      if (productoEnCarrito.id === producto.id) {
        productoEnCarrito.cantidad++;
        return productoEnCarrito;
      } else {
        return productoEnCarrito;
      }
    });
    carrito.splice(0, carrito.length, productos);
  } else {
    carrito.push(producto);
  }

  // 5. Actualizar la tabla del carrito con los productos agregados y el botón para borrar el elemento correspondiente.
  actualizarCarrito();
}

function actualizarCarrito() {
  const tbody = document.querySelector("#lista-carrito tbody");
  tbody.innerHTML = "";

  carrito.forEach((producto) => {
    const { imagen, titulo, precio, cantidad, id } = producto;

    const fila = document.createElement("tr");
    fila.innerHTML = `
      <td><img src="${imagen}" width=100></td>
      <td>${titulo}</td>
      <td>${precio}</td>
      <td>${cantidad}</td>
      <td><a href="#" class="borrar-curso" data-id="${id}">X</a></td>
    `;
    tbody.appendChild(fila);
  });
}

// 6. Agregar un evento para vaciar el carrito
const botonVaciar = document.querySelector("#vaciar-carrito");
botonVaciar.addEventListener("click", vaciarCarrito);

function vaciarCarrito() {
  carrito.splice(0, carrito.length);
  actualizarCarrito();
}