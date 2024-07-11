package com.moh.phlat.backend.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moh.phlat.backend.model.Control;


public interface ControlRepository extends JpaRepository<Control, Long> {
	public List<Control> findByFileName(String fileName);
	
	/* According to these:
	 * https://www.baeldung.com/spring-data-jpa-pagination-sorting#:~:text=The%20findAll(Pageable%20pageable)%20method,custom%20methods%20returning%20paginated%20data.
	 * https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/PagingAndSortingRepository.html
	 * 
	 * pageable returns a Page list.
	 * 
	 * Added in specification to allow for a list of controls to be returned.
	 */
	public List<Control> findAll(Specification<Control> spec, Pageable pageable);
}
