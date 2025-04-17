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
}
