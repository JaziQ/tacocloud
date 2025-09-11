package jaq.tacocloud.data;

import jaq.tacocloud.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepo extends CrudRepository<Taco, Long>{

}
