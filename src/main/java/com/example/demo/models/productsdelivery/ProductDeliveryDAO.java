package com.example.demo.models.productsdelivery;

import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductDeliveryDAO {

//    @PersistenceContext
//    EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public ProductDeliveryDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public List<ProductDelivery> getList(){
        return entityManager.createQuery("FROM ProductDelivery").getResultList();
    }

    public ProductDelivery findOne(Long id){
        return (ProductDelivery) entityManager.find(ProductDelivery.class, id);
     }

     @Transactional
    public ProductDelivery save(ProductDelivery productDelivery){

        entityManager.getTransaction().begin();
        ProductDelivery result = null;
        Long deliveryId = productDelivery.getDeliveryId();
        if(deliveryId == null){
            entityManager.persist(productDelivery);
            entityManager.flush();
            result = productDelivery;
        } else {
            entityManager.merge(productDelivery);
            entityManager.flush();
            result = productDelivery;
        }
        result = entityManager.find(ProductDelivery.class, result.getDeliveryId());
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

}
