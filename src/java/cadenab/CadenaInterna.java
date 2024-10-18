package cadenab;

public class CadenaInterna {

    public BlockChain blockChain = new BlockChain();  
    
    public int cadenaAdd(String datosbloque, int i){
        Miner miner = new Miner();  
        Block block0 = new Block(0,"transaction1",Constants.GENESIS_PREV_HASH);  
    
        miner.mine(block0, blockChain);  
            Block block1 = new Block(i,datosbloque,blockChain.getBlockChain().get(blockChain.size()-1).getHash());  
            miner.mine(block1, blockChain);  
       return 1;
    }
    
    public String cadenaLeer(){
        String cadena="";
        for(Block b:blockChain.getBlockChain())  
        {
            cadena=cadena+"<br>hash:"+b.getHash();
            cadena=cadena+"<br>previousHash:"+b.getPreviousHash() ;
        }     
        return cadena;
    }    
    
}
