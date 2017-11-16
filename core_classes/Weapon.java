public class Weapon{
  
  private String name;
    /* Swords are type R, Lances are type B, and Axes are type G [reavers will be implemented later]
     * R >> G >> B >> R >>... ad infinitum.. or ad nauseam. Whichever happens first.
     * so a sword's advType is G and its disType is B
     * && a lances advType is R and its disType is G
     * && an axe's advType is B and its disType is R
     */
  private char type;
  private char advType;
  private char disType;
  private int mt; // Might: the weapon's attack power
  private int hit; // Hit: the weapon's "accuracy"
  private int crit; // Critical Rate: the weapon's innate chance to do triple damage
  private int wt; // Weight: how much the weapon weighs
  private boolean isMagic; // eventually for tomes but also for Levin Swords and such
  
  public Weapon(){
    
    name = "Claw"; // will be treated as a sword
    type = 'R';
    advType = 'G';
    disType = 'B';
    mt = 4;
    hit = 85;
    crit = 0;
    wt = 1;
    isMagic = true;
    
  } // DC
  
  public Weapon(String name, char type, char advType, char disType, int mt, int hit, int crit, int wt, boolean isMagic){
    
    this.name = name;
    this.type = type;
    this.advType = advType;
    this.disType = disType;
    this. mt = mt;
    this.hit = hit;
    this.crit = crit;
    this.wt = wt;
    this.isMagic = isMagic;
    
  } // 5C
  
//*********************************************************************************************************************
  public String getName(){return this.name;}
  public char getType(){return this.type;}
  public char getAdvType(){return this.advType;}
  public char getDisType(){return this.disType;}
  public int getMt(){return this.mt;}
  public int getHit(){return this.hit;}
  public int getCrit(){return this.crit;}
  public int getWt(){return this.wt;}
  public boolean getIsMagic(){return this.isMagic;}
//*********************************************************************************************************************
  public String toString(){
    
    String s = this.name + "\nMT: " + this.mt + "  HIT: " + this.hit + "\nCRIT: " + this.crit + "  WT: " + this.wt + "\n";
    return s;
    
  }
  
  public boolean advantage(Weapon defending){
    
    if(this.type == defending.disType)
      return true;
    else
      return false;
    
  }
  public boolean disadvantage(Weapon defending){
    
    if(this.type == defending.advType)
      return true;
    else
      return false;
    
  }
  
} // class