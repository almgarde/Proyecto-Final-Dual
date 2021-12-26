package com.nttdata.PF.Services;

import java.util.List;

import com.nttdata.PF.Persistence.Product;

/**
 * Interfaz de los Servicios de gestión de los productos
 * 
 * @author agadelao
 *
 */
public interface ProductServiceI {

	/** Añadir un producto a la BBDD */
	public Product addProduct(final ProductDto productDto);

	/** Añadir o actualizar un producto a la BBDD */
	public Product updateProduct(final ProductDto productDto);

	/** Eliminar un producto de la BBDD */
	public void deleteProduct(final String nameProduct);

	/** Muestra todos los productos almacenados en la BBDD */
	public List<ProductDto> showAllProducts();

	/** Buscar un producto por su nombre */
	public Product findByNameProduct(final String nameProduct);

}
