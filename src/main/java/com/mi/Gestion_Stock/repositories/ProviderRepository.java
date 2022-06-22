package com.mi.Gestion_Stock.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mi.Gestion_Stock.entities.*;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
	/*@Query("FROM Article a WHERE a.provider.id cr= ?1");
	/*List<Article> findArticlesByProvider(long id);*/
	@Query("select p FROM Provider p  where p.name like  :x")
	public Page<Provider> chercher(@Param("x") String mc,Pageable pageable);

}
