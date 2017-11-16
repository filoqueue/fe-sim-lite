public class FEClass{
  
  public String name;
  //public char weakness; // N-none, A-armor, F-flying, W-wyvern, H-horse, M-monster [will be implemented later]
  public int hit; // classes like Sniper give bonuses to hit
  public int avo; // classes like Swordmaster give bonuses to avoid
  public int crit;  // classes like Assassin give bonuses to critical
  public int ddg; // classes like Berserker give penalties to dodge
  
  public FEClass(){
    
    name = "Revenant";
    //weakness = 'M';
    hit = 0;
    avo = 0;
    crit = 0;
    ddg = 0;
    
  } // DC
  
  public FEClass(String name/*, char weakness*/){
    
    this.name = name;
    //this.weakness = weakness;
    hit = 0;
    avo = 0;
    crit = 0;
    ddg = 0;
    
  } // 2C
  
  public FEClass(String name, /*char weakness,*/ int hit, int avo, int crit, int ddg){
    
    this.name = name;
    //this.weakness = weakness;
    this.hit = hit;
    this.avo = avo;
    this.crit = crit;
    this.ddg = ddg;
    
  } // 6C
  
//*********************************************************************************************************************
  public int getHit(){return this.hit;}
  public int getAvo(){return this.avo;}
  public int getCrit(){return this.crit;}
  public int getDdg(){return this.ddg;}
  
} // class