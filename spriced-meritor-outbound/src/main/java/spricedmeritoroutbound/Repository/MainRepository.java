package spricedmeritoroutbound.Repository;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import spricedmeritoroutbound.Entities.Dummy;

@Repository
public interface MainRepository extends JpaRepository<Dummy,Integer>{

    @Query(value = "SELECT count(*) from customer WHERE (DATE(updated_date) = CURRENT_DATE OR DATE(updated_date) = (CURRENT_DATE - INTERVAL '1 day')) AND custid_d = 'SOLDTO' and geo = 'US'", nativeQuery = true)
    public Object getNumRowsCust();


    @Query(value = "SELECT count(*) from list_price WHERE (DATE(updated_date) = CURRENT_DATE OR DATE(updated_date) = (CURRENT_DATE - INTERVAL '1 day')) AND partnum is not null", nativeQuery = true)
    public Object getNumRowsListPrice();

    @Query(value = "SELECT count(*) from list_price WHERE (DATE(updated_date) = CURRENT_DATE OR DATE(updated_date) = (CURRENT_DATE - INTERVAL '1 day')) AND partnum is not null AND lpriceca is not null", nativeQuery = true)
    public Object getNumRowsListPriceCA();

    @Query(value = "SELECT count(*) from prcdsc WHERE CURRENT_DATE BETWEEN DATE(prcstdt) and DATE(prceddt) and prcregcd='US'", nativeQuery = true)
    public Object getNumRowsPrcdsc();


    @Query(value = "SELECT count(*) from disccode WHERE CURRENT_DATE BETWEEN DATE(sdate) and DATE(edate) and reg = 'US'", nativeQuery = true)
    public Object getNumRowsDiscCode();


    @Query(value = "SELECT count(*) from partdisc WHERE CURRENT_DATE BETWEEN DATE(pstrdt) and DATE(penddt)and pfds='US'", nativeQuery = true)
    public Object getNumRowsPartDisc();


    @Query(value = "SELECT count(*) from trkcodmm WHERE (DATE(updated_date) = CURRENT_DATE OR DATE(updated_date) = (CURRENT_DATE - INTERVAL '1 day')) and mfdscd='US'", nativeQuery = true)
    public Object getNumRowsTrkcodmm();

    @Query(value = "SELECT count(*) from trkcodf WHERE (DATE(updated_date) = CURRENT_DATE OR DATE(updated_date) = (CURRENT_DATE - INTERVAL '1 day')) and tfdscd='US'", nativeQuery = true)
    public Object getNumRowstrkcodf();


    @Query(value = "SELECT count(*) from prmoprce WHERE (DATE(updated_date) = CURRENT_DATE OR DATE(updated_date) = (CURRENT_DATE - INTERVAL '1 day')) and prmfds='US'", nativeQuery = true)
    public Object getNumRowsPrmoprce();

  @Query(value = "SELECT count(*) from quotefil WHERE (DATE(updated_date) = CURRENT_DATE OR DATE(updated_date) = (CURRENT_DATE - INTERVAL '1 day')) and qfds='US'", nativeQuery = true)
    public Object getNumRowsQuotefil();

    @Query(value = "SELECT count(*) from promos WHERE (DATE(updated_date)=CURRENT_DATE OR DATE(updated_date) = (CURRENT_DATE - INTERVAL '1 day')) and prfds='US'", nativeQuery = true)
    public Object getNumRowsPromos();

    @Query(value = "SELECT count(*) from future_core_planning where DATE(effdt)=current_date", nativeQuery = true)
    public Object getNumRowsFcp();


}
