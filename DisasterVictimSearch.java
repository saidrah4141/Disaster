
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface DisasterVictimSearch {

    List<DisasterVictim> searchByPartialFirstName(String partialFirstName, Set<DisasterVictim> victimList);

	DisasterVictim searchByFirstName(String firstName, Set<DisasterVictim> victimList);


}