package com.soycj.shopassist.crud.DAO;

import com.soycj.shopassist.crud.models.Items;
import javax.persistence.Persistence;

/**
 *
 * @author elnor
 */
public class ItemsDAO extends ItemsJpaController{
    public ItemsDAO(){
        super(Persistence.createEntityManagerFactory("com.soycj.shopassist.crud_SHOPASSIST-CRUD"));
    }
    
    public Items findByCode(String code)
    {
       return (Items)this.getEntityManager()
               .createQuery("SELECT i FROM Items i WHERE i.code = :code")
               .setParameter("code", code).getSingleResult();
    }
}
