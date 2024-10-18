package cadenab;

import java.util.Date;  
  
public class Block 
{
    private int id;  
    private int nonce;  
    private long timeStamp;  
    private String hash;  
    private String previousHash;  
    private String transaction;  
    
    public Block(int id, String transaction, String previousHash) 
    {   this.id = id;  
        this.transaction = transaction;  
        this.previousHash = previousHash;  
        this.timeStamp = new Date().getTime();  
        generateHash();  
    }  
    
    public Block(int id, String transaction, String previousHash, long timeStamp, int nonce,String hash) 
    {   this.id = id;  
        this.transaction = transaction;  
        this.previousHash = previousHash;  
        this.timeStamp = timeStamp;  
        this.nonce=nonce;
        this.hash=hash;
    }  
    
  
    public void generateHash() 
    {   String dataToHash = Integer.toString(id) + previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + transaction.toString();  
        String hashValue = SHA256Helper.generateHash(dataToHash);  
        this.hash = hashValue;  
    }  
      
    public String getHash()                         {  return this.hash;   }  
    public void setHash(String hash)                {  this.hash = hash;   }  
    
    public String getPreviousHash()                 {  return this.previousHash;         }  
    public void setPreviousHash(String previousHash){  this.previousHash = previousHash; } 

    public int getId()                              {  return id;    }
    public void setId(int id)                       {  this.id = id;    }
    
    public int getNonce()                           {  return nonce;    }
    public void setNonce(int nonce)                 {  this.nonce = nonce;    }

    public long getTimeStamp()                     {  return timeStamp;   }
    public void setTimeStamp(long timeStamp)       {  this.timeStamp = timeStamp;    }
    
      
    
    public void incrementNonce()   {   this.nonce++;  }  

    @Override  
    public String toString() 
    {   return this.id+"-"+this.previousHash+"-"+this.timeStamp+"-"+this.nonce+"-"+this.transaction;  
    }  
}   