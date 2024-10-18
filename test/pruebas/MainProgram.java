
package pruebas;

import cadenab.Block;
import cadenab.BlockChain;
import cadenab.Constants;
import cadenab.Miner;
import java.util.ArrayList;
import java.util.Arrays;

public class MainProgram{  
  
    public static void main(String[] args) 
    { 
        int generar=20;
         
        BlockChain blockChain = new BlockChain();  
        Miner miner = new Miner();  
        Block block0 = new Block(0,"transaction1",Constants.GENESIS_PREV_HASH);  
  
        miner.mine(block0, blockChain);  

        String datosx="";
for(int i=1; i<generar; i++){
        datosx="adr:"+i;
        Block block1 = new Block(i,"transaction2",blockChain.getBlockChain().get(blockChain.size()-1).getHash());  
        miner.mine(block1, blockChain);  
}


System.out.println("\n tamaño:"+blockChain.size()  );        

 for(Block b:blockChain.getBlockChain())  {
  System.out.println("\nhash:"+b.getHash() );
  System.out.println("previousHash:"+b.getPreviousHash() );
} 

    }  
 } 