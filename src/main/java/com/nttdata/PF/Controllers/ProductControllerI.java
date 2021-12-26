package com.nttdata.PF.Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.nttdata.PF.Services.ProductDto;
import com.nttdata.PF.Services.ProviderDto;

/**
 * Interfaz asociada al controlador de los productos
 * 
 * @author agadelao
 *
 */
public interface ProductControllerI {

	/**
	 * Dirije a la pantalla principal de los productos
	 * 
	 * @return String
	 */
	public String productPage();

	/**
	 * Añade o actualiza un producto a/de la BBDD
	 * 
	 * @param productDto
	 * @return String
	 */
	public String addOrUpdateProduct(final ProductDto productDto);

	/**
	 * Elimina un producto de la BBDD
	 * 
	 * @param model, nameProduct
	 * @return String
	 */
	public String deleteProduct(Model model, @PathVariable String nameProduct);

	/**
	 * Muestra todos los productos
	 * 
	 * @param model
	 * @return String
	 */
	public String showAllProducts(Model model);

	/**
	 * Muestra un producto consultado por su nombre
	 * 
	 * @param model, nameProduct
	 * @return String
	 */
	public String searchProductByName(Model model, final String nameProduct);

}
