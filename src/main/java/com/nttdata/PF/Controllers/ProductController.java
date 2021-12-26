package com.nttdata.PF.Controllers;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nttdata.PF.Persistence.Product;
import com.nttdata.PF.Services.ProductDto;
import com.nttdata.PF.Services.ProductServiceI;

/**
 * Controlador de los productos
 * 
 * @author agadelao
 *
 */
@Controller
@RequestMapping("/home/product")
public class ProductController implements ProductControllerI {

	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	/** Servicio de gestión de los productos */
	@Autowired
	ProductServiceI productService;

	/**
	 * Dirije a la pantalla principal de los productos
	 * 
	 * @return String
	 */
	@Override
	@GetMapping
	public String productPage() {
		return "/product";
	}

	/**
	 * Añade o actualiza un producto a/de la BBDD
	 * 
	 * @param productDto
	 * @return String
	 */
	@Override
	@PostMapping("/addProduct")
	public String addOrUpdateProduct(final ProductDto productDto) {

		LOGGER.info("Inicio del método del controlador addOrUpdateProduct");

		// Vista resultante.
		String viewResult = "Errors";

		// Verificación de integridad.
		if (productDto != null && productDto.getNameProduct() != null
				&& NumberUtils.isCreatable(productDto.getPriceProduct())
				&& NumberUtils.toDouble(productDto.getPriceProduct()) > 0) {

			if (productService.findByNameProduct(productDto.getNameProduct()) == null) {
				// Invocación a servicio de registro.
				Product productSaved = productService.addProduct(productDto);
				LOGGER.debug(productSaved.toString());
			} else {
				// Invocación a servicio de actualizado
				Product productUpdated = productService.updateProduct(productDto);
				LOGGER.debug(productUpdated.toString());
			}

			viewResult = "redirect:././";
		}

		LOGGER.info("Fin del método del controlador addOrUpdateProduct");

		return viewResult;
	}

	/**
	 * Elimina un producto de la BBDD
	 * 
	 * @param model, nameProduct
	 * @return String
	 */
	@Override
	@GetMapping("/deleteProduct/{nameProduct}")
	public String deleteProduct(Model model, @PathVariable String nameProduct) {

		LOGGER.info("Inicio del método del controlador deleteProduct");

		// Invocación a servicio de borrado
		productService.deleteProduct(nameProduct);

		LOGGER.info("Fin del método del controlador deleteProduct");

		return "redirect:/home/product/";

	}

	/**
	 * Muestra todos los productos
	 * 
	 * @param model
	 * @return String
	 */
	@Override
	@GetMapping("/showAllProducts")
	public String showAllProducts(Model model) {

		LOGGER.info("Inicio del método del controlador showAllProducts");

		// Vista resultante.
		String viewResult = "Errors";

		// Invocación a servicio de consulta de juegos.
		final List<ProductDto> productsResult = productService.showAllProducts();

		if (productsResult != null) {
			model.addAttribute("products", productsResult);
			viewResult = "/productList";
		} else {
			LOGGER.error("Se ha obtenido una lista nula");
		}

		LOGGER.info("Fin del método del controlador showAllProducts");

		return viewResult;
	}

	/**
	 * Muestra un producto consultado por su nombre
	 * 
	 * @param model, nameProduct
	 * @return String
	 */
	@Override
	@PostMapping("/searchProduct")
	public String searchProductByName(Model model, final String nameProduct) {

		LOGGER.info("Inicio del método del controlador searchProductByName");

		// Vista resultante.
		String viewResult = "Errors";

		// Invocación a servicio de registro.
		Product p = productService.findByNameProduct(nameProduct);

		// Verificación de integridad.
		if (p != null) {
			model.addAttribute("productData", p);
			viewResult = "/cardProduct";
		} else {
			viewResult = "notFound";
		}

		LOGGER.info("Fin del método del controlador searchProductByName");

		return viewResult;
	}

}
