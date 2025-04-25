package re.edu.business.service.product;

import re.edu.business.dao.product.ProductDao;
import re.edu.business.dao.product.ProductDaoImp;
import re.edu.business.model.Product;

import java.util.List;

public class ProductServiceImp implements ProductService {
    private final ProductDao productDao;

    public ProductServiceImp() {
        productDao = new ProductDaoImp();
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public boolean addProduct(Product product) {
        return productDao.addProduct(product);
    }

    @Override
    public boolean updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    @Override
    public Product findProductById(int id) {
        return productDao.findProductById(id);
    }

    @Override
    public boolean deleteProduct(Product product) {
        return productDao.deleteProduct(product);
    }

    @Override
    public List<Product> searchProductByBrand(Product product) {
        return productDao.searchProductByBrand(product);
    }

    @Override
    public List<Product> searchProductByPriceRange(double price_min, double price_max) {
        return productDao.searchProductByPriceRange(price_min, price_max);
    }

    @Override
    public List<Product> searchProductByStockAvailability(int start_stock, int end_stock) {
        return productDao.searchProductByStockAvailability(start_stock, end_stock);
    }

    @Override
    public boolean isNameDuplicate(String name) {
        return productDao.isNameDuplicate(name);
    }
}
