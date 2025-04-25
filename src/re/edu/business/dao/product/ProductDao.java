package re.edu.business.dao.product;

import re.edu.business.dao.AppDao;
import re.edu.business.model.Product;

import java.util.List;

public interface ProductDao extends AppDao<Product> {
    List<Product> findAll();
    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    Product findProductById(int id);
    boolean deleteProduct(Product product);
    List<Product> searchProductByBrand(Product product);
    List<Product> searchProductByPriceRange(double price_min, double price_max);
    List<Product> searchProductByStockAvailability(int start_stock, int end_stock);
    boolean isNameDuplicate(String name);
}
