package re.edu.business.dao.product;

import re.edu.business.dao.AppDao;
import re.edu.business.model.Product;

import java.util.List;

public interface ProductDao extends AppDao<Product> {
    List<Product> findAll();
    boolean addProduct(Product product);


}
