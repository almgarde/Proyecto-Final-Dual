package com.nttdata.PF.Services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.PF.Persistence.Product;
import com.nttdata.PF.Persistence.ProductRepositoryI;

/**
 * Servicios de gestión de los PRODUCTOS
 * 
 * @author agadelao
 *
 */
@Service
public class ProductServiceImpl implements ProductServiceI {

	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

	/** Métodos de gestión de los productos */
	@Autowired
	ProductRepositoryI productRep;

	/**
	 * Añadir un producto a la BBDD
	 * 
	 * @return ProductDto
	 */
	@Override
	public Product addProduct(final ProductDto productDto) {

		LOGGER.info("Inicio del servicio addProduct");

		Product productSaved = null;

		if (productDto != null) {

			if (NumberUtils.isCreatable(productDto.getPriceProduct())) {

				double priceNumber = NumberUtils.toDouble(productDto.getPriceProduct());

				// Verificación de integridad
				if (priceNumber > 0) {

					// Volcado de DTO a entidad
					Product p = new Product();
					p.setNameProduct(productDto.getNameProduct());
					p.setPriceProduct(priceNumber);

					// Guardado
					productSaved = productRep.save(p);
					LOGGER.debug(productSaved.toString());
					LOGGER.info("Producto almacenado correctamente");

				} else {
					LOGGER.error("El precio debe ser mayor que cero");
				}
			} else {
				LOGGER.error("El precio debe ser convertible a numérico");
			}

		} else {
			LOGGER.error("El productDto no puede ser nulo");
		}

		LOGGER.info("Fin del servicio addProduct");

		return productSaved;

	}

	/**
	 * Actualizar un producto de la BBDD
	 * 
	 * @return ProductDto
	 */
	@Override
	public Product updateProduct(final ProductDto productDto) {

		LOGGER.info("Inicio del servicio updateProduct");

		Product productUpdated = null;

		if (productDto != null) {

			if (NumberUtils.isCreatable(productDto.getPriceProduct())) {

				double priceNumber = NumberUtils.toDouble(productDto.getPriceProduct());

				// Verificación de integridad
				if (priceNumber > 0) {

					// Consulta a la BBDD
					Product productBbdd = productRep.findByNameProduct(productDto.getNameProduct());
					LOGGER.debug(productBbdd.toString());

					// Actualizado del producto
					productBbdd.setNameProduct(productDto.getNameProduct());
					productBbdd.setPriceProduct(priceNumber);

					// Guardado
					productUpdated = productRep.save(productBbdd);
					LOGGER.debug(productUpdated.toString());
					LOGGER.info("Producto actualizado correctamente");

				} else {
					LOGGER.error("El precio debe ser mayor que cero");
				}

			} else {
				LOGGER.error("El precio debe ser convertible a numérico");
			}
		} else {
			LOGGER.error("El productDto no puede ser nulo");
		}

		LOGGER.info("Fin del servicio updateProduct");

		return productUpdated;

	}

	/**
	 * Eliminar un producto de la BBDD
	 * 
	 * @return ProductDto
	 */
	@Override
	public void deleteProduct(final String nameProduct) {

		LOGGER.info("Inicio del servicio deleteProduct");

		if (nameProduct != null) {

			// Volcado de DTO a Entidad
			Product p = productRep.findByNameProduct(nameProduct);
			LOGGER.debug(p.toString());

			if (p != null) {
				// Borrado
				productRep.delete(p);
			} else {
				LOGGER.error("No se ha encontrado el producto");
			}

		} else {
			LOGGER.error("El nameProduct no puede ser nulo");
		}

		LOGGER.info("Fin del servicio deleteProduct");

	}

	/**
	 * Muestra todos los productos almacenados en la BBDD
	 * 
	 * @return List<ProductDto>
	 */
	@Override
	public List<ProductDto> showAllProducts() {

		LOGGER.info("Inicio del servicio showAllProducts");

		// Resultado
		List<ProductDto> productsDto = new ArrayList<ProductDto>();

		// Consulta a la base de datos
		List<Product> products = productRep.findAll();

		// Condición de integridad
		if (products != null && products.size() > 0) {

			// Volcado de Entidad a Dto
			for (Product p : products) {
				ProductDto productDto = new ProductDto();
				productDto.setNameProduct(p.getNameProduct());
				String priceString = String.valueOf(p.getPriceProduct());
				productDto.setPriceProduct(priceString);
				productsDto.add(productDto);
			}

		} else {
			LOGGER.error("No hay productos en la BBDD");
		}

		LOGGER.info("Fin del servicio showAllProducts");

		return productsDto;
	}

	/**
	 * Buscar un producto por su nombre
	 * 
	 * @return Product
	 */
	@Override
	public Product findByNameProduct(final String nameProduct) {

		LOGGER.info("Inicio del servicio findByNameProduct");

		// Resultado
		Product p = null;

		if (nameProduct != null) {

			// Consulta a la base de datos
			p = productRep.findByNameProduct(nameProduct);

			// Volcado de Entidad a DTO
			if (p != null) {
				ProductDto productDto = new ProductDto();
				productDto.setNameProduct(p.getNameProduct());
				String priceString = String.valueOf(p.getPriceProduct());
				productDto.setPriceProduct(priceString);
			} else {
				LOGGER.error("Este producto no se encuentra en la BBDD");
			}

		} else {
			LOGGER.error("El nameProduct no puede ser nulo");
		}

		LOGGER.info("Fin del servicio findByNameProduct");

		return p;
	}

}
