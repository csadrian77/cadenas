
package sis.bpj;

import java.util.ArrayList;
import java.util.List;
import sis.business.GlobalBusiness;
import sis.modelo.BoletoList;
import sis.modelo.Boletos;
import sis.modelo.Rifa;
import sis.modelo.Ventab;

public class GlobalBusinessBpj {
    private List<Boletos> listBoleto;
    private List<BoletoList> listarBoletosfs;
    private List<Rifa> listarRifa;
    private BoletoList lBoletosfs;
    private Boletos lBoleto;
    private List<Ventab> listarVentab;
     
    public List<Boletos> getListaBoletos(String cod) 
    {   GlobalBusiness listaPac = new GlobalBusiness();
        this.listBoleto = new ArrayList();
        this.listBoleto = listaPac.getListaBoletos(cod);
        return this.listBoleto;
    }    
    public Boletos getBoleto(String cod) 
    {   GlobalBusiness listaPac = new GlobalBusiness();
        this.lBoleto = new Boletos();
        this.lBoleto = listaPac.getBoleto(cod);
        return this.lBoleto;
    }        
    public List<BoletoList> getListarBoletosfs(String cod) 
    {   GlobalBusiness listaPac = new GlobalBusiness();
        this.listarBoletosfs = new ArrayList();
        this.listarBoletosfs = listaPac.getListarBoletosfs(cod);
        return this.listarBoletosfs;
    }  
    public List<Ventab> getListaVentab(String id) 
    {   GlobalBusiness listaPac = new GlobalBusiness();
        this.listarVentab = new ArrayList();
        this.listarVentab = listaPac.getListaVentab(id) ;
        return this.listarVentab;
    }  
    public List<Rifa> getSorteoBoletos(String id) 
    {   GlobalBusiness listaPac = new GlobalBusiness();
        this.listarRifa = new ArrayList();
        this.listarRifa = listaPac.getSorteoBoletos(id) ;
        return this.listarRifa;
    }  
    public BoletoList getBoletosfs(String nboleto, String boleto)
    {   GlobalBusiness listaPac = new GlobalBusiness();
        this.lBoletosfs = new BoletoList();
        this.lBoletosfs = listaPac.getBoletosfs(nboleto,boleto );
        return this.lBoletosfs;
    }    
    
}


