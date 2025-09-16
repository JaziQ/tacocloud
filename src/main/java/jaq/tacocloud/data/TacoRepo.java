package jaq.tacocloud.data;

import jaq.tacocloud.Taco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TacoRepo extends CrudRepository<Taco, Long>, PagingAndSortingRepository<Taco,Long> {
}
