package bookmarket;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MileageRepository extends PagingAndSortingRepository<Mileage, Long>{

    List<Mileage> findByOrderId(Long orderId);

}