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

	public static Specification<Control> buildSpecificationIn(List<String> ids, List<String> fileName, List<String> userIds, 
			List<String> fileExtractedDates, List<String> batchLabelNames, List<String> loadTypeFacilitys, List<String> loadTypeHds, 
			List<String> loadTypeBusOrgs, List<String> loadTypeOFRelationships, List<String> loadTypeOORelationships, 
			List<String> loadTypeIORelationships, List<String> loadTypeWlOrgXrefs, List<String> loadTypeWlPracIdentXrefs, List<String> processStartDates, 
			List<String> processEndDates, List<String> statusCodes, List<String> createdBy, List<String> createdAt, List<String> updatedBy, 
			List<String> updatedAt) {

		Specification<Control> combinedSpecification = null;

		if(ids != null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
					root.get("id").in(ids));
		}
		if(fileName != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("fileName").in(fileName));
		} else if (fileName != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("fileName").in(fileName));
		}
		if(userIds != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("userIds").in(userIds));
		} else if (userIds != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("userId").in(userIds));
		}
		if(fileExtractedDates != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("fileExtractedDate").in(fileExtractedDates));
		} else if (fileExtractedDates != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("fileExtractedDate").in(fileExtractedDates));
		}
		if(batchLabelNames != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("batchLabelName").in(batchLabelNames));
		} else if (batchLabelNames != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("batchLabelName").in(batchLabelNames));
		}
		if(loadTypeFacilitys != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("loadTypeFacility").in(loadTypeFacilitys));
		} else if (loadTypeFacilitys != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("loadTypeFacility").in(loadTypeFacilitys));
		}
		if(loadTypeHds != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("loadTypeHd").in(loadTypeHds));
		} else if (loadTypeHds != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("loadTypeHd").in(loadTypeHds));
		}
		if(loadTypeBusOrgs != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("loadTypeBusOrg").in(loadTypeBusOrgs));
		} else if (loadTypeBusOrgs != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("loadTypeBusOrg").in(loadTypeBusOrgs));
		}
		if(loadTypeOFRelationships != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("loadTypeOFRelationship").in(loadTypeOFRelationships));
		} else if (loadTypeOFRelationships != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("loadTypeOFRelationship").in(loadTypeOFRelationships));
		}
		if(loadTypeOORelationships != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("loadTypeOORelationship").in(loadTypeOORelationships));
		} else if (loadTypeOORelationships != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("loadTypeOORelationship").in(loadTypeOORelationships));
		}
		if(loadTypeIORelationships != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("loadTypeIORelationship").in(loadTypeIORelationships));
		} else if (loadTypeIORelationships != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("loadTypeIORelationship").in(loadTypeIORelationships));
		}
		if(loadTypeWlOrgXrefs != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("loadTypeWlOrgXref").in(loadTypeWlOrgXrefs));
		} else if (loadTypeWlOrgXrefs != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("loadTypeWlOrgXref").in(loadTypeWlOrgXrefs));
		}
		if(loadTypeWlPracIdentXrefs != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("loadTypeWlPracIdentXref").in(loadTypeWlPracIdentXrefs));
		} else if (loadTypeWlPracIdentXrefs != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("loadTypeWlPracIdentXref").in(loadTypeWlPracIdentXrefs));
		}
		if(processStartDates != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("processStartDate").in(processStartDates));
		} else if (processStartDates != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("processStartDate").in(processStartDates));
		}
		if(processEndDates != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("processEndDate").in(processEndDates));
		} else if (processEndDates != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("processEndDate").in(processEndDates));
		}
		if(statusCodes != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("statusCode").in(statusCodes));
		} else if (statusCodes != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("statusCode").in(statusCodes));
		}
		if(createdBy != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("createdBy").in(createdBy));
		} else if (createdBy != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("createdBy").in(createdBy));
		}
		if(createdAt != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("createdAt").in(createdAt));
		} else if (createdAt != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("createdAt").in(createdAt));
		}
		if(updatedBy != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("updatedBy").in(updatedBy));
		} else if (updatedBy != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("updatedBy").in(updatedBy));
		}
		if(updatedAt != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("updatedAt").in(updatedAt));
		} else if (updatedAt != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("updatedAt").in(updatedAt));
		}

		return combinedSpecification;
	}
}
