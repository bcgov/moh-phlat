package com.moh.phlat.backend.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.moh.phlat.backend.model.Control;


public interface ControlRepository extends JpaRepository<Control, Long> {
	public List<Control> findByFileName(String fileName);
	
	public List<Control> findAll(Specification<Control> spec);

	@Query(value="Select distinct id FROM control",nativeQuery=true)
	public List<String> findAllDistinctId();
	@Query(value="Select distinct file_name FROM control",nativeQuery=true)
	public List<String> findAllDistinctFileName();
	@Query(value="Select distinct user_id FROM control",nativeQuery=true)
	public List<String> findAllDistinctUserId();
	@Query(value="Select distinct file_extracted_date FROM control",nativeQuery=true)
	public List<String> findAllDistinctFileExtractedDate();
	@Query(value="Select distinct batch_label_name FROM control",nativeQuery=true)
	public List<String> findAllDistinctBatchLabelName();
	@Query(value="Select distinct load_type_facility FROM control",nativeQuery=true)
	public List<String> findAllDistinctLoadTypeFacility();
	@Query(value="Select distinct load_type_hds FROM control",nativeQuery=true)
	public List<String> findAllDistinctLoadTypeHds();
	@Query(value="Select distinct load_type_bus_org FROM control",nativeQuery=true)
	public List<String> findAllDistinctLoadTypeBusOrg();
	@Query(value="Select distinct load_type_o_f_relationship FROM control",nativeQuery=true)
	public List<String> findAllDistinctLoadTypeOFRelationship();
	@Query(value="Select distinct load_type_o_o_relationship FROM control",nativeQuery=true)
	public List<String> findAllDistinctLoadTypeOORelationship();
	@Query(value="Select distinct load_type_i_o_relationship FROM control",nativeQuery=true)
	public List<String> findAllDistinctLoadTypeIORelationship();
	@Query(value="Select distinct load_type_wl_org_xref FROM control",nativeQuery=true)
	public List<String> findAllDistinctLoadTypeWlOrgXref();
	@Query(value="Select distinct load_type_wl_prac_ident_xref FROM control",nativeQuery=true)
	public List<String> findAllDistinctLoadTypeWlPracIdentXref();
	@Query(value="Select distinct process_start_date FROM control",nativeQuery=true)
	public List<String> findAllDistinctProcessStartDate();
	@Query(value="Select distinct process_end_date FROM control",nativeQuery=true)
	public List<String> findAllDistinctProcessEndDate();
	@Query(value="Select distinct status_code FROM control",nativeQuery=true)
	public List<String> findAllDistinctStatusCode();
	@Query(value="Select distinct created_by FROM control",nativeQuery=true)
	public List<String> findAllDistinctCreatedBy();
	@Query(value="Select distinct created_at FROM control",nativeQuery=true)
	public List<String> findAllDistinctCreatedAt();
	@Query(value="Select distinct updated_by FROM control",nativeQuery=true)
	public List<String> findAllDistinctUpdatedBy();
	@Query(value="Select distinct updated_at FROM control",nativeQuery=true)
	public List<String> findAllDistinctUpdatedAt();
}
